package chapter18.lesson7;

import java.io.*;

public class BinaryFile {
    public static byte[] read(File file) {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] data = new byte[bis.available()];
            bis.read(data);
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] read(String fileName) {
        return read(new File(fileName));
    }

    public static void main(String[] args) throws IOException {
        char[] chars = "0123456789ABCDEF".toCharArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(read("./target/classes/chapter18/lesson7/BinaryFile.class"));
        byte[] CAFEBABE = new byte[8];
        byteArrayInputStream.read(CAFEBABE);
        StringBuilder sb = new StringBuilder();
        for (byte b : CAFEBABE) {
            sb.append(chars[(b >>> 4) & 0x0f]);
            sb.append(chars[b & 0x0f]);
        }
        System.out.println(sb.toString());
    }

}


