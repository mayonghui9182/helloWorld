## 1、基本应用

Java泛型可以用在类、接口和方法上。基本使用请参考《on Java 8》.

## 2、类型擦除

​	泛型时Java 5以后添加进去的，为了兼容以前的版本，相对于其他泛型语言，Java用特别的方法实现了泛型，这实现了Java向上兼容的功能，但同时也限制了Java泛型的功能。这个特别方法就是类型擦除了。

​	类型擦除并不是Java语言特性，它是通过编译器来实现泛型的。编译器在做静态检测时，通过一系列的处理来保证泛型实现，在静态类型检查之后，程序中的每个泛型类型都将被擦除，用非泛型的第一个边界替换它（可能会有多个边界）。因此，在泛型类/接口/方法里面，你并不能得到关于类型参数的具体信息，你能知道的是类型参数的表示符和他的边界类型（默认边界时Object）。例如List\<T>被擦除为List，接口/类中的每个泛型变量/参数/返回值类型都被擦除到非泛型边界，默认边界为Object，List\<T>没有上界，被擦除为Object。所以，ArrayList\<String>和ArrayList\<Integer>是一种类型。他们都被擦除为原始类型List。

​	类型的擦除的代价是巨大的。泛型类型不能用于显式引用运行时类型的操作，如强制转换、instanceof操作和new表达式。因为所有关于参数的类型信息都丢失了。在编写泛型代码时，你只知道有这么一个类型参数，以及这个类型参数的类型上界是什么，并不知道这个参数的具体信息。在使用泛型类时，您必须不断地提醒自己，你只是看起来好像知道有关参数的具体信息而已。

​	比如：ArrayList\<String> list = new ArrayList<>()，在使用list时，你好像是知道它是一个String数组，而泛型语法也在强烈暗示你，在整个类的各个地方，类型E都被替换了。但是事实并不是如此。在ArrayList类的编写过程中，你实际上知道的只是，有个参数类型是E，他的类型上界是Object。

​	那么问题来了，既然在运行时ArrayList\<String>的类型信息被擦除了，那么我们在用get方法时，得到对象时一个String，不用cast，且我们用set方法时，参数必须时一个字符串，或者能调用toString方法的对象呢。下面我们先探讨另外一个问题。泛型的边界问题。

## 3、泛型的出入口

​	泛型类型可以时无任何意义的字符，比如E。在静态检测后，类型E就被擦除为非泛型上界了，所以在运行时，时无法知道泛型的具体信息的。

~~~java
import java.util.*;
public class ListMaker<T> {
    private Class<T> kind;
    @SuppressWarnings("unchecked")
    T[] createArray(int size) {
    	return (T[])Array.newInstance(kind, size);
    }
    List<T> createList() { return new ArrayList<>(); }
    public static void main(String[] args) {
         ListMaker<String> stringMaker = new ListMaker<>();
         String[] stringArray = stringMaker.createArray(9);
		List<String> stringList = stringMaker.createList();
	}
}
~~~

​	上面的代码段中，T会被编译器擦除，Class\<T>最终存储的只是Class，new ArrayList<>()变为new ArrayList()。

​	在createArray()方法中，Array.newInstance()方法不知道T的任何信息，所以在运行时，并不能产生T类型的数组，所以需要转cast。

​	createList方法中，尽管最终，new ArrayList<>()变为new ArrayList()，但是如果我们把new ArrayList<>()直接更换位new ArrayList()，编译器就会给出警告。

​	所以类型T不是没有意义的，那么它的意义在哪呢？看下面代码。

~~~java
import java.util.*;
import java.util.function.*;
import onjava.*;
public class FilledList<T> extends ArrayList<T> {
    FilledList(Supplier<T> gen, int size) {
        Suppliers.fill(this, gen, size);
    }
    public FilledList(T t, int size) {
        for(int i = 0; i < size; i++)
            this.add(t);
    }
    public static void main(String[] args) {
        List<String> list = new FilledList<>("Hello", 4);
        System.out.println(list);
        // Supplier version:
        List<Integer> ilist = new FilledList<>(() -> 47, 4);
        System.out.println(ilist);
    }
}
~~~

