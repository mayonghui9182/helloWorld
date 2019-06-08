package com.ma.test.classloader;

//https://www.cnblogs.com/Fang3s/p/3970783.html
// 初始化父类Static --> 子类的Static （如果是类实例化，接下来还会： 初始化父类的其他成员变量－>父类构造方法－>子类其他成员变量－>子类的构造方法）。
public class LoadedOrder extends Super{
    protected static int iTest0 = Super.iTest0 + 1;
    private static LoadedOrder instance = new LoadedOrder();
    protected static int iTest1;
    private static int iTest2 = 0;
    static {
        System.out.println(LoadedOrder.class.getName() + " : static{}");
        iTest1++;
        iTest2++;
    }

    public LoadedOrder() {
        System.out.println(LoadedOrder.class.getName() + " : Constuctor with this = " + this);
        iTest1++;
        iTest2++;
    }

    /**
     * @return the instance
     */
    public static LoadedOrder getInstance() {
        return instance;
    }

    public void doSomeThing() {
        System.out.println("LoadedOrder.iTest0 = " + iTest0);
        System.out.println("LoadedOrder.iTest1 = " + iTest1);
        System.out.println("LoadedOrder.iTest2 = " + iTest2);
    }

    public static void main(String[] args) {
        //private static void main(String[] args)
        //Error: Main method not found in class san.LoadedOrder, please define the main method as:
        //   public static void main(String[] args)
        //   or a JavaFX application class must extend javafx.application.Application
        System.out.println("public static void main(String[] args)");

        LoadedOrder.getInstance().doSomeThing();
        System.out.println("Super.iTest0 = " + Super.iTest0);
        System.out.println(Const.constanceString);//对类的静态变量进行读取、赋值操作的。static,final且值确定是常量,是编译时确定的，调用的时候直接用，不会加载对应的类
        System.out.println("------------------------");
        Const.doStaticSomeThing();
        System.out.println("------------------------");
        Super[] supers=new Super[1];
//        LoadedOrder[] loadedOrders=new LoadedOrder[1];
//        supers=loadedOrders;
//        supers[0]=new Super();
//        LoadedOrder loadedOrder = loadedOrders[0];
//        loadedOrder.test();

    }
    void test(){
        System.out.println(123);
    }
}

class Super {
    protected static int iTest0;
    private static Super instance = new Super();
    private static final Super instance1 = new Super();
    protected static int iTest1 = 0;
    static {
        System.out.println(Super.class.getName() + " : static{}");
        iTest0 = LoadedOrder.iTest0 + 1;//1
    }

    public Super() {
        System.out.println(Super.class.getName() + " : Constuctor with this = " + this + ", iTest0 = " + iTest0);
        iTest1++;
    }
}

class Const {
    public static final String constanceString = "Const.constanceString";
    static {
        System.out.println(Const.class.getName() + " : static{}");
    }
    public static void doStaticSomeThing() {
        System.out.println(Const.class.getName() + " : doStaticSomeThing();");
    }
}