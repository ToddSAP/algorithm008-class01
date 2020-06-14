package Week_08.order_practices;

import org.junit.Assert;
import org.junit.Test;

public class InsertionOrder {
    /**
     * 相邻的两个元素直接swap，效率不是最好，因为每次target元素都要被赋值一次
     */
    public static int[] order(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j-1] > nums[j]) {
                    int tmp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = tmp;
                } else break;
            }
        }
        return nums;
    }

    /**
     * 每次只移动相邻元素中需要移动的那个，每次遍历后，再把target插入到应该在的位置
     */
    public static int[] order_enhance(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 1; i < nums.length; i++) {
            int targetIndex = i, currValue = nums[i];
            for (int j = i; j > 0; j--) {
                if (nums[j-1] > currValue) {
                    targetIndex--;
                    nums[j] = nums[j-1];
                } else break;
            }
            nums[targetIndex] = currValue;
        }
        return nums;
    }

    /**
     * 每次遍历只记录target的下标，遍历结束后批量移动元素，最后将target插入应该在的位置
     */
    public static int[] order_enhance1(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 1; i < nums.length; i++) {
            int targetIndex = i, currValue = nums[i];
            for (int j = i; j > 0; j--)
                if (nums[j-1] > currValue) targetIndex--;
                else break;
            System.arraycopy(nums, targetIndex, nums, targetIndex+1, i-targetIndex);
            nums[targetIndex] = currValue;
        }
        return nums;
    }

    @Test
    public void test_practice() {
        int[] result = InsertionOrder.order(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = InsertionOrder.order(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);

        result = InsertionOrder.order_enhance(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = InsertionOrder.order_enhance(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);

        result = InsertionOrder.order_enhance1(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = InsertionOrder.order_enhance1(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);
    }
}
