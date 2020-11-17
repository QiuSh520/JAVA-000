package io.kimmking.homework9_2.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfiguration {

    @Bean("student123")
    public Student student123() {
        return new Student(123, "KK123");
    }

    @Bean("student100")
    public Student student100() {
        return new Student(100, "KK100");
    }

    @Bean("class1")
    public Klass class1() {
        Klass klass = new Klass();
        List<Student> students = new ArrayList<>();
        students.add(student100());
        students.add(student123());
        klass.setStudents(students);

        return klass;
    }

    @Bean("school")
    public ISchool school(){
        return new School();
    }
}
