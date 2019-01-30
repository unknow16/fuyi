package com.fuyi.upms.rpc;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SpringBootTest
public class AppTest {

    @Test
    public void test1() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        try {
            // do something
        } finally {
            reentrantLock.unlock();
        }
    }

    @Test
    public void test2() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        new CyclicBarrier(1);

        new Semaphore(1);

        new ConcurrentHashMap();

    }


}
