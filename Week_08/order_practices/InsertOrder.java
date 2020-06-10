package Week_08.order_practices;

import org.junit.Assert;
import org.junit.Test;

public class InsertOrder {
    public static int[] order(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tmp;
        }
        return nums;
    }

    @Test
    public void test_practices() {
        int[] result = InsertOrder.order(new int[]{4,3,8,1,0,9,5,7,2,6});
        Assert.assertArrayEquals(new int[]{0,1,2,3,4,5,6,7,8,9}, result);

        result = InsertOrder.order(new int[]{4,3,8,1,0,9,5,0,7,2,0,6});
        Assert.assertArrayEquals(new int[]{0,0,0,1,2,3,4,5,6,7,8,9}, result);
    }
}
