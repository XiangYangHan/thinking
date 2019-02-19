package chapter21.lesson2;

public class LiftOff implements Runnable {
    private static int taskCount = 0;

    protected int countDown = 10;
    private final int id = taskCount++;

    public LiftOff() {}

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + ")  ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
