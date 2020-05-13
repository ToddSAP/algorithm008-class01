package Week_04.assign_cookie;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AssignCookie {
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); Arrays.sort(s);
        int result = 0, childNum = 0, cookieNum = 0;
        while (childNum < g.length && cookieNum < s.length) {
            if (g[childNum] <= s[cookieNum]) {cookieNum++; childNum++; result++;}
            else cookieNum++;
        }
        return result;
    }

    @Test
    public void test_official() {
        //Given

        //When
        /*int result = AssignCookie.findContentChildren(new int[]{1,2,3}, new int[]{1,1});
        Assert.assertEquals(result, 1);

        result = AssignCookie.findContentChildren(new int[]{1,2}, new int[]{1,2,3});
        Assert.assertEquals(result, 2);

        result = AssignCookie.findContentChildren(new int[]{1,2,3}, new int[]{3});
        Assert.assertEquals(result, 1);*/

        int result = AssignCookie.findContentChildren(new int[]{10,9,8,7}, new int[]{5,6,7,8});
        Assert.assertEquals(result, 1);
        //Then
    }
}
