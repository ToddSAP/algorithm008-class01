package Week_08.binary_convert;

import org.junit.Assert;
import org.junit.Test;

public class BinaryConvert {
    public static String convert(int n) {
        boolean isPositive = n >= 0;
        StringBuilder sb = new StringBuilder();
        if (!isPositive) n = n + Integer.MAX_VALUE + 1;
        while (n > 0) {
            if ((n & 1) == 0) sb.append("0");
            else sb.append("1");
            n /= 2;
        }
        return isPositive ? sb.reverse().toString() : sb.reverse().insert(0, "1").toString();
    }

    @Test
    public void test_official() {
        //Given
        Assert.assertEquals(Integer.toBinaryString(93), BinaryConvert.convert(93));
        Assert.assertEquals(Integer.toBinaryString(127), BinaryConvert.convert(127));
        Assert.assertEquals(Integer.toBinaryString(-77), BinaryConvert.convert(-77));
        Assert.assertEquals(Integer.toBinaryString(-88), BinaryConvert.convert(-88));
        Assert.assertEquals(Integer.toBinaryString(-999), BinaryConvert.convert(-999));
        Assert.assertEquals(Integer.toBinaryString(2147483570), BinaryConvert.convert(2147483570));


        //When

        //Then
    }
}
