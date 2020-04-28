package Week_02.n_ary_tree_level_order_travesal;

import Week_02.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<List<Integer>> levelOrder_traversal(Node root) {
        return null;
    }


    public static List<List<Integer>> levelOrder_recursion1(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrder_recursion1(root, 1, result);
        return result;
    }

    public static void levelOrder_recursion1(Node node, int level, List<List<Integer>> result) {
        // terminator
        if (node == null) {
            return;
        }

        // process current level
        if (result.size() < level) {
            List<Integer> currLevelResult = new ArrayList<>();
            currLevelResult.add(node.val);
            result.add(currLevelResult);
        } else {
            result.get(level - 1).add(node.val);
        }

        // drill down
        if (node.children != null) {
            node.children.stream().forEach(child -> levelOrder_recursion1(child, level + 1, result));
        }
    }

    @Test
    public void test_() {
        //Given
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        root.children = new ArrayList<>();
        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);
        Node child5 = new Node(5);
        Node child6 = new Node(6);
        child3.children = new ArrayList<>();
        child3.children.add(child5);
        child3.children.add(child6);

        //When
        List<List<Integer>> result = NAryTreeLevelOrderTraversal.levelOrder_recursion1(root);

        //Then
        Assert.assertTrue(result.get(1).get(2)==4);
    }
}
