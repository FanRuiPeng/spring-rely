package com.bmf.test;

import com.bmf.tools.Man;
import com.bmf.tools.SubMan;
import com.bmf.tools.Week;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Test
    public void ResourceTest() throws IOException, EncoderException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        UrlResource urlResource = new UrlResource(new URL("http://zhuangdan.oss-cn-beijing.aliyuncs.com/video/shilian33tian.mp4"));
//        File file = urlResource.getFile();
//        if(file.exists()) {
//            MultimediaInfo info = new Encoder().getInfo(file);
//            System.out.println(info.toString());
//        }
        Class<?> aClass = this.getClass().getClassLoader().loadClass("D:\\IDEAworkspace\\main\\build\\classes\\java\\main\\com\\code_space\\support\\im\\rong\\util\\ArraySplit.class");
        Object o = aClass.newInstance();
    }


    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
