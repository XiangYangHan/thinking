package chapter18.lesson8;

import java.io.PrintWriter;

public class ChangeSystemOut {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.println("hello, world!");
//        printWriter.close();
    }
}
