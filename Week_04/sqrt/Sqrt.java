package Week_04.sqrt;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

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

    public static int mySqrt_exercise(int x) {
        int left = 0, right = x, mid = 0;
        BigDecimal bx = new BigDecimal(String.valueOf(x));
        while (left <= right) {
            mid = (left + right) >>> 1;
            BigDecimal b1 = new BigDecimal(String.valueOf(mid));
            BigDecimal b2 = new BigDecimal(String.valueOf(mid));
            if (b1.multiply(b2).compareTo(bx) == 0) {
                return mid;
            } else if (b1.multiply(b2).compareTo(bx) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (left + right) >>> 1;
    }


    public static int mySqrt_exercise1(int x) {
        int left = 0, right = x / 2, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if ((long)mid*mid == x) {
                return mid;
            } else if ((long)mid*mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (left + right) >>> 1;
    }

    public static int mySqrt_exercise2(int x) {
        int left = 0, right = x, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (mid > 0) {
                if (mid == x / mid) {
                    return mid;
                } else if (mid > x / mid) {
                    right = mid - 1;
                } else {
                    left = left + 1;
                }
            } else {
                return x;
            }
        }
        return (left + right) >>> 1;
    }

    public static int mySqrt_newton(int n) {
        long x = n;
        while (x*x > n) {
           x = (x + n / x) / 2;
        }
        return (int) x;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = -1;
        result = Sqrt.mySqrt_exercise1(8);
        Assert.assertEquals(result, 2);

        result = Sqrt.mySqrt_exercise1(2);
        Assert.assertEquals(result, 1);

        result = Sqrt.mySqrt_exercise1(4);
        Assert.assertEquals(result, 2);

        result = Sqrt.mySqrt_exercise1(2147395599);
        Assert.assertEquals(result, 46339);

        result = Sqrt.mySqrt_exercise1(2147395599);
        Assert.assertEquals(result, 46339);
        //Then
    }
}
