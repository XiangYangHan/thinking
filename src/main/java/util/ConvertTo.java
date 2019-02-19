package util;

public class ConvertTo {

    public static int[] primitive(Integer[] arr) {
        if (null == arr) {
            return null;
        }
//        Arrays.stream(arr).mapToInt(Integer::intValue).collect(to)
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i].intValue();
        }
        return result;
    }
}
