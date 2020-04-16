package Week_01.two_sum;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * 思路：
     *      1. 暴力法： 测试每两个元素的和是否为给定数，时间O(n2),空间O(1)
     *      2. 二分法： 先排序，固定一个元素，然后用二分法查找另一个元素，使得两数之和为给定数，
     *                 时间O(nlogn),空间O(n)
     *                 因为本题是找下标，需要将元素和下标对应，逻辑较复杂，且需额外空间，故二分法不太适合此题
     *      3. 散列法： 创建一个散列表，根据当前元素和给定数，在散列表中查找另一元素，
     *                 没找到则将当前元素加入散列表，直到找到或遍历完毕
     *                 时间O(n),空间O(n)
     *                 该题此法综合最优
     */


    public static int[] twoSum_brute(int[] nums, int target) {
        if (nums!=null && nums.length>1) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }


    public static int[] twoSum_binary(int[] nums, int target) {
        if (nums!=null && nums.length>1) {
            // make map to keep mapping between element and index
            Map<Integer, ArrayList<Integer>> elementIndexMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                ArrayList<Integer> elementList = elementIndexMap.get(nums[i]);
                if (elementList == null) {
                    elementList = new ArrayList<>();
                }
                elementList.add(i);
                elementIndexMap.put(nums[i], elementList);
            }

            // sort
            Arrays.sort(nums);

            // traverse and binary search to identify another element
            for (int i = 0; i < nums.length; i++) {
                int targetNum = binarySearch(nums, i + 1, target - nums[i]);
                if (targetNum != Integer.MIN_VALUE) {
                    // find index in map
                    if (nums[i] == targetNum) {
                        ArrayList<Integer> indexArray = elementIndexMap.get(targetNum);
                        return new int[]{indexArray.get(0), indexArray.get(1)};
                    } else {
                        return new int[]{elementIndexMap.get(nums[i]).get(0), elementIndexMap.get(targetNum).get(0)};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int startIndex, int targetNum) {
        int left = startIndex, right = nums.length - 1;

        while (left<=right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == targetNum) {
                return nums[mid];
            } else if (nums[mid] > targetNum) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static int[] twoSum_hash(int[] nums, int target) {
        Map<Integer, Integer> elementIndexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer indexOfTargetNum = elementIndexMap.get(target - nums[i]);
            if (indexOfTargetNum != null && i != indexOfTargetNum) {
                return new int[]{indexOfTargetNum, i};
            } else {
                elementIndexMap.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }






    @Test
    public void test_official() {
        //Given
        int[] nums = new int[]{2,7,11,15};

        //When And The for brute
        int[] result = TwoSum.twoSum_brute(nums, 9);
        Assert.assertArrayEquals(result, new int[]{0, 1});

        //When And Then for hash
        result = TwoSum.twoSum_hash(nums, 9);
        Assert.assertArrayEquals(result, new int[]{0, 1});

        //When And Then for binary
        result = TwoSum.twoSum_binary(nums, 9);
        Assert.assertArrayEquals(result, new int[]{0, 1});
    }

    @Test
    public void test_empty() {
        //Given
        int[] nums = new int[0];

        //When And Then for brute
        int[] result = TwoSum.twoSum_brute(nums, 9);
        Assert.assertArrayEquals(result, new int[]{-1, -1});

        //When And Then for hash
        result = TwoSum.twoSum_hash(nums, 9);
        Assert.assertArrayEquals(result, new int[]{-1, -1});

        //When And Then for binary
        result = TwoSum.twoSum_binary(nums, 9);
        Assert.assertArrayEquals(result, new int[]{-1, -1});
    }

    @Test
    public void test_negative() {
        //Given
        int[] nums = new int[]{3,-1,2,9,8,5,-899,1};

        //When And Then for brute
        int[] result = TwoSum.twoSum_brute(nums, 1);
        Assert.assertArrayEquals(result, new int[]{1, 2});

        //When And Then for hash
        result = TwoSum.twoSum_hash(nums, 1);
        Assert.assertArrayEquals(result, new int[]{1, 2});

        //When And Then for binary
        result = TwoSum.twoSum_binary(nums, 1);
        Assert.assertArrayEquals(result, new int[]{1, 2});
    }
    @Test
    public void test_zero() {
        //Given
        int[] nums = new int[]{0,-1,-2,0};

        //When And Then for brute
        int[] result = TwoSum.twoSum_brute(nums, 0);
        Assert.assertArrayEquals(result, new int[]{0, 3});

        //When And Then for hash
        result = TwoSum.twoSum_hash(nums, 0);
        Assert.assertArrayEquals(result, new int[]{0, 3});

        //When And Then for binary
        result = TwoSum.twoSum_binary(nums, 0);
        Assert.assertArrayEquals(result, new int[]{0, 3});
    }
}