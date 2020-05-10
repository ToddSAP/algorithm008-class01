package Week_03.queen;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class N_Queen_Problem {

    public static List<List<String>> solveNQueens(int n) {
        String rowStr = "........";
        List<String> path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            path.add(rowStr);
        }
        List<List<String>> result = new ArrayList<>();

        backtrack(path, 0, result);
        return null;
    }

    public static void backtrack(List<String> path, int row, List<List<String>> result) {
        // terminator
        if (row == path.size()) {
            result.add(path);
            return;
        }

        int colNum = path.get(row).length();
        for (int i = 0; i < colNum; i++) {
            // make decision
            if (!isValid(path, row, i)) {
                continue;
            }
            char[] chars = path.get(row).toCharArray();
            chars[i] = 'Q';
            path.set(row, new String(chars));

            // backtrack
            backtrack(path, row+1, result);

            // withdraw
            chars = path.get(row).toCharArray();
            chars[i] = '.';
            path.set(row, new String(chars));
        }
    }

    private static boolean isValid(List<String> path, int row, int col) {
        //检查列是否有Queen
        for (int i = 0; i < path.size(); i++) {
            // 某一行的所有列
            char[] chars = path.get(i).toCharArray(); 
            if (chars[col] == 'Q') {
                return false; 
            }
        }
        //检查左上方是否有Queen
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            char[] chars = path.get(i).toCharArray();
            if (chars[j] == 'Q') {
                return false;
            }
        }

        //检查右上方是否有Queen
        for (int i = row, j = col; i >= 0 && j < 8 ; i--, j++) {
            char[] chars = path.get(i).toCharArray();
            if (chars[j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_official() {
        //Given
        List<String> expected = new ArrayList<>();
        expected.add(".Q..");
        expected.add("...Q");
        expected.add("Q...");
        expected.add("..Q.");

        //When
        List<List<String>> result = N_Queen_Problem.solveNQueens(8);

        //Then
        Assert.assertArrayEquals(result.get(0).toArray(new String[8]), expected.toArray());
    }
}
