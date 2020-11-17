package io.kimmking.homework9_1;


import lombok.Data;

@Data
public class School implements ISchool {
    
    @Override
    public void ding(){
    
        System.out.println("school has ding, we should be ready for class");
        
    }
    
}
