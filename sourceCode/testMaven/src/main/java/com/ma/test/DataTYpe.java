package com.ma.test;


import org.junit.jupiter.api.Test;

public class DataTYpe {
	public static final  int i;
	static{
		i=1;
	}
	public DataTYpe(){
	}
	@Test
	public  void test(){
		String s;
		autoboxing	(2);
		final Number num;
		num=2;
		Integer i=1;
		i++;
		System.out.println(i+""+i.getClass());
	}
	public static void autoboxing(Object o){
		System.out.println(o.getClass());
		Integer integer=2;
		System.out.println(o.getClass());
/*		short sh=i;
		sh=1;
		Integer.valueOf(i);
		integer.toBinaryString(i);
		Long lg=(long) 1;
		lg.intValue();
		String s=null;double inte=100d;
		lg.toBinaryString(i);
		Integer.parseInt(s);*/	
		
	}
	public static void unboxing(int o){
		Integer i= o;
	}

}
