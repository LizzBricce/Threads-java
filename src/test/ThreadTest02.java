package test;


class ThreadExampleRunnable2 implements Runnable {
    private final String c;

    public ThreadExampleRunnable2(String c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println("Nome da thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 500; i++) {
            System.out.print(c);
            if (i % 100 == 0) {
                System.out.println();

            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            Thread.yield(); //sugere uma troca de threads porem nao tem garantia de que isso vai acontecer msm entao tem uqe tomar cuidado pra nao depender disso
        }
    }
}

public class ThreadTest02 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadExampleRunnable2("A"));
        Thread t2 = new Thread(new ThreadExampleRunnable2("B"));
        Thread t3 = new Thread(new ThreadExampleRunnable2("C"));
        Thread t4 = new Thread(new ThreadExampleRunnable2("D"));


        t1.start();
        t1.join(); //diz para o main terminar essa thread antes de continuar com a proxima thread
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        t4.start();
        t4.join();


    }
}

