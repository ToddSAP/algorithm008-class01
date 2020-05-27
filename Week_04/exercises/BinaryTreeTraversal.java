package Week_04.exercises;

import Week_02.TreeNode;
import Week_04.min_mutation.Exercise;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeTraversal {
    public List<List<Integer>> levelOrder_bfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> currResult = new ArrayList<>();
            while (levelNum-- > 0) {
                TreeNode curr = queue.pop();
                currResult.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            result.add(currResult);
        }
        return result;
    }

    public List<List<Integer>> levelOrder_dfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode currNode, int level, List<List<Integer>> result) {
        if (currNode == null) return;
        if (level == result.size()) result.add(new ArrayList<>());
        result.get(level).add(currNode.val);
        if (currNode.left != null) dfs(currNode.left, level+1, result);
        if (currNode.right != null) dfs(currNode.right, level+1, result);
    }

    public static int minMutation(String start, String end, String[] bank) {
        if (start.isEmpty() || start == null || bank == null || bank.length == 0) return -1;
        List<String> bankList = new ArrayList<>(Arrays.asList(bank));
        char[] mutations = new char[]{'A','G','T','C'};
        LinkedList<String> queue = new LinkedList();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int itemNum = queue.size();
            while (itemNum-- > 0) {
                String curr = queue.pop();
                char[] gens = curr.toCharArray();
                for (int i = 0; i < gens.length; i++) {
                    char oldGen = gens[i];
                    for (int j = 0; j < mutations.length; j++) {
                        gens[i] = mutations[j];
                        String newGens = new String(gens);
                        if (newGens.equals(end) && bankList.contains(end)) return step;
                        if (bankList.contains(newGens)) {
                            queue.offer(newGens);
                            bankList.remove(newGens);
                        }
                    }
                    gens[i] = oldGen;
                }
            }
        }
        return -1;
    }

    @Test
    public void test_exercises() {
        //Given

        //When
        /*int result = BinaryTreeTraversal.minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"});
        Assert.assertEquals(result, 3);*/

        /*int result = BinaryTreeTraversal.minMutation("AACCTTGG", "AATTCCGG", new String[]{"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"});
        Assert.assertEquals(result, -1);*/

        int result = BinaryTreeTraversal.minMutation("AAAAAAAA", "CCCCCCCC", new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"});
        Assert.assertEquals(result, -1);

        //Then
    }
}
