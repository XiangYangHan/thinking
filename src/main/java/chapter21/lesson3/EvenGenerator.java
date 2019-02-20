package chapter21.lesson3;

public class EvenGenerator extends IntGenerator {

    private int currentValue = 0;
    @Override
    public synchronized int next() {
        currentValue++;
        currentValue++;
        return currentValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
