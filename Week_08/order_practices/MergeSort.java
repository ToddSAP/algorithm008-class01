package Week_08.order_practices;

import org.junit.Assert;
import org.junit.Test;

public class MergeSort {
    public static int[] order (int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        sort(nums, 0, nums.length-1, new int[nums.length]);
        return nums;
    }

    private static void sort (int[] nums, int start, int end, int[] workspace) {
        int mid;
        if (start < end) {
            mid = (start + end) >>> 1;
            sort(nums, start, mid, workspace);
            sort(nums, mid + 1, end, workspace);
            merge(nums, start, mid, mid + 1, end, workspace);
        }
    }

    private static void merge (int[] nums, int start1, int end1, int start2, int end2, int[] workspace) {
        int k = 0, i = start1, j = start2;
        while (i <= end1 && j <= end2)
            if (nums[i] > nums[j]) workspace[k++] = nums[j++];
            else workspace[k++] = nums[i++];
        while (i <= end1) workspace[k++] = nums[i++];
        while (j <= end2) workspace[k++] = nums[j++];
        System.arraycopy(workspace, 0, nums, start1, end2-start1+1);
    }


    @Test
    public void test_practices () {
        int[] result = MergeSort.order(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = MergeSort.order(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);
    }
}
