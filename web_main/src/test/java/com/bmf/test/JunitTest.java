package com.bmf.test;

import com.bmf.tools.Man;
import com.bmf.tools.SubMan;
import com.bmf.tools.Week;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class JunitTest {

    @Test
    public void test() {
//        List<String>[] lists = new List[1];
//        List<Integer> integers = Arrays.asList(12);
//        int a = 1;
//        integers.get(--a);
//        System.out.println(a);
//        Object[] objects = lists;
//        objects[0] = integers;
//        String s = lists[0].get(0);
//        System.out.println(s);

//        Man[] men = new Man[2];
//        men[0] = new Man();
//        men[1] = new SubMan();
//        Stream.of(men).parallel().forEach(System.out::println);
//        for(Man m: men) {
//            System.out.println(m);
//        }

        Runnable runnable = () -> Week.values();
        Executors.newSingleThreadExecutor().submit(runnable);
        System.out.println(Week.Fri.ordinal());

//        Text text = new Text();
//        text.applyStyles(1);
        EnumSet es = EnumSet.allOf(Text.class);
        boolean contains = es.contains(Text.Style_1);
        System.out.println(contains);

        EnumMap<Text, Object> stringObjectEnumMap = new EnumMap<Text, Object>(Text.class);
        boolean b = stringObjectEnumMap.containsKey(Text.Style_1);
//        stringObjectEnumMap.put()
        System.out.println(b);


    }



    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
