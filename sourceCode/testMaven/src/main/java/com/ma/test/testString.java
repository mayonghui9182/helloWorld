package com.ma.test;

public class testString {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
	}
	
	//true
	public static void test1() {
		String a = "a1";
		String b = "a"+ 1;
		System.out.println(a==b);
	}
	//false
	public static void test2() {
		String a = "ab";
		String bb = "b";
		String b = "a"+ bb; //??????????????????
		System.out.println(a==b);
	}
	//true
	public static void test3() {
		String a = "ab";
		final String bb = "b";
		String b = "a"+ bb; //bb??final???????????????????????b
		System.out.println(a==b);
	}
	//false
	public static void test4() {
		String a = "ab";
		final String bb = getBB();
		String b = "a"+ bb;//bb?????????????????????????final????????????????????????????????bb???
		System.out.println(a==b);
	}

	private static String getBB() {
		return  "b";
	}
	
	//false true
	private static String a = "ab";
	public static void test5() {
		String s1 = "a";
		String s2 = "b";
		String s = s1 + s2;//+???�?
		System.out.println(s == a);
		System.out.println(s.intern() == a);//intern?????
	}
	
	//false false true
	private static String a1 = new String("ab");
	public static void test6() {
		String s1 = "a";
		String s2 = "b";
		String s = s1 + s2;
		System.out.println(s == a1);
		System.out.println(s.intern() == a1);
		System.out.println(s.intern() == a1.intern());
	}
	public static void test7() {
		String s0= "kvill"; 
		String s1=new String("kvill"); 
		String s2=new String("kvill"); 
		System.out.println( s0==s1 ); 
		System.out.println( "**********" ); 
		s1.intern(); 
		s2=s2.intern(); //?????????"kvill"?????????s2 
		System.out.println( s0==s1); 
		System.out.println( s0==s1.intern() ); 
		System.out.println( s0==s2 ); 
	}
}
