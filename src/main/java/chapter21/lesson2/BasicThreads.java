package chapter21.lesson2;

public class BasicThreads {

    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff(5));
        t.start();
        System.out.println("waiting for LiftOff()");
    }
}
