package chapter21.lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(new WaxOn(car));
//        exec.execute(new WaxOff(car));
        MyCar myCar = new MyCar();
        exec.execute(()->{
            try {
                while (!Thread.interrupted()) {
//                    myCar.waxing();
                    myCar.waxOn();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On task");
        });
        exec.execute(()->{
            try {
                while (!Thread.interrupted()) {
//                    myCar.buffing();
                    myCar.waxOff();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax off task");
        });
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}

class MyCar {
    private boolean waxOn = false;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void waxOn() throws InterruptedException {
        lock.lock();
        try {
            System.out.print("Start waxOn... ");
            Thread.sleep(100);
            waxOn = true;
            System.out.println("complete waxOn!");
            condition.signalAll();
            while (waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void waxOff() throws InterruptedException {
        lock.lock();
        try {
            System.out.print("Start waxOff... ");
            Thread.sleep(100);
            waxOn = false;
            System.out.println("complete waxOff");
            condition.signalAll();
            while (!waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }


    public synchronized void waxing() throws InterruptedException {
        System.out.print("Start waxOn... ");
        Thread.sleep(100);
        waxOn = true;
        System.out.println("complete waxOn!");
        notifyAll();
        while (waxOn) wait();
    }
//    public void waxOn(){}
    public synchronized void buffing() throws InterruptedException {
        System.out.print("Start waxOff... ");
        Thread.sleep(100);
        waxOn = false;
        System.out.println("complete waxOff");
        notifyAll();
        while (!waxOn) wait();
    }
}

class Car {
    private boolean waxOn = false;
    public synchronized void waxed() { waxOn = true; notifyAll();}
    public synchronized void buffed() { waxOn = false; notifyAll();}
    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) wait();
    }
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    public WaxOn(Car car) {this.car = car;}

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(100);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car car) { this.car = car; }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(100);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}