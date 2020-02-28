### HashMap



##### HashMap 和 HashTable 区别

The <tt>HashMap</tt> class is roughly equivalent to <tt>Hashtable</tt>, except that it is **unsynchronized** and permits nulls.



**String类型的hashcode函数**

```java
	public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
```



为什么用31作为乘子？

1. 大家都喜欢选一个质数（传统）
2. 而且31不大不小，既不会像2、3一样导致冲突现象明显，也不会像101一样过大，可能导致指数运算后数据溢出（尽管101作为乘子时，hash函数可能映射得更为均匀）
3. 为什么不用37、41、43呢，是因为31与2^5 