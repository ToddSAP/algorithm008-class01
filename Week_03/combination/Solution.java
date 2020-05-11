package Week_03.combination;

import Week_02.Node;
import Week_02.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    List<List<Integer>> output = new LinkedList();
    int n;
    int k;

    public void backtrack(int first, LinkedList<Integer> curr) {
        // if the combination is done
        if (curr.size() == k)
            output.add(new LinkedList(curr));

        for (int i = first; i < n + 1; ++i) {
            // add i into the current combination
            curr.add(i);
            // use next integers to complete the combination
            backtrack(i + 1, curr);
            // backtrack
            curr.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<Integer>());
        return output;
    }

    /*public void dfs(Node root) {
        LinkedList<Node> stack = new LinkedList();
        if (root == null) {
            return;
        }

        stack.offer(root);
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();

            //处理当前节点
            // ....

            //这里的判空必不可少，防止children未被初始化时下列for循环会抛NPE的问题
            if (currNode.children != null) {
                //将下层节点放到队列，注意点：
                // 1. 如果child为空，则表示已达路径尽头，出队的下个节点自然是下一个路径的起点
                // 2. 由于是dfs，加入队列的顺序一定要倒序，这样，按顺序出队时才是child从左到右
                for (int i = currNode.children.size() - 1; i >= 0; i--) {
                    if (currNode.children.get(i) != null) {
                        stack.addFirst(currNode.children.get(i));
                    }
                }
            }
        }
    }*/

    public void dfs(Node root) {
        // 终止条件
        if (root == null) {
            return;
        }

        // 处理当前层

        // 下钻
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                if (root.children.get(i) != null) {
                    dfs(root.children.get(i));
                }
            }
        }
    }
}
