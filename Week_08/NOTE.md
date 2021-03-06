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
  
### 3. LRU
  - LRU表示Last Recent Used，即最近最新使用。  
  - LRU的查询和增删操作时间复杂度要求都是O(1)，故通常用来做为高性能缓存策略的一种。  
  - 实现上，由于时间复杂度O(1)的要求，可以配合哈希表和双向链表来实现。查询时，利用哈希表O(1)的特性，而在增删时，利用链表O(1)的特性。具体的做法是哈希表的键和缓存的键一致，而值是指向链表某节点的引用。这样通过键在哈希表中查到某节点引用后，直接利用该引用操作链表节点完成增删。  
  
### 4. 排序算法
  - 排序算法按照不同维度可以做不同划分，通常分为比对排序和非比对排序。比对排序即为比较各值之间的大小来排序，非比对排序即为不利用比较值大小来排序。  
  - 比对类排序的时间复杂度上限是O(nlogn)，因为比对肯定要过一遍全部元素，而效率最高的比对方式 -- 二分查找 的时间复杂度也是O(logn)，故O(nlogn)是比对类排序的最佳时间规模。  
  - 非比对类排序往往利用值的天然顺序，可以做到O(n)的时间复杂度。但适用条件也往往比较苛刻，需要具体问题具体分析是否适用。  
  - 除了时间规模，稳定性也是算法的一个重要考量。不稳定的算法在排序后会导致重复值的顺序改变，在某些场景下可能引发不可预测的问题。故工业级的排序算法一定是稳定的。  
  
  |排序方法|时间复杂度(均摊)|时间复杂度(最坏)|空间复杂度|稳定性|
  |---------|-----------|---------------|--------|------|
  |插入排序|O(n^2)|O(n^2)|O(1)|稳定|
  |希尔排序|O(n^1.3)|O(n^2)|O(1)|不稳定|
  |选择排序|O(n^2)|O(n^2)|O(1)|不稳定|
  |冒泡排序|O(n^2)|O(n^2)|O(1)|稳定|
  |堆排序排序|O(nlogn)|O(nlogn)|O(1)|不稳定|
  |快速排序|O(nlogn)|O(n^2)|O(n)|不稳定|
  |归并排序|O(nlogn)|O(nlogn)|O(n)|稳定|
  ||||||
  |计数排序|O(n+k)|O(n+k)|O(n+k)|稳定|
  |桶排序|O(n+k)|O(n^2)|O(n+k)|稳定|
  |基数排序|O(n*k)|O(n*k)|O(n*k)|稳定|
  

