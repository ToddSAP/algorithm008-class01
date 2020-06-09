package Week_08.power_of_two;

import org.junit.Assert;
import org.junit.Test;

public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        int cnt = 0, c = n;
        while (n != 0) {
            cnt++;
            n &= n - 1;
        }
        return c > 0 && cnt == 1;
    }

    @Test
    public void test_official() {
        //Given
        boolean result = PowerOfTwo.isPowerOfTwo(128);
        Assert.assertTrue(result);

        result = PowerOfTwo.isPowerOfTwo(127);
        Assert.assertFalse(result);

        result = PowerOfTwo.isPowerOfTwo(-2147483648);
        Assert.assertFalse(result);

        //When

        //Then
    }
}
