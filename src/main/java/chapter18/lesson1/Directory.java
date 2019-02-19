package chapter18.lesson1;

import util.PPrint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory {

    public static File[] local(File dir, String regex) {
        return null;
    }

    public static File[] local(String path, String regex) {
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) +
                    "\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                result.addAll(recurseDirs(item, regex));
                result.dirs.add(item);
            } else {
                try {
//                    System.out.println(item.getParentFile().getCanonicalFile());
                    System.out.println(item.getCanonicalFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(String start) {
        return walk(new File(start));
    }

    public static TreeInfo walk(File start) {
        return recurseDirs(start, ".*");
    }

    public static void main(String[] args) {
//        System.out.println(walk("./src"));
        walk("./src");
    }
}
