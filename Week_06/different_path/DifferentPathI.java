package Week_06.different_path;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DifferentPathI {
    public static int uniquePaths_math(int m, int n) {
        if (m <= 0 || n <= 0) return -1;
        // 计算C(m-1, m+n-2)
        int j = Math.max(m - 1, n - 1), h = Math.min(m - 1, n - 1), k = m + n - 2;
        long s1 = 1, s2 = 1;
        for (int i = k; i > j; i--) {
            s1 *= i;
        }
        for (int i = 1; i <= h; i++) {
            s2 *= i;
        }
        return (int) (s1/s2);
    }

    public static int uniquePaths_dp(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static int unique_dp_enhance(int m, int n) {
        int j = Math.min(m, n), z = Math.max(m, n);
        int[] dp = new int[j];
        Arrays.fill(dp, 1);
        for (int i = 1; i < z; i++)
            for (int k = 1; k < j; k++)
                dp[k] += dp[k-1];
        return dp[j-1];
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = DifferentPathI.uniquePaths_math(3, 7);
        Assert.assertEquals(28, result);

        result = DifferentPathI.uniquePaths_math(3, 2);
        Assert.assertEquals(3, result);

        result = DifferentPathI.uniquePaths_math(10,10);
        Assert.assertEquals(48620, result);

        result = DifferentPathI.uniquePaths_dp(10,10);
        Assert.assertEquals(48620, result);

        result = DifferentPathI.unique_dp_enhance(10,10);
        Assert.assertEquals(48620, result);
        //Then
    }
}
