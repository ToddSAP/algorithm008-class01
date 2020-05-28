# 学习笔记

## 算法思想
1.  动态规划

### 动态规划
#### 1. 概述  

动态规划是一种解决多阶段决策过程最优问题的思维，是从分治思维演化出来的。  
动态规划的前提是问题可分解成重复子问题，且最终解可由阶段解推导得到。  
动态规划的基本思路是定义状态、找到最优子结构、得到状态转移方程和最终求解。  
动态规划较暴力枚举求解的优势在于，最优问题往往无需最优解细节，如求解最少数量而不是求解最少数量的路径。如果需要求解路径，则动态规划不适用，更适用广度优先遍历。  
动态规划舍弃了求解路径，只保留求解值(阶段最优值)，故自带剪枝技能。

#### 2. 状态定义  
状态也可以理解成维度，如对于背包问题，重量、物品价值、大小都是可能的维度。  

#### 3. 最优子结构  
寻找最优子结构的过程就是对问题进行建模，即建模最终解如何由最近的子问题推导得到。如爬楼梯问题(gap=1 || 2)，最终解只与f(n-1)和f(n-2)有关。  
常规的寻找思路是先考察最终解所在状态有哪些，如取和不取、加和不加等；然后再看哪些最近子问题是相关的，如爬到n-1级台阶和n-2级台阶的步数，之前的步数无关；最后写出状态转移方程。  

#### 4. 状态转移方程  
由最优子结构和问题综合考察后得出。  


# 做题记录

