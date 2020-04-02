## spring基本概念之IOC

### 概念

​		java社区有很多轻量级容器框架，用来将多个不同的项目组件组建为一个紧密的应用程序，比如，spring，PicoContainer等。这些轻量级容器框架经常提到一个概念，IOC。那么什么是IOC呢?

​		IOC（控制反转），字面理解控制权被反转了，这里并没有具体定义什么控制权，谁的控制权，怎么被反转的，所以这是一个比较笼统的概念。所有涉及到控制权被反转的东西都可以称为控制反转。所以很多框架或多或少都涉及到了IOC的概念。而spring这些容器使用的DI是控制反转的一种。

​		因为IOC概念笼统且难以理解，所以Martin给了这些框架一个更具体的名字，DI，依赖注入。依赖注入指通过构造器注入，set方法注入，接口注入等方式，将被依赖对象注入到依赖对象中。这是这些容器实现控制反转的方式。

### 例子

​		为了帮助我们更好的理解这个概念，我们举个例子。在这个例子中，我们有一个组件，提供一个特定导演导演的电影列表，下面是代码：

~~~java
class MovieLister{
	//...省略一些代码
  public Movie[] moviesDirectedBy(String arg) {
      List allMovies = finder.findAll();
      for (Iterator it = allMovies.iterator(); it.hasNext();) {
          Movie movie = (Movie) it.next();
          if (!movie.getDirector().equals(arg)) it.remove();
      }
      return (Movie[]) allMovies.toArray(new Movie[allMovies.size()]);
  }
}
~~~

​		finder对象的findAll()方法提供了所有的电影，然后遍历电影列表，找到电影是该导演导演的电影。代码的重点是finder对象，或者说重点是怎么讲finder对象注入到movieLister中。如果我们希望MovieLister对象独立于finder对象中电影的存储方式，事情就变得有趣了。

​		根据dependency inversion principle，MovieLister对象应该依赖于finder对象的抽象，所以需要定义一个抽象接口，如下：

~~~java
public interface MovieFinder {
    List findAll();
}
~~~

​		现在moviesDirectedBy方法已经与movie具体的存储方式解耦了，无论哪种存储方式，只要提供一个MovieFinder具体的实现类，实现将movies从存储中取出来并返回即可。这样一来，MovieLister对象需要一个具体的MovieFinder类来从一个存储中提取电影，我们需要将MovieFinder具体的实现类和MovieLister关联起来，但是怎么关联起来呢？

​		传统的方式，放到构造函数中。

~~~java
class MovieLister.{
//...省略一些代码
  private MovieFinder finder;
  public MovieLister() {
    finder = new ColonDelimitedMovieFinder("movies1.txt");
  }
}
~~~

​		这样就将MovieFinder具体的实现类和MovieLister关联起来了，在ColonDelimitedMovieFinder中，从movies1.txt文件中获取以冒号分割的电影列表。

​		现在的依赖情况如下：

![1585643618660](C:\Users\myh\AppData\Roaming\Typora\typora-user-images\1585643618660.png)

​		我们可以看到，MovieLister同时依赖MovieFinder接口和MovieFinder实现类。

​		我们的目标是只依赖抽象，因为依赖具体实现会导致代码不好维护。比如，我们需要把电影存储到一个xml中，或者数据库中，我们要怎么办呢？这时我们就需要一个不同的MovieFinder具体的实现类来抓取数据了。因为moviesDirectedBy方法依赖的是MovieFinder接口，所以我们不需要修改moviesDirectedBy方法。但是因为构造函数依赖了MovieFinder的实现类，所以需要修改构造函数，替换MovieFinder的实现类。

​		所以，实现DIP的关键是将构造函数的依赖移除，但是移除构造函数依赖，怎么讲MovieFinder的实现类注入到MovieLister对象中呢？

​		这些轻量级容器框架通过DI实现了这个功能，它引入了第三方容器，通过容器，将被依赖对象注入到依赖对象中。Martin叫这种情况为插件，它不在编译的时候将对象注入，以便在改变的时候，不需要重新编译，只需要改变插件的种类，就可以将MovieFinder实现类插入进去

​		对于依赖注入来说，反转的是如何寻找插件的实现。在传统的项目中，我们通过直接实例化来实现插件的查找与注入，但是这不能让finder和MovieLister解耦，finder也不能称之为插件。而这些轻量级容器框架，通过定义一种所有用户都遵循的方式，将实现类注入进入依赖类，这样依赖，被依赖对象的实现类就像一个个插件，而容器将这些插件插入到被依赖对象。

### 依赖注入和IOC

​		依赖注入是IOC的一种具体实现形式。那么DI是怎么实现IOC的呢？或者说DI中有哪些控制被反转了呢？

- 依赖对象实例化控制权的反转：传统的程序中，依赖对象直接实例化被依赖对象，这时，实例化的决定权在依赖对象那里；而在DI中，实例化被依赖对象是由第三方容器完成的，而决定被依赖对象是否能被实例化的权利在被依赖对象那里，被依赖对象遵循了第三方规则，那么他就被实例化，否则不被实例化。
- 被依赖对象的定位权反转：传统的代码中，通过直接实例化被依赖对象实现类，来定位被依赖对象；而在DI中，被依赖对象实现类在哪里依赖对象并不知道，它的位置是由被依赖对象实现类自己决定的。
- 待续

### 参考

[InversionOfControl](https://martinfowler.com/bliki/InversionOfControl.html)

[Inversion of Control Containers and the Dependency Injection pattern](https://martinfowler.com/articles/injection.html)

​		