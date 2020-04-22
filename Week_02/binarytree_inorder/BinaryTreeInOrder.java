package Week_02.binarytree_inorder;

import Week_02.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeInOrder {
    /**
     * 思路：
     *      1. 递归：递推公式 recurse(node.left); recurse(node); recurse(node.right); 终止条件： node == null
     *      2. 迭代：先循环找出最左侧的节点，处理该节点，然后切换到该节点最近的一个右侧节点,再处理，直到所有节点处理完毕。
     *              原理是无论当前节点是左、中还是右侧节点，那么其最近的一个右侧节点都满足中序遍历的要求。
     */

    public static List<Integer> inorderTraversal_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(root, result);
        return result;
    }

    public static List<Integer> inorderTraversal_iteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr!=null || !stack.isEmpty()) {
            //寻找最左侧的节点，并逐一放入栈中
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //出栈当前最左侧的节点
            curr = stack.pop();
            //处理节点
            result.add(curr.val);
            //切换到当前节点最近的一个右侧节点
            curr = curr.right;
        }
        return result;
    }

    private static void recursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        recursion(root.left, result);
        result.add(root.val);
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
        expected.add(3);
        expected.add(2);

        //When And Then for recursion
        List<Integer> result = BinaryTreeInOrder.inorderTraversal_recursion(root);
        Assert.assertArrayEquals(expected.toArray(new Integer[3]), result.toArray(new Integer[3]));

        //When And Then for iteration
        result = BinaryTreeInOrder.inorderTraversal_iteration(root);
        Assert.assertArrayEquals(expected.toArray(new Integer[3]), result.toArray(new Integer[3]));
    }

    @Test
    public void test_empty() {
        //Given

        //When And Then for recursion
        List<Integer> result = BinaryTreeInOrder.inorderTraversal_recursion(null);
        Assert.assertTrue(result.size() == 0);

        //When And Then for iteration
        result = BinaryTreeInOrder.inorderTraversal_iteration(null);
        Assert.assertTrue(result.size() == 0);
    }
}
