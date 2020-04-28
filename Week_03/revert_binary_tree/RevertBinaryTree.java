package Week_03.revert_binary_tree;

import Week_02.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class RevertBinaryTree {
    /**
     * 思路：
     *      1. 递归：递归每个节点，遍历顺序没关系，将每个节点的左右节点互换即可。时间O(n),空间O(n).
     *      2. 迭代：每个节点入队，然后每轮出队将该节点的左右节点互换，直到队列为空。时间O(n),空间O(n)。
     */


    public static TreeNode invertTree_recurse(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree_recurse(root.right);
        TreeNode left = invertTree_recurse(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    public static TreeNode inverTree_traversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode currLeft = curr.left;
            TreeNode currRight = curr.right;
            curr.left = currRight;
            curr.right = currLeft;

            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }
        return root;
    }

    @Test
    public void test_official() {
        //Given

        //When And Then for recursion
        TreeNode result = RevertBinaryTree.invertTree_recurse(getTreeNode());
        Assert.assertEquals(result.left.val, 7);
        Assert.assertEquals(result.right.val, 2);


        //When And Then for traversal
        result = RevertBinaryTree.inverTree_traversal(getTreeNode());
        Assert.assertEquals(result.left.val, 7);
        Assert.assertEquals(result.right.val, 2);
    }

    private TreeNode getTreeNode() {
        TreeNode root = new TreeNode(4);
        TreeNode child1 = new TreeNode(2);
        TreeNode child2 = new TreeNode(7);
        root.left = child1;
        root.right = child2;
        TreeNode child1Left = new TreeNode(1);
        TreeNode child1Right = new TreeNode(3);
        child1.left = child1Left;
        child1.right = child1Right;
        TreeNode child2Left = new TreeNode(6);
        TreeNode child2Right = new TreeNode(9);
        child2.left = child2Left;
        child2.right = child2Right;
        return root;
    }
}
