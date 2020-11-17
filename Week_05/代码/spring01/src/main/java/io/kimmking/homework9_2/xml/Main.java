package io.kimmking.homework9_2.xml;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-homework9_2_xml.xml");

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