​	在main方法中，我们在创建FilledList的同时往list里面添加对象，虽然编译器并不知道关于T的任何东西，但是编译器在编译期间能确保放到FilledList里面的类型时T。所以，虽然在方法/类/接口里面T的相关信息被擦除了，编译器仍然可以确保方法或类中使用类型的方式的内部一致性。换句话说，编译器能保证泛型在其作用域内使用方式一致性。泛型作用域，如果声名在接口/类上，那么就是在类和类方法的内部，如果是静态方法，那就是静态方法内部。

​	擦除虽然删除了方法主体中的类型信息，但是编译器能保证泛型作用域内使用方式的一致性，那么泛型进出作用域时，编译器是这么保证它的一致性呢？接口/类/方法运行时边界:泛型对象进入和离开主体的点。

​	接口/类的进出点：field的取值和赋值，方法的参数和返回值。

​	方法的进出口：参数和返回值。

​	编译器在编译时执行类型检查并插入类型转换代码。这也是编译器保证泛型类/方法内外泛型使用一致性的方式。

下面的代码：

```java
public class GenericHolder2<T> {
  //  静态变量不能使用泛型
  // public static T test;
  public T genericBound;
  private T obj;
  public void set(T obj) { this.obj = obj; }
  public T get() { return obj; }
  public static void main(String[] args) {
    GenericHolder2<String> holder =
      new GenericHolder2<>();
    holder.set("Item");
    String genericBound = holder.genericBound;
    String s = holder.get();
  }
}
```

用javap命令反编译上面代码的字节码：

~~~
PS D:\Study\githup\helloWorld\sourceCode\testMaven\onjava8\src\main\java\generics> javap -s -c -private  GenericHolder2
警告: 二进制文件GenericHolder2包含generics.GenericHolder2
Compiled from "GenericHolder2.java"
public class generics.GenericHolder2<T> {
  public T genericBound;
    descriptor: Ljava/lang/Object;

  private T obj;
    descriptor: Ljava/lang/Object;

  public generics.GenericHolder2();
    descriptor: ()V
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void set(T);
    descriptor: (Ljava/lang/Object;)V
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field obj:Ljava/lang/Object;
       5: return

  public T get();
    descriptor: ()Ljava/lang/Object;
    Code:
       0: aload_0
       1: getfield      #2                  // Field obj:Ljava/lang/Object;
       4: areturn

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    Code:
       0: new           #3                  // class generics/GenericHolder2
       3: dup
       4: invokespecial #4                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: ldc           #5                  // String Item
      11: invokevirtual #6                  // Method set:(Ljava/lang/Object;)V
      14: aload_1
      15: getfield      #7                  // Field genericBound:Ljava/lang/Object;
      18: checkcast     #8                  // class java/lang/String
      21: astore_2
      22: aload_1
      23: invokevirtual #9                  // Method get:()Ljava/lang/Object;
      26: checkcast     #8                  // class java/lang/String
      29: astore_3
      30: return
}
~~~

​	我们可以看到，发编译后，类变量T的descriptor是Ljava/lang/Object，即他是引用类型Object；方法set的descriptor是(Ljava/lang/Object;)V，即参数是object，无返回值；方法get的descriptor是()Ljava/lang/Object，无参数，返回object类型。在编译阶段，会做类型检测，如果类内部T的使用方式不一致，将报错。

​	既然变量genericBound，get方法返回的是object类型，那我们在使用的时候，返回值的接受类型又怎么变为运行时指定的类型呢，在main方法中我们可以看到，在getfield和invokevirtual（get）命名后都紧跟着类型转换命令，checkcast，这个时编译阶段编译器自动添加的。但是set方法调用呢，我们并没有发现set方法调用时又上面特殊操作保证他是String啊？其实，这个工作时在编译阶段类型检测所作的事情，类型检测保证了set方法的参数时object，所以反编译后就不需要特殊处理了。

​	利用类型检测，编译器保证了方法调用参数等类入口的泛型一致性，以及类内部泛型使用方式的一致性。利用cast自动插入，保证了方法调用返回值等泛型出口的泛型一致性。

## 4、泛型的补偿

​	在类/方法内部，泛型擦除了方法信息，这就限制了泛型的使用。比如，不能instanceof操作，不能使用new，不能创建数组（可以声名泛型数组），这些都可以通过适当的方法进行弥补。

​	instanceOf可以用isInstance()方法替代。

​	new的替代方式：

- 利用Class对象的newInstance()方法，该方法要保证class有无参构造器
- 提供一个工厂对象（supplier），利用lamda表达式的::new

​    数组的创建方式：

