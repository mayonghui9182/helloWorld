

## Java的两次编译流程

​		从java文件到机器执行的最终文件，需要经过下列步骤，最终机器被执行：

![java编译流程](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\java编译流程.png)

根据完成任务不同，可以将编译器的组成部分划分为前端（Front End）与后端（Back End）。

> 前端编译主要指与源语言有关但与目标机无关的部分，包括词法分析、语法分析、语义分析与中间代码生成。
>
> 后端编译主要指与目标机有关的部分，包括代码优化和目标代码生成等。

参考文章：[java的两次编译](https://www.hollischuang.com/archives/2322)。

## 前端编译

### 流程

​		我们先不管后端编译，说前端编译。将java文件转换为class文件的字节码的过程，需要经过下列步骤。

![第一次编译](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\java第一次编译.png)

参考文章：[深入分析 Javac 编译原理](https://juejin.im/post/5b9fa2e5f265da0ad2217f84)

### lexical structure (词汇结构)

#### unicode编码

​		文本文件内容都有一个编码方式，UTF-8，GBK等（在此普及个知识点，笔者遇见过很多次，文件名称的编码格式和文本内容的编码格式不是一回事哦）。而我们编写代码的java文件本质上也是一个文本文件，所以我们编写的文本文件内容也会有一个编码格式，将你编写的内容正确存储起来（idea右下角有标识）。

​		*java编译器编辑java文件的时候，会将文件按照原本编码方式读取，然后转换为java内置的unicode编码方式，编译器讲过编译，又将内容转为unicode编码（UTF-8和UTF-16都属于Unicode编码）的字节码，即class文件。*

参考文章：[如何理解java采用Unicode编码](https://blog.csdn.net/gjb724332682/article/details/43229563)

#### lexical translations（词汇转换）

编译器会将unicode编码讲过词汇转换，转换为一个个token。具体步骤如下：

1. 将Unicode转义转换为Unicode字符流。兼容ASCII编码
2. 将第1步产生的Unicode字符流转换为字符和行终止符的流
3. 将步骤2产生的字符和行终止符流转换为一系列输入元素，这些元素在删除空格和注释后将包含作为语法规则终结符号的标记。

下面按时字符转换知识点：

- Unicode Escapes （unicode转义）:将ASCII码\u和紧随其后的4个十六进制转换为一个UTF-16单元，表示补充字符需要两个连续的Unicode转义，这个转换步骤的结果是一个Unicode输入字符序列。将Unicode编码和ASCII编码做转换的规则，具体参考书中的逻辑。

- Line Terminators（行终结符）：作为行结束符号CR/LF。

- Input Elements（输入元素）： 输入元素包括空白字符，注释和token。token又包括Identifier（表示符），
  Keyword（关键字），
  Literal（常量）， Separator（分隔符），
  Operator（操作符）。

- White Space （空白）：空白字符包括ASCII空格字符，水平制表符，换页符和行终止符。

- Comments （注释）：//和/* */不支持嵌套

- 标识符：Java letters 和Java digits，必须以java letters开头。不能包括相似的序列，Boolean literal，null literal，关键字。相似的序列指的是相同的Unicode编码，例如，不同编码标识相同的A，他们仍然不是相同的标识符。

- java letters：Character.isJavaIdentifierStart(int)返回true，包括但不限于大小写的a-z，_和$。$符号仅应在机械生成的源代码中使用（例如内部类），或者很少用于访问旧系统上的现有名称。

- Java letter-or-digit：Character.isJavaIdentifierPart(int)返回true

- 关键字：50个关键字。true和false不作为关键字，他们是boolean的字面量。相似的还有null。![java关键字](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\java关键字.png)

- literals： 常量是原始类型，字符串类型或空类型的值的源代码表示。

- Separators（分隔符）：12个分隔符![java分隔符](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\Java分隔符.png)

- Operators（操作符）：38个操作符![1576589591559](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\Java操作符.png))

  注：Java letters和Java digit几乎包含了所有的Unicode编码。包含但不仅限于中文，日文，韩文等。

  

## Java类型特点

Java时静态的，强类型语言。即在编译期，所有的变量和所有的表达式都有一个类型，且变量持有的值的类型和表达式产生的类型都被限定了。

Java类型有两种，基本数据类型和引用类型了，以及特殊的表达式类型null。相应的，有两种数据值，可以被存储在变量中，当作参数，返回值和操作数。

原始类型有8种：byte，short，char，int，long，float，double以及非数值类型boolean。

引用类型有四种：类，接口，type变量，数组，如下图：![java引用类型](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\java引用类型.png)

#### 引用类型

##### Object  

​		类或数组的实例叫做Object，即对象。对象可以通过显式创建，比如new关键字，反射，数组创建表达式等。也可以通过隐式创建，比如装箱，String的凭借操作（+）。

​		针对实例有很多操作：

- cast 强制操作
- Field访问
- 方法访问
- \+ 特指String
- instanceOf 
- ==和！=
- ? : 三元操作泛

##### Object 类 

所有对象的最终父对象，包括Class和数组，在没有显示声名extends关键字的时候，Object是其直接父类。

因为是所有引用对象的最终父类，所有它的所有public方法是所有的类共有的，它的方法请参考jdk文档。

其中比较重要的一个方法是getClass()方法，它返回的是代表这个类的一个Class实例。在方法上增加关键字synchronized即使在这个类的class对象上加锁。

##### Type Variablesi  

和下面的Parameterized types搅和到了一起，所以这张详写。

类型变量是在类，接口，方法和构造函数主体中用作类型的非限定标识符。我是这样理解这句话的，我们在类，接口，方法，构造函数种声名一个类型的时候，一般需要一个全路径的限定符，即包名+类名+类型参数。而类型变量在声名一个变量的时候，只需要使用它的标识符即可。不需要全限定名，此时也没有它的全限定名。

类型变量由泛型类、接口、方法或构造函数的类型参数的声明引入，如下：![java泛型类型参数](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\java泛型类型参数.png)

可以看到。类型变量TypeVariable是TypeBound的组成部分，TypeBound是TypeParameter的组成部分。那么声名是TypeParameter呢，他就是泛型的参数化参数种尖括号内的部分（个人理解，不争取请指正，谢谢）。

综合jsl的概念，个人理解，type variable指的泛型中的类型参数T，即<T extends A&B&...&H>种的T。另外在<T extends A&B&...&H>种，如果有A到H，A可以是类和接口，但是B到H必须是接口。一个类的如果有两个相同的直接或间接父接口，这两个父接口的参数类型必须一样。

##### Parameterized Types

泛型类或接口声明定义了一组参数化类型。

参数化类型是形式为C <T<sub>1</sub>,...,T<sub>n</sub>>的类或接口类型，其中C是泛型类型的名称，而<T<sub>1</sub>,...,T<sub>n</sub>>是类型参数的列表，表示泛型的特定参数化。

泛型类型具有type parameters T<sub>1</sub>,...,T<sub>n</sub>，其边界为B<sub>1</sub>,...,B<sub>n</sub>。 参数化类型的每个类型实参Ti覆盖所有类型，这些类型是在相应边界中列出的所有类型的子类型。 也就是说，对于Bi中每个绑定类型S，Ti是S [F1：= T1，...，Fn：= Tn]的子类型。

个人理解，参数化类型指的是泛型使用时的声名（不是定义声名的泛型）。例如：ArrayList\<T>是java中的一个泛型类。我们定义一个String类型的ArrayList变量：ArrayList\<String> strList。此时，ArrayList\<String>就是一个参数化类型(Parameterized Types)。

下列情况下，两个参数化类型是完全不同的类型：

- 属于两个完全不同的泛型
- 同一个泛型，拥有不同的类型参数。值得注意的一点，如果两个参数化类型，他们的类型参数有父子关系，但是这两个参数化类型是没有父子关系的，拥有父类型参数的参数化类型不能接收子类型参数的参数化类型实例，例如 ArrayList\<Map> strList = new ArrayList\<HashMap>()会报编译错误 。此时，通配符的价值就体现出来了，ArrayList\<?> strList = new ArrayList\<HashMap>()

1、Type Arguments of Parameterized Types（参数化类型中的类型参数）（类型捕获读懂后才能更好明白这一节）

在上文strList变量类型声名中ArrayList\<String> strList。ArrayList\<String>是参数化类型，String叫做类型参数。类型参数可以是引用类型或者通配符。

下列情况下，两个类型参数是完全不同的：

- 两个类型参数都不是通配符和类型变量，且不是同一种引用类型。
- 一个是上界为S的通配符或者类型变量，一个是引用类型T，而且，S不是T的子类型，T也不是S的子类型。
- 两个都是上界为S的通配符或者类型变量，一个上界为S，另一个为T，但是S不是T的子类型，T也不是S的子类型。

在不指的类型参数的详细信息的时候，通配符就变得非常有用了。

2、Members and Constructors of Parameterized Types（参数化类型的成员和构造函数）

泛型C的类型参数为<A<sub>1</sub>,...A<sub>n</sub>>，他的参数化类型的类型参数< T<sub>1</sub>,...,T<sub>n</sub>>。 T<sub>i</sub>不是通配符，是引用类型。那么：

- m是C的一个成员或者构造函数，他的类型是T，那么C的成员m的类型T是<T<sub>1</sub>,...,T<sub>n</sub>>种的一种。
- m是D的一个成员或者构造函数，D是C的父类，D的类型参数为<U<sub>1</sub>,...,U<sub>n</sub>>那么C的成员m的类型T是<U<sub>1</sub>,...,U<sub>n</sub>>种的一种。

如果T<sub>i</sub>是通配符。

- C的成员变量，方法，构造函数的类型是通过capture conversion的来。
- D是C种声名的一个类或接口，如果D是泛型，D所有的参数都是无界通配符。

注意：通配符不能用在new表达式中。类型捕获不使用instanceof。

##### Type Erasure（类型擦除）

java的泛型机制是弱化的泛型。它和其他语言的泛型比起来，少了一些强大的功能。这样的结果是为了兼容java5版本以前的代码。为了兼容，java决定采用类型擦除机制。

java的泛型是用擦除实现的，所以，下面一些定义需要明确一下。

~~~java
public class SuperAndSubGeneric<T> {
    public static void main(String[] args) {
        // illegal 两个参数化类型，如果参数化类型不一致，即使他们是父子关系，他们也不是同一种泛型，不能画上等号
        //SuperAndSubGeneric<Super> sup = new SuperAndSubGeneric<Sub>();
        //泛型的无类型参数的参数化类型，即raw type，形如SuperAndSubGeneric，可以接收任意的参数化类型
        SuperAndSubGeneric sup = new SuperAndSubGeneric<Super>();
        //泛型的无类型参数的参数化类型，形如SuperAndSubGeneric，可以赋值给一个参数化类型，但是会有警告产生。
        SuperAndSubGeneric<Sub> sup1 = new SuperAndSubGeneric();
    }
}
class Super{}
class Sub{}
~~~

其中，判别两个参数化类型不同的标准在参数化类型一章有介绍。raw type在下面的章节种会有介绍

类型擦除是将一个类型（参数化类型和类型变量）映射为另一个类型（非参数化类型或者类型变量）。

我们知道，类型变量并不是一个真正意义上的类型，它只是取的变量的概念，代表着它表示一种类型，那编译器是怎么处理的呢？编译器在编译后，擦除类型变量的声名，将用到这个类型变量的地方转换为一种真正的变量类型，它取的是边界的最左边界。其中，没有边界的类型变量默认是Object。

这就是泛型擦除的含义，具体它是怎么处理的，请参考《onjava8》泛型章节。

##### Reifiable Types（具体化类型）

可以在运行时得到类型的全部信息，那么该类型就是具体化类型。下列都是具体化类型

- 引用了非泛型类或接口声名。
- 所有类型变量都是无界通配符的参数化类型
- raw type
- primitive type
- 具体化类型的数组
- 嵌套类型，但是每一层都是具体化类型

##### raw type（未经加工的原类型）

java泛型声名泛型的地方有两个，一个是类或接口的后面，一个是在方法的声名种，如下：

~~~java
public class SuperAndSubGeneric<T>{
    public static  <T> T test(Class<T> clazz) { }
    public  <T> T test2(Class<T> clazz) { }

} 
~~~

这两个地方以及他实现父接口和继承父类时声名的类型参数都会被擦除。

变量单独声名一个类型变量没有意义，因为没有一个地方能声名它具体的引用类型，无论他是静态还是非静态的。

raw type是为泛型代码和遗留代码的更好的交互。经过类型擦除后的泛型和泛型数组可以作为一个类型使用。那么怎么确定一个类型是raw type 呢：

- 没有泛型参数列表，只有泛型名的泛型。形如List

- raw type数组，形如 List[]

- raw type R的非静态成员类型，它不是从R的超类或超接口继承而来的。即，R中的所有非欸静态类型成员，这句话结合我们上面讲到的，实例方法，成员只能通过类变量声名时指定，实例方法本身不能指定一个单独的类型变量，而且不能根据参数类型推断出来。而且形如：Outer.Inner<Double> x = null; // illegal这样的声名是非法的。如下：

  ~~~java
  public class SuperAndSubGeneric<T> {
      public static void main(String[] args) {
          SuperAndSubGeneric<Sub> sup1 = new SuperAndSubGeneric();
  		//Sub test = sup1.test2(Sub.class); //illegal
          Sub test = SuperAndSubGeneric.test(Sub.class);
      }
  
      public  static <T>  T test(Class<T> clazz) {
          return null;
      }
      public <K> T test2(Class<K> clazz) {
          return null;
      }
  
  }
  ~~~

  

raw type的概念是针对泛型提出的，非泛型类是么有raw type一说的。

raw type是所有泛型的擦除，raw type的父类/父接口，是这个泛型的所有参数化类型的父类/父接口的擦除。

raw type C中的构造方法，实例方法，非静态域的类型，如果不是继承自父类/父接口，那么这些方法和域的类型就是C中类型变量擦除后的类型，如果时参数化类型，那么就是这个参数化类型的对应的raw type。

raw type C中静态方法和静态域的类型和在C中声名的类型一样，类型变量根据类型推断得出。

如果给raw type C中的非静态类型成员传递类型参数，那么会报编译错误。

如果参数化类型的非静态成员是raw type，也会出现编译错误，java语言规范中也说这种错误并没有正当的理由。他只是说，在遗留代码中，都不会使用泛型，在泛型版本中，都需要使用泛型，给所有使用的泛型参数。

~~~java
public class SuperAndSubGeneric<T> {
    public static void main(String[] args) {
        //两个泛型参数不一样，inner中T使用的Integer类型，SuperAndSubGeneric使用String，虽然两个类型变量都是T
        SuperAndSubGeneric<String>.inner<Integer> test1= null;
        //此时SuperAndSubGeneric和inner都是raw type
        SuperAndSubGeneric.inner x= null;
        //raw type 的非静态成员不能是参数化类型。raw type SuperAndSubGeneric中不能单独为inner传递类型参数
        // SuperAndSubGeneric.inner<Integer> test2= null; illegal
        //参数化类型的非静态成员不能是raw type
        // SuperAndSubGeneric<String>.inner x= null;
    }
   
    //这里的T会覆盖SuperAndSubGeneric<T>中的T。
    class inner<T> {}
}
~~~

raw type只是为了兼容旧的代码，在泛型版本不建议写raw type，因为这个可能会在未来版本中移除。为了确信没有违反这个建议，我们在访问raw type成员的时给出了警告信息。类型擦除后，在进行访问域和方法时，导致访问操作两边的参数不一致，就会出现警告。

原始类型的某些成员不会被擦除，即类型被参数化的静态成员和从非泛型超类型继承的成员。

![RawMember抽象类](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\RawMember抽象类.png)

上面图中：iterator继承自Collection\<String>，String类型会被擦除，所以rw。iterator得到时iterator raw type。给出警告。

父类NonGeneric不是泛型，没有类型擦除。所以myNumbers返回的时Collection\<Number>。没有警告。

cng时静态变量，保留了他的类型信息，不会被擦除，也不会有警告。

上面的例子说明：raw type 的特定成员的类型信息不会被擦除：

- 静态成员
- 继承自非泛型的成员。

##### Intersection Types（交集类型）

一个类型变量的边界可以是一种类或接口，也可以是一个类和多个接口。形如：T<sub>1</sub>,...,T<sub>n</sub>，其中T<sub>1</sub>可以是类，也可以是接口，剩下的必须都是接口。当n>1的时候，这个类型变量就是交集类型，它的值必须是这个类的子类且实现了所有的接口。

交集类型的实现是通过类型擦除到最左边界和cast操作实现的。他也出现在类型捕获处理和最小上界计算中。

交集类型类型的成员包含了T<sub>1</sub>,...,T<sub>n</sub>中的所有成员。

##### Subtyping （子类型）

子类型和父类型是类型上的二元关系。

S :>T指S是T的父类型，包含S=T

S >T指S是T的合适父类型，不包含S=T

S ><sub>1</sub>T指S是T的直接父类型

S :<T指S是T的子类型，包含S=T		

S <T指S是T的合适子类型，不包含S=T

S <<sub>1</sub>T指S是T的直接子类型

这个规则不适用参数或类型，如：C\<T> <: C\<S>

对于基本数据类型的父子类型关系如下：

![基本数据类型的父子类型关系](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\基本数据类型的父子类型关系.png)

对于类和接口：父子类型关系如下：

1. 非泛型 C的直接父类型
   - C的直接父类
   - C的直接父接口
   - 如果C是没有直接父接口的接口，那么直接父类是Object。
2. 泛型C<T<sub>1</sub>,...,T<sub>n</sub>>的raw type的直接父类型
   - raw type C的直接父类
   - raw type C的直接父接口
   - 如果raw type C是没有直接父接口的接口，那么直接父类是Object。
3. 泛型C<T<sub>1</sub>,...,T<sub>n</sub>>的直接父类型
   - C<T<sub>1</sub>,...,T<sub>n</sub>>直接父类
   - C<T<sub>1</sub>,...,T<sub>n</sub>>的直接父接口
   - 如果C<T<sub>1</sub>,...,T<sub>n</sub>>是没有直接父接口的泛型接口，那么直接父类是Object。
   - raw type c
4. 参数化类型C<T<sub>1</sub>,...,T<sub>n</sub>>的直接父类型
   - C<T<sub>1</sub>,...,T<sub>n</sub>>直接夫类型D<U<sub>1</sub>,...,U<sub>n</sub>>，且U<sub>K</sub>:=T<sub>k</sub>,
   - C<S<sub>1</sub>,...,S<sub>n</sub>>且U<sub>K</sub>包含T<sub>k</sub>,
   - 如果C<T<sub>1</sub>,...,T<sub>n</sub>>是没有直接父接口的泛型接口，那么直接父类是Object。
   - raw type c

数组，父子类型：

![	数组，父子类型](D:\Study\githup\helloWorld\知识体系\博客\jls读书笔记\java编译和类型\数组父子类型.png)

##### Where Types Are Used

1. 在声名中：
   - extends和implements语句中后面的类型	（类或者接口）
   - 方法的返回值类型
   - 构造函数和方法的throws语句中抛出的异常类型
   - 泛型的类型参数的extends语句中的下界类型
   - 类和接口的域声名的类型
   - 构造函数，方法，lambdab表达式中形参的类型。
   - 方法的接收参数类型
   - 本地变量乐星
   - 异常参数类型
   
2.   表达式中：
   - 明确的构造函数调用，类实例创建，方法调用表达式中，其明确的参数类型列表。类实例创建需要指明type，比如 new Object()。用到了Object类型。	
   - 非全限定类实例创建，作为要实例化的类型，或者作为匿名类的直接父类/接口
   - 数组创建表达式的元素类型。
   - cast操作类型
   - instanceof 判断的类型
   - 方法引用表达式中，作为搜索成员方法的引用类型类，或者被创建的类/数组类型
   
3. 其他：
   - 数组的元素类型，以上情况中
   - 非通配符类型参数，有界通配符参数，参数化类型，以上情况中
   
4. 特殊情况
   - 无界通配符
   - ...表达式
   - 构造函数类型的简单名字，来表示要创建的对象。
   
5. 下列情况完全禁止参数化引用类型(上面说的是type使用的地方，这里说的是参数化类型)

   - 方法和构造器抛出的异常类型
   - 异常参数声名中(catch 语句中的异常参数)

   ```java
   class MyException<T> extends Exception{} //编译错误
   ```

声明中出现的五个类型上下文与许多声明上下文占据相同的句法空间：

- 方法的返回值类型
- 类/接口的域类型声名
- 方法，构造函数，lambda表达式的形参
- 本地变量类型声名
- 异常参数类型声名

程序中相同的语法位置既可以是类型上下文也可以是声明上下文的事实，是由于声明的修饰符紧随已声明实体的类型之前而出现的。

在类型上下文中，类型的含义将在后续的章节一一解释。

## 参考文章：

[深入分析 Javac 编译原理](https://juejin.im/post/5b9fa2e5f265da0ad2217f84)

[java的两次编译](https://www.hollischuang.com/archives/2322)

[如何理解java采用Unicode编码](https://blog.csdn.net/gjb724332682/article/details/43229563)