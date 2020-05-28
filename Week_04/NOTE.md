# 学习笔记  

## 算法思想
   1. 深度优先搜索(DFS)
   2. 广度优先搜索(BFS)
   3. 贪心算法
   4. 二分查找
   

### 深度优先搜索
#### 1. 概述
深度优先搜索是一种搜索树和图的算法，这种算法由根节点开始，对一条给定的路径做尽可能深的搜索，然后回溯到最近的未探索的路径,并继续上述搜索。这个算法会重复上述步骤，直到树或图中所有节点都被探索过为止。  

在计算机科学中，很多问题都可以归结成图，如分析网络、地图路由、规划、寻找生成树等。分析这些问题时，像深度优先算法是非常有用的。  

深度优先算法也经常作为其他复杂算法的子程序，如匹配算法、hopcroft-karp算法等，利用深度优先算法在图中寻找匹配项。  

深度优先算法也经常被在树的遍历中，如树的搜索。此外，深度优先算法也是迷宫和数独等问题很好的解决方法。  

在树和图中，节点本身称为顶点(vertex), 节点之间的线称为边(edge)。每次搜索时，要沿边搜索，访问过的节点需要被标记为"已访问"。

对于二叉树的遍历，深度优先搜索可分为先序、中序、后序遍历，其区别就是根节点被访问的顺序

#### 2. 代码模板

