https://lixiangyun.gitbooks.io/disruptor/content/1.3.html

https://github.com/LMAX-Exchange/disruptor/wiki/Getting-Started

-  伪共享

com.cxd.disruptor.FalseSharing

缓存系统中是以缓存行（cache line）为单位存储的。缓存行是2的整数幂个连续字节，一般为32-256个字节。最常见的缓存行大小是64个字节。
当多线程修改互相独立的变量时，如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。
缓存行上的写竞争是运行在SMP系统中并行线程实现可伸缩性最重要的限制因素。
有人将伪共享描述成无声的性能杀手，因为从代码中很难看清楚是否会出现伪共享。
为了让可伸缩性与线程数呈线性关系，就必须确保不会有两个线程往同一个变量或缓存行中写。两个线程写同一个变量可以在代码中发现。
为了确定互相独立的变量是否共享了同一个缓存行，就需要了解内存布局，或找个工具告诉我们。Intel VTune就是这样一个分析工具。

对于HotSpot JVM，所有对象都有两个字长的对象头。第一个字是由24位哈希码和8位标志位（如锁的状态或作为锁对象）组成的Mark Word。
第二个字是对象所属类的引用。如果是数组对象还需要一个额外的字来存储数组的长度。每个对象的起始地址都对齐于8字节以提高性能。
因此当封装对象的时候为了高效率，对象字段声明的顺序会被重排序成下列基于字节大小的顺序：
doubles (8) 和 longs (8)
ints (4) 和 floats (4)
shorts (2) 和 chars (2)
booleans (1) 和 bytes (1)
references (4/8)
<子类字段重复上述顺序>

https://lixiangyun.gitbooks.io/disruptor/content/1.3.html 该章节中可能因为使用的环境不同，对填充long的数量可能不一致，
在如下环境配置中：
```
笔记本 MacBook Pro
处理器 Intel Core i5
嫩存 8 GB 1867 MHz DDR3

java version "1.8.0_74"
Java(TM) SE Runtime Environment (build 1.8.0_74-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.74-b02, mixed mode)
```

了解这些之后就可以在任意字段间用7个（以上环境需要6个）long来填充缓存行。在Disruptor里我们对RingBuffer的cursor和BatchEventProcessor的序列进行了缓存行填充。
为了展示其性能影响，我们启动几个线程，每个都更新它自己独立的计数器。计数器是volatile long类型的，所以其它线程能看到它们的进展。
