package io.kimmking.homework9_2.annotation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("io.kimmking.homework9_2.annotation");

        Student student =  context.getBean(Student.class);
        System.out.println(student.toString());

        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);

        school.ding();

        class1.dong();
    }
}
