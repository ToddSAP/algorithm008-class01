package Week_02.binarytree_postorder;

import Week_02.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostOrder {
    /**
     * 思路：
     *      1. 递归： 递推公式：recurse(node.left); recurse(node.right); recurse(node) 终止条件：node == null。
     *      2. 迭代： 后序的遍历顺序是 左->右->根，那么可以转换成 根->右->左 的反序，故代码模板和先序是一样的，不同之处在于
     *                先处理根节点，然后按照先左后右的顺序入栈，再依次处理节点，处理结果用addFirst添加到最终结果中。
     *
     */

    public static List<Integer> postorderTraversal_recursion(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        recurse(root, result);
        return result;
    }

    public static List<Integer> postorderTraversal_iteration(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.addFirst(curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return result;
    }

    private static void recurse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        recurse(root.left, result);
        recurse(root.right, result);
        result.add(root.val);
    }

    @Test
    public void test_official() {
        //Given
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(2);
        expected.add(1);

        //When And Then for recursion
        List<Integer> result = BinaryTreePostOrder.postorderTraversal_recursion(root);
        Assert.assertArrayEquals(expected.toArray(new Integer[3]), result.toArray(new Integer[3]));

        //When And Then for iteration
        result = BinaryTreePostOrder.postorderTraversal_iteration(root);
        Assert.assertArrayEquals(expected.toArray(new Integer[3]), result.toArray(new Integer[3]));
    }

    @Test
    public void test_empty() {
        //Given

        //When And Then for recursion
        List<Integer> result = BinaryTreePostOrder.postorderTraversal_iteration(null);
        Assert.assertTrue(result.size() == 0);

        //When And Then for iteration
        result = BinaryTreePostOrder.postorderTraversal_recursion(null);
        Assert.assertTrue(result.size() == 0);
    }
}