|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|课后习题|动态规划|[62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)|完成|5月27日|  
|课后习题|动态规划|[63. 不同路径II](https://leetcode-cn.com/problems/unique-paths-ii/)|完成|5月28日|

[62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)  
- 思路:  
    - 从最左上到最右下，由于只能往右和下，故每个合法解中往右的步数和往下的步数是固定的。这就可以转化成组合问题。  
        - 扣除最左上和最右下的格子，假设有m行n列，那么每个合法解都需要且仅需要向右移动m-1步和向下移动n-1步。  
        - 总的步数就是m-1 + n-1 = m+n-2。  
        - 如果某个合法解中向右的步骤确定了，那么相应地，向下的步骤也确定了，因为只能向下和向右。  
        - 好了，说了这么多，得出结论：需要计算的组合就是C(m-1, m+n-2)。  
        - 要注意计算阶乘时的整型溢出问题。本题有限定，有long即可，但终极杀器是BigDecimal。如有更好选择，请赐教(微信13482664259)。
        - 时间复杂度: 计算组合需要先计算排列P(m-1, m+n-2)，排列要计算阶乘(m+n-2)!，故O(m+n)。  
        - 空间复杂度: 只利用个别变量存阶乘，故O(1)。  
        ```java
                  public int uniquePaths_math(int m, int n) {
                      if (m <= 0 || n <= 0) return -1;
                      // 计算C(m-1, m+n-2)
                      int j = Math.max(m - 1, n - 1), h = Math.min(m - 1, n - 1), k = m + n - 2;
                      long s1 = 1, s2 = 1;
                      for (int i = k; i > j; i--) {
                          s1 *= i;
                      }
                      for (int i = 1; i <= h; i++) {
                          s2 *= i;
                      }
                      return (int) (s1/s2);
                  }       
        ```
        
    - 基础动归。初始化首行列后按状态转移方程dp[i][j] = dp[i-1][j] + dp[i][j-1]计算。  
        - 时间复杂度: 两层循环，故O(m*n)。 
        - 空间复杂度: 需要dp数组，故O(m*n)。
        ```java
                public int uniquePaths_dp(int m, int n) {
                    int[][] dp = new int[m][n];
                    for (int i = 0; i < m; i++) {
                        dp[i][0] = 1;
                    }
                    for (int i = 0; i < n; i++) {
                        dp[0][i] = 1;
                    }
                    for (int i = 1; i < m; i++) {
                        for (int j = 1; j < n; j++) {
                            dp[i][j] = dp[i-1][j] + dp[i][j-1];
                        }
                    }
                    return dp[m-1][n-1];
                }
        ```
    - 空间优化动归。利用只有一列的dp数组迭代计算。  
        - 时间复杂度: 依旧两层循环，故O(m*n)。  
        - 空间复杂度: 如果取min(m,n)做dp数组的行数，则O(min(m,n))，否则O(max(m,n))。  
        ```java
                public int unique_dp_enhance(int m, int n) {
                    int j = Math.min(m, n), z = Math.max(m, n);
                    int[] dp = new int[j];
                    Arrays.fill(dp, 1);
                    for (int i = 1; i < z; i++)
                        for (int k = 1; k < j; k++)
                            dp[k] += dp[k-1];
                    return dp[j-1];
                }
        ```

[63. 不同路径II](https://leetcode-cn.com/problems/unique-paths-ii/)  
- 思路：  
    - 在62题的基础上考虑障碍，如何将障碍编织在dp中，可以从第一行/列中的障碍和其他的障碍两方面思考  
    - 对于第一行/列，如果有障碍，那么遇到障碍前都还是可以走的，而遇到障碍后，障碍所在和之后的格子都不能走了。
        - 假设第一行有10列，第三列有障碍，转化成dp就是[1,1,0,0,0,0,0,0,0,0]。 
        - 假设有5行2列，第4行有障碍，转化成dp就是   
                                            [1,1]  
                                            [1,1]  
                                            [1,1]  
                                            [0,1]  
                                            [0,1]  
    - 对于非首行/列的障碍，只有在走到障碍所在格子时才不能通行，转化成dp就是dp[i][j] = 0。  
    - 对于首行/列非障碍的格子，都是可以通行的，故可初始化成dp[i][j] = 1。  
    - 对于非首行/列的格子，由于只能往右和下走，故其值都是由dp[i-1][j]和dp[i][j-1]计算而来，状态转移方程即dp[i][j] = dp[i-1][j] + dp[i][j-1]。  
- 代码:  
    ```java
              public int uniquePathsWithObstacles(int[][] obstacleGrid) {
                  // 找到有几行几列
                  int rowNum = obstacleGrid.length, colNum = -1;
                  if (rowNum <= 0) return 0; else colNum = obstacleGrid[0].length;
                  // 定义一个新dp
                  int[][] dp = new int[rowNum][colNum];
                  // 确认首行/列是否有障碍，及障碍之所在
                  boolean isFirstRowBlocked = false, isFirstColBlocked = false;
                  int firstRowBlockedIndex = -1, firstColBlockedIndex = -1;
                  for (int i = 0; i < rowNum; i++) {
                      if (obstacleGrid[i][0] == 1) {
                          firstColBlockedIndex = i;
                          isFirstColBlocked = true;
                          break;
                      }
                  }
                  for (int i = 0; i < colNum; i++) {
                      if (obstacleGrid[0][i] == 1) {
                          firstRowBlockedIndex = i;
                          isFirstRowBlocked = true;
                          break;
                      }
                  }
                  for (int i = 0; i < rowNum; i++) {
                      for (int j = 0; j < colNum; j++) {
                          // 根据所在行、列和障碍的关系决定格子如何计算值
                          // 格子值为0的场景: 1) 当前格子就是障碍 2) 首行，且当前格子就是障碍或在障碍之后 3)首列，且当前格子就是障碍或在障碍之下
                          if (obstacleGrid[i][j] == 1
                                  || (i == 0 && isFirstRowBlocked && j > firstRowBlockedIndex)
                                  || (j == 0 && isFirstColBlocked && i > firstColBlockedIndex)
                          ) {
                              dp[i][j] = 0;
                          } else if (i == 0 || j == 0) {
                              dp[i][j] = 1;
                          } else {
                              dp[i][j] = dp[i-1][j] + dp[i][j-1];
                          }
                      }
                  }
                  return dp[rowNum-1][colNum-1];
              } 
    ```                                               

