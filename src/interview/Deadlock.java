package interview;

/**
 * 简单死锁范例
 */
public class Deadlock {
    public static void main(String[] args) {
        DeadLockThread deadLock1 = new DeadLockThread(true);
        DeadLockThread deadLock2 = new DeadLockThread(false);
        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);
        t1.start();
        t2.start();
    }
}

class DeadLockThread implements Runnable {
    boolean flag;
    static Object o1 = new Object();// 这里必须是静态变量，否则加锁就不是在同一个对象上加锁了
    static Object o2 = new Object();

    public DeadLockThread(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        if (flag) {
            synchronized (o1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {// 要放在o1临界区内，因为要保持o1的锁，申请o2的锁
                    System.out.println("I am not dead lock!");
                }
            }
        } else {
            synchronized (o2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {// 此处类似，保持o2的锁，申请o1的锁
                    System.out.println("I am not dead lock, too.");
                }
            }
        }
    }
}
