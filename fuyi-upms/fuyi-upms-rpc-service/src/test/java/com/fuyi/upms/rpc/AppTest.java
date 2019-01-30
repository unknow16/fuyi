package com.fuyi.upms.rpc;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.ReadWriteLock;
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
}
