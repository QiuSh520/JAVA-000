package io.kimmking.homework9_2.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Klass { 

    @Autowired
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
