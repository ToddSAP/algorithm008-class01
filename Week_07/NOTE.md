#  学习笔记
##  算法思想
1. Trie树
2. 并查集

###  Trie树
 - Trie树也称字典树，顾名思义，它是一个树形结构，树节点中值的存放是利于字典查找的。  
 - 具体来说，Trie树的根节点是不存字符的，只用来作为遍历的起点，其他节点只存单个字符。  
 - 节点之间的关系是上层字符引用下层字符，从上至下对树的一个分支做DFS，就可得到一个完整的字符串。  
 - Trie树在本质是利用字符串间的公共前缀，将重复的前缀合并在一起。  
 - Trie数需要存储字符串中的所有字符，所以是一种空间换时间的做法。极端情况下，没有任何公共子串，那么Trie树无法共享公共子串，将会占用更多空间。  
 - Trie树的适用场景：
    - 查询某个字符串是否在集合中  
    - 查询某个字符串出现的频次  
    - 查询包含某个公共子串的字符串的数量  
    
###  并查集
 -  


## 做题记录
|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|课后练习|Trie树|[208 实现Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)|完成|6月1日|
|课后练习|Trie树|[212 单词搜索II](https://leetcode-cn.com/problems/word-search-ii/)|完成|6月1日|

[208 实现Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)
- 思路:
    - 构造Trie树时可用哈希表或数组来作为存储子节点的数据结构。如果选择哈希表，用字符作为key，子节点作为value。如果选择数组，用'a'作为基准，c-'a'作为下标。  
    - 在查询Trie树时，看结束节点的isEnded是否为True，是的话则查到，否的话意味着target只是Trie树种的子串，表示改字符串不在Trie树种。  
    - 哈希表和数组各有利弊，如果字符集合是可枚举的，如英文小写字母，用数组较好，无需存储额外指针。如果字符集合不是可枚举的，如中文，用哈希表的扩展性较好，但效率较数组低一些。视场景灵活选择。  
    - 插入时间复杂度: 由于是直接遍历每个字符，故O(k)。空间复杂度：未占用额外空间，故O(1)。  
    - 查找时间复杂度: 需要遍历每个字符，故O(k)。空间复杂度：未占用额外空间，故O(1)。  
    ```java
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
          
              static class TrieNode {
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
    ```
    
[212 单词搜索II](https://leetcode-cn.com/problems/word-search-ii/)
- 思路:
    - 有两个查找方向：
        - 在board里找word，对于在board中的每个字符，都去words里验证是否某个字符串的合法部分。时间复杂度：board中的每个字符都要遍历，words中的字符串中的每个字符每次都要遍历，故O(m*n * w^k)。
        - 用word去board中查找是否存在合法路径。时间复杂度：O((m* n)^(w* k))
    - DFS: 找明细适合DFS或BFS。本题涉及四联通，用DFS更直观。
    - BFS: 用一个node维护一个子串状态，手工维护一个堆栈。  
    - Trie树: 和DFS类似，只不过用words先构造一个Trie树，然后用Trie树来验证每个路径是否合法。  
    ```java
          
    ```