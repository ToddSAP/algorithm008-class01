package Week_01.rotate_sorted_array;

import org.junit.Assert;
import org.junit.Test;

public class RotateSortedArray {
    /**
     *   思路：
     *      1. 暴力法：开一个新数组，将后面n-k个元素依次放到新数组，再讲前k个元素接到新数组中即可。时间O(n),空间(n)。
     *      2. 分段交换法：先将整个数组反序，然后根据k将数组分成两部分，再分别反序即可。
     *      3. 环替换法：因为每个元素都需要被仅移动一次,故总的元素移动步数一定等于元素总个数。利用这一特性，每次移动一个元素到最终位置，直到总移动步数等于元素个数。
     */


    public static void rotate_brute(int[] nums, int k) {
        int[] newNums = new int[nums.length];
        int n = nums.length;
        if (n > 0) {
            k = k > n ? k % n : k;
            System.arraycopy(nums, 0, newNums, k, n - k);
            System.arraycopy(nums, n - k, newNums, 0, k);
            System.arraycopy(newNums, 0, nums, 0, n);
        }
    }

    public static void rotate_swap(int[] nums, int k) {
        int n = nums.length, i = 0, j = n - 1, tmp;
        if (n > 0) {
            k = k > n ? k % n : k;
            //reverse entire array
            reverse(nums, 0, n - 1);

            //reverse first n-k
            reverse(nums, 0, k - 1);

            //reverse last k
            reverse(nums, k, n - 1);
        }
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int tmp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = tmp;
        }
    }

    public static void rotate_cycle(int[] nums, int k) {
        int n = nums.length;
        if (n > 0) {
            int c = 0; k = k % n;
            for (int start = 0; c < n; start++) {
                int current = start;
                int preview = nums[start];
                do {
                    int next = (current + k) % n;
                    int tmp = nums[next];
                    nums[next] = preview;
                    preview = tmp;
                    current = next;
                    c++;
                } while (start != current);
            }
        }
    }


    public static void rotate_cycle_test(int[] nums, int k) {
        int n = nums.length;
        if (n > 0) {
            int moveCount = 0;
            for (int startIndex = 0; moveCount < n; startIndex++) {
                int prevVal = nums[startIndex];
                int currIndex = startIndex;
                int nextVal;
                do {
                    int nextIndex = (currIndex + k) % n;
                    //cache next value
                    nextVal = nums[nextIndex];

                    //set preview element to right position
                    nums[nextIndex] = prevVal;
                    //reset current index and temporary value
                    currIndex = nextIndex;
                    prevVal = nextVal;
                    moveCount++;
                } while (startIndex != currIndex);
            }
        }
    }

    @Test
    public void test_official() {
        //Given
        int[] nums = new int[]{1,2,3,4,5,6,7};

        //When And Then for brute
        RotateSortedArray.rotate_brute(nums, 3);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for brute
        nums = new int[]{1,2,3,4,5,6,7};
        RotateSortedArray.rotate_swap(nums, 3);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for cycle
        nums = new int[]{1,2,3,4,5,6,7};
        RotateSortedArray.rotate_cycle_test(nums, 3);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for cycle
        nums = new int[]{1,2,3,4,5,6};
        RotateSortedArray.rotate_cycle_test(nums, 3);
        Assert.assertArrayEquals(nums, new int[]{4,5,6,1,2,3});
    }

    @Test
    public void test_k_greater_than_array_length() {
        //Given
        int[] nums = new int[]{1,2,3,4,5,6,7};

        //When And Then for brute
        RotateSortedArray.rotate_brute(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for brute
        nums = new int[]{1,2,3,4,5,6,7};
        RotateSortedArray.rotate_swap(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});

        //When And Then for cycle
        nums = new int[]{1,2,3,4,5,6,7};
        RotateSortedArray.rotate_cycle(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{5,6,7,1,2,3,4});
    }

    @Test
    public void test_array_is_empty() {
        //Given
        int[] nums = new int[]{};

        //When And Then for brute
        RotateSortedArray.rotate_brute(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{});

        //When And Then for brute
        nums = new int[]{};
        RotateSortedArray.rotate_swap(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{});

        //When And Then for brute
        nums = new int[]{};
        RotateSortedArray.rotate_cycle(nums, 17);
        Assert.assertArrayEquals(nums, new int[]{});
    }
}
