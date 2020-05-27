package Week_04.sqrt1;

import org.junit.Assert;
import org.junit.Test;

public class PerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int left = 0, right = num, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if ((long)mid*mid == num) {
                return true;
            } else if ((long)mid*mid > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    @Test
    public void test_official() {
        //Given

        //When
        boolean result = PerfectSquare.isPerfectSquare(16);

        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void test_corner() {
        //Given

        //When
        boolean result = PerfectSquare.isPerfectSquare(14);
        Assert.assertTrue(!result);

        result = PerfectSquare.isPerfectSquare(1);
        Assert.assertTrue(!result);

    }
}
