package Week_02.group_anagram;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GroupAnagram {
    /**
     * 思路：
     *      1. 排序法：将数组中的每个字符串的数组排序，然后两两比较，将相同的结果放到同一个数组内输出。
     *                时间O(nklog(k))，空间O(nk),k为最长的字符串数组长度。适用于任何字符，包括unicode
     *      2. 统计法：将数组中的每个字符串的字符出现的次数统计好，并将统计信息拼装成一个字符串作为key，
     *                将相同key的字符串放到同一列表中。时间O(nK),空间O(nK)。只适用字符可枚举的情况，unicode不适用。
     */


    public static List<List<String>> groupAnagrams_sorted(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> result = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String stringAfterSorted = String.valueOf(chars);
            if (!result.containsKey(stringAfterSorted)) {
                result.put(stringAfterSorted, new ArrayList<>());
            }
            result.get(stringAfterSorted).add(str);
        }
        return new ArrayList<>(result.values());
    }

    @Test
    public void test_official() {
        //Given
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        //When
        List<List<String>> result = GroupAnagram.groupAnagrams_sorted(strs);

        //Then
        String[] item1 = new String[]{"eat","tea","ate"};
        String[] item2 = new String[]{"tan","nat"};
        String[] item3 = new String[]{"bat"};

        Assert.assertArrayEquals(result.get(0).toArray(new String[3]), item1);
        Assert.assertArrayEquals(result.get(2).toArray(new String[2]), item2);
        Assert.assertArrayEquals(result.get(1).toArray(new String[1]), item3);
    }
}
