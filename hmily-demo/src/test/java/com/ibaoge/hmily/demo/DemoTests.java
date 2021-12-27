package com.ibaoge.hmily.demo;

import org.junit.jupiter.api.Test;

import java.io.*;

public class DemoTests implements Serializable{

    @Test
    void print() throws IOException {
        System.out.println("before System.out.close()");
//        System.out.close();
        System.out.println("after System.out.close()");
        System.out.println(System.out.checkError());
//        InputStream in = System.in;
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oos"));
//        oos.writeObject(new DemoTests());
//        try (FilterOutputStream fis = new PrintStream(System.out)) {
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
