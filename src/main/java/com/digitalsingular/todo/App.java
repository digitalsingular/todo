package com.digitalsingular.todo;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		String defaultMessage = ctx.getMessage("message", null, "", null);
		System.out.printf("El mensaje con las locales del sistema es %s%n", defaultMessage);
		String englishMessage = ctx.getMessage("message", null, "", Locale.ENGLISH);
		System.out.printf("El mensaje en inglés es %s%n", englishMessage);
		String spanishMessage = ctx.getMessage("message", null, "", new Locale("es"));
		System.out.printf("El mensaje en español es %s%n", spanishMessage);
	}
}
