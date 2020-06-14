package Week_08.order_practices;

import org.junit.Assert;
import org.junit.Test;

public class SelectionOrder {
    public static int[] order(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++)
                if (nums[minIndex] > nums[j]) minIndex = j;
            int tmp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tmp;
        }
        return nums;
    }

    @Test
    public void test_practices() {
        int[] result = SelectionOrder.order(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = SelectionOrder.order(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);
    }
}
