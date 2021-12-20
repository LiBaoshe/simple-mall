package com.ibaoge.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    private int id;
    private String name;
    private String key;

    public Person(int id, String name){
        this.id = id;
        this.name = name;
        this.key = id + name + id;
    }
}
