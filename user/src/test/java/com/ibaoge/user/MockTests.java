package com.ibaoge.user;

import javafx.scene.effect.Bloom;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class MockTests {

    @Test
    void mockTest() {

        Person mockPerson = mock(Person.class);
        mockPerson.setId(1);
        mockPerson.setName("TestOps");
        verify(mockPerson).setId(1);
        verify(mockPerson).setName("TestOps");
    }

    @Test
    void stubTest(){
        Person mockPerson = mock(Person.class);
        when(mockPerson.getId()).thenReturn(1);
//        when(mockPerson.getName()).thenThrow(new NoSuchMethodError());
        HashMap hashMap = new HashMap();
        hashMap.put(1,1);

        System.out.println(mockPerson.getId());
        System.out.println(mockPerson.getName());
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 用hash映射数组，提高查找效率
        Map<Integer, Integer> arr2Map = new HashMap<>();
        for(int i = 0; i < arr2.length; i++){
            arr2Map.put(arr2[i], i);
        }

        Integer[] arr = Arrays.stream(arr1).boxed().toArray(Integer[]::new);

        Arrays.sort(arr, (a, b) -> {
            int ai = arr2Map.getOrDefault(a, arr2.length);
            int bi = arr2Map.getOrDefault(b, arr2.length);
            return ai == bi ? a - b : ai - bi;
        });
        return Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
    }

    @Test
    void strDistinct(){
        String[] tests = {"aa", "Bb", "bb", "bB", "cc", "CC"};
        String[] strs = new String[10000];
        Arrays.stream(strs).map((s) -> s = tests[0]);
        for (int i = 0; i < strs.length; i++) {
            strs[i] = tests[i % tests.length];
        }
        System.out.println("原字符串：" + Arrays.toString(strs));

        Map<String, String> map = new HashMap<>();
        Arrays.stream(strs).parallel().forEach(s -> map.put(s.toLowerCase(), s));
        strs = map.values().toArray(new String[0]);

        System.out.println("去重后：" + Arrays.toString(strs));
    }

    @Test
    void testBloom(){
        Bloom bloom = new Bloom();
    }
}
