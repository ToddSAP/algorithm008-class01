package Week_01.plus_one;

        import org.junit.Assert;
        import org.junit.Test;

        import java.math.BigDecimal;

public class Plus_One {
    /**
     * 思路:
     *      1. 暴力法：将数组转换成整形数字，加一后再转成数组,时间O(n), 空间O(n)
     *      2. 模拟进位法：数组从后向前遍历，如有进位则处理，直到无进位或遍历结束，时间O(n), 空间O(1)
     */

    public static int[] plusOne_brute(int[] digits) {
        StringBuilder sb = new StringBuilder();
        // convert int to string
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        // plus one
        BigDecimal bigNum = new BigDecimal(sb.toString());
        BigDecimal one = new BigDecimal("1");
        BigDecimal numWithPlusOne = bigNum.add(one);
        // convert string to int
        String[] stringWithPlusOne = numWithPlusOne.toString().split("");
        int[] result = new int[stringWithPlusOne.length];
        for (int i = 0; i < stringWithPlusOne.length; i++) {
            result[i] = Integer.parseInt(stringWithPlusOne[i]);
        }
        return result;
    }


    public static int[] plusOne_carry(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;

        return result;
    }



    @Test
    public void test_official() {
        //Given
        int[] digits = new int[]{1,2,3};

        //When And Then for brute
        int[] result = Plus_One.plusOne_brute(digits);
        Assert.assertArrayEquals(result, new int[]{1,2,4});

        //When And Then for carry
        result = Plus_One.plusOne_carry(digits);
        Assert.assertArrayEquals(result, new int[]{1,2,4});


        //Given
        digits = new int[]{4,3,2,1};

        //When And Then for brute
        result = Plus_One.plusOne_brute(digits);
        Assert.assertArrayEquals(result, new int[]{4,3,2,2});

        //When And Then for carry
        result = Plus_One.plusOne_carry(digits);
        Assert.assertArrayEquals(result, new int[]{4,3,2,2});

    }

    @Test
    public void test_zero() {
        //Given
        int[] digits = new int[]{0};

        //When And Then for brute
        int[] result = Plus_One.plusOne_brute(digits);
        Assert.assertArrayEquals(result, new int[]{1});

        //When And Then for carry
        result = Plus_One.plusOne_carry(digits);
        Assert.assertArrayEquals(result, new int[]{1});
    }

    @Test
    public void test_carry() {
        //Given
        int[] digits = new int[]{9};

        //When And Then for brute
        int[] result = Plus_One.plusOne_brute(digits);
        Assert.assertArrayEquals(result, new int[]{1,0});

        //When And Then for carry
        result = Plus_One.plusOne_carry(digits);
        Assert.assertArrayEquals(result, new int[]{1,0});
    }

    @Test
    public void test_big_num() {
        //Given
        int[] digits = new int[]{7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,9};

        //When And Then for brute
        int[] result = Plus_One.plusOne_brute(digits);
        Assert.assertArrayEquals(result, new int[]{7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,1,0});

        //When And Then for carry
        result = Plus_One.plusOne_carry(digits);
        Assert.assertArrayEquals(result, new int[]{7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,1,0});
    }
}
