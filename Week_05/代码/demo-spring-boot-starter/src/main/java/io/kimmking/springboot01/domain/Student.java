package io.kimmking.springboot01.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Student implements Serializable {

    private int id = -1;
    private String name = "null";

    public void init() {
        System.out.println("hello...........");
    }

    public Student create() {
        return new Student(101, "KK101");
    }
}
