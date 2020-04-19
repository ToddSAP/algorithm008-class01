package Week_01.remove_duplicated_from_sorted_array;

import org.junit.Assert;
import org.junit.Test;

public class RemoveDuplicatedFromSortedArray {
    /**
     * 思路：
     *      1. 暴力法：先遍历找出所有不同的元素，再讲元素赋值回原数组。时间O(n),空间O(n)。不符合题目要求。
     *      2. 双指针法：一个指针指向最后一个不重复元素，一个指针指向下一个待遍历元素，两者相同则跳过，不同则赋值。时间O(n),空间O(1)。
     */


    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i+1;
    }

    @Test
    public void test_official() {
        //Given
        int[] nums = new int[]{1,1,2};

        //When And Then
        int result = RemoveDuplicatedFromSortedArray.removeDuplicates(nums);
        Assert.assertTrue(result == 2);

        //Given
        nums = new int[]{0,0,1,1,1,2,2,3,3,4};

        //When And Then
        result = RemoveDuplicatedFromSortedArray.removeDuplicates(nums);
        Assert.assertTrue(result == 5);
    }

    @Test
    public void test_empty() {
        //Given
        int[] nums = new int[]{};

        //When
        int result = RemoveDuplicatedFromSortedArray.removeDuplicates(nums);

        //Then
        Assert.assertTrue(result == 0);
    }

    @Test
    public void test_only_one_element() {
        //Given
        int[] nums = new int[]{1};

        //When
        int result = RemoveDuplicatedFromSortedArray.removeDuplicates(nums);

        //Then
        Assert.assertTrue(result == 1);
    }
}
