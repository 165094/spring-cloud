package com.test.go;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class TestGo implements Runnable {
    private int theadId;
    private int sid = 0;
    private static volatile AtomicInteger count = new AtomicInteger(0);

    public static void inCreate() {
        count.incrementAndGet();
    }

    public TestGo(int theadId) {
        this.theadId = theadId;
    }

    @Override
    public void run() {
        while (count.get() < 36) {
            synchronized (TestGo.class) {
                sid = count.get() / 3 % 3+1;
                if (sid == theadId) {
                    for (int i = 0; i < 3; i++) {
                        inCreate();
                        System.out.println(Thread.currentThread().getName()+ ":"+count.get());
                    }
                    TestGo.class.notifyAll();
                } else {
                    try {
                        TestGo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,3, 1L, TimeUnit.SECONDS, queue);
//        try {
//            for (int i = 0; i < 3; i++) {
//                poolExecutor.submit(new TestGo(i+1));
//            }
//        } catch (Exception e) {
//            log.error("执行异常：{}", e);
//        } finally {
//            poolExecutor.shutdown();
//        }
        System.out.println(12/3%3);
    }
}