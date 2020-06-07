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
 - 并查集是一种设计精巧的数据结构，专门用来解决集合相交问题。  
 - 并查集主要有合并和查找两个操作，分别对应`unite`和`search`。  
 - 在合并的时候注意合并的方向，将高度小的集合合并到高度达的集合中。  
 - 在查找时可以开启路径压缩，在后续查找时提升效率。  
 - 路径压缩是基于相邻元素都有公共根节点，那么构建只有两层高度的树即可表达相邻元素间的关系。第一层是公共根节点，第二层是其他相邻节点。  
 - 路径压缩本质上是舍弃了节点间相邻的细节，直接保存节点相邻这个结果。  
 
 
### 高级剪枝(prune)


## 做题记录
|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|课后练习|Trie树|[208 实现Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)|完成|6月1日|
|课后练习|Trie树|[212 单词搜索II](https://leetcode-cn.com/problems/word-search-ii/)|完成|6月1日|
|作业|并查集|[547 朋友圈](https://leetcode-cn.com/problems/friend-circles/)|完成|6月4日|
|作业|并查集|[200 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)|完成|6月4日|


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
        - 对于小写英文字母，通过asicc码的计算可以直接定位数组下标，故用数组来存节点比较高效。  
        - 每个叶子节点存完整的字符串，可以替代`isEnded`标志位。  
        - 如果不在叶子节点中存完整字符串，也可以在DFS中用`StringBuilder`来存中间字符串以达到相同效果。  
    ```java
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
    ```

[547 朋友圈](https://leetcode-cn.com/problems/friend-circles/)
- 思路：
    - DFS：从一个点出发，找该点所有右边和下边的相邻节点，递归搜索，直到完成，则完成了一个朋友圈的遍历。注意的是，每搜索完一个相邻节点，要将该节点标记为visited。  
    - BFS：从一个点出发，找该点所有右边和下边的相邻节点加入队列，完成一轮搜索，然后进行第二轮搜索，直到队列为空。  
    - 并查集：构造一个并查集，遍历相邻点且合并后，输出连通分量的个数即可。  
    ```java
                 public int findCircleNum(int[][] M) {
                     if (M == null || M.length == 0) return 0;
                     int n = M.length;
                     UF uf = new UF(n);
                     for (int i = 0; i < n; i++) {
                         for (int j = 0; j < i; j++) {
                             if (M[i][j] == 1) {
                                 uf.unite(i, j);
                             }
                         }
                     }
                     return uf.count;
                 }
             
                 static class UF {
                     private int count;
                     private int[] parent;
                     private int[] size;
             
                     public UF (int n) {
                        this.count = n;
                        parent = new int[n];
                        size = new int[n];
                         for (int i = 0; i < n; i++) {
                             parent[i] = i;
                             size[i] = 1;
                         }
                     }
             
                     public boolean connected(int p, int q) {
                         return find(p) == find(q);
                     }
             
                     public void unite (int p, int q) {
                         int rootP = find(p);
                         int rootQ = find(q);
                         if (rootP == rootQ) return;
             
                         if (size[rootP] > size[rootQ]) {
                             parent[rootQ] = rootP;
                             size[rootP] += size[rootQ];
                         } else {
                             parent[rootP] = rootQ;
                             size[rootQ] += rootP;
                         }
                         count--;
                     }
             
                     private int find (int x) {
                         while (parent[x] != x) {
                             // 路径压缩
                             parent[x] = parent[parent[x]];
                             x = parent[x];
                         }
                         return x;
                     }
                 }   
    ```
  
[200 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)
- 思路：
    - 和547 朋友圈如出一辙，DFS、BFS和并查集都行，练练手把。  
    ```java
                  public int numIslands(char[][] grid) {
                         if (grid == null || grid.length == 0) return 0;
                         int rowNum = grid.length, colNum = grid[0].length;
                         if (colNum < 1) return 0;
                         UF uf = new UF(rowNum);
                         for (int i = 0; i < rowNum; i++) {
                             for (int j = 0; j < colNum; j++) {
                                 if (grid[i][j] == '1') {
                                     grid[i][j] = '0';
                                     if (rowNum - 1 > 0 && grid[i-1][j] == '1') uf.unite(i-1, j);
                                     if (rowNum + 1 < rowNum && grid[i+1][j] == '1') uf.unite(i+1, j);
                                     if (colNum - 1 > 0 && grid[i][j-1] == '1') uf.unite(i, j-1);
                                     if (colNum + 1 < colNum && grid[i][j+1] == '1') uf.unite(i, j+1);
                                 }
                             }
                         }
                         return uf.count;
                     }
                 
                     static class UF {
                         // 连通分量数量
                         private int count;
                         // 根节点关系列表
                         private int[] parent;
                         // 各集合中的元素个数
                         private int[] size;
                 
                         public UF (int n) {
                             count = n;
                             parent = new int[n];
                             size = new int[n];
                             for (int i = 0; i < n; i++) {
                                 parent[i] = i;
                                 size[i] = 1;
                             }
                         }
                 
                         public void unite(int p, int q) {
                             int rootP = search(p);
                             int rootQ = search(q);
                             if (rootP == rootQ) return;
                             if (size[rootP] > size[rootQ]) {
                                 parent[rootQ] = parent[rootP];
                                 size[rootP] += size[rootQ];
                             } else {
                                 parent[rootP] = parent[rootQ];
                                 size[rootQ] += size[rootP];
                             }
                             count--;
                         }
                 
                         public int search(int p) {
                             while (parent[p] != p) {
                                 parent[p] = parent[parent[p]];
                                 p = parent[p];
                             }
                             return p;
                         }
                     }               
    ```