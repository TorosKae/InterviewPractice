import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        // task будет ждать, пока его не оповестят через lock
        Runnable task = () -> {
            synchronized(lock) {
                try {
                    System.out.println("lock.wait(); " + Thread.currentThread().getName());
                    System.out.println("tread have lock 1 " + Thread.currentThread().holdsLock(lock));
                    lock.wait();
                    System.out.println("tread have lock 2 " + Thread.currentThread().holdsLock(lock));
                } catch(InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            // После оповещения нас мы будем ждать, пока сможем взять лок
            System.out.println("thread "+Thread.currentThread().getName());
        };
        Runnable task2 = () -> {
            synchronized(lock) {
                System.out.println("taking lock " + Thread.currentThread().getName());

            }
            // После оповещения нас мы будем ждать, пока сможем взять лок
            System.out.println("thread "+Thread.currentThread().getName());
        };
        Thread taskThread = new Thread(task);
        Thread taskThread2 = new Thread(task2);
        Thread taskThread3 = new Thread(task2);
        Thread taskThread4 = new Thread(task2);

        taskThread.start();
        taskThread2.start();
        taskThread3.start();
        taskThread4.start();
        // Ждём и после этого забираем себе лок, оповещаем и отдаём лок
        Thread.currentThread().sleep(1000);
        System.out.println("main");
        synchronized(lock) {
            lock.notify();
        }
    }
}