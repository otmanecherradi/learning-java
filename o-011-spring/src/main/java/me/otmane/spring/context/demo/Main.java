package me.otmane.spring.context.demo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "me.otmane.spring.context.demo")
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        IController conroller = applicationContext.getBean("CONTROLLER_IMPLEMENTATION_1", IController.class);
        conroller.callService();
    }
}