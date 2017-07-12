可重入锁，使用 InterProcessMutex
可重入锁即对某个线程而言，可重复获取该锁，sychronized默认有该语义。
可重入的关键代码：
``` java
private boolean internalLock(long time, TimeUnit unit) throws Exception
{
    /*
       Note on concurrency: a given lockData instance
       can be only acted on by a single thread so locking isn't necessary
    */

    Thread currentThread = Thread.currentThread();

    LockData lockData = threadData.get(currentThread);
    if ( lockData != null )
    {
        // re-entering
        lockData.lockCount.incrementAndGet();
        return true;
    }

    String lockPath = internals.attemptLock(time, unit, getLockNodeBytes());
    if ( lockPath != null )
    {
        LockData newLockData = new LockData(currentThread, lockPath);
        threadData.put(currentThread, newLockData);
        return true;
    }

    return false;
}

```

当前线程的锁信息会被缓存到InterProcessMutex中不可变属性threadData中。