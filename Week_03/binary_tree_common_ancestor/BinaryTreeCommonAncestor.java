package Week_03.binary_tree_common_ancestor;

import Week_02.TreeNode;

public class BinaryTreeCommonAncestor {
    /**
     * 思路:
     *      1. 暴力法：分别找到两个节点的所有祖先，然后按深度由大到小对比是否有相同的节点。找到节点需要O(n),查找节点祖先需要O(logn),总体时间O(n)。空间O(logn)。
     *      2. 递归：根据p和q在左右分支的情况递归判定。
     */

    private TreeNode ancestor;

    public TreeNode lowestCommonAncestor_brute(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ancestor;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean leftChild = dfs(root.left, p, q);
        boolean rightChild = dfs(root.right, p, q);
        if ((leftChild && rightChild) || (root.val == p.val || root.val == q.val) && (leftChild && rightChild) ) {
            ancestor = root;
        }
        return leftChild || rightChild || (root.val == p.val || root.val == q.val);
    }
}
