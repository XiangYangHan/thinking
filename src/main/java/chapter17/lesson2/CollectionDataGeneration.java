package chapter17.lesson2;

import util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionDataGeneration {

    public static void main(String[] args) {
        System.out.println(new ArrayList<>(
                CollectionData.list(
                        new RandomGenerator.String(4), 10)));
        System.out.println(new HashSet<>(
                new CollectionData<>(new RandomGenerator.Integer(100), 10)
        ));
    }
}
