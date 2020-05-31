package Week_06.min_path_sum;

import org.junit.Assert;
import org.junit.Test;

public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rowNum = grid.length, colNum = grid[0].length;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (i == 0 && j > 0) grid[i][j] += grid[i][j-1];
                else if (j == 0 && i > 0) grid[i][j] += grid[i-1][j];
                else if (i > 0 && j > 0) grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return colNum > 0 ? grid[rowNum-1][colNum-1] : 0;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = MinPathSum.minPathSum(new int[][]{{1,3,1}, {1,5,1}, {4,2,1}});
        Assert.assertEquals(7, result);

        //Then
    }
}
