package Week_04.search_rotated_array_ii;

import org.junit.Assert;
import org.junit.Test;

public class SearchRotatedArrayII {
    public static boolean search(int[] nums, int target) {
        if (nums.length == 0) return false;
        int left = 0, right = nums.length - 1, mid;
        // strip duplicated of beginning
        while (left < right && nums[left] == nums[right]) left++;

        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == target) return true;
            if (nums[left] <= nums[mid])
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            else
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
        }
        return false;
    }

    public static boolean search_enhance(int[] nums, int target) {
        if (nums.length == 0) return false;
        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
           mid = (left + right) >>> 1;
           if (nums[mid] == target) return true;
           if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
               left++; right--; continue;
           }
           if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) right = mid - 1;
                else left = mid + 1;
           } else {
                if (nums[mid] <= target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
           }
        }
        return false;
    }


    public static boolean search_enhance1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == target) return true;
            if (nums[left] <= nums[mid]) {
                if (nums[left] < target && nums[left] == nums[mid]) {
                    left++; continue;
                }
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return false;
    }

    @Test
    public void test_official() {
        //Given

        //When
        boolean result = SearchRotatedArrayII.search(new int[]{2,5,6,0,0,1,2}, 6);
        Assert.assertTrue(result);

        result = SearchRotatedArrayII.search_enhance(new int[]{1,3,1,1,1}, 3);
        Assert.assertTrue(result);

        /*result = SearchRotatedArrayII.search(new int[]{2,5,6,0,0,1,2}, 3);
        Assert.assertTrue(!result);*/

        /*result = SearchRotatedArrayII.search(new int[]{1}, 0);
        Assert.assertTrue(!result);

        result = SearchRotatedArrayII.search(new int[]{1}, 1);
        Assert.assertTrue(result);*/
    }
}
