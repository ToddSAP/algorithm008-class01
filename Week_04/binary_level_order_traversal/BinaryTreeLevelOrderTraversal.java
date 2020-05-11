package Week_04.binary_level_order_traversal;

import Week_02.TreeNode;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    /**
     * 思路：
     *      1. DFS: 使用代码模板迭代
     *      2. 递归：利用level来定位层的结果集
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 终止条件
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            LinkedList<TreeNode> nextLevelNodes = new LinkedList<>();

            List<Integer> currResult = new ArrayList<>();
            while (!queue.isEmpty()) {
                // 处理当前层
                TreeNode currNode = queue.pop();
                currResult.add(currNode.val);

                // 下钻
                if (currNode.left != null) {
                    nextLevelNodes.add(currNode.left);
                }
                if (currNode.right != null) {
                    nextLevelNodes.add(currNode.right);
                }
            }

            if (currResult.size() > 0) {
                result.add(currResult);
            }
            queue = nextLevelNodes;
        }

        return result;
    }


    public List<List<Integer>> levelOrder_enhanced(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 终止条件
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            int currLevelNodeNum = queue.size();
            List<Integer> currLevelNodes = new ArrayList<>();

            for (int i=0; i < currLevelNodeNum; i++) {
                // 处理当前层
                TreeNode currNode = queue.pop();
                currLevelNodes.add(currNode.val);

                // 下钻
                if (currNode.left != null) {
                    queue.addLast(currNode.left);
                }
                if (currNode.right != null) {
                    queue.addLast(currNode.right);
                }
            }

            if (currLevelNodes.size() > 0) {
                result.add(currLevelNodes);
            }
        }
        return result;
    }


    public List<List<Integer>> levelOrder_recurse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    public void dfs(TreeNode root, int level, List<List<Integer>> result) {
        // 终止条件
        if (root == null) {
            return;
        }

        if (result.size() == level) {
            result.add(new ArrayList<>());
        }
        // 处理当前层
        result.get(level).add(root.val);

        //下钻
        if (root.left != null) {
            dfs(root.left, level+1, result);
        }
        if (root.right != null) {
            dfs(root.right, level+1, result);
        }
    }

    public List<List<Integer>> levelOrder_bfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            List<Integer> cr = new ArrayList<>(); TreeNode cn; int n = queue.size();
            while (n-- > 0) {
                cn = queue.pop(); cr.add(cn.val);
                if (cn.left != null) queue.addLast(cn.left);
                if (cn.right != null) queue.addLast(cn.right);
            }
            result.add(cr);
        }
        return result;
    }


    public List<List<Integer>> levelOrder_dfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs1(root, 0, result);
        return result;
    }

    private void dfs1(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) return;
        if (result.size() == level) result.add(new ArrayList<>());
        result.get(level).add(root.val);
        if (root.left != null) dfs1(root.left, level+1, result);
        if (root.right != null) dfs1(root.right, level+1, result);
    }
}
