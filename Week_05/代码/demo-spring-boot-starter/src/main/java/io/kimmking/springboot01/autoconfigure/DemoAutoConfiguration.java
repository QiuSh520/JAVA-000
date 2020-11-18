package io.kimmking.springboot01.autoconfigure;

import io.kimmking.springboot01.domain.ISchool;
import io.kimmking.springboot01.domain.Klass;
import io.kimmking.springboot01.domain.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DemoConfiguration.class)
@ConditionalOnClass({ISchool.class, Student.class, Klass.class})
@ConditionalOnProperty(prefix = "demo", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DemoAutoConfiguration {

}
