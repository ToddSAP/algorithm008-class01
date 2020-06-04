package Week_07.trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class Trie_Array {

    TrieNode root;

    public Trie_Array () {
        root = new TrieNode();
    }


    public void insert(String word) {
        Objects.requireNonNull(word);
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.isEnded = true;
    }

    public boolean search(String word) {
        Objects.requireNonNull(word);
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c-'a'] == null) return false;
            curr = curr.children[c-'a'];
        }
        return curr.isEnded;
    }

    public boolean startsWith(String prefix) {
        Objects.requireNonNull(prefix);
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c-'a'] == null) return false;
            curr = curr.children[c-'a'];
        }
        return true;
    }

    @Test
    public void test_official() {
        //Given
        Trie_Array trie = new Trie_Array();
        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        Assert.assertTrue(trie.startsWith("app"));
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));

        //When

        //Then
    }

    public static class TrieNode {
        public boolean isEnded;
        public TrieNode[] children;
        public final static int CHARACTER_NUM = 26;

        TrieNode () {
            this.children = new TrieNode[CHARACTER_NUM];
        }
        TrieNode (boolean isEnded, TrieNode[] children) {
            this.isEnded = isEnded;
            this.children = children;
        }
    }
}
