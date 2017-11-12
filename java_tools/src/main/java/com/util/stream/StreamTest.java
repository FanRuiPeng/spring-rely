package com.util.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class StreamTest {

    public static void main(String[] args) throws IOException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 9, 8);

        Spliterator<Integer> spliterator = list.spliterator();
        Spliterator<Integer> split = null;
        split(spliterator);
        list.stream().forEach(System.out::println);
//        Spliterator<Integer> integerSpliterator = spliterator.trySplit();
//        integerSpliterator.forEachRemaining(System.out::println);



        //static
//        Stream.Builder<Integer> builder = Stream.builder();
//        builder.accept(56);
//        Stream.generate(() -> Math.random()).forEach(System.out::println);
//        Stream.iterate(9, a -> a * a).limit(10).forEach(System.out::println);
//        builder.add(1).add(2).add(3).add(4).add(4).build().forEach(System.out::println);
//        Stream.concat(builder.add(1).add(2).add(3).add(4).add(4).build(), list.stream()).forEach(System.out::println);
//                .forEach(System.out::println);
//        builder.andThen(System.out::println);

        //flatMap
//        Stream<String> lines = Files.lines(Paths.get("C:\\Users\\BMF\\Desktop\\file\\resources.xml"), StandardCharsets.UTF_8);
//        Stream<String> rStream = lines.flatMap(line -> Stream.of(line.split("/")));
//        rStream.forEach(System.out::println);
//                .forEach(System.out::println);
//        out.println(new String(Files.readAllBytes(Paths.get("C:\\\\Users\\\\BMF\\\\Desktop\\\\file\\\\resources.xml"))));
//        stream(list);


    }

    private static void split(Spliterator<Integer> spliterator) {
        Spliterator<Integer> split;
        while ((split = spliterator.trySplit()) != null) {
        split.forEachRemaining(a -> {
            System.out.println("element is : " + a + " remaind : " + spliterator.estimateSize() +
            " character : " + spliterator.characteristics() + " extra : " + spliterator.getExactSizeIfKnown());
        });
        System.out.println("------------------------");
        }
    }

    private static void stream(List<Integer> list) {
        //filter
//        List<Double> collect =
//        Double[] doubles =
//        Double reduce =
//        Long collect =
//        Double aDouble =
//        long count =
//        boolean b =
        Double aDouble = list.stream().parallel().filter(a -> a > 4)
//                .sorted((a, b) -> b -a)
                .skip(1)
//                .limit(3)
                .peek(out::println)
                .distinct()
                .map(m -> Math.pow(m, 2))
//                .sorted()
//                .limit(2)
                .peek(out::println)
//                .reduce(0.0, Double::sum, Double::sum);
//        .collect(ArrayList<Double>::new, ArrayList::add, ArrayList::addAll);
//                .reduce(Double::sum).get();
//                .toArray();
//                .toArray(Double[]::new);
//                .max((a, b) -> Double.valueOf(a - b).intValue()).get();
//                .count();
//                .anyMatch(p -> p > 50);
//                .allMatch(p -> p > 50);
//                .noneMatch(p -> p > 500);
                .findFirst().get();
//        .findAny().get();
//                .collect(Collectors.counting());
//        out.println("final is : " + reduce);
//        out.println("size is : " + doubles.length);
//        out.println("size is : " + collect.size());
        out.println("size is : " + aDouble);
    }
}
