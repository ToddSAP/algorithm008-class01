package Week_08.order_practices;

public class ArrayUtils {
    public static int[] getTestingArrayWithNonDuplicates() {
        return new int[]{4,3,8,1,0,9,5,7,2,6};
    }

    public static int[] getTestingArrayWithDuplicates() {
        return new int[]{4,3,8,1,0,9,5,0,7,2,0,6};
    }

    public static int[] getExpectedResultWithNonDuplicates() {
        return new int[]{0,1,2,3,4,5,6,7,8,9};
    }

    public static int[] getExpectedResultWithDuplicates() {
        return new int[]{0,0,0,1,2,3,4,5,6,7,8,9};
    }
}
