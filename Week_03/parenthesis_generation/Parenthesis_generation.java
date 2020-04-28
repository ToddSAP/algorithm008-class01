package Week_03.parenthesis_generation;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Parenthesis_generation {
    /**
     * 思路：
     *      1. 递归：利用系统栈保存中间括号串，每次出栈处理该中间状态的下一轮，直到所有中间状态处理完毕。时间O(n),空间O(n)。
     *      2. 递归优化：使用StringBuilder来暂存括号串的中间状态，不会每次递归都创建新的中间括号串，每个系统栈帧中只新建指向StringBuilder
     *                的引用，可节省大量空间。时间O(n),空间(n)。
     *      3. 迭代：利用队列或栈存储括号串的中间状态，每次出队(迭代)处理一个中间状态的下一轮，直到所有中间状态处理完毕(队列为空)。
     */


    public static List<String> generateParenthesis_recursion(int n) {
        List<String> result = new ArrayList<>();
        recurse("", n, n, result);
        return result;
    }

    private static void recurse(String currString, int remainingLeft, int remainingRight, List<String> result) {
        // terminator
        if (remainingLeft ==0 && remainingRight == 0) {
            result.add(currString);
            return;
        }

        // process current level
        if (remainingLeft > 0) {
            // drill down
            recurse(currString + "(", remainingLeft-1, remainingRight, result);
        }
        if (remainingRight > 0 && remainingRight > remainingLeft) {
            // drill down
            recurse(currString + ")", remainingLeft, remainingRight-1, result);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            if (currNode.left == 0 && currNode.right == 0) {
                res.add(currNode.res);
            }
            // 可以加左括号的条件就是左括号还没有用完
            if (currNode.left > 0) {
                queue.offer(new Node(currNode.res+"(", currNode.left-1, currNode.right));
            }
            // 可以加右括号的条件是右括号的没用完，且右括号未使用的数量大于左括号未使用数量
            if (currNode.right > 0 && currNode.left < currNode.right) {
                queue.offer(new Node(currNode.res+")", currNode.left, currNode.right-1));
            }
        }
        return res;
    }

    static class Node {
        private String res;
        private int left;
        private int right;

        public Node (String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }
    }

    public static List<String> generateParenthesis_recurse_one_path_execution(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        generateParenthesis_recurse_one_path(sb, n, n, result);
        return result;
    }

    public static void generateParenthesis_recurse_one_path(StringBuilder sb, int remainingLeft, int remainingRight, List<String> result) {
        // terminator
        if (remainingLeft == 0 && remainingRight == 0) {
            result.add(sb.toString());
            return;
        }

        if (remainingLeft > 0) {
            // process current level
            sb.append("(");
            // drill down
            generateParenthesis_recurse_one_path(sb, remainingLeft-1, remainingRight, result);
            // clean
            sb.deleteCharAt(sb.length()-1);
        }

        if (remainingRight > remainingLeft) {
            // process current level
            sb.append(")");
            // drill down
            generateParenthesis_recurse_one_path(sb, remainingLeft, remainingRight-1, result);
            // clean
            sb.deleteCharAt(sb.length()-1);
        }
    }

    @Test
    public void test_official() {
        //Given

        //When And Then
        List<String> result = Parenthesis_generation.generateParenthesis(3);
        Assert.assertArrayEquals(result.toArray(new String[5]), new String[]{"((()))","(()())","(())()","()(())","()()()"});

        result = Parenthesis_generation.generateParenthesis_recursion(3);
        Assert.assertArrayEquals(result.toArray(new String[5]), new String[]{"((()))","(()())","(())()","()(())","()()()"});

        result = Parenthesis_generation.generateParenthesis_recurse_one_path_execution(3);
        Assert.assertArrayEquals(result.toArray(new String[5]), new String[]{"((()))","(()())","(())()","()(())","()()()"});
    }
}
