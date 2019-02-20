package chapter21.lesson3;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator{
    private AtomicInteger ai = new AtomicInteger(0);

    @Override
    public int next() { return ai.getAndAdd(2); }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
