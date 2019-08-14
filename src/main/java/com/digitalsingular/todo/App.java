package com.digitalsingular.todo;

public class App {
	public static void main(String[] args) {
		Runnable r = () -> System.out.println("Run!");
		r.run();
	}
}
