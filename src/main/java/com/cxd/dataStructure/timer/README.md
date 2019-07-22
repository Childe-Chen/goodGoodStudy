### 定时器

http://novoland.github.io/并发/2014/07/26/定时器（Timer）的实现.html

https://my.oschina.net/anur/blog/2252539

http://xiaoyue26.github.io/2018/10/27/2018-10/HashedWheelTimer-大量定时器解决方案-Netty与kafka/

https://www.ibm.com/developerworks/cn/linux/l-cn-timers/index.html

https://blog.csdn.net/u013256816/article/details/80697456

### 实现结构

定时器的基本行为：

- add/schedule 增加定时任务
- cancel 取消一个定时任务
- expire 触发一个定时任务

1. Sorted-Linked-List

2. Heap

3. TimerWheel


已整理到博客