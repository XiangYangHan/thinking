package chapter21.lesson6;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedDeadlock {
    public static void main(String[] args) throws IOException {
        int size = 5;
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                exec.execute(new Philosopher(chopsticks[(i + 1) % size], chopsticks[i], i, 2));
            } else {
                exec.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i, 2));
            }
        }
        System.out.println("Press 'Enter' to quit");
        System.in.read();
    }
}
