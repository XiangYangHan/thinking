package util;

public final class Generated {

    public static <T> T[] array(T[] arr, Generator<? extends T> generator) {
        if (null == arr) {
            return null;
        }
        if (null == generator) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generator.next();
        }
        return arr;
    }
}
