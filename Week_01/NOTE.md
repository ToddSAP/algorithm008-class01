# 学习笔记

面对不熟悉的问题时如何思考？

1. 看能否暴力解决
2. 能否找出最近、重复 子问题

## 代码模板
左右边界(双指针对撞)
``` java
int left = 0, right = length - 1; 
while (left < right) {
    if (some logic) {
        right--;
        while (some logic to skip duplicated) {
            right--;
        }
    } else {
        left++;
        while (some logic to skip duplicated) {
            left++;
        }
    }
}
```
## 代码分析
### Queue
1. Queue提供了两套操作API以适配容量充足和受限的场景。  
容量充足时，可使用`add(e)、remove()、element()`，在操作失败时，这套API会抛出Exception。  
在容量受限时，可使用`offer(e)、poll()、peek()`，在操作失败时，这套API会返回特定值，可根据特定值来定制处理逻辑，如阻塞或拒绝。  

2. Queue支持多种入队出队方式，如FIFO、LIFO等。

3. Null不建议做为值存放进Queue中，因为`poll()`会返回Null来表示队列为空。

### PriorityQueue
1. 优先队列底层是基于优先堆的，其中的元素都是按照指定的顺序或自然顺序排列的，而不是基于入队先后的顺序，故名为优先队列。  
2. 优先堆一般为小顶堆，最底层是用数组的方式来存放元素，有逻辑支持数组扩容，故优先队列理论上是无界的。  
3. `add()`和`offer()`方法没有区别，都是抛出NPE，都是将新元素加到堆底，然后自顶向上重建堆。  
4. `peek()`方法返回堆顶元素。  
5. `remove(E e)`和`poll()`方法不同，前者是删除指定元素，逻辑是先在queue里遍历定位元素位置，然后删除；后者是直接删除堆顶元素。  
6. `shiftDown`和`shiftUp()`是两种重建堆的方式，一个自下而上，一个自上而下，逻辑都是比较`parent`和`child`的大小。




