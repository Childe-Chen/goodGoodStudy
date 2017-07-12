curator实现的锁放在org.apache.curator.framework.recipes.locks包下。

curator-recipes工程下通过区分不同的包，封装了以下几个功能：
1. atomic 分布式的原子操作
2. barriers 分布式的屏障
3. cache 分布式缓存
4. leader leader选举
5. locks 分布式锁（例子参见lock包）
6. queue 分布式队列
7. shared 共享值，结合lock使用（参见学习lock包下Semaphore）