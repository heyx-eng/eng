package org.engdream.test;

public class App {
	public static void main(String[] args) {
		System.out.println(App.class.getClassLoader().getResource("").getPath());
	}
}
