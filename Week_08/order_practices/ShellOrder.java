package Week_08.order_practices;

import org.junit.Assert;
import org.junit.Test;

public class ShellOrder {
    public static int[] order(int[] nums) {
       if (nums == null || nums.length == 0) return nums;
       int gap = nums.length, n = nums.length;

       while (gap > 1) {
           gap = gap / 3 + 1;
           boolean hasSwap = false;

           for (int i = 0; i + gap < n; i++) {
               for (int j = i; j + gap < n; j += gap) {
                   if (nums[j + gap] < nums[j]) {
                       int tmp = nums[j];
                       nums[j] = nums[j + gap];
                       nums[j + gap] = tmp;
                       hasSwap = true;
                   }
               }
           }

           if (!hasSwap) return nums;
       }

       return nums;
    }

    public static int[] order_enhance(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int gap = nums.length, n = nums.length;
        while (gap > 1) {
            gap = gap / 3 + 1;
            for (int i = n - 1; i > 0; i--)
                for (int j = i; j - gap >= 0 ; j -= gap) {
                    int tmp;
                    if (nums[j - gap] > nums[j]) {
                        // swap
                        tmp = nums[j - gap];
                        nums[j - gap] = nums[j];
                        nums[j] = tmp;
                    }
                }
        }
        return nums;
    }

    @Test
    public void test_practices() {
        int[] result = ShellOrder.order(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = ShellOrder.order(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);

        result = ShellOrder.order_enhance(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = ShellOrder.order_enhance(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);
    }
}
