package Week_02.n_ary_tree_postorder_traversal;

import Week_02.Node;

import java.util.LinkedList;
import java.util.List;

public class NAryTreePostOrderTraversal {
    /**
     * 思路：
     *      1. 递归：递推公式 for (child : node.children) { recurse(child);} process(node)  终止条件：node == null
     *      2. 迭代：按照先序遍历的顺序处理节点，但添加结果时需要addFirst，即反序添加
     */


    public static List<Integer> postorder_recursion(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        recurse(root, result);
        return result;
    }

    public static List<Integer> postorder_iteration(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            result.addFirst(curr.val);
            if (curr.children != null) {
                for (Node child : curr.children) {
                    if (child != null) {
                        stack.push(child);
                    }
                }
            }
        }
        return result;
    }

    private static void recurse(Node root, LinkedList<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.children != null) {
            for (Node child : root.children) {
                recurse(child, result);
            }
        }
        result.addLast(root.val);
    }
}
