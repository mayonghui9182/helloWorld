# spring基本概念

## IOC概念

​	现在有一个解析Excel的需求，并处理解析结果。现在我们需要一个解析excel的类，叫ExcelParser，它有一个parse方法。一个结果处理类，我们叫它Processer，他有一个process方法。下面是需求的不同中实现。

### 正常的程序实现：

~~~java
public class ExcelParser {
    public String parse(){
        return "Excel内容";
    }
}
~~~

~~~java
public class Processer {
    private ExcelParser excelParser;

    public ExcelParser getExcelParser() {
        return excelParser;
    }

    public void setExcelParser(ExcelParser excelParser) {
        this.excelParser = excelParser;
    }

    public void process(){
        String excelContent = new ExcelParser().parse();
        System.out.println("处理Excel解析结果："+excelContent);
    }

    public static void main(String[] args) {
        Processer processer = new Processer();
        ExcelParser excelParser = new ExcelParser();
        processer.setExcelParser(excelParser);
        processer.process();
    }
}
~~~

  	在非IOC程序中，我们都是使用者，也就是main方法，需要依赖Processer和ExcelParser两个对象完成解析Excel并处理Excel内容的任务，new出来Processer和ExcelParser变量，然后使用，不用了，也就是方法结束，交给GC去处理掉这两个实例。这样一来，都是使用者去维护依赖项实例以及他们的依赖关系。这样依赖，每个使用者都要维护各自独立的依赖项。

​	那么有没有一种方法，使用者不必都维护独立的，而是由依赖项自己维护自己的生命周期和依赖关系。看下面的一种实现。

### 工厂方法实现：

~~~java
public class ExcelParser {
    public String parse(){
        return "Excel内容";
    }
    public static ExcelParser getInstance(){
        return new ExcelParser();
    }
}
~~~

~~~java
public class Processer {
    private ExcelParser excelParser;

    public ExcelParser getExcelParser() {
        return excelParser;
    }

    public void setExcelParser(ExcelParser excelParser) {
        this.excelParser = excelParser;
    }

    public void process(){
        String excelContent = new ExcelParser().parse();
        System.out.println("处理Excel解析结果："+excelContent);
    }

    public static Processer getInstance(){
        Processer processer = new Processer();
        processer.setExcelParser(ExcelParser.getInstance());
        return processer;
    }

    public static void main(String[] args) {
        Processer.getInstance().process();
    }
}
~~~

​	这样，把每一个类的实例和其依赖关系交给自己管理，使用者只需要在使用的从它本身的工厂方法获取实例，然后调用其方法就行了，是不是很方便。

​	注：这里如果用单例模式可以实现各个类的实例为单例的。

​	可是现在还是有点麻烦，就是每个实例都需要写一个工厂方法，这不是增加工作量嘛！

​	员工：老板！涨工资！

​	老板：滚！耽误项目进度，还想要涨工资！想的比嫦娥还美！

​	更重要的是，工厂方法和业务代码耦合了！类的功能也变得多了。代码的目标可以高内聚，低耦合，类功能单一哦。

​	那么有没有一种方法，我们各自的类也不必维护其实例和依赖关系了，而是自动维护好了呢？可是，类自己不想做，使用者也不想干，怎么办呢？

​	没办法，只有找第三方了。此时我们的主角spring framework core就登场了。以下简称spring

​	spring就是一个维护类的实例，并维护它的依赖关系的框架。下面是他的实现。

### spring实现：

~~~java
@Component
public class ExcelParser {
    public String parse(){
        return "Excel内容";
    }
}
~~~

~~~java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Processer {
    @Autowired
    private ExcelParser excelParser;

    public void process(){
        String excelContent = new ExcelParser().parse();
        System.out.println("处理Excel解析结果："+excelContent);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Processer.class,ExcelParser.class);
        Processer processer = annotationConfigApplicationContext.getBean("processer", Processer.class);
        processer.process();
    }
}
~~~

​	这样一看，你会发现main的代码还是很方法。其实这个不必担心，因为从spring获取实例的代码你可以只写一次，不必每个使用者都写。省略spring的启动代码和获取实例的代码一看，你就只剩下一行代码，processer.process();是不是很简单。为什么获取实例的代码可以省略呢，因为在实际使用中，Processer也是自动注入的。

### spring代码实现原理：

​	spring的作用是实例化每一个注册进入spring的类，并将该类依赖的所有类都注入到这个类中，比如：类Processer依赖类ExcelParser，那么当我们使用spring的时候，需要把Processer和ExcelParser都注册进入sprnig，然后spring会根据Processer和ExcelParser new两个实例：实例Processer和实例ExcelParser，并将实例ExcelParser注入实例Processer中，这样，在使用Processer的时候，直接使用Processer即可，不必再在Processer中注入ExcelParser了。

​	那么spring是怎么知道该实例化ExcelParser和Processer呢？这就要注意到@Autowired。@Configuration和@Component两个注解了。spring除了这几个注解，还有很多其他的注解，比如@Bean，@Primary，@Service等等。这些都是在你要注册的类上使用的，是为了告诉spring，它要怎么样去实例化一个，怎么维护类之间的依赖关系的。至于这些注解的使用，详情参看官方文档：https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/core.html#spring-core。

​	spring两种配置的形式，xml配置和注解配置，具体选择看你喜好了！

​	spring会把注解和xml解析成一种元素据。这个元素据就是描述一个类需要怎么被实例化，以及这个类的依赖关系的。这个元素据在spring中是以类的形式出现的，它的顶层接口叫做：BeanDefiniton。就像Class对象一样。

​	我们都知道，class文件是我们编写的java文件经过javac命令编译得到的一个文件，是由java虚拟机执行的文件，在虚拟机启动的时候，jvm会把class文件加载到内存，生成一个Class对象，放到元数据区。这些类经过new关键字或者反射等手段可以构建一个或多个实例，而这些实例才是真正完成我们业务逻辑的单位。他们的关系就像模具和产品的概念性，类是摸具，实例是产品。

​	而BeanDefinition，它是类概念的延伸，是对类的又一层封装。spring根据BeanDefinition创建实例，为了区分spring根据BeanDefinition创建的实例和一般实例的区别，spring叫这种实例为Bean。

​	下面是BeanDefinition的源码，有兴趣的可以看一看。

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



### 总结：

​	spring叫这种机制为IOC（ Inversion of Control），我常见的翻译为控制反转，但个人更喜欢控制转移这个翻译。要说反转，第二种工厂模式实现个人觉得才是真正的反转，是不是感觉有点道理（手动滑稽）。

​	spring framework core的核心由两个，IOC和AOP。IOC是由BeanFactory和BeanDefinition实现的。spring根据BeanDefinition实例化Bean，并把Bean放进BeanFactory容器种供使用者使用。

​	欢迎各位纠正各种错误！谢谢！

​	

