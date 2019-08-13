# spring基本概念

## BeanDefinition和Bean

​	讲BeanDefinition和bean之前，先回顾一下java中类和实例的概念：我们都知道，class文件是我们编写的java文件经过javac命令编译得到的一个文件，是由java虚拟机执行的文件。class文件中定义着一个或多个接口或类。这些类经过new关键字或者反射等手段可以构建一个或多个实例，而这些实例才是真正完成我们业务逻辑的单位。他们的关系就像模具和产品的概念性，类是摸具，实例是产品。

​	为了实现一定的功能，在spring core中，定义了一种元素据，BeanDefinition，它是类概念的延伸，是对类的又一层封装。BeanDefinition和bean的关系类比类和实例的关系。以下是BeanDefinition接口的源码：

~~~java

/**
 * BeanDefinition描述了一个bean实例，它具有属性值、构造函数参数值和由具体实现提供的进一步信息。
 * 这只是一个最小的接口:主要目的是允许BeanFactoryPostProcessor,比如PropertyPlaceholderConfigurer
 * 内省和修改属性值和其他bean元数据。
 */
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
	 * 静态变量
	 * 单例(SCOPE_SINGLETON)和多例(SCOPE_PROTOTYPE)表示符号，用于标识bean的生命周期，
	 * spring会根据字符串实例化具体的scope对象，对bean的生命周期进行处理
	 * 在子类中可能支持更多的生命周期，比如springmvc中支持request和session等
	 */
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	/**
	 * 静态变量
	 * 角色，ROLE_APPLICATION表示BeanDefinition是应用程序的主要部分。通常对应于用户定义的bean。
	 * ROLE_SUPPORT表示bean定义是一些较大配置(通常是外部组件定义)的支持部分。（待了解）
	 * ROLE_INFRASTRUCTURE表示bean定义提供一个完全后台角色，与最终用户无关，是spring内部beanDefinition 	  * 使用的角色
	 */
	int ROLE_APPLICATION = 0;
	int ROLE_SUPPORT = 1;
	int ROLE_INFRASTRUCTURE = 2;

	// Modifiable attributes

	/** 
	 * 设置该beanDefinition的父beanDefinition的名称，就像类的父类一样，子beanDefinition会继承父		 * beanDefinition的一些定义
	 */
	void setParentName(@Nullable String parentName);
	@Nullable
	String getParentName();

	/**
	 * 指定此bean定义的bean类名。
	 * 类名可以在bean工厂的后处理过程中修改，通常用经过解析的类名变体替换原来的类名。
	 */
	void setBeanClassName(@Nullable String beanClassName);

	/**
	 * 注意，在子定义覆盖/继承其父类名的情况下，这不必是运行时使用的实际类名。
	 * 此外，这可能只是调用工厂方法的类，或者在调用方法的工厂bean引用的情况下，
	 * 它甚至可能是空的。因此，不要认为这是运行时的最终bean类型，
	 * 而是只在单个bean定义级别上使用它进行解析。
	 */
	@Nullable
	String getBeanClassName();

	/**
	 * 设置和获取scope，即java的生命周期
	 */
	void setScope(@Nullable String scope);
	@Nullable
	String getScope();

	/** 
	 * 设置和获取是否懒加载
	 * spring对bean的初始化有两种，一种是启动时实例化，另一种是懒加载，用到时即getBean操作时实例化。
	 */
	void setLazyInit(boolean lazyInit);
	boolean isLazyInit();

	/**
	 * 设置此bean依赖于初始化的bean的名称。bean工厂将保证首先初始化这些bean。
	 * 依赖注入的依赖不需要设置此属性，他是在么有依赖注入关系的时候，有别的需求需要依赖别的bean，
	 * 此时可以设置此属性，比如静态方法的处理
	 */
	void setDependsOn(@Nullable String... dependsOn);
	@Nullable
	String[] getDependsOn();

	/**
	 * 设置此bean是否是自动作为其他bean的候选bean
	 * 注意，此标志仅用于影响基于类型的自动连接。它不影响按名称显式引用，
	 * 即使指定的bean没有标记为自动连接候选，也会解析该引用。
	 * 因此，如果名称匹配，则按名称自动连接将注入bean。
	 */
	void setAutowireCandidate(boolean autowireCandidate);
	boolean isAutowireCandidate();

	/**
	 * 设置此bean是否为主要自动连接候选。在有多个候选bean的时候，获取primary为true的一个，
	 * 如果primary为true的有多个，报错
	 */
	void setPrimary(boolean primary);
	boolean isPrimary();

	/** 
	 * 指定要使用的工厂bean(如果有的话)。
	 * 这是调用指定工厂方法的bean的名称。
	 */
	void setFactoryBeanName(@Nullable String factoryBeanName);
	@Nullable
	String getFactoryBeanName();

	/**
	 * 指定工厂方法(如果有的话)。将使用构造函数参数调用此方法，如果没有指定参数，
	 * 则不使用参数。该方法将在指定的工厂bean上调用(如果有的话)，否则将作为本地bean类上的静态方法调用。
	 * @see #setFactoryBeanName
	 * @see #setBeanClassName
	 */
	void setFactoryMethodName(@Nullable String factoryMethodName);
	@Nullable
	String getFactoryMethodName();

	/** 
	 * 返回此bean的构造函数参数值。
	 * 返回的实例可以在bean工厂后处理期间修改。
	 */
	ConstructorArgumentValues getConstructorArgumentValues();

	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	/**
	 * 返回要应用于bean的新实例的属性值。
	 * 返回的实例可以在bean工厂的后处理过程中修改。
	 * @since 5.0.2
	 */
	MutablePropertyValues getPropertyValues();
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}


	// 以下是只读属性

	/**
	 * 是否是单例bean
	 */
	boolean isSingleton();

	/**
	 * 是否是多例bean
	 */
	boolean isPrototype();

	/**
	 * 该beanDefinition是否是抽象的，抽象的不能被实例化
	 */
	boolean isAbstract();

	/**
	 * 该BeanDefinition对应的角色
	 */
	int getRole();

	/**
	 * 返回此bean定义的可读描述
	 */
	@Nullable
	String getDescription();

	/**
	 * 返回此bean定义来自的资源的描述(用于在出现错误时显示上下文)。
	 */
	@Nullable
	String getResourceDescription();

	/**
	 * 返回初始的bean定义，如果没有，返回null。允许检索修饰过的bean定义(如果有的话)。
	 * 注意，此方法返回直接发起者。遍历发起者链，找到用户定义的原始bean定义。
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}

~~~

​	以上就是spring自定义的元素据BeanDefinition的基础接口，同时也定义了用于实例bean的基础属性。

​	java根据类的变量、方法等信息为模板实例化一个类实例，spring根据BeanDefinition的信息，例如：是单利还是多例，是不是懒加载，根据类的全限定名/工厂方法/静态工厂方法等信息实例化一个类的实例，为了区别这个对象和一般的对象，即bean。。而spring会对这些bean进行统一的管理。

​	beanDefinition和bean是为了实现IOC而做的。

## IOC

​	在DI/IOC概念之前，我们实例化一个对象A，都是在需要A实例的对象中，比如对象B依赖对象A，那么就在对象B中，直接通过new关键字或者反射得到A的实例的。此时A实例的生命周期，例如A什么时候创建，使用，销毁（此处销毁指的是不在有A的引用，交给GC处理）等，都是由B控制，而A自己是无法控制的。

​	那么我们是不是可以完成一种机制，就是有A自己定义管理自己的生命周期，当别人需要使用A的时候，直接把A自己创建的实例交给使用者。但是如果把A创建自己，交给别人调用，销毁自己这些逻辑写入A的类中，会使A中业务逻辑与生命周期耦合，也违背了类的单一职责的原则。为了解决这些问题，我们需要引入一个第三方，来维护管理A的生命周期呢，当其他类要使用A的时候，由第三方把维护的A的实例交给使用则。

​	很显然，spring就是上文提到的第三方。

​	那么spring怎么知道什么时候需要创建A，怎么创建A，什么时候创建A呢？用传统的class对象，已经不能实现这个功能了。所以，在class的基础上，我们需要一种元数据来描述这些行为。这个元数据就是上文提到的BeanDefinition。

​	BeanDefinition就是spring创建bean的依据

​	下面看下spring官网给的一些解释：



```
IoC is also known as dependency injection (DI). It is a process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes or a mechanism such as the Service Locator pattern.
```

下面是结合有道词典的翻译

~~~
控制反转（IoC）也称为依赖注入(dependency injection, DI)。依赖注入指的是一个过程，在这个过程中，对象仅通过构造函数参数、工厂方法的参数，或直接在得到的对象实例上设置属性来定义它们的依赖关系(即与它们一起工作的其他对象)。然后容器在创建bean时注入这些依赖项。本质上，这个过程与bean本身通过直接构造类或一种机制(如服务定位器模式)来控制依赖项的实例化或位置相反(因此称为控制反转)。
~~~

​	spring是通过一个叫做Beanfactory的接口管理各种bean的实例。BeanFactory是IOC机制的基础接口，它提供了配置框架和基本功能。为了添加了更多企业特定的功能，在BeanFactory之上，spring提供了ApplicationContext接口，ApplicationContext是BeanFactory的一个完整超集。