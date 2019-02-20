package chapter21.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CriticalSection {
    static void testApproaches(PairManager p1, PairManager p2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator
                pm1 = new PairManipulator(p1),
                pm2 = new PairManipulator(p2);
        PairChecker
                pc1 = new PairChecker(p1),
                pc2 = new PairChecker(p2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pc1);
        exec.execute(pc2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                p1 = new PairManager1(),
                p2 = new PairManager2();
        testApproaches(p1, p2);
    }
}

class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x; this.y = y;
    }
    public Pair() {this(0, 0);}

    public int getX() {return x;}
    public int getY() {return y;}
    public void incrementX() {x++;}
    public void incrementY() {y++;}
    public String toString() {return "x: " + x + ", y: " + y;}

    public void checkState() {
        if (x != y) throw new RuntimeException("Pair values not equal: " + this);
    }
}

abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger();
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    public synchronized Pair getPair() {return new Pair(p.getX(), p.getY());}

    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) { }
    }

    public abstract void increment();
}

class PairManager1 extends PairManager {
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementY();
            p.incrementX();
            temp = getPair();
        }
        store(temp);
    }
}

class PairManipulator implements Runnable {
    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) pm.increment();
    }

    @Override
    public String toString() {
        return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
