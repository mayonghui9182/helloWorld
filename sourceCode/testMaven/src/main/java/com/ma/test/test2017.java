package com.ma.test;

public class test2017 {
	public static void main(String[] args) {
		test6();
	}
	//short s1 = 1; s1 = s1 + 1;��ʲô��? short s1 = 1; s1 += 1;��ʲô��? 
	public static void test6(){
		short s1 = 1; 
		s1 = (short) (s1 + 1); 
		short s2 = 1; s2 += 1;
	}
	
	public static boolean test16(){
		if(true){};
		int  x = 1;
		return x==1?true:false;
	}
	
}
