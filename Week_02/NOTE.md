# 本周作业

## HashMap总结
### 1. HashMap的容量一定要是2的幂，原因是当容量是2的幂次时，可使用位进行高效的模运算，提升查询效率。这种思想在多个方法中都得到了体现。
####最大容量的定义:
```java
    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
```
    
####对于自定义的容量，扩到最近的2的幂次：
```java
    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```
####如扩容时就是直接扩到当前容量的两倍。
```java
    final Node<K,V>[] resize() {
        ....
        
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
    }
```


###2. HashMap底层是一个数据类型是Node的数组，好处是利用数组随机访问时间复杂度O(1)的优势来提升查询速度，具体是根据hash函数的
####底层存储
```java
    transient Node<K,V>[] table;
``` 
####hash值和容量的与运算可以直接定位目标在数组中的位置
```java
    newTab[e.hash & (newCap - 1)] = e;
```

###3.发生Hash冲突时，利用树化规避退化，以提升查询性能。
```java

    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                // hash相同但key不同，表示发生了hash冲突
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            // 发生hash冲突时，拿该hash的头节点，再根据节点是树节点还是普通节点来查
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

###4.在放置元素时根据阈值将hash冲突的节点树化或链表化。
```java
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 先判断是否要扩容
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 第一次放置，直接放
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            // 再次放置时，要判断是数节点还是链表节点
            Node<K,V> e; K k;
            // hash与key皆相同，则说明找到节点，直接替换值
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                // 找到了树节点
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        //有LRU的切点供注入
        afterNodeInsertion(evict);
        return null;
    }
```