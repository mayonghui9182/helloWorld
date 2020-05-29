## springframework基本接口

​		作为一个框架，springframework需要把自己有的功能进行有效的组织，根据接口分离法则，以合理细粒度的接口形式暴露给client。那么，springframework有哪些功能，又暴露了哪些接口呢？

### DI容器和bean

​		首先，springframework基本功能是一个DI容器，它将所有client委托给自己的类的信息转换为一个BD（Bean Definition，以下简称BD）放到一个容器中，存储起来，在client需要的时候，返回bd对象的实例，即bean。所以我们常常称BF是bean容器，因为我们从BF中拿到的就是bean。

​		DI容器的基本功能在`org.springframework.beans`和`org.springframework.context` 包下。BF接口提供了能够管理任何类型对象的高级配置机制。 `ApplicationContext`在 BF的基础上，增加了以下几种功能。

- 更容易集成Aop特性
- 消息资源处理(用于国际化)
- 事件发布
- 特定于应用程序层的上下文，如web应用程序中使用的WebApplicationContext。

~~~
In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans
~~~

​		上面是spring官网对bean的定义，翻译过来是：在spring中，那些构成应用程序主干并由Spring IoC容器管理的对象称为bean。

​		可以再宽泛点，由spring管理的类的实例都叫做bean。

![1586010272481](C:\Users\ma\AppData\Roaming\Typora\typora-user-images\1586010272481.png)

​		上面是来自spring官方的一张图，你提供的对象加上配置信息，就构成了DI容器，经过创建和初始化以后，就可以使用这个容器了。

#### 配置信息

​		你让一个人干活，就要告诉他这个活怎么干，要告诉他怎么干，就需要一些规则。你要让spring帮你管理bean，就需要一些规则来告诉spring怎么管理这些bean。spring定义了一套规则，或者叫做配置信息，来让你告诉他怎么管理你的bean，即如何实例化、配置和组装应用程序中的对象。这些配置信息可以通过xml，注解，Java代码来定义。

​		根据类的功能划分：

- controller
- service
- dao
- infrastructure objects （基础设施的对象）

​		在spring定义的配置信息里面，一部分时关于bean的配置信息，client按照spring给定的规则，定义Bean的一些配置信息（没有显示定义的取默认值），然后将这个定义的Bean交给spring管理，spring根据clent提供的配置信息，维护bean的生命周期。

​		在运行时，Spring用一种叫做BD的数据结构，存储client提供的配置信息。BD是存储Bean定义信息的一数据结构，和java.lang包里的Class类似。它存储了以下信息：

![1586086849254](C:\Users\ma\AppData\Roaming\Typora\typora-user-images\1586086849254.png)

以上信息被转换为BD的以下属性：

- class
- name
- scope
- 构造函数参数
- 属性
- 自动装配类型
- 懒加载模式
- 初始化方法
- 销毁方法

![1586665052420](C:\Users\ma\AppData\Roaming\Typora\typora-user-images\1586665052420.png)

![1586668196892](C:\Users\ma\AppData\Roaming\Typora\typora-user-images\1586668196892.png)

![1586668427903](C:\Users\ma\AppData\Roaming\Typora\typora-user-images\1586668427903.png)



##### DI容器扩展点



以下是BD对外暴露的统一接口：

~~~java
package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.AttributeAccessor;
import org.springframework.lang.Nullable;

