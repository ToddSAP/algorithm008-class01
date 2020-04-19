package Week_01.rotate_sorted_array;

import org.junit.Assert;
import org.junit.Test;

public class RotateSortedArray {
    /**
     *   思路：
     *      1. 暴力法：开一个新数组，将后面n-k个元素依次放到新数组，再讲前k个元素接到新数组中即可。时间O(n),空间(n)。
     *      2. 分段交换法：先将整个数组反序，然后根据k将数组分成两部分，再分别反序即可。
     */


    public static void rotate_brute(int[] nums, int k) {
        int[] newNums = new int[nums.length];
        int n = nums.length;
        if (n > 0) {
            k = k > n ? k % n : k;
            System.arraycopy(nums, 0, newNums, k, n - k);
            System.arraycopy(nums, n - k, newNums, 0, k);
            System.arraycopy(newNums, 0, nums, 0, n);
        }
    }

    public static void rotate_swap(int[] nums, int k) {
        int n = nums.length, i = 0, j = n - 1, tmp;
        if (n > 0) {
            k = k > n ? k % n : k;
            //reverse entire array
            while (i < j) {
                swap(nums, i++, j--);
            }

            //reverse first n-k
            i = 0;
            j = k - 1;
            while (i < j) {
                swap(nums, i++, j--);
            }

            //reverse last k
            i = k;
            j = n - 1;
            while (i < j) {
                swap(nums, i++, j--);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test_official() {
        //Given
        int[] nums = new int[]{1,2,3,4,5,6,7};

        //When And Then for brute
        RotateSortedArray.rotate_brute(nums, 3);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for brute
        nums = new int[]{1,2,3,4,5,6,7};
        RotateSortedArray.rotate_swap(nums, 3);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});
    }

    @Test
    public void test_k_greater_than_array_length() {
        //Given
        int[] nums = new int[]{1,2,3,4,5,6,7};

        //When And Then for brute
        RotateSortedArray.rotate_brute(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for brute
        nums = new int[]{1,2,3,4,5,6,7};
        RotateSortedArray.rotate_swap(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});
    }

    @Test
    public void test_array_is_empty() {
        //Given
        int[] nums = new int[]{};

        //When And Then for brute
        RotateSortedArray.rotate_brute(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{});

        //When And Then for brute
        nums = new int[]{};
        RotateSortedArray.rotate_swap(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{});
    }
}
