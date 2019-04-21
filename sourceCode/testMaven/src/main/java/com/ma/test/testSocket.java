package com.ma.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class testSocket {
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("127.0.0.1", 9999);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
