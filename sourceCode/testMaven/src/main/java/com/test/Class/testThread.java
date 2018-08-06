package com.test.Class;

public class testThread {
	void waitForSignal(){
		Object obj=new Object();
		synchronized (obj.getClass()) {
			try {
				obj.getClass().wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			obj.getClass().notify();
		}
	}
	public static void main(String[] args) {
		new testThread().waitForSignal();
	}
}
