package Week_09.atoi;

import org.junit.Assert;
import org.junit.Test;

public class AtoI {
    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int start = 0;
        // trim前部
        for (int i = 0; i < str.length(); i++) if (str.charAt(i) == ' ') start++; else break;
        if (start == str.length()) return 0;
        boolean isPositive = true;
        int validStrStart = start, validStrEnd = start;
        // 判断符号
        if (str.charAt(start) == '-') {
            isPositive = false;
            validStrStart++; validStrEnd++;
        } else if (str.charAt(start) == '+') {
            validStrStart++; validStrEnd++;
        } else if (str.charAt(start) < '0' || str.charAt(start) > '9') {
            return 0;
        }
        // 截取有效数字
        for (int i = validStrStart; i < str.length(); i++) {
            // 遇到无效数字
            if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
            else validStrEnd++;
        }
        // 处理数字
        int result = 0, digit;
        for (int i = validStrStart; i < validStrEnd; i++) {
            digit = str.charAt(i)-'0';
            if ((Integer.MAX_VALUE - digit) / 10 < result) return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            result = result*10 + digit;
        }
        return isPositive ? result : -result;
    }

    @Test
    public void test_official() {
        //Given
        /*int result = AtoI.myAtoi("  -232hsdfl 134");
        Assert.assertEquals(-232, result);

        result = AtoI.myAtoi("  2 32hsdfl 134");
        Assert.assertEquals(2, result);

        result = AtoI.myAtoi("w");
        Assert.assertEquals(0, result);*/

        /*int result = AtoI.myAtoi("   37329472937492346234792374932");
        Assert.assertEquals(Integer.MAX_VALUE, result);*/

        int result = AtoI.myAtoi(" ");
        Assert.assertEquals(0, result);

        //When

        //Then
    }
}
