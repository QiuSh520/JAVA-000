package io.kimmking.springboot01;

import io.kimmking.springboot01.domain.ISchool;
import io.kimmking.springboot01.domain.Klass;
import io.kimmking.springboot01.domain.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

		Student student123 = (Student) applicationContext.getBean("student123");
		System.out.println(student123.toString());

		Student student100 = (Student) applicationContext.getBean("student100");
		System.out.println(student100.toString());

		Klass class1 = applicationContext.getBean(Klass.class);
		System.out.println(class1);

		ISchool school = applicationContext.getBean(ISchool.class);
		System.out.println(school);

		school.ding();

		class1.dong();
	}

}
