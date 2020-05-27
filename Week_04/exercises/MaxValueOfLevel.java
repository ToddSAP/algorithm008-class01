package Week_04.exercises;

import Week_02.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MaxValueOfLevel {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size(), max = Integer.MIN_VALUE;
            while (levelNum-- > 0) {
                TreeNode curr = queue.pop();
                max = Math.max(max, curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            result.add(max);
        }
        return result;
    }
}
