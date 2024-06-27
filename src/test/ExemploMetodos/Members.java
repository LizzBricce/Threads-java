package test.ExemploMetodos;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Members {
    private final Queue<String> emails = new ArrayBlockingQueue<>(10); //thread safe
    private boolean open = true;

    public boolean isOpen() {
        return open;
    }

    public int pendingEmails() {
        synchronized (emails) {
            return emails.size();
        }
    }

    public void addMemberEmail(String email) {
        synchronized (this.emails) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "adicionou email na lista");
            this.emails.add(email);
            this.emails.notifyAll();// "avisa" todas as threads

        }
    }

    public String reatrieveEmail() throws InterruptedException { //o metodo nao pode parar mesmo que nao tenham emails pq se nao as threads morrem :c
        System.out.println(Thread.currentThread() + "verificando se tem novos emails");
        synchronized (this.emails) {
            while (this.emails.size() == 0) {
                if (!open) return  null;
                System.out.println(Thread.currentThread().getName() + "nao tem email na lista, entrando em modo espera");
                this.emails.wait();
            }
            return this.emails.poll();
        }
    }
    public void close() {
        open = false;
        synchronized (this.emails) {
        }
        System.out.println(Thread.currentThread().getName() + "notifcando que nao estamos mais pegando emails");
    }
}


