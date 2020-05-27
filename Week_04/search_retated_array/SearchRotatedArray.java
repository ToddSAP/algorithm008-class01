package Week_04.search_retated_array;

import org.junit.Assert;
import org.junit.Test;

public class SearchRotatedArray {
    /**
     * 思路:
     *      有序部分好判断，条件始终写有序部分的
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }

            // 前半部分有序
            if (nums[left] <= nums[mid]) {
                // 如果target小于有序部分上界且大于等于有序部分下届，则target在有序部分中
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { //后半部分有序
                // 如果target大于有序部分下届且小于等于有序部分上界，则target在有序部分中
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static int search_dfs(int[] nums, int target) {
        return dfs(nums, 0, nums.length-1, target);
    }

    private static int dfs(int[] nums, int left, int right, int target) {
        if (nums.length == 0) return -1;
        if (left == right) return nums[left] == target ? left : -1;
        int mid = (left + right) >>> 1;
        if (nums[mid] == target) return mid;

        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) return dfs(nums, left, mid - 1, target);
            else return dfs(nums, mid + 1, right, target);
        } else {
            if (nums[mid] < target && target <= nums[right]) return dfs(nums, mid + 1, right, target);
            else return dfs(nums, left, mid - 1, target);
        }
    }


    @Test
    public void test_official() {
        //Given

        //When
        int result = SearchRotatedArray.search(new int[]{4,5,6,7,0,1,2}, 0);
        Assert.assertEquals(result, 4);

        /*result = SearchRotatedArray.search_dfs(new int[]{4,5,6,7,0,1,2}, 0);
        Assert.assertEquals(result, 4);*/

        /*result = SearchRotatedArray.search_dfs(new int[]{}, 5);
        Assert.assertEquals(result, -1);*/

        result = SearchRotatedArray.search_dfs(new int[]{1,3}, 0);
        Assert.assertEquals(result, -1);

        //Then
    }
}
