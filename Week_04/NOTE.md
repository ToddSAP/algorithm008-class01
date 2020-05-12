#学习笔记
##算法思想
   1. 深度优先搜索(DFS)
   2. 广度优先搜索(BFS)
   3. 贪心算法
   4. 二分查找
   

###深度优先搜索
####1. 概述
深度优先搜索是一种搜索树和图的算法，这种算法由根节点开始，对一条给定的路径做尽可能深的搜索，然后回溯到最近的未探索的路径,并继续上述搜索。这个算法会重复上述步骤，直到树或图中所有节点都被探索过为止。  

在计算机科学中，很多问题都可以归结成图，如分析网络、地图路由、规划、寻找生成树等。分析这些问题时，像深度优先算法是非常有用的。  

深度优先算法也经常作为其他复杂算法的子程序，如匹配算法、hopcroft-karp算法等，利用深度优先算法在图中寻找匹配项。  

深度优先算法也经常被在树的遍历中，如树的搜索。此外，深度优先算法也是迷宫和数独等问题很好的解决方法。  

在树和图中，节点本身称为顶点(vertex), 节点之间的线称为边(edge)。每次搜索时，要沿边搜索，访问过的节点需要被标记为"已访问"。

对于二叉树的遍历，深度优先搜索可分为先序、中序、后序遍历，其区别就是根节点被访问的顺序

####2. 代码模板

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
       
    
##做题记录
|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|实战题目|BFS|[102 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description)|完成|5月11日|
|实战题目|BFS|[433 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/#/description)|||
   
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

