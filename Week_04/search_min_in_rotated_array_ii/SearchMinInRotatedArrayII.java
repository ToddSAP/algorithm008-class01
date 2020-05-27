package Week_04.search_min_in_rotated_array_ii;

import org.junit.Assert;
import org.junit.Test;

public class SearchMinInRotatedArrayII {
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1, mid, min = Integer.MAX_VALUE;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                min = Math.min(min, nums[mid]);
                left++; right--; continue;
            }
            if (nums[left] > nums[mid]) {
                min = Math.min(min, nums[mid]);
                right = mid;
            } else {
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }
        }
        return min;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = SearchMinInRotatedArrayII.findMin(new int[]{2,2,2,0,1});
        Assert.assertEquals(result, 0);

        result = SearchMinInRotatedArrayII.findMin(new int[]{1});
        Assert.assertEquals(result, 1);

        result = SearchMinInRotatedArrayII.findMin(new int[]{3,1,3});
        Assert.assertEquals(result, 1);

        //Then
    }
}
