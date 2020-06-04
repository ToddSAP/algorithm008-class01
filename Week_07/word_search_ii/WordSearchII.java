package Week_07.word_search_ii;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearchII {
    /*public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0) return result;
        // 构造Trie树
        Trie_Array trie = new Trie_Array();
        for (String word : words) trie.insert(word);

        // DFS验证
        int rowNum = board.length, colNum = board[0].length;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (trie.search(String.valueOf(board[i][j]))) {
                    dfs(board, i, j,    );
                }
            }
        }
    }

    public void dfs(char[][] board, int row, int col) {

    }*/


    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    @Test
    public void test_official() {
        //Given
        WordSearchII wordSearchII = new WordSearchII();
        List<String> result = wordSearchII.findWords(new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain"});
        Assert.assertEquals(Arrays.asList(new String[]{"oath","eat"}), result);
        //When

        //Then
    }
}
