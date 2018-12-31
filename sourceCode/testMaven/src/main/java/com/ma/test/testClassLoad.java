package com.ma.test;

public class testClassLoad {
	public static void main(String[] args) {
		// Super s1 = new Sub();  
        // Super s2 = new Super();  
        Sub s3 = new Sub();  
	}
}
class Super {  
	 //a
    static int static_a = static_a();  
    final static int final_static_a = final_static_a();  
    static {  
        System.out.println("加载Super的静态块a");  
    }  
    int ordinary_a = ordinary_a();  
    
    {  
        System.out.println("加载Super的普通块a");  
    }  
    //b
    static int static_b = static_b();  
    final static int final_static_b = final_static_b();  
    static {  
        System.out.println("加载Super的静态块b");  
    }  
    int ordinary_b = ordinary_b();  
    
    {  
        System.out.println("加载Super的普通块b");  
    }  
    
    private void init() {
    	 System.out.println("加载Super的init");  
	}
  
    Super() {  
        System.out.println("加载Super的构造器");  
    }  
  
    private static int static_a() {
    	System.out.println("加载Super的静态变量static_a");  
		return 0;
	}
    private static int final_static_a() {
    	System.out.println("加载Super的静态常量final_static_a");  
		return 0;
	}
  
    static int ordinary_a() {  
        System.out.println("初始化Super的实例变量ordinary_a");  
        return 2;  
    }  
    private static int static_b() {
    	System.out.println("加载Super的静态变量static_b");  
    	return 0;
    }
    private static int final_static_b() {
    	System.out.println("加载Super的静态常量final_static_b");  
    	return 0;
    }
    
    static int ordinary_b() {  
    	System.out.println("初始化Super的实例变量ordinary_b");  
    	return 2;  
    }  
  
}  
  
class Sub extends Super {  
  
	 //a
    static int static_a = static_a();  
    final static int final_static_a = final_static_a();  
    static {  
        System.out.println("加载Sub的静态块a");  
    }  
    int ordinary_a = ordinary_a();  
    
    {  
        System.out.println("加载Sub的普通块a");  
    }  
    //b
    static int static_b = static_b();  
    final static int final_static_b = final_static_b();  
    static {  
        System.out.println("加载Sub的静态块b");  
    }  
    int ordinary_b = ordinary_b();  
    
    {  
        System.out.println("加载Sub的普通块b");  
    }  
  
    private void init() {
   	 System.out.println("加载Sub的init");  
	}
  
    Sub() {  
        System.out.println("加载Sub的构造器");  
    }  
  
    private static int static_a() {
    	System.out.println("加载Sub的静态变量static_a");  
		return 0;
	}
    private static int final_static_a() {
    	System.out.println("加载Sub的静态常量final_static_a");  
		return 0;
	}
  
    static int ordinary_a() {  
        System.out.println("初始化Sub的实例变量ordinary_a");  
        return 2;  
    }  
    private static int static_b() {
    	System.out.println("加载Sub的静态变量static_b");  
    	return 0;
    }
    private static int final_static_b() {
    	System.out.println("加载Sub的静态常量final_static_b");  
    	return 0;
    }
    
    static int ordinary_b() {  
    	System.out.println("初始化Sub的实例变量ordinary_b");  
    	return 2;  
    }  
}  