- 用List替代
- (T[])new Object[sz]。内部维护T[]，创建一个Object[]数组进行强转。但是这种方法如果把数组暴露给外部，接受对象如果不是Object[]，会产生ClassCastException，因为父对象时不能赋值给子对象的，这条规则同样适用于数组。所以此方法只使用数组只是内部使用的情况。为什么(T[])new Object[sz]这里可以强转，用其他数组接收却不可以，因为T最终被擦除了，变为了Object，所以实际上(T[])new Object[sz]等价于(Object[])new Object[sz]。
- new Object[sz]，内部维护的数组就是Object[]，在获取数组原始后进行类型转换，这个方法需要在每个获取数组元素的地方进行强转。这个方法暴露数组对象给外部的时候只能用Object[]接收，依然不能转换为其他对象。将数组内部处理为Object[]而不是T[]的优点是，您不太可能忘记数组的运行时类型，从而意外地引入错误(尽管在运行时可以快速检测到大多数(也许所有)此类bug)。
- (T[])Array.newInstance(type, sz);这个是创建泛型数组的较好的选择。创建的数组就是实际类型的数组，不必担心类型转换，还可以将数组暴露给外部，并用真正类型数组接收。

## 5、边界

​	边界是将泛型参数约束到一个子类型，然后可以使用被约束的子类型的功能。

```java
package generics;// generics/BasicBounds.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

interface HasColor {
    java.awt.Color getColor();
}

class WithColor<T extends HasColor> {
    T item;
    WithColor(T item) { this.item = item; }
    T getItem() { return item; }

    // The bound allows you to call a method:
    java.awt.Color color() {  return item.getColor(); }
}

class Coord {
    public int x, y, z;
}

// This fails. Class must be first, then interfaces:
// class WithColorCoord<T extends HasColor & Coord> {
// Multiple bounds:
class WithColorCoord<T extends Coord & HasColor> {
    T item;

    WithColorCoord(T item) { this.item = item; }
    T getItem() { return item; }
    java.awt.Color color() {  return item.getColor(); }
    int getX() {  return item.x; }
    int getY() { return item.y; }
    int getZ() { return item.z; }
}

interface Weight {
    int weight();
}

// As with inheritance, you can have only one
// concrete class but multiple interfaces:
class Solid<T extends Coord & HasColor & Weight> {
    T item;

    Solid(T item) { this.item = item; }
    T getItem() { return item; }
    java.awt.Color color() { return item.getColor(); }
    int getX() { return item.x; }
    int getY() { return item.y; }
    int getZ() {  return item.z; }
    int weight() { return item.weight(); }
}

class Bounded extends Coord implements HasColor, Weight {
    @Override
    public java.awt.Color getColor() { return null; }

    @Override
    public int weight() { return 0; }
}

public class BasicBounds {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<>(new Bounded());
        solid.color();
        solid.getY();
        solid.weight();
    }
}
```

我们将Solid类进行反编译如下：

```java
PS D:\Study\githup\helloWorld\sourceCode\testMaven\onjava8\src\main\java\generics> javap -c -s -private Solid
警告: 二进制文件Solid包含generics.Solid
Compiled from "BasicBounds.java"
class generics.Solid<T extends generics.Coord & generics.HasColor & generics.Weight> {
  T item;
    descriptor: Lgenerics/Coord;

  generics.Solid(T);
    descriptor: (Lgenerics/Coord;)V
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field item:Lgenerics/Coord;
       9: return

  T getItem();
    descriptor: ()Lgenerics/Coord;
    Code:
       0: aload_0
       1: getfield      #2                  // Field item:Lgenerics/Coord;
       4: areturn

  java.awt.Color color();
    descriptor: ()Ljava/awt/Color;
    Code:
       0: aload_0
       1: getfield      #2                  // Field item:Lgenerics/Coord;
       4: checkcast     #3                  // class generics/HasColor
       7: invokeinterface #4,  1            // InterfaceMethod generics/HasColor.getColor:()Ljava/awt/Color;
      12: areturn

  int getX();
    descriptor: ()I
    Code:
       0: aload_0
       1: getfield      #2                  // Field item:Lgenerics/Coord;
       4: getfield      #5                  // Field generics/Coord.x:I
       7: ireturn

  int getY();
    descriptor: ()I
    Code:
       0: aload_0
       1: getfield      #2                  // Field item:Lgenerics/Coord;
       4: getfield      #6                  // Field generics/Coord.y:I
       7: ireturn

  int getZ();
    descriptor: ()I
    Code:
       0: aload_0
       1: getfield      #2                  // Field item:Lgenerics/Coord;
       4: getfield      #7                  // Field generics/Coord.z:I
       7: ireturn

  int weight();
    descriptor: ()I
    Code:
       0: aload_0
       1: getfield      #2                  // Field item:Lgenerics/Coord;
       4: checkcast     #8                  // class generics/Weight
       7: invokeinterface #9,  1            // InterfaceMethod generics/Weight.weight:()I
      12: ireturn
}
```

​	可以看到T已经被擦除到了Coord。而在类中，可以使用Coord/HasColor/Weight的属性和方法了。但是同时，你使用Solid的时候，他的泛型参数必须是Coord/HasColor/Weight的共同子类。这就是边界的限制。

​	可是T被擦除到了Coord，为什么可以使用HasColor/Weight的属性和方法呢？在反编译代码中可以看到，在调用HasColor和Weight的时候，编译器插入了强转代码，把Coord强转为了HasColor/Weight。

上面的类可以利用继承重写，简化相关代码：

```java
class HoldItem<T> {
  T item;
  HoldItem(T item) { this.item = item; }
  T getItem() { return item; }
}

class WithColor2<T extends HasColor> extends HoldItem<T> {
  WithColor2(T item) { super(item); }
  java.awt.Color color() { return item.getColor(); }
}

class WithColorCoord2<T extends Coord & HasColor> extends WithColor2<T> {
  WithColorCoord2(T item) {  super(item); }
  int getX() { return item.x; }
  int getY() { return item.y; }
  int getZ() { return item.z; }
}

class Solid2<T extends Coord & HasColor & Weight>
extends WithColorCoord2<T> {
  Solid2(T item) {  super(item); }
  int weight() { return item.weight(); }
}

public class InheritBounds {
  public static void main(String[] args) {
    Solid2<Bounded> solid2 =
      new Solid2<>(new Bounded());
    solid2.color();
    solid2.getY();
    solid2.weight();
  }
}
```

## 6、通配符

​	我们指定在数组中，子类型的数组Sub[]是可以赋值给父类型的数组Sup[]。因为Sub是Sup的子类，而数组是有他的元素的具体信息的，比如Sub[]数组知道自己存放的元素就是Sub。子类型可以放到一个父类型数组中，不管父数组的实际类型是啥，但是如果父类型的实际类型时子类型2，你如果此时把子类型1放到父类型数组中，在编译器没事，在运行时会报错。数组的这个规则如果滥用，会导致一些错误。泛型可以解决这个问题。

​	但是在泛型中，因为擦除，泛型类是不知道泛型的具体信息的。所以，虽然Sub时Sup的子类，但是Sub的集合ArrayList\<Sub>不是Sub的集合ArrayList\<Sup>的子类，所以ArrayList\<Sub>不能"向上转型"为ArrayList\<Sup>！因为泛型类在java中只有一种类型，那就是原型ArrayList，ArrayList\<Sub>和ArrayList\<Sup>都会被擦除为ArrayList。所以这样的赋值操作是不可以的，因为ArrayList并不知道泛型参数的实际类型的具体信息。

​	但是，有时候我们又需要集合的"向上转型"这种操作，来建立两类集合的这种关系。比如接收一个方法返回值的时候。泛型通过另一个方法实现了这个功能，那就是参数通配符**"?”**。

### ? extends T

​	List<? extends Fruit>，您可以将其读作“从水果继承的任何类型的列表”。然而，这并不意味着这个清单里会有任何种类的水果。通配符指的是一个确定的类型，所以它的意思是**list**引用没有指定的某个特定类型”。因此，被分配的列表必须包含某些特定类型，如Fruit或Apple，但是list并不关心它是由那种子类"向上转型"过来的。

​	那么你用List<? extends Fruit>接收到一个List以后，你能干什么呢？前面说过，通配符是为了解决数组“向上转型”的问题的。这个是什么问题呢？看下面的代码：

~~~java
    List<Orange> oranges = new ArrayList<>();
    List<? extends Fruit> flist = oranges;
    // flist.add(new Apple()) 编译错误
    Orange orange = oranges.get(0);
~~~

