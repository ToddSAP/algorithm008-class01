package Week_08.count_for_1;

import org.junit.Assert;
import org.junit.Test;

public class CountFor1 {
    public static int hammingWeight(int n) {
        if (n == 0) return 0;
        int mask = 1, cnt = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            if ((n & mask) != 0) cnt++;
            mask <<= 1;
        }
        return cnt;
    }

    public static int hammingWeight_enhance(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt++;
            n &= n - 1;
        }
        return cnt;
    }

    @Test
    public void test_official() {
        //Given
        int result = CountFor1.hammingWeight(127);
        Assert.assertEquals(7, result);


        result = CountFor1.hammingWeight_enhance(127);
        Assert.assertEquals(7, result);
        //When

        //Then
    }
}