## 做题记录
|题目类型|知识点|题目   |完成情况|完成时间|
|-------|-----|---|-------|------|
|课后习题|位运算|[191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/)|完成|6月9日|
|课后练习|LRU|[146. LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/#/)|完成|6月9日|
|课后练习|位运算|[231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)|完成|6月9日|
|作业|位运算|[190. 颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)|完成|6月10日|
|作业|排序|十大排序算法练手|完成|6月12日|


[146. LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/#)
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
[190. 颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)
- 思路：
    - 暴力法：10进制整型转成二进制字符串，遍历字符串反转，然后转成10进制整型。  
    - 位移法：第1位左移31位，第2位左移30位，以此类推，最后将所有左移值求和。  
    - 加强位移法：利用分治思想，先将前16位和后16位整体交换，再将两个16位块中的前后8位交换，以此类推，直到全部交换。  
    ```java
                      public int reverseBits(int n) {
                          int sum = 0;
                          for (int i = 0; i < 32; i++)
                              sum += ((n >> i) & 1) << (31-i);
                          return sum;
                      }
    ```
    ```java
                      public int reverseBits(int n) {
                          n = (n >> 16) | (n << 16);
                          n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
                          n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
                          n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
                          n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
                          return n;
                      }                   
    ```
  
[排序算法练手](https://www.google.com)
#### 选择排序
 - 每次选择待排序区中最小元素，插入已排序区末尾。  
 - 需要注意的是，因为每次要把待排序区首个元素交换到最小元素的位置,所以选择排序是不稳定的。     
```java
    public static int[] order(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++)
                if (nums[minIndex] > nums[j]) minIndex = j;
            int tmp = nums[i]; nums[i] = nums[minIndex]; nums[minIndex] = tmp;
        }
        return nums;
    }
```

#### 插入排序
 - 对于元素的移动有三种策略：每次相邻元素swap、每次只移动大于target的元素、System.arraycopy批量移动。在数据规模大的场景下，第三种策略显然效率更高。
```java
    public static int[] order_enhance1(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 1; i < nums.length; i++) {
            int targetIndex = i, currValue = nums[i];
            for (int j = i; j > 0; j--)
                if (nums[j-1] > currValue) targetIndex--;
                else break;
            System.arraycopy(nums, targetIndex, nums, targetIndex+1, i-targetIndex);
            nums[targetIndex] = currValue;
        }
        return nums;
    }
```

#### 冒泡排序
 - 可以加一个标志位来跳过不必要的比对。  
```java
    public static int[] order(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 0; i < nums.length; i++) {
            boolean hasSwap = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    hasSwap = true;
                }
            }
            if (!hasSwap) break;
        }
        return nums;
    }
```

#### 希尔排序
 - 关键是增量间隔要设置好，目前已知最佳的值是n/3+1。  
 - 关于内存循环，有两种遍历方向，从前往后和从后往前。我把两种都实现了，个人感觉后者更顺一些，在判断边界时考虑的点更自然。  
```java
    public static int[] order(int[] nums) {
       if (nums == null || nums.length == 0) return nums;
       int gap = nums.length, n = nums.length;
       
       while (gap > 1) {
           gap = gap / 3 + 1;
           boolean hasSwap = false;

           for (int i = 0; i + gap < n; i++) {
               // 从前往后遍历
               for (int j = i; j + gap < n; j += gap) {
                   if (nums[j + gap] < nums[j]) {
                       int tmp = nums[j];
                       nums[j] = nums[j + gap];
                       nums[j + gap] = tmp;
                       hasSwap = true;
                   }
               }
           }

           if (!hasSwap) return nums;
       }

       return nums;
    }

    public static int[] order1(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int gap = nums.length, n = nums.length;
        while (gap > 1) {
            gap = gap / 3 + 1;
            for (int i = n - 1; i > 0; i--)
                // 从后往前遍历
                for (int j = i; j - gap >= 0 ; j -= gap) {
                    int tmp;
                    if (nums[j - gap] > nums[j]) {
                        // swap
                        tmp = nums[j - gap];
                        nums[j - gap] = nums[j];
                        nums[j] = tmp;
                    }
                }
        }
        return nums;
    }
```

#### 归并排序
 - 反复利用一个初始化好的数组可以将空间复杂度降到O(n)。  
```java
    public static int[] order (int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        sort(nums, 0, nums.length-1, new int[nums.length]);
        return nums;
    }

    private static void sort (int[] nums, int start, int end, int[] workspace) {
        int mid;
        if (start < end) {
            mid = (start + end) >>> 1;
            sort(nums, start, mid, workspace);
            sort(nums, mid + 1, end, workspace);
            merge(nums, start, mid, mid + 1, end, workspace);
        }
    }

    private static void merge (int[] nums, int start1, int end1, int start2, int end2, int[] workspace) {
        int k = 0, i = start1, j = start2;
        while (i <= end1 && j <= end2)
            if (nums[i] > nums[j]) workspace[k++] = nums[j++];
            else workspace[k++] = nums[i++];
        while (i <= end1) workspace[k++] = nums[i++];
        while (j <= end2) workspace[k++] = nums[j++];
        System.arraycopy(workspace, 0, nums, start1, end2-start1+1);
    }


    @Test
    public void test_practices () {
        int[] result = MergeSort.order(ArrayUtils.getTestingArrayWithNonDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithNonDuplicates(), result);

        result = MergeSort.order(ArrayUtils.getTestingArrayWithDuplicates());
        Assert.assertArrayEquals(ArrayUtils.getExpectedResultWithDuplicates(), result);
    }
```

#### 快速排序

