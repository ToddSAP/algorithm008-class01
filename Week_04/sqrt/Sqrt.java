package Week_04.sqrt;

import org.junit.Assert;
import org.junit.Test;

public class Sqrt {
    public static int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1, right = x;
        while (left < right) {
            int mid = (left+right) >>> 1;
            if (mid*mid < x) left = mid;
            else right = mid-1;
        }
        if (left*left == x) return left;
        else return left-1;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = -1;
        /*result = Sqrt.mySqrt(8);
        Assert.assertEquals(result, 2);*/

        /*result = Sqrt.mySqrt(2);
        Assert.assertEquals(result, 1);*/

        /*result = Sqrt.mySqrt(4);
        Assert.assertEquals(result, 2);*/

        result = Sqrt.mySqrt(2147395599);
        Assert.assertEquals(result, 46339);
        //Then
    }
}
