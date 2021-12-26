package com.ibaoge.user;

import com.ibaoge.user.entity.User;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionSortTests {

    @Test
    void linkedHashMapTest(){
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();


        List list = new LinkedList();
        Set set = new HashSet();
        Collections.sort(list);
//        Collections.sort(set);

        TreeSet treeSet = new TreeSet((o1, o2) -> 0);

        TreeMap treeMap = new TreeMap(((o1, o2) -> 0));


        treeSet.add(new User());


    }
}
