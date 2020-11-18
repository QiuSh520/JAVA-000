package io.kimmking.springboot01.autoconfigure;

import io.kimmking.springboot01.domain.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo")
@Data
public class DemoProperties {
    private Student student123 = new Student();

    private Student student100 = new Student();
}
