package test;

// existem dois tipos de threads: daemon e user.
// no caso do tipo user, o programa é encerrado assim que todas as threads desse tipo terminam
// ja as daemon não tem tanta prioridade, o programa pode acabar mesmo com elas em execução

// usar a classe thread por meio de herança não é a melhor alternativa.
class ThreadExample extends Thread {
    private char c;

    public ThreadExample(char c) {
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
        }
        super.run();
    }
}

class ThreadExampleRunnable implements Runnable {
    private final char c;

    public ThreadExampleRunnable(char c) {
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
            //fazer a thread dormir
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ThreadTest01 {
    public static void main(String[] args) {
        // pegando o nome da thread que esta sendo usada
        // System.out.println("Nome da thread: " + Thread.currentThread().getName());

        // usando a classe Thread
        // ThreadExample t1 = new ThreadExample('A');
        // ThreadExample t2 = new ThreadExample('B');
        // ThreadExample t3 = new ThreadExample('C');
        // ThreadExample t4 = new ThreadExample('D');

        // se usar o metodo run ele vai rodar tudo na mesma thread
        // t1.run();
        // t2.run();
        // t3.run();
        // t4.run();

        // a melhor maneira é instanciar um objeto Thread e usar Runnable dentro do construtor
        Thread t1 = new Thread(new ThreadExampleRunnable('A'), "Thread 1"); //o construtor é sobrecarregado entao da pra ter ou nao nome na thread !!
        Thread t2 = new Thread(new ThreadExampleRunnable('B'), "Thread 2");
        Thread t3 = new Thread(new ThreadExampleRunnable('C'), "Thread 3");
        Thread t4 = new Thread(new ThreadExampleRunnable('D'), "Thread 4");

        //prioridades, 1 - 10 ou MIN - MAX
        //a prioridade nem sempre funciona do jeito esperado entao nao é bom fazer algo que dependa dela
        t4.setPriority(Thread.MAX_PRIORITY);

        // com o metodo start ele inicia uma nova thread para cada um
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        //nao pode startar a msm thread mais de uma vez
    }
}
