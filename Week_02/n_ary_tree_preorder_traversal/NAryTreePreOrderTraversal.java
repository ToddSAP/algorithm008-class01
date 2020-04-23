package Week_02.n_ary_tree_preorder_traversal;

import Week_02.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NAryTreePreOrderTraversal {
    /**
     * 思路：
     *      1. 递归：递推公式 process(node); for (child : node.children) { recurse(child);} 终止条件：node == null
     *      2. 迭代：按照先序遍历的顺序处理节点，在处理子节点时注意要反序入栈，出栈时利用栈的特性再反序，最终是按正序遍历子节点
     */

    public static List<Integer> preorder_recursion(Node root) {
        List<Integer> result = new ArrayList<>();
        recurse(root, result);
        return result;
    }

    public static List<Integer> preorder_iteration(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            //处理当前节点
            result.add(curr.val);
            //如果子节点不为Null，将所有子节点反序加入栈,
            //出栈时则会按照子节点的正序出栈，正符合先序遍历的规则
            if (curr.children != null) {
                for (int i = curr.children.size() - 1; i >= 0; i--) {
                    if (curr.children.get(i) != null) {
                        stack.push(curr.children.get(i));
                    }
                }
            }
        }
        return result;
    }

    private static void recurse(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                recurse(root.children.get(i), result);
            }
        }
    }

}
