package Week_01.move_zeros;

import org.junit.Assert;
import org.junit.Test;

public class MoveZero {
    /**
     * 思路：
     *      1. 暴力法：开一个数组，将非0元素添加到该数组并记录0元素的个数，遍历结束后，在数组后面补相应个数的0
     *                时间O(n),空间O(n)
     *                该解法不符合题意
     *      2. 分区法：将数组分成0和非0两个区间，两个指针j, i分别指向0区的第一个元素和遍历的下一个元素，如果i位置上的元素非0，
     *                则和j位置上的元素交换,直到遍历结束
     *                时间O(n),空间O(1)
     */


    public static int[] moveZeroes_brute(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int indexOfNewArray = 0;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                result[indexOfNewArray++] = nums[i];
            }
        }

        while (indexOfNewArray < nums.length) {
            result[indexOfNewArray++] = 0;
        }
        return result;
    }

    public static void moveZeroes_section(int[] nums) {
        int indexOfFirstElementInZeroSection = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // swap only when two points point different element
                if (i > indexOfFirstElementInZeroSection) {
                    int tmp = nums[indexOfFirstElementInZeroSection];
                    nums[indexOfFirstElementInZeroSection] = nums[i];
                    nums[i] = tmp;
                }

                indexOfFirstElementInZeroSection++;
            }
        }
    }

    @Test
    public void test_official() {
        //Given
        int[] nums = new int[]{0,1,0,3,12};

        //When And Then for brute
        int[] result = MoveZero.moveZeroes_brute(nums);
        Assert.assertArrayEquals(result, new int[]{1,3,12,0,0});

        //When And Then for section
        MoveZero.moveZeroes_section(nums);
        Assert.assertArrayEquals(nums, new int[]{1,3,12,0,0});
    }

    @Test
    public void test_empty() {
        //Given
        int[] nums = new int[]{};

        //When And Then for brute
        int[] result = MoveZero.moveZeroes_brute(nums);
        Assert.assertArrayEquals(result, new int[]{});

        //When And Then for section
        MoveZero.moveZeroes_section(nums);
        Assert.assertArrayEquals(nums, new int[]{});
    }

    @Test
    public void test_all_zeros() {
        //Given
        int[] nums = new int[]{0,0,0,0,0};

        //When And Then for brute
        int[] result = MoveZero.moveZeroes_brute(nums);
        Assert.assertArrayEquals(result, new int[]{0,0,0,0,0});

        //When And Then for section
        MoveZero.moveZeroes_section(nums);
        Assert.assertArrayEquals(nums, new int[]{0,0,0,0,0});
    }

    @Test
    public void test_without_zeros() {
        //Given
        int[] nums = new int[]{5,4,3,2,1};

        //When And Then for brute
        int[] result = MoveZero.moveZeroes_brute(nums);
        Assert.assertArrayEquals(result, new int[]{5,4,3,2,1});

        //When And Then for section
        MoveZero.moveZeroes_section(nums);
        Assert.assertArrayEquals(nums, new int[]{5,4,3,2,1});
    }
}
