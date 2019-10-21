package chapter17.lesson2;

import util.Generator;

import java.util.LinkedHashMap;

public class MapData<K, V> extends LinkedHashMap<K, V> {

    public MapData(Generator<Pair<K, V>> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Pair<K, V> next = gen.next();
            put(next.key, next.value);
        }
    }

    public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), genV.next());
        }
    }

    public MapData(Generator<K> genK, V value, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), value);
        }
    }

    public MapData(Iterable<K> iteK, Generator<V> genV) {
        for (K k : iteK) {
            put(k, genV.next());
        }
    }

    public MapData(Iterable<K> iteK, V value) {
        for (K k : iteK) {
            put(k, value);
        }
    }

    public static <K, V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int quantity) {
        return new MapData<>(genK, genV, quantity);
    }

    public static <K, V> MapData<K, V> map(Generator<K> genK, V value, int quantity) {
        return new MapData<>(genK, value, quantity);
    }

    public static <K, V> MapData<K, V> map(Iterable<K> iteK, Generator<V> genV) {
        return new MapData<>(iteK, genV);
    }

    public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity) { return new MapData<>(gen, quantity); }

    public static <K, V> MapData<K, V> map(Iterable<K> iteK, V value) { return new MapData<>(iteK, value); }
}

