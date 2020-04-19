package test;

import java.util.concurrent.TimeUnit;

/**
 * 测试interrupt()方法的效果
 */
public class LazyThread extends Thread {
    // 是否被打扰
    public void run() {

        // 没有被打扰就继续吃
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("eat...");

            // 这个线程真的很懒，不断的吃和睡
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // 打扰后唤醒
        System.out.println("wake up...");
    }


    public static void main(String[] args) {

        Thread lt = new LazyThread();
        lt.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("wake up");
        lt.interrupt();
    }
}
