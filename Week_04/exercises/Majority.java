package Week_04.exercises;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Majority {
    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int threshold = nums.length >>> 1;
        HashMap<Integer, Integer> hm = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (hm.get(nums[i]) == null) hm.put(nums[i], 1);
            else hm.put(nums[i], hm.get(nums[i])+1);
            if (hm.get(nums[i]) > threshold) return nums[i];
        }
        return 0;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = Majority.majorityElement(new int[]{2,2,1,1,1,2,2});
        Assert.assertEquals(2, result);

        result = Majority.majorityElement(new int[]{3,2,3});
        Assert.assertEquals(3, result);

        //Then
    }

    public static int search(int[] num, int target) {
        int left = 0, right = num.length-1, mid;
        int count = 0;
        while (left <= right) {
            count++;
            mid = (left+right) >>> 1;
            if (num[mid] == target) return count;
            else if (num[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return count;
    }

    @Test
    public void test_search() {
        //Given

        //When
        Majority.search(new int[]{2,11,15,19,30,32,61,72,88,90,96}, 15);

        //Then
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length < 4) return new ArrayList<>(result);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    int left = k + 1, right = nums.length - 1, mid, find = target - nums[i] - nums[j] - nums[k];
                    while (left <= right) {
                        mid = (left + right) >>> 1;
                        if (nums[mid] == find) {
                            result.add(new ArrayList(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k], nums[mid]})));
                            left++;
                            right--;
                        } else if (nums[mid] > find) {
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
    @Test
    public void test_1() {
        //Given

        //When
        Majority.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);

        //Then
    }

    public static int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if( i == end){ //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    @Test
    public void test_2() {
        //Given

        //When
        int result = Majority.jump(new int[]{2,3,1,1,4});
        Assert.assertEquals(2, result);

        //Then
    }
}
