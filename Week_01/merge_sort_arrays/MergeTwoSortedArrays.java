package Week_01.merge_sort_arrays;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MergeTwoSortedArrays {
    /**
     * 思路：
     *      1. 暴力法：两层循环，合并两个数组到新数组中,时间O(m+n),空间O(max(m,n))
     *      2. 排序法：按题意，将num2接到num1后面，再排序num1,时间((m+n)log(m+n)),空间O(1)
     *      3. 优化排序法：二分查找，找到两个数组的交集部分，只对交集做merge, 时间O(m+n), 空间O(max(m,n))
     *      4. 官方-双指针：双指针，分别指向数组最大元素，比较后将大的那个放入长数组的最后。时间O(m+n),空间O(1)
     */

    public static void merge_sort(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    public static void merge_double_point_backward(int[] nums1, int m, int[] nums2, int n) {
        int indexOfNum1 = m - 1, indexOfNum2 = n - 1, indexOfSorted = m + n - 1;

        while (indexOfNum1 >=0 && indexOfNum2 >= 0) {
            if (nums1[indexOfNum1] <= nums2[indexOfNum2]) {
                nums1[indexOfSorted--] = nums2[indexOfNum2--];
            } else {
                nums1[indexOfSorted--] = nums1[indexOfNum1--];
            }
        }
        System.arraycopy(nums2, 0, nums1, 0, indexOfNum2+1);
    }

    public static void merge_brute(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m+n];
        int indexOfNum1 = 0, indexOfNum2 = 0, indexOfResult = 0;

        while (indexOfNum1 < m && indexOfNum2 < n) {
            if (nums1[indexOfNum1] <= nums2[indexOfNum2]) {
                result[indexOfResult++] = nums1[indexOfNum1++];
            } else {
                result[indexOfResult++] = nums2[indexOfNum2++];
            }
        }
        while (indexOfNum1 < m) {
            result[indexOfResult++] = nums1[indexOfNum1++];
        }
        while (indexOfNum2 < n) {
            result[indexOfResult++] = nums2[indexOfNum2++];
        }
        System.arraycopy(result, 0, nums1, 0, m + n);
    }


    @Test
    public void test_official() {
        //Given
        int[] nums1 = new int[]{1,2,3,0,0,0,0};
        int[] nums2 = new int[]{2,5,6,7};

        //When And Then for sort
        MergeTwoSortedArrays.merge_sort(nums1, 3, nums2, nums2.length);
        Assert.assertArrayEquals(nums1, new int[]{1,2,2,3,5,6,7});

        //When And Then for brute
        int[] nums11 = new int[]{1,2,3,0,0,0,0};
        int[] nums22 = new int[]{2,5,6,7};
        MergeTwoSortedArrays.merge_brute(nums11, 3, nums22, nums22.length);
        Assert.assertArrayEquals(nums11, new int[]{1,2,2,3,5,6,7});

        //When And Then for double point backward
        int[] nums111 = new int[]{1,2,3,0,0,0,0};
        int[] nums222 = new int[]{2,5,6,7};
        MergeTwoSortedArrays.merge_double_point_backward(nums111, 3, nums222, nums222.length);
        Assert.assertArrayEquals(nums111, new int[]{1,2,2,3,5,6,7});
    }

    @Test
    public void test_nums2_has_min() {
        //Given
        int[] nums1 = new int[]{2,2,3,0,0,0,0,0,0};
        int[] nums2 = new int[]{1,1,2,5,6,7};

        //When And Then for double point backward
        MergeTwoSortedArrays.merge_double_point_backward(nums1, 3, nums2, nums2.length);
        Assert.assertArrayEquals(nums1, new int[]{1,1,2,2,2,3,5,6,7});
    }
}
