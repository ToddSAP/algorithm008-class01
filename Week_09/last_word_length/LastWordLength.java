package Week_09.last_word_length;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LastWordLength {
    public static int lengthOfLastWord(String s) {
        if (s == null || s.trim().equals("")) return 0;
        char[] chars = s.toCharArray();
        int result = 0, end = chars.length - 1;
        while (end >= 0) if (chars[end] == ' ') end--; else break;
        s = new String(Arrays.copyOfRange(chars, 0, end+1));
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) == ' ') return result; else result++;
        return result;
    }

    public static int lengthOfLastWord_enhance(String s) {
        if (s == null || s.length() == 0) return 0;
        int end = s.length()-1;
        while (end >= 0) if (s.charAt(end) == ' ') end--; else break;
        int start = end;
        for (int i = end; i >= 0; i--)
            if (s.charAt(i) != ' ') start--; else break;
        return end - start;
    }

    @Test
    public void test_official() {
        //Given
        int result = LastWordLength.lengthOfLastWord("hello world");
        Assert.assertEquals(result, 5);

        result = LastWordLength.lengthOfLastWord("a");
        Assert.assertEquals(result, 1);

        result = LastWordLength.lengthOfLastWord(" a ");
        Assert.assertEquals(result, 1);

        result = LastWordLength.lengthOfLastWord_enhance("hello world");
        Assert.assertEquals(result, 5);

        result = LastWordLength.lengthOfLastWord_enhance("a");
        Assert.assertEquals(result, 1);

        result = LastWordLength.lengthOfLastWord_enhance(" a ");
        Assert.assertEquals(result, 1);
        //When

        //Then
    }
}
