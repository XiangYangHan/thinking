package util;

@FunctionalInterface
public interface Generator<T> {

    T next();

}
