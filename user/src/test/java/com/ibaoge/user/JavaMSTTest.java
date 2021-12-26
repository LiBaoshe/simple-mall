package com.ibaoge.user;

import com.ibaoge.user.entity.User;
import org.junit.jupiter.api.Test;
import sun.awt.SunHints;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JavaMSTTest {

    @Test
    void stringInternTest() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    @Test
    void string(){
        String str1="abc";//栈中开辟一块空间存放引用 str1，str1 指向池中 String 常量"abc"
        String str2="def";//栈中开辟一块空间存放引用 str2，str2 指向池中 String 常量"def"
        String str3=str1+str2;//栈中开辟一块空间存放引用 str3//str1+str2 通过 StringBuilder 的最后一步toString()方法返回一个新的 String 对象"abcdef"
        String str4=str1+str2;
        String str5="abc"+"def";
        System.out.println(str3==str4);
        System.out.println(str3=="abcdef");
        System.out.println(str5=="abcdef");
    }

    @Test
    void utf8Test(){
        byte[] bytes = "我爱你〇".getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));
        bytes = "龠龡龢龣龤龥".getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));

        bytes = new byte[]{(byte) 0xF0, (byte) 0xA0, (byte) 0x80, (byte) 0x80};
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    void oos() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oos_test.txt"));
        oos.writeObject(new User());
    }

    @Test
    void ois() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oos_test.txt"));
        User user = (User) ois.readObject();
        System.out.println(user);
    }

    @Test
    void pecs(){
        List<? extends B> list = new LinkedList<>();
//        list.add(new A());
//        list.add(new B());
//        list.add(new C());
        list.add(null);
        list.get(0);
        List<? super B> list1 = new LinkedList<>();
//        list1.add(new A());
        list1.add(new B());
        list1.add(new C());
        list1.get(0);

    }

    class A{}
    class B extends A{}
    class C extends B{}

    @Test
    void calc(){
        int x = 6 / 2 * (1 + 2);
        System.out.println(x);
    }
}