​	你现在知道的是flist中保存的是一种Fruit的子类型，但是不知道是哪一种类型。如果第三行代码，向flist添加一个apple成功，那么第四行代码orange实际上拿到的就是一个apple，这个就会造成类型转换异常，把list换位数组，就是我说的数组问题。为了解决这个异常，编译器直接再编译阶段就给出了错误提示。

​	所以List<? extends Sup>禁止你往作用域中添加null之外的任何元素。这里注意，flist.add(new Apple())无法编译的原因是ArrayList中，add方法的参数是泛型。

​	也就是说，如果一个泛型类，你指定他的泛型是? extends Sup，那么在任何泛型作用域内，任何泛型入口都是被编译器禁止的。

​	但是你可以从flist中获取一个元素，获取的元素类型是fruit。

​	从泛型擦除的概念理解：根据擦除的规则，? extends T会被查出到第一个边界，也就是T。所以，在进出口边界，泛型会被T类型所替换。进行相应强转和类型检测。在出口处，因为? extends T是T的一个子类型，所以强转为T是没问题的。但是在入口处进行检测时，编译器知道泛型类要求T的一种子类型，但不知道具体时哪一种子类型，所以无论你传什么，为了安全起见，他都会拒绝你传入。

​	所以，有一种说法，? extends Sup是消费者泛型。

### ? Supper T

​	那么我们怎么向通配泛型里面赋值呢？与extends相对的，泛型中也有supper关键字，<? super MyClass>或者<? super T>，意思是一个类型，他是MyClass/T的父类，这个类型可以通过泛型入口进入泛型作用域。这样，泛型在往里面增加值的时候，只要保证增加的都是MyClass/T的子类就行。

​	根据泛型擦除规则，? Supper T被擦除为T。所以，在进出口边界，泛型会被T类型所替换。进行相应强转和类型检测。在出口处，因为? super T是T的一个父类型，编译器并不知道T是什么类型，为了安全起见，所以将T强转为了Object。在入口处进行检测时，编译器知道泛型类要求T的一种父类型，但不知道具体时哪一种父类型，为了安全起见，它要求你传一个大范围的类型，就是T或者T的子类型。

​	所以，有一种说法，? extends Sup是生产者泛型。

### ?

​	这个通配符使用效果和使用原泛型效果一样，即：ArrayList<?>等价于ArrayList。

​	它有一个作用就是泛型类型推断转换，就是说你如果将一个原类型类实例传入一个参数为无界通配符的方法中，那么编译器可以推断出原类型的泛型的实际类型，这个方法可以用具体类型调用另一个泛型方法。

​	这个功能点没做深究，有机会补充。

## 7、泛型缺点：

- 不能使用基本类型。
- 不能同时实现两个相同的泛型接口，即使他们的泛型不同。
- 不能使用instanceof
- 两个方法，除了参数的泛型类型不同外，其他都相同，他们的方法签名相同，不算重载。

## 8、总结

​	泛型擦除有两种，一种是定义擦除，一种是使用擦除。这两种擦除都又都包含两种：边界擦除和非边界擦除。

​	非边界查出是在使用的时候直接指定了泛型类的具体类型，不使用extend和super关键字，编译器在你使用泛型类的地方将泛型擦除为你指定的类型。

​	边界擦除是指含有extend和super关键字的泛型的擦除，extend和supper关键字为了约束泛型的类型并使用被约束类型的功能。边界擦除将泛型擦除为第一个边界类型。

​	知道了边界查出和非边界擦除，我们再回来看定义擦除和使用擦除。

​	定义擦除：是指编译器在编译定义的泛型类时，将泛型类总的泛型（边界和非边界）的类型信息擦除，并做相应的类型检测。

​	使用擦除：是指在使用泛型类的时候，将你指定的放心的具体类型（边界和非边界）查出，再在泛型类的入口做相应的类型检测，在出口做强转。

​	使用擦除中有一种比较特殊的情况，那就是使用通配符"?"的使用，通配符只用在使用泛型使用。? extend T，这种通配符边界擦除，会在泛型出口的所有地方将泛型擦除为T，并强转，在入口处进行类型检测的时候直接报错。？ super T，会在泛型出口的所有地方将泛型擦除为Object，并强转为Object，在入口做类型检测，保证入口时T或T子类型。？会在泛型出口的所有地方将泛型擦除为Object，并强转为Object，在入口在入口做类型检测，保证他是Object类型即可，所以？和不适用泛型的原类型时相同的效果。

参考资料

《on Java 8》 Bruce Eckel。注：Java编程思想第五版。

