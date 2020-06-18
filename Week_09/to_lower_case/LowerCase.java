package Week_09.to_lower_case;

import org.junit.Assert;
import org.junit.Test;

public class LowerCase {
    public static String toLowerCase(String str) {
        if (str == null || str.trim().equals("")) return str;
        char[] newCharArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                newCharArray[i] = (char) (str.charAt(i) + 32);
        return new String(newCharArray);
    }

    @Test
    public void test_official() {
        //Given
        String result = LowerCase.toLowerCase("HeRe");
        Assert.assertEquals(result, "here");
        //When

        //Then
    }
}
