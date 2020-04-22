package Week_02.binarytree_preorder;

import Week_02.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreOrder {
    /**
     * 思路:
     *      1. 递归： 递推公式：recurse(node); recurse(node.left); recurse(node.right); 终止条件：node == null。
     *      2. 迭代： 手工维护一个栈，先处理栈中node本身，然后按照先right后left的顺序把非Null节点加入栈中，顺序出栈处理，直到栈空。
     */


    public static List<Integer> preorderTraversal_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(root, result);
        return result;
    }

    public static List<Integer> preorderTraversal_iteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    private static void recursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        recursion(root.left, result);
        recursion(root.right, result);
    }


    @Test
    public void test_official() {
        //Given
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        //When And Then for recursion
        List<Integer> result = BinaryTreePreOrder.preorderTraversal_recursion(root);
        Assert.assertArrayEquals(expected.toArray(new Integer[3]), result.toArray(new Integer[3]));

        //When And Then for iteration
        result = BinaryTreePreOrder.preorderTraversal_iteration(root);
        Assert.assertArrayEquals(expected.toArray(new Integer[3]), result.toArray(new Integer[3]));
    }

    @Test
    public void test_empty() {
        //Given

        //When And Then for recursion
        List<Integer> result = BinaryTreePreOrder.preorderTraversal_recursion(null);
        Assert.assertTrue(result.size() == 0);

        //When And Then for iteration
        result = BinaryTreePreOrder.preorderTraversal_iteration(null);
        Assert.assertTrue(result.size() == 0);
    }
}
