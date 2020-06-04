package Week_07.word_search_ii;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WordSearchII_practice {
    /**
     * 思路： 标准Trie树+回溯
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || words == null || board.length == 0 || words.length == 0) return result;
        int rowNum = board.length, colNum = board[0].length;
        if (colNum < 1) return result;
        TrieNode root = buildTrie(words);
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                dfs(i, j, root, board, new StringBuilder(), result);
            }
        }
        return result;
    }

    private void dfs(int i, int j, TrieNode node, char[][] board, StringBuilder sb, List<String> result) {
        char c = board[i][j];
        // #表示已经走过该节点了， null表示在Trie里找不到
        if (c == '#' || node.children[c-'a'] == null) return;
        node = node.children[c-'a'];
        sb.append(c);
        if (node.isEnded) result.add(sb.toString());

        board[i][j] = '#';
        if (i > 0) dfs(i-1, j, node, board, sb, result);
        if (j > 0) dfs(i, j-1, node, board, sb, result);
        if (i < board.length - 1) dfs(i+1, j, node, board, sb, result);
        if (j < board[0].length - 1) dfs(i, j+1, node, board, sb, result);
        board[i][j] = c;
        sb.deleteCharAt(sb.length()-1);
    }

    public TrieNode buildTrie(String[] word) {
        Trie trie = new Trie();
        Objects.requireNonNull(word);
        for (String s : word) trie.insert(s);
        return trie.getRoot();
    }

    static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isEnded;
    }

    static class Trie {
        private TrieNode root;

        public Trie () {
            root = new TrieNode();
        }

        public void insert(String word) {
            Objects.requireNonNull(word);
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c-'a'] == null) curr.children[c-'a'] = new TrieNode();
                curr = curr.children[c-'a'];
            }
            curr.isEnded = true;
        }

        public TrieNode getRoot() {
            return root;
        }
    }

    @Test
    public void test_official() {
        //Given
        WordSearchII_practice wordSearchII = new WordSearchII_practice();
        List<String> result = wordSearchII.findWords(new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain"});
        Assert.assertEquals(Arrays.asList(new String[]{"oath","eat"}), result);
        //When

        //Then
    }
}
