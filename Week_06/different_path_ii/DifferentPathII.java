package Week_06.different_path_ii;

import org.junit.Assert;
import org.junit.Test;

public class DifferentPathII {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 找到有几行几列
        int rowNum = obstacleGrid.length, colNum = -1;
        if (rowNum <= 0) return 0; else colNum = obstacleGrid[0].length;
        // 定义一个新dp
        int[][] dp = new int[rowNum][colNum];
        // 确认首行/列是否有障碍，及障碍之所在
        boolean isFirstRowBlocked = false, isFirstColBlocked = false;
        int firstRowBlockedIndex = -1, firstColBlockedIndex = -1;
        for (int i = 0; i < rowNum; i++) {
            if (obstacleGrid[i][0] == 1) {
                firstColBlockedIndex = i;
                isFirstColBlocked = true;
                break;
            }
        }
        for (int i = 0; i < colNum; i++) {
            if (obstacleGrid[0][i] == 1) {
                firstRowBlockedIndex = i;
                isFirstRowBlocked = true;
                break;
            }
        }
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                // 根据所在行、列和障碍的关系决定格子如何计算值
                // 格子值为0的场景: 1) 当前格子就是障碍 2) 首行，且当前格子就是障碍或在障碍之后 3)首列，且当前格子就是障碍或在障碍之下
                if (obstacleGrid[i][j] == 1
                        || (i == 0 && isFirstRowBlocked && j > firstRowBlockedIndex)
                        || (j == 0 && isFirstColBlocked && i > firstColBlockedIndex)
                ) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[rowNum-1][colNum-1];
    }

    @Test
    public void test_official() {
        //Given

        //When
        /*int result = DifferentPathII.uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}});
        Assert.assertEquals(2, result);*/

        /*int result = DifferentPathII.uniquePathsWithObstacles(new int[][]{{1,0}});
        Assert.assertEquals(0, result);*/

        int result = DifferentPathII.uniquePathsWithObstacles(new int[][]{{0,0,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,0},{1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,1,0,1},{0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0},{0,0,0,0,0,1,0,0,0,0,1,1,0,1,0,0,0,0},{1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},{0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},{0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0},{0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1},{0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1},{1,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,1,0},{0,0,0,1,0,0,0,0,1,1,1,0,0,1,0,1,1,0},{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,1,1,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,0,0},{0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1},{0,1,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0},{1,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0},{0,1,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0},{1,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,1},{1,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0}});
        Assert.assertEquals(13594824, result);

        //Then
    }
}
