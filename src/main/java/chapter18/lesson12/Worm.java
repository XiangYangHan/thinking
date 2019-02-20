package chapter18.lesson12;

import java.io.*;
import java.util.Random;

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private Worm next;
    private char c;

    public Worm(int i, char c) {
        System.out.println("Worm constructor: " + i);
        this.c = c;
        if (i > 0) {
            next = new Worm(--i, (char) (c + 1));
        }
    }
    public Worm() {
        System.out.println("Default constructor!");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(":");
        sb.append(c);
        sb.append("(");
        for (Data data : d) {
            sb.append(data);
        }
        sb.append(")");
        if (next != null) {
            sb.append(next);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/java/chapter18/lesson12/worm.out"));
        oos.writeObject("Worm storage\n");
        oos.writeObject(w);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/java/chapter18/lesson12/worm.out"));
        String s = (String) ois.readObject();
        Worm w2 = (Worm) ois.readObject();
        System.out.println(s + "w2 = " + w2);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(baos);
        oos2.writeObject("Worm storage\n");
        oos2.writeObject(w2);
        oos2.close();
        ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        String s2 = (String) ois2.readObject();
        Worm w3 = (Worm) ois2.readObject();
        System.out.println(s2 + "w3 = " + w3);
    }
}
