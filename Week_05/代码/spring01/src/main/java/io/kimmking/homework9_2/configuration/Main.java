package io.kimmking.homework9_2.configuration;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("io.kimmking.homework9_2.configuration");

        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());

        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());

        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);

        school.ding();

        class1.dong();
    }
}
