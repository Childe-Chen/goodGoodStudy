package com.cxd.curator.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by childe on 2017/7/10.
 */
public interface Locks {
    Logger log = LoggerFactory.getLogger(Locks.class);

    void doWork(long time, TimeUnit unit) throws Exception;
}
