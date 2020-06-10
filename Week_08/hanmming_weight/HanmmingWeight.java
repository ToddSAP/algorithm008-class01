package Week_08.hanmming_weight;

import org.junit.Assert;
import org.junit.Test;

public class HanmmingWeight {
    @Test
    public void test_official() {
        int i = 54321;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString( 0x55555555));
        System.out.println(Integer.toBinaryString(i & 0x55555555));
        System.out.println(Integer.toBinaryString((i >> 1) & 0x55555555));
        System.out.println(Integer.toBinaryString((i & 0x55555555) + ((i >> 1) & 0x55555555)));
        int result = hanmmingWeightCount(i);
        Assert.assertEquals(7, result);
    }

    public static int hanmmingWeightCount(int n) {
        n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        n = (n & 0x0F0F0F0F) + ((n >> 4) & 0x0F0F0F0F);
        n = (n + (n >> 8) + (n >> 16) + (n >> 24)) & 0x0000000F;
        //n = (n * 0x01010101) >> 24;
        return n;
    }
}
