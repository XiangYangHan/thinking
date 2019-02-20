package chapter21.lesson3;

import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed() {
        boolean tryLock = lock.tryLock();
        try {
            System.out.println("tryLock: " + tryLock);
        } finally {
            if (tryLock) {
                lock.unlock();
            }
        }
    }
    public void timed() {
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(10, SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(10, SECONDS): " + tryLock);
        } finally {
            if (tryLock) {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();
        new Thread() {
            {setDaemon(true);}
            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.sleep(100);
//        Thread.yield();
        System.out.println(System.currentTimeMillis());
        al.untimed();
        al.timed();
        System.out.println(System.currentTimeMillis());
    }


}
