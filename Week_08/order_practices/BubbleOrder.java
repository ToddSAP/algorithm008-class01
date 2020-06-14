package Week_08.order_practices;

import org.junit.Assert;
import org.junit.Test;

public class BubbleOrder {
    public static int[] order(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 0; i < nums.length; i++) {
            boolean hasSwap = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    hasSwap = true;
                }
            }
            if (!hasSwap) break;
        }
        return nums;
    }

    @Test
    public void test_practices() {
        int[] result = BubbleOrder.order(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = BubbleOrder.order(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);
    }
}
