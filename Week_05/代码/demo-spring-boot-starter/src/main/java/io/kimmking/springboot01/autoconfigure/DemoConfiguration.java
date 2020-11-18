package io.kimmking.springboot01.autoconfigure;

import io.kimmking.springboot01.domain.ISchool;
import io.kimmking.springboot01.domain.Klass;
import io.kimmking.springboot01.domain.School;
import io.kimmking.springboot01.domain.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@EnableConfigurationProperties(DemoProperties.class)
public class DemoConfiguration {
    private final DemoProperties properties;

    public DemoConfiguration(DemoProperties properties) {
        this.properties = properties;
    }

    @Bean("school")
    @ConditionalOnMissingBean(ISchool.class)
    public ISchool school(@Qualifier("class1") Klass klass, @Qualifier("student100") Student student) {
        School school = new School();
        school.setClass1(klass);
        school.setStudent100(student);

        return school;
    }

    @Bean("class1")
    @ConditionalOnMissingBean(Klass.class)
    public Klass class1(List<Student> students) {
        Klass klass = new Klass();
        klass.setStudents(students);
        return klass;
    }

    @Bean("student100")
    @ConditionalOnMissingBean(name = {"student100"})
    public Student student100() {
        Student student = new Student();
        student.setId(properties.getStudent100().getId());
        student.setName(properties.getStudent100().getName());
        return student;
    }

    @Bean("student123")
    @ConditionalOnMissingBean(name = {"student123"})
    public Student student123() {
        Student student = new Student();
        student.setId(properties.getStudent123().getId());
        student.setName(properties.getStudent123().getName());
        return student;
    }
}