递归版本：
和老师的版本略有不同，用判空替代了访问判断。
```java
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

        //清理
        //视情况，如返回的结果是树或图的子集，则需要恢复现场到本次递归前的状态
}
```
非递归版本：  
基于二叉树的前、中、后序遍历整理的代码模板，与老师的略有不同，没有做访问判断，而是在入队时判断节点是否能入队。
```java
public void dfs(Node root) {
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
    }
}
```
       
    
## 做题记录
|题目类型|知识点|题目   |完成情况|完``成时间|
|-------|-----|---|-------|------|
|实战题目|BFS、DFS|[102 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description)|完成|5月11日|
|实战题目|BFS、DFS|[433 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/#/description)|完成|5月12日|
|作业|贪心|[860 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/description/)|完成|5月13日|
|作业|贪心|[122 股票买卖最佳时机II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/)|完成|5月13日|
   
5月11日(星期一)  
主题：深度优先、广度优先遍历   
技能：递归遍历、循环遍历

[LeetCode 102. 二叉树的层级遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)   
- 思路：  
    - BFS：  
        - 层级遍历是典型的广度优先场景，比较符合直觉的是循环遍历。  
        - 总体想法是使用队列，在某一层，先把队列中的节点出队并处理完毕，即把上一层节点处理完毕，然后再依次把本层<font color="red">非空</font>节点加入队列，直到队列为空，则表示全部节点都已处理。  
        - 特别需要注意的是一定是"非空"节点才能加入队列，否则会报错。  
        - 针对输出要求按层归类，有两种方法可实现：  
            - 将下一层的所有节点放到一个临时queue中，处理完当前层节点后，将队列指向临时queue。好处是简单明了，坏处是额外占用了空间。  
            - 使用当前队列中节点个数来决定出队多少节点，因为入队时可确保仅且仅有同一层的所有节点同时入队，出队的节点则一定是同一层的所有节点。这种方式更好，不需额外占用空间。  
        -  代码：  
        
        ```java
            public List<List<Integer>> levelOrder_bfs(TreeNode root) {
                List<List<Integer>> result = new ArrayList<>();
                if (root == null) return result;
                LinkedList<TreeNode> queue = new LinkedList<>();
                queue.push(root);
                while (!queue.isEmpty()) {
                    List<Integer> cr = new ArrayList<>(); TreeNode cn; int n = queue.size();
                    while (n-- > 0) {
                        cn = queue.pop(); cr.add(cn.val);
                        if (cn.left != null) queue.addLast(cn.left);
                        if (cn.right != null) queue.addLast(cn.right);
                    }
                    result.add(cr);
                }
                return result;
            }
        ```        
          
    - 递归:
        - 用递归也可以实现广度优先遍历，只不过有点反直觉，关键是要用`level`来识别当前层数。    
        - 针对输出要求按层归类，可使用参数`level`来初始化当前层结果集` if (result.size() == level) result.add(new ArrayList<>())`。这句代码的依据是在深度优先遍历时，层数是逐级递增的，走过一层就会加一个子结果集，那么恰好子结果集的个数比遍历过的层数少1，所以这句代码成立。  
        - 先序遍历递归代码：
        ```java
            public List<List<Integer>> levelOrder_recurse(TreeNode root) {
                List<List<Integer>> result = new ArrayList<>();
                dfs1(root, 0, result);
                return result;
            }
        
            private void recurse(TreeNode root, int level, List<List<Integer>> result) {
                if (root == null) return;
                if (result.size() == level) result.add(new ArrayList<>());
                result.get(level).add(root.val);
                if (root.left != null) recurse(root.left, level+1, result);
                if (root.right != null) recurse(root.right, level+1, result);
            }
        ``` 
      
5月12日(星期二)    
主题：深度优先、广度优先遍历   
技能：递归遍历、循环遍历

[LeetCode 443. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/)  
- 感悟：
    - 如果是找最少层数，BFS效率较高；如果是找其中一个结果，DFS效率较高。  
    - 原因和BFS和DFS的遍历方式有关，BFS是水波纹式遍历，每次遍历走遍某层所有节点，DFS是打破砂锅式遍历，每次遍历会走完某路径上所有节点。  
    
- 意外：
    - 在LeetCode上提交DFS代码时遇到一个bug，相同的用例，相同的DFS代码，在"执行代码"和"提交"时输出竟然不一样。
    - 用例如下：**"AACCGGTT","AAACGGTA",["AACCGGTA","AACCGCTA","AAACGGTA"]**  
    
- 思路：
    - 一开始没有思路，看了答案才意识到是一个图的路径搜索问题，可以用BFS和DFS来解决。  
    - 开始基因序列可以看做图的起点，结束基因序列可以看做图中的某节点，基因库可以看做图的顶点集合，找最少变化次数就是在图的顶点中寻找起点和重点间的最短路径，要求是每次变化一个基因，及每次变化一个基因的顶点间才可以画边。  
    - 把问题抽象好了就简单了，基因碱基是可枚举的，每个位置变化的可能就是碱基的个数。把每次变化后的序列都去基因库里查找，看是否是合法的顶点，是的话放入队列，表示此路可能走的通，否的话不处理，表示此路不通。  
    - 处理队列时，遇到合法的序列，需要在基因库中将其删除，表示已处理过该顶点了，否则会导致重复处理的问题。
        - BFS：
            ```java
                  public static int minMutation(String start, String end, String[] bank) {
                      // 转换成基因序列集合，提升后续比对效率
                      HashSet<String> geneSequences = new HashSet<>(Arrays.asList(bank));
                      // 验证最终基因序列是否合法，当然，本题已假定必然合法，可不加
                      if (!geneSequences.contains(end)) {
                          return -1;
                      }
                      // 碱基集合
                      char[] basePairs = new char[]{'A','C','G','T'};
                      // 存放中间基因序列
                      LinkedList<String> queue = new LinkedList<>();
                      // 起始基因序列入队
                      queue.offer(start);
                      // 变化次数，即BFS走过的层数
                      int step = 0;
                      // 所有的中间序列都处理完毕，才意味着BFS走完了所有可能的路径
                      while (!queue.isEmpty()) {
                          // 进入下一层
                          step++;
                          // 一次最外层while需要把当前queue中的序列都处理掉，即处理BFS的完整的一层
                          // 确定本次要出队多少中间序列
                          int geneSequenceNumInQueue = queue.size();
                          // 遍历并出队中间序列
                          while (geneSequenceNumInQueue-- > 0) {
                              String currGeneSequence = queue.pop();
                              char[] genes = currGeneSequence.toCharArray();
                              // 两层循环，替换每一位上的基因，并比对处理
                              for (int i = 0; i < genes.length; i++) {
                                  // 保存原始的基因
                                  char oldGene = genes[i];
                                  for (int j = 0; j < basePairs.length; j++) {
                                      // 替换基因
                                      genes[i] = basePairs[j];
                                      String newGeneSequence = String.valueOf(genes);
                                      // 验证新的序列是否合法
                                      if (geneSequences.contains(newGeneSequence)) {
                                          // 找到了最终序列
                                          if (newGeneSequence.equals(end)) {
                                              return step;
                                          }
                                          // 合法但不是最终序列，即发现了新的中间序列，加入队列
                                          queue.offer(newGeneSequence);
                                          // 从基因序列集合中删除，表示已访问过BFS的某顶点
                                          geneSequences.remove(newGeneSequence);
                                      }
                                  }
                                  // 恢复成原始的基因
                                  genes[i] = oldGene;
                              }
                          }
                      }
                      return -1;
                  }
            ```
        - DFS：
            ```java
                  static int minStep = Integer.MAX_VALUE;
                  public static int minMutation(String start, String end, String[] bank) {
                      dfs(new HashSet<>(), 0, start, end, bank); 
                      return minStep == Integer.MAX_VALUE ? -1 : minStep; 
                  }
              
                  private static void dfs(HashSet<String> visited, int step, String curr, String end, String[] bank) {
                      if (curr.equals(end))  minStep = Math.min(step, minStep);
                      for (String s : bank) {
                          int diff = 0;
                          for (int i = 0; i < s.length(); i++) {
                              if (curr.charAt(i) != s.charAt(i)) diff++;  
                              if (diff > 1) break;
                          }
                          if (diff == 1 && !visited.contains(s)) {
                              visited.add(s);
                              dfs(visited, step+1, s, end, bank);
                              visited.remove(s);
                          }
                      }
                  }
            ```
[LeetCode 22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/#/description)
- 思路：
    - 抽象之后，和443题很像了，只不过本题换成了二叉树的遍历。老一套，BFS和DFS。无他，唯手熟尔。  
    - BFS：
        - 借助一个辅助类来存放中间状态，针对本题，就是左、右括号的剩余数量和中间状态的括号。
            ```java
                  public List<String> generateParenthesis(int n) {
                      List<String> result = new ArrayList<>();
                      if (n <= 0) return result;
                      LinkedList<Bracket> queue = new LinkedList<>();
                      queue.offer(new Bracket("(", n-1, n));
                      while (!queue.isEmpty()) {
                          Bracket curr = queue.pop();
                          if (curr.left == 0 && curr.right == 0) result.add(curr.bracket);
                          if (curr.left > 0) queue.offer(new Bracket(curr.bracket+"(", curr.left-1, curr.right));
                          if (curr.right > curr.left) queue.offer(new Bracket(curr.bracket+")", curr.left, curr.right-1));
                      }
                      return result;
                  } 
          
                  class Bracket {
                      public int left;
                      public int right;
                      String bracket;
                     
                      public Bracket(String curr, int left, int right) {
                          this.bracket = curr;
                          this.left = left;
                          this.right = right;
                      }
                  } 
            ```
    - DFS：
        - 递归，有两种写法，一种用StringBuilder来回溯，需要恢复现场，但占用空间少；一种是每次用新String，不用恢复现场，但会创建很多中间状态String，空间占用多一些。
            ```java
                  public static List<String> generateParenthesis_dfs(int n) {
                      List<String> result = new ArrayList<>();
                      dfs(new StringBuilder(), n, n, result);
                      return result;
                  }
              
                  private static void dfs(StringBuilder curr, int left, int right, List<String> result) {
                      if (left == 0 && right == 0) {
                          result.add(curr.toString());
                          return;
                      }
              
                      if (left > 0) {
                          dfs(curr.append("("), left-1, right, result);
                          curr.deleteCharAt(curr.length()-1);
                      }
                      if (right > left) {
                          dfs(curr.append(")"), left, right-1, result);
                          curr.deleteCharAt(curr.length()-1);
                      }
                  }   
            ```
5月13日(星期四)        
主题：贪心  
技能：贪心

[LeetCode 860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/description/)    
- 思路:  
    - 关键是找出找零的策略，只有3种面值，找零的方法可以穷举。
    - 用贪心的话，高面值的分支需要些在前面。 
    - 代码   
        ```java
              public boolean lemonadeChange(int[] bills) {
                  int five = 0, ten = 0;
                  for (int bill : bills) {
                      if (bill == 5) five++;
                      else if (bill == 10 && five > 0) {five--; ten++;}
                      else if (bill == 20 && (ten > 0 && five > 0)) {five--;ten--;}
                      else if (bill == 20 && (five >= 3)) five -=3;
                      else return false;
                  }
                  return true;
              } 
        ```
      
[LeetCode 122. 买卖股票的最佳时机II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/)      
- 思路:
    - 首先想到是DFS，找到所有赚钱的路径，然后返回赚钱最多的那个。当然，这样做相当于遍历整个递归树，在节点数量增加的情况下，需要遍历的节点数会指数增加。  
    - 本周讲贪心，那么用贪心的思维来思考。最大化收益就是赚到每一分钱，及所有正收益。如何计算所有正收益？可以看到，正收益只发生在第二天的价格比前一天高，那么判断每两天间的价格、累加正收益就能得到总最大收益。 
    - 递归:
        ```java
              static int profit = 0;
              public static int maxProfit_dfs(int[] prices) {
                 dfs(0, prices);
                 return profit;
              }
        
              private static void dfs(int level, int[] prices) {
                 if (level == prices.length-1) {
                     return ;
                 }
        
                 if (level+1<prices.length && prices[level] < prices[level+1]) {
                     profit = profit + (prices[level+1] - prices[level]);
                 }
                 dfs(level+1, prices);
              }
        ```
    - 直接遍历：
        ```java
              public static int maxProfit(int[] prices) {
                  int profit = 0;
                  for (int i = 1; i < prices.length; i++) {
                      if (prices[i] > prices[i-1]) profit += prices[i] - prices[i-1];
                  }
                  return profit;
              }   
        ```
[LeetCode 455. 分发饼干](https://leetcode-cn.com/problems/assign-cookies/description/)    
- 思路：
    - 类似田忌赛马，最优解是尺寸最大的饼干给胃口最大的孩子。针对贪心，可以分别对小孩和饼干排序，然后再分配。
    - 代码：
        ```java
              public int findContentChildren(int[] g, int[] s) {
                  Arrays.sort(g); Arrays.sort(s);
                  int result = 0, childNum = 0, cookieNum = 0;
                  while (childNum < g.length && cookieNum < s.length) {
                      if (g[childNum] <= s[cookieNum]) {cookieNum++; childNum++; result++;}
                      else cookieNum++;
                  }
                  return result;
              }  
        ```
5月14日(星期五)
工作忙，只复习了二分算法代码模板和DFS、BFS代码模板。

5月15日(星期六)
家庭出游

5月16日(星期日)
家庭出游
