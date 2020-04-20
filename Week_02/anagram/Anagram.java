package Week_02.anagram;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagram {
    /**
     * 思路:
     *      1. 排序法：将两个字符串分别排序，然后再对比两个字符串是否相等。时间O(nlog(n)),空间O(1)。
     *      2. 枚举统计法：利用英文字母可以枚举的特性，设置长度为26的数组，利用asiic码相减来寻找数组下标。
     *                  s中的字母个数在数组中做加法，t中的字母个数在数组中做减法，最后遍历数组，每项都为0则true，否则false。时间O(n),空间O(1)。
     *      3. 统计法扩展：对于unicode，无法枚举，那么用一个Map来模拟counter，其他逻辑同解法2。
     *                  因为用到了语言封装的数据结构，实际效率会低不少。时间O(n),空间O(n)。
     *      综上，对于小规模数据，排序法最优，且能兼顾unicode。对于大规模数据，统计法扩展较好，能兼容unicode，复杂度也较低。
     */


    public static boolean isAnagram_sorted(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] arrayS = s.toCharArray();
        char[] arrayT = t.toCharArray();
        Arrays.sort(arrayS);
        Arrays.sort(arrayT);
        return Arrays.equals(arrayS, arrayT);
    }


    public static boolean isAnagram_counter(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (counter[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram_counter_expend(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        String[] arrayS = s.split("");
        String[] arrayT = t.split("");
        for (int i = 0; i < arrayS.length; i++) {
            if (map.get(arrayS[i]) == null) {
                map.put(arrayS[i], 1);
            } else {
                map.put(arrayS[i], map.get(arrayS[i]) + 1);
            }
        }
        for (int i = 0; i < arrayT.length; i++) {
            if (map.get(arrayT[i]) == null) {
                return false;
            } else {
                map.put(arrayT[i], map.get(arrayT[i]) - 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_official() {
        //Given And When And Then for sorted
        String s = "anagram", t = "nagaram";
        boolean result = Anagram.isAnagram_sorted(s, t);
        Assert.assertTrue(result);

        //Given And When And Then for sorted
        String s1 = "rat", t1 = "car";
        result = Anagram.isAnagram_sorted(s1, t1);
        Assert.assertTrue(!result);

        //Given And When And Then for counter
        String s2 = "anagram", t2 = "nagaram";
        result = Anagram.isAnagram_counter(s2, t2);
        Assert.assertTrue(result);

        //Given And When And Then for counter
        String s3 = "rat", t3 = "car";
        result = Anagram.isAnagram_counter(s3, t3);
        Assert.assertTrue(!result);

        //Given And When And Then for counter expend
        String s4 = "anagram", t4 = "nagaram";
        result = Anagram.isAnagram_counter_expend(s4, t4);
        Assert.assertTrue(result);

        //Given And When And Then for counter expend
        String s5 = "rat", t5 = "car";
        result = Anagram.isAnagram_counter_expend(s5, t5);
        Assert.assertTrue(!result);
    }

    @Test
    public void test_empty_or_have_not_same_length() {
        //Given And When And Then for sorted
        String s = "", t = "";
        boolean result = Anagram.isAnagram_sorted(s, t);
        Assert.assertTrue(result);

        //Given And When And Then for counter
        String s2 = "", t2 = "";
        result = Anagram.isAnagram_counter(s2, t2);
        Assert.assertTrue(result);

        //Given And When And Then for sorted
        String s3 = "abc", t3 = "abde";
        result = Anagram.isAnagram_sorted(s3, t3);
        Assert.assertTrue(!result);

        //Given And When And Then for counter
        String s4 = "abc", t4 = "abde";
        result = Anagram.isAnagram_counter(s4, t4);
        Assert.assertTrue(!result);

        //Given And When And Then for counter expend
        String s5 = "abc", t5 = "abde";
        result = Anagram.isAnagram_counter_expend(s5, t5);
        Assert.assertTrue(!result);

        //Given And When And Then for counter
        String s6 = "", t6 = "";
        result = Anagram.isAnagram_counter_expend(s6, t6);
        Assert.assertTrue(result);
    }

    @Test
    public void test_for_unicode() {
        //Given
        String s = "你好吗";
        String t = "吗你好";

        //When And Then for counter expend
        boolean result = Anagram.isAnagram_counter_expend(s, t);
        Assert.assertTrue(result);

        //When And Then for counter expend
        result = Anagram.isAnagram_sorted(s, t);
        Assert.assertTrue(result);
    }
}
