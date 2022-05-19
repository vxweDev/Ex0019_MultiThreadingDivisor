package at.guelly.ex0019;

public class Calculator {

    public static long calculateNumberOfDivisible(long first, long last, long divisor) {
        long amount = 0;
        for(long i = first; i <= last; i++) {
            if(i % divisor == 0) {
                amount++;
            }
        }
        return amount;
    }
}
