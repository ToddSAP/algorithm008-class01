package Week_06.max_square;

import org.junit.Assert;
import org.junit.Test;

public class MaxSquare {
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int rowNum = matrix.length, colNum = matrix[0].length; char maxLengthOfSide = matrix[0][0]=='1'?'1':'0';
        if (colNum < 1) return 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (i > 0 && j > 0 && matrix[i][j] == '1') {
                    matrix[i][j] = (char) ((Math.min(Math.min(matrix[i - 1][j], matrix[i][j - 1]), matrix[i - 1][j - 1])) + 1);
                    maxLengthOfSide = (char) Math.max(maxLengthOfSide, matrix[i][j]);
                } else if (matrix[i][j] == '1') {
                    maxLengthOfSide = (char) Math.max(maxLengthOfSide, matrix[i][j]);
                }
            }
        }
        return Character.getNumericValue(maxLengthOfSide)*Character.getNumericValue(maxLengthOfSide);
    }

    @Test
    public void test_official() {
        //Given

        //When
        /*int result = MaxSquare.maximalSquare(new char[][]{{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}});
        Assert.assertEquals(4, result);*/

        /*int result = MaxSquare.maximalSquare(new char[][]{{'0','1'}});
        Assert.assertEquals(1, result);*/

        /*int result = MaxSquare.maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}});
        Assert.assertEquals(4, result);*/

        int result = MaxSquare.maximalSquare(new char[][]{{'1','1'},{'1','1'}});
        Assert.assertEquals(4, result);

        //Then
    }
}
