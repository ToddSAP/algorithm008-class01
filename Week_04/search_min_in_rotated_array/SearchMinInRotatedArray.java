package Week_04.search_min_in_rotated_array;

import org.junit.Assert;
import org.junit.Test;

public class SearchMinInRotatedArray {
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1, mid, min = Integer.MAX_VALUE;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[left] <= nums[mid]) {
                min = Math.min(nums[left], min);
                left = mid + 1;
            } else {
                min = Math.min(nums[mid], min);
                right = mid - 1;
            }
        }
        return min;
    }

    public static int findMin_enhance(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1, mid;
        // 如果存在逆序，那么最小值一定在逆序区
        while (left <= right) {
            // 如果二分后已经正序，那么nums[left]即为最小值
            if (nums[left] <= nums[right]) return nums[left];
            mid = (left + right) >>> 1;
            if (nums[left] > nums[mid]) right = mid;
            else left = mid + 1;
        }
        return -1;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = SearchMinInRotatedArray.findMin(new int[]{4,5,6,7,0,1,2});
        Assert.assertEquals(result, 0);

        //Then
    }
}
