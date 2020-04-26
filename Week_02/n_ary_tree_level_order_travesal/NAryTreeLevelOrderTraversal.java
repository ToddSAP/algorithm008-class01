package Week_02.n_ary_tree_level_order_travesal;

import Week_02.Node;

import java.util.ArrayList;
import java.util.List;

public class NAryTreeLevelOrderTraversal {
    /**
     * 思路：
     *      1. 递归：每次递归时将每层的所有节点放到结果中。时间O(n),空间O(log(n))。
     *      2. 迭代：使用队列将节点顺序入队，另一个队列记录层数。时间O(n),空间O(n)。
     */


    public static List<List<Integer>> levelOrder_recursion(Node root) {
        return recurse(root, 0,  new ArrayList<>());
    }

    private static List<List<Integer>> recurse(Node node, int level, List<List<Integer>> result) {
        if (node == null) {
            return result;
        }
        List<Integer> list = result.size() > level ? result.get(level) : new ArrayList<>();
        list.add(node.val);
        if (result.size() <= level) {
            result.add(list);
        }
        for (Node n : node.children) {
            recurse(n, level + 1, result);
        }
        return result;
    }
}
