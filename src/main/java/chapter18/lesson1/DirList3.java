package chapter18.lesson1;

import java.io.File;
import java.util.Arrays;

public class DirList3 {

    public static void main(String[] args) {
        File path = new File("./src/main/java/chapter18/lesson1");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list((dir, name) -> name.matches(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
