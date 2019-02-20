package chapter21.lesson3;

public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();

    public void calcel() {canceled = true;}

    public boolean isCanceled() { return canceled; }
}
