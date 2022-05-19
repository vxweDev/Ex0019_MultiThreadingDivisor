package at.guelly.ex0019;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class UI {

    private static final long MAX_NUMBER = 3000000000l;

    private static final long DIVISOR = 3;

    public static void main(String[] args) {
        Instant startTime = Instant.now();
        long x1 = Calculator.calculateNumberOfDivisible(0, MAX_NUMBER, DIVISOR);
        Instant endTime = Instant.now();
        long timeNeeded = Duration.between(startTime, endTime).toMillis();
        System.out.println("Result               : " + x1 + " calculated in " + timeNeeded + " ms");

        startTime = Instant.now();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<FutureTask<Long>> taskList = new ArrayList<>();

        Callable<Long> task1 = () ->
                Calculator.calculateNumberOfDivisible(0, MAX_NUMBER / 2, DIVISOR);
        FutureTask<Long> ft1 = new FutureTask<>(task1);
        taskList.add(ft1);
        executor.execute(ft1);

        Callable<Long> task2 = () ->
                Calculator.calculateNumberOfDivisible(MAX_NUMBER / 2 + 1, MAX_NUMBER, DIVISOR);
        FutureTask<Long> ft2 = new FutureTask<>(task2);
        taskList.add(ft2);
        executor.execute(ft2);

        long x2 = 0;
        for(FutureTask<Long> ft : taskList) {
            try {
                x2 = x2 + ft.get();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        endTime = Instant.now();
        timeNeeded = Duration.between(startTime, endTime).toMillis();
        System.out.println("Result               : " + x1 + " calculated in " + timeNeeded + " ms");
    }
}