public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
		单例表示符号
	 */
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
	 多例
	 */
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	/** 
		角色提示，表示{@code BeanDefinition}是应用程序的主要部分。通常对应于用户定义的bean。
	 */
	int ROLE_APPLICATION = 0;


	int ROLE_SUPPORT = 1;

	/**
	spring基础类
	 */
	int ROLE_INFRASTRUCTURE = 2;

	/** 
	设置该bean定义的父定义的名称，父类吗？
	 */
	void setParentName(@Nullable String parentName);

	@Nullable
	String getParentName();

	/**
	 * 类名可以在bean工厂的后处理过程中修改，通常用经过解析的类名变体替换原来的类名。
	 */
	void setBeanClassName(@Nullable String beanClassName);

	@Nullable
	String getBeanClassName();

	/**
	 * 重设scope
	 */
	void setScope(@Nullable String scope);

	/**
	 * 获取bd scope
	 */
	@Nullable
	String getScope();

	/** 重设lazy
	 */
	void setLazyInit(boolean lazyInit);

	boolean isLazyInit();

	/**
		设置此bean依赖于初始化的bean的名称。bean工厂将保证首先初始化这些bean。
	 */
	void setDependsOn(@Nullable String... dependsOn);

	@Nullable
	String[] getDependsOn();

	/**
	 * 设置此bean是否是自动连接到其他bean的候选bean
	 * 注意，此标志仅用于影响基于类型的自动连接。它不影响按名称显式引用，
	 * 即使指定的bean没有标记为自动连接候选，也会解析该引用。因此，如果名称匹配，则按名称自动连接将注入bean。
	 */
	void setAutowireCandidate(boolean autowireCandidate);

	boolean isAutowireCandidate();

	/**
	 * 设置此bean是否为主要自动连接候选。
	 */
	void setPrimary(boolean primary);

	boolean isPrimary();

	/** 指定要使用的工厂bean(如果有的话)。
	 * 这是调用指定工厂方法的bean的名称。
	 */
	void setFactoryBeanName(@Nullable String factoryBeanName);

	@Nullable
	String getFactoryBeanName();

	/**
	 * 指定工厂方法(如果有的话)。将使用构造函数参数调用此方法，如果没有指定参数，
	 * 则不使用参数。该方法将在指定的工厂bean上调用(如果有的话)，否则将作为本地bean类上的静态方法调用。
	 */
	void setFactoryMethodName(@Nullable String factoryMethodName);


	@Nullable
	String getFactoryMethodName();

	/** 返回此bean的构造函数参数值。
	 * Return the constructor argument values for this bean.
	 * 返回的实例可以在bean工厂后处理期间修改。
	 */
	ConstructorArgumentValues getConstructorArgumentValues();

	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}
    
	MutablePropertyValues getPropertyValues();

	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	boolean isSingleton();


	boolean isPrototype();

	/**
	 * Return whether this bean is "abstract", that is, not meant to be instantiated.
	 */
	boolean isAbstract();

	/**
	 * Get the role hint for this {@code BeanDefinition}. The role hint
	 * provides the frameworks as well as tools with an indication of
	 * the role and importance of a particular {@code BeanDefinition}.
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	int getRole();

	/**
	 * Return a human-readable description of this bean definition.
	 */
	@Nullable
	String getDescription();

	/**
	 * Return a description of the resource that this bean definition
	 * came from (for the purpose of showing context in case of errors).
	 */
	@Nullable
	String getResourceDescription();

	/**
	 * Return the originating BeanDefinition, or {@code null} if none.
	 * Allows for retrieving the decorated bean definition, if any.
	 * <p>Note that this method returns the immediate originator. Iterate through the
	 * originator chain to find the original BeanDefinition as defined by the user.
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}

~~~



​		根据Dependency inversion principle，所有对BD的依赖都需要依赖抽象，所以

​		那springframework就需要暴露容器的接口给client，所谓容器。springframework基本的容器接口时BF（BeanFactory，以下简称BF），它

~~~java
 package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
 
public interface BeanFactory {
 
	String FACTORY_BEAN_PREFIX = "&";
 
	Object getBean(String name) throws BeansException;
 
	<T> T getBean(String name, @Nullable Class<T> requiredType) throws BeansException;
 
	Object getBean(String name, Object... args) throws BeansException;
 
	<T> T getBean(Class<T> requiredType) throws BeansException;
 
	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;
 
	boolean containsBean(String name);
 
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
 
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
 
	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;
 
	boolean isTypeMatch(String name, @Nullable Class<?> typeToMatch) throws NoSuchBeanDefinitionException;
 
	@Nullable
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;
 
	String[] getAliases(String name);

}

~~~

