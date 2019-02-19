package chapter18.lesson7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
    // read a file as a single String;
    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try(
            BufferedReader br = new BufferedReader(new FileReader(fileName))
        ) {
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    // Write a single file in one method call
    public static void write(String fileName, String text) {
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        while ("".equals(get(0))) {
            remove(0);
        }
    }

    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write(String fileName) {
        try (PrintWriter pw = new PrintWriter(fileName)) {
            for (String s : this) {
                pw.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String file = read("./src/main/java/chapter18/lesson7/TextFile.java");
        write("./src/main/java/chapter18/lesson7/TextFile.bak", file);
        TextFile textFile = new TextFile("./src/main/java/chapter18/lesson7/TextFile.bak");
        textFile.write("./src/main/java/chapter18/lesson7/TextFile.bak2");
        TreeSet<String> words = new TreeSet<>(new TextFile("./src/main/java/chapter18/lesson7/TextFile.bak", "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
