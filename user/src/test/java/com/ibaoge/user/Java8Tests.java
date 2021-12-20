package com.ibaoge.user;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Java8Tests {

    @Test
    void testDup1(){
        String str = "Kobe Is is The the best player In in Basketball basketball game .";
        List<String> list = Arrays.asList(str.split("\\s"));
        list.stream().distinct().forEach(s -> System.out.print(s+" "));
    }

    @Test
    void testDup2(){
        // just for constructing a sample list
        String str = "Kobe Is is The the best player In in Basketball basketball game .";
        List<String> list = new ArrayList<>(Arrays.asList(str.split("\\s")));

        // the actual operation
        TreeSet<String> seen = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        list.removeIf(s -> !seen.add(s));

        // just for debugging
        System.out.println(String.join(" ", list));
    }

    @Test
    void testDup3(){
        String input = "Kobe Is is The the best player In in Basketball basketball game .";
        String output = input.replaceAll("(?i)\\b(\\w+)\\s+\\1\\b", "$1");

        System.out.println(output);
    }

    @Test
    void testDup4(){
        String str = "Kobe Is is The the best player In in Basketball basketball game .";
        String result = Pattern.compile("\\s")
                .splitAsStream(str)
                .collect(Collectors.collectingAndThen(Collectors.toMap(String::toLowerCase,
                        Function.identity(),
                        (l, r) -> l,
                        LinkedHashMap::new),
                        m -> String.join(" ", m.values())));
        System.out.println(result);
    }

    @Test
    void testDup5(){
        String str = "aa aA AA";

        // 集合流 转 Map流 Collectors.toMap 方法
        Collector<String, ?, Map<String, String>> collector = Collectors.toMap(
                String::toLowerCase, // 这个是 K
                String::toString, // 这个是 U
                (oldVal, val) -> oldVal, // 这个是 BinaryOperator<U>, BiFunction<T, U, R> 的子类，
                // BiFunction<T, U, R> 中的方法是 R apply(T t, U u)，把 t 和 u 合并后的结果返回。
                // BinaryOperator<U> 内部真实调用：remappingFunction.apply(oldValue, value)，
                // 具体含义是，当转换过程中 map 的 key 重复时，需要如何处理。
                // 多线程合并应该是内部的 mapMerger 实现的，调用者不用关心并行问题
                LinkedHashMap::new // 这里是 map M extends Map<K, U>，必须是 Map 的子类
        );


        String result = Pattern.compile("\\s")
                .splitAsStream(str)
                .collect(Collectors.collectingAndThen(collector,
                        m -> String.join(" ", m.values())));
        System.out.println(result);
    }
}
