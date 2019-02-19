package util;

import java.util.Arrays;
import java.util.Collection;

public class PPrint {

    public static String pformat(Collection<?> collection) {
        if (null == collection) {
            return "";
        }
        if (collection.size() == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (Object object : collection) {
            if (collection.size() != 1) {
                sb.append("\n\t");
            }
            sb.append(object);
        }
        if (collection.size() != 1) {
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void pprint(Collection<?> collection) {
        System.out.println(pformat(collection));
    }

    public static void pprint(Object[] objects) {
        pprint(Arrays.asList(objects));
    }
}
