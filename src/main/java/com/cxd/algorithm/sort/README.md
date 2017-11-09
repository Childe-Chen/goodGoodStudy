常用的几种排序算法：
1. [选择排序](https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F)

首先从未排序序列中找到最小的元素，放置到排序序列的起始位置，然后从剩余的未排序序列中继续寻找最小元素，放置到已排序序列的末尾。所以称之为选择排序。

选择排序的交换操作介于 {\displaystyle 0} {\displaystyle 0}和 {\displaystyle (n-1)} (n-1)次之间。选择排序的比较操作为 {\displaystyle n(n-1)/2} n(n-1)/2次之间。选择排序的赋值操作介于 {\displaystyle 0} {\displaystyle 0}和 {\displaystyle 3(n-1)} 3(n-1)次之间。

比较次数 {\displaystyle O(n^{2})} O(n^{2})，比较次数与关键字的初始状态无关，总的比较次数 {\displaystyle N=(n-1)+(n-2)+...+1=n\times (n-1)/2} N=(n-1)+(n-2)+...+1=n\times (n-1)/2。交换次数 {\displaystyle O(n)} O(n)，最好情况是，已经有序，交换0次；最坏情况是，逆序，交换 {\displaystyle n-1} n-1次。交换次数比冒泡排序较少，由于交换所需CPU时间比比较所需的CPU时间多， {\displaystyle n} n值较小时，选择排序比冒泡排序快。
原地操作几乎是选择排序的唯一优点，当空间复杂度要求较高时，可以考虑选择排序；实际适用的场合非常罕见。