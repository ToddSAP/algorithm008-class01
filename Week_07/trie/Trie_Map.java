package Week_07.trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Trie_Map {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie_Map() {
        root = new TrieNode(false, new HashMap());
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Objects.requireNonNull(word);
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.isEnded = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Objects.requireNonNull(word);
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) return false;
        }
        return curr.isEnded;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Objects.requireNonNull(prefix);
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) return false;
        }
        return true;
    }


    static class TrieNode {
        public boolean isEnded;
        public Map<Character, TrieNode> children;

        TrieNode () {
            this.children = new HashMap<>();
        }
        TrieNode (boolean isEnded, Map<Character, TrieNode> children) {
            this.isEnded = isEnded;
            this.children = children;
        }
    }

    @Test
    public void test_official() {
        //Given
        Trie_Map trie = new Trie_Map();
        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        Assert.assertTrue(trie.startsWith("app"));
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));

        //When

        //Then
    }
}
