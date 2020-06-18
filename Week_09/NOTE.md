#  学习笔记

## 算法思想

### 1. 字符串操作
 - 基本思路是操纵字符串背后的字符数组。  
 - 反转字符串：用数组下标原地反转即可。  
 - 大小写：用char对应的ASCII进行运算来转换大小写效率最高。  
 - 计算特定子串长度：
    - 可复制char数组，根据需要在新char数组中做操作。这种解法的空间复杂度为O(n)。    
    - 可定义两个头尾指针，根据需要移动指针，最后根据头尾指针的位置截取子串。这解法的空间复杂度O(1)。  
 - 一个子串是否在另一个子串中：
    - 可以用暴力求解，时间复杂度O(n^2)，空间复杂度O(1)。  
    - 可以用Hash表来空间换时间，时间复杂度O(n)，空间复杂度O(m)。  
        - 在可枚举的情况下，可以用数组模拟Hash表来提升效率。  
    - 可以用Trie，时间复杂度O(m+nlog(n))，空间复杂度O(m)。  
    - 可以用并查集，时间复杂度O(m+nlog(n))，空间复杂度O(m)。 
 - 坑：
    - 不要忘记trim。  
    - 不要忘记判空。  
    - 不要忘记Java里String是Immutable的。  
    
### 2. 字符串匹配



## 做题记录
|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|作业|字符串操作|[709. 转换成小写字母](https://leetcode-cn.com/problems/to-lower-case/)|完成|6月16日|
|作业|字符串操作|[58. 最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/)|完成|6月16日|
|作业|字符串操作|[771. 宝石与石头](https://leetcode-cn.com/problems/jewels-and-stones/)|完成|6月16日|


[709. 转换成小写字母](https://leetcode-cn.com/problems/to-lower-case/)  
- 思路：
    - 暴力法：遍历每一个字符，对每个字符判断是否大写，是的话转成小写。其中，使用ASCII来判定大小写比较方便。  
        - 时间复杂度O(n)，空间复杂度O(1)。    
    - 辅助统计法：使用HashMap或字符数组来记忆每个字符出现的次数。如果字符不可枚举，使用HashMap；如果可枚举，使用字符数组效率更高。   
        - 时间复杂度O(n)，空间复杂度O(n)。     
    ```java
          public String toLowerCase(String str) {
              if (str == null || str.trim().equals("")) return str;
              char[] newCharArray = str.toCharArray();
              for (int i = 0; i < str.length(); i++)
                  if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                      newCharArray[i] = (char) (str.charAt(i) + 32);
              return new String(newCharArray);
          }
    ```
  
[58. 最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/)
- 思路：
    - 暴力法：遍历每个字符，遇到空格则记录单词开始/结束(start、end)，直到遇到最后一个字符，用end减去start即为结果。   
        - 时间复杂度：O(n)，空间复杂度O(1)。
    - 从后遍历法：先对字符串做后部trim，再自后向前遍历，直到遇到第一个空格，之间的下标相减即为结果。  
        - 可以做到原地，使用常数个指针，分别指向trim后字符串结尾下标、trim后字符串自后向前第一个空格下标。  
        - 时间复杂度O(n)，空间复杂度O(1)。  
    ```java
          public int lengthOfLastWord(String s) {
              if (s == null || s.length() == 0) return 0;
              int end = s.length()-1;
              while (end >= 0) if (s.charAt(end) == ' ') end--; else break;
              int start = end;
              for (int i = end; i >= 0; i--)
                  if (s.charAt(i) != ' ') start--; else break;
              return end - start;
          }
    ```
  
[771. 宝石与石头](https://leetcode-cn.com/problems/jewels-and-stones/)  
- 思路：
    - 暴力法：遍历并逐一统计每个字符是否珠宝。
        - 时间复杂度：每次遍历需要检查一次珠宝列表，故O(m*n)，空间复杂度O(1)。  
    - Hash表法：将珠宝列表放到Hash表里，再遍历字符串。  
        - 时间复杂度：O(m+n)，空间复杂度O(m)。  
    ```java
         public int numJewelsInStones(String J, String S) {
             if (J == null || S == null || J.length() == 0 || S.length() == 0) return 0;
             HashMap<Character, Character> jewelMap = new HashMap<>();
             for (char c : J.toCharArray())
                 jewelMap.put(c, c);
             int n = 0;
             for (char c : S.toCharArray())
                 if (jewelMap.get(c) != null) n++;
             return n;
         } 
    ```

