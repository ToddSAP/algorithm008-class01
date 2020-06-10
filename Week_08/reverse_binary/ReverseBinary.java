package Week_08.reverse_binary;

import org.junit.Assert;
import org.junit.Test;

public class ReverseBinary {
    public static int reverseBits(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++)
            sum += ((n >> i) & 1) << (31-i);
        return sum;
    }

    public static int reverseBits_enhance(int n) {
        //System.out.println(Integer.toBinaryString(n));
        //System.out.println(Integer.toBinaryString(n >> 16));
        //System.out.println(Integer.toBinaryString(n << 16));
        n = (n >> 16) | (n << 16);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString((n & 0xff00ff00) >> 8));
        System.out.println(Integer.toBinaryString((n & 0x00ff00ff) << 8));
        System.out.println(Integer.toBinaryString(((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8) ));
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        return n;
    }

    @Test
    public void test_official() {
        //Given
        int result = ReverseBinary.reverseBits(Integer.valueOf("00000010100101000001111010011100", 2));
        Assert.assertEquals(964176192, result);

        ReverseBinary.reverseBits_enhance(987654321);

        //When

        //Then
    }
}
