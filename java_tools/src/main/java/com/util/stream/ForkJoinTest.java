package com.util.stream;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = Stream.iterate(1, a -> a + 1)
                .limit(10000)
//                .peek(System.out::println)
                .collect(Collectors.toList());

        Integer integer1 = list.stream().reduce(Integer::sum).get();
        System.out.println(" traditional : " + integer1);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Sum sum = new Sum(0, 10000);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(sum);
        Integer integer = submit.get();
        System.out.println("forkJoin : " + integer);
    }

    static class Sum extends RecursiveTask<Integer> {

        private static final int SIZE = 10;

        private int start, end;

        public Sum(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            int trueSize = end - start;
            boolean can = trueSize < SIZE;
            if (can) {
                sum = Stream.iterate(start, a -> a + 1).limit(trueSize + 1).peek(System.out::print).reduce(Integer::sum).get();
            } else {
                int middle = (start + end) >>> 1;
                Sum leftSum = new Sum(start, middle);
                Sum rightSum = new Sum(middle + 1, end);
                leftSum.fork();
                rightSum.fork();

                Integer left = leftSum.join();
                Integer right = rightSum.join();
                sum = left + right;
            }
            System.out.println("--------Thread ID is : " + Thread.currentThread().getId());
            return sum;
        }
    }
}
