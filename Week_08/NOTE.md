#  学习笔记

## 算法思想

### 1. 位运算
  - 位运算共有六种：与、或、非、异或、左移及右移，分别用符号`&`、`|`、`~`、`^`、`<<`、`>>`来表示。  
  - 与运算：两个位都为1，运算结果为1，否则为0。  
  - 或运算：两个位只要有1个为1，则运算结果为1，否则为0。  
  - 非运算：对位取反。  
  - 异或运算：两个位不相同才为1，否则为0。  
  - 左移：分逻辑左移和算术左移，他们的效果是一样的，都是对操作数乘2。  
  - 右移：分逻辑右移和算术右移。逻辑右移不考虑符号位，在高位补0。算术右移考虑符号位，对于负数，右移后符号位仍然是1。  
  - n & (n - 1):可以消除最低位的1。  
  - n >>> 1: 除2。 n <<< 1: 乘2。  
     
### 2. 布隆过滤器
  - 布隆过滤器是一种类似于哈希表的数据结构，主要用在某值是否存在的场景。  
  - 布隆过滤器不存具体的值，而是将哈希值映射到位数组上。在查找时，如果所有映射的数组位都是1，那么该值可能存在，可以后续进一步确认；如果不是所有映射的数组位都是1，那么说明该值肯定不存在，可以过滤掉。  
  - 布隆过滤器可以使用多个不同的哈希函数产生多个哈希值，每个哈希值对应位数组中的一个slot。  
  - 布隆过滤器无法做到哈希表那样精确匹配，故适合容忍误差的场景。  
3. LRU
4. 排序算法

## 做题记录
|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|课后习题|位运算|[191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/)|完成|6月9日|
|课后练习|LRU|[146. LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/#/)|完成|6月9日|
|课后练习|为运算|[231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)|完成|6月9日|


[146. LRU缓存机制]
- 思路：
    - LRU涉及查询和增删操作，时间复杂度都要是O(1)的。以往学过的数据结构中，没有一个数据结构能达到这个标准，所以我们需要自己造一个轮子。  
    - 查询O(1)的数据结构是Hash表，而增删O(1)的数据结构是链表，所以我们查找时用Hash表，增删用链表，用链表节点作为Hash表的值，这样就可以把HasH表和链表结合起来，只用他们的长处。  
    - Hash表的键是缓存的键，Hash表的值是链表节点，即通过Hash表就可以建立缓存键和缓存值的关联，查询O(1)搞定。当然，哈希冲突不能太严重。  
    - LRU只涉及在头部增加、在尾部删除和删除特定节点。由于头、尾的位置都是已知的，O(1)就可以得到头、尾节点的引用，而特定节点引用可以通过Hash表来获得，所以增删操作可以做到O(1)。  
    - 好了，理解了上述4点LRU就没什么难度了，剩下的只是实现链表的addFirst、remove和removeLast操作，即Hash表的get和put操作即可。  
    ```java
             public class LRUCache {
                 private HashMap<Integer, Node> map;
                 private DoubleLinkedList cache;
                 private int capacity;
             
                 public LRUCache () {
                     map = new HashMap<>();
                     cache = new DoubleLinkedList();
                     this.capacity = 2;
                 }
             
                 public int get (int key) {
                     if (!map.containsKey(key)) return -1;
                     int val = map.get(key).val;
                     put(key, val);
                     return val;
                 }
             
                 public void put (int key, int val) {
                     Node x = new Node(key, val);
                     if (map.containsKey(key)) {
                         cache.remove(map.get(key));
                     } else {
                         if (capacity == cache.size) {
                             Node last = cache.removeLast();
                             map.remove(last.key);
                         }
                     }
                     cache.addFirst(x);
                     map.put(key, x);
                 }
             
                 static class DoubleLinkedList {
                     private final Node head, tail;
                     private int size;
             
                     public DoubleLinkedList () {
                         head = new Node(0, 0);
                         tail = new Node(0, 0);
                         head.next = tail;
                         tail.prev = head;
                         size = 0;
                     }
             
                     public void addFirst (Node x) {
                         head.next.prev = x;
                         x.next = head.next;
                         head.next = x;
                         x.prev = head;
                         size++;
                     }
             
                     public Node removeLast () {
                         if (size > 0) return remove(tail.prev);
                         return null;
                     }
             
                     public Node remove (Node x) {
                         x.prev.next = x.next;
                         x.next.prev = x.prev;
                         x.prev = null;
                         x.next = null;
                         size--;
                         return x;
                     }
             
                     public int size () {
                         return size;
                     }
                 }
             
                 static class Node {
                     private int key, val;
                     private Node prev, next;
                     public Node (int k, int v) {
                         this.key = k;
                         this.val = v;
                     }
                 } 
              }
    ```
  
[191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/)
- 思路:
    - 暴力法：将Integer换成二进制String，遍历计数。  
    - 位移法：将Integer自身不停(逻辑)右移，再将右移后的值和1做`&`操作，对结果为1进行计数。  
    - 掩码法：利用一个掩码`mask=1`，对掩码做(逻辑)右移，再和Integer做`&`操作，对结果为1进行计数。  
    - 去1法：利用`n & (n - 1)`可以去除最低位1，记录结果不为0前能操作的次数即为结果。  
    ```java
                  public static int hammingWeight(int n) {
                          if (n == 0) return 0;
                          int mask = 1, cnt = 0;
                          for (int i = 0; i < Integer.SIZE; i++) {
                              if ((n & mask) != 0) cnt++;
                              mask <<= 1;
                          }
                          return cnt;
                      }
                  
                      public static int hammingWeight_enhance(int n) {
                          int cnt = 0;
                          while (n > 0) {
                              cnt++;
                              n &= n - 1;
                          }
                          return cnt;
                      }
    ```
  
[231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)
- 思路:
    - 由于某数是2的幂的话，二进制一定只有一个1，故可利用191的题解来直接解答，需要注意的是负数不会是2的幂，因为负数没有平方根。  
    ```java
                      public boolean isPowerOfTwo(int n) {
                              int cnt = 0, c = n;
                              while (n != 0) {
                                  cnt++;
                                  n &= n - 1;
                              }
                              return c > 0 && cnt == 1;
                          }
    ```
