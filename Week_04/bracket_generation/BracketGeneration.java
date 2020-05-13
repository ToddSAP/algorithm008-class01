package Week_04.bracket_generation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BracketGeneration {
    class Bracket {
        public int left;
        public int right;
        String bracket;

        public Bracket(String curr, int left, int right) {
            this.bracket = curr;
            this.left = left;
            this.right = right;
        }
    }
    public List<String> generateParenthesis_bfs(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        LinkedList<Bracket> queue = new LinkedList<>();
        queue.offer(new Bracket("(", n-1, n));
        while (!queue.isEmpty()) {
            Bracket curr = queue.pop();
            if (curr.left == 0 && curr.right == 0) result.add(curr.bracket);
            if (curr.left > 0) queue.offer(new Bracket(curr.bracket+"(", curr.left-1, curr.right));
            if (curr.right > curr.left) queue.offer(new Bracket(curr.bracket+")", curr.left, curr.right-1));
        }
        return result;
    }

    public static List<String> generateParenthesis_dfs(int n) {
        List<String> result = new ArrayList<>();
        dfs(new StringBuilder(), n, n, result);
        return result;
    }

    private static void dfs(StringBuilder curr, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(curr.toString());
            return;
        }

        if (left > 0) {
            dfs(curr.append("("), left-1, right, result);
            curr.deleteCharAt(curr.length()-1);
        }
        if (right > left) {
            dfs(curr.append(")"), left, right-1, result);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    @Test
    public void test_official() {
        //Given

        //When
        List<String> result = BracketGeneration.generateParenthesis_dfs(3);
        result.stream().forEach(System.out::println);


        //Then
    }
}
