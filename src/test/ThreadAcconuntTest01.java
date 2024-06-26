package test;

public class ThreadAcconuntTest01 implements Runnable{
    private Account account = new Account();

    public static void main (String[] args){
        ThreadAcconuntTest01 threadAcconuntTest01 = new ThreadAcconuntTest01();

        Thread t1 = new Thread(threadAcconuntTest01, "primeira");
        Thread t2 = new Thread(threadAcconuntTest01, "segunda");

        t1.start();
        t2.start();
    }
    @Override
    public void run() {
        for(int i = 0; i < 5 ; i ++){
            withdrawal(10);
            if(account.getBalance() < 0){
                System.out.println("deu errado !! ");
            }
        }
    }
    //usar o synchronized em um metodo faz com que apenas uma thread possa usar ele por vez
    private synchronized void withdrawal(int amount) {
        //tmb tem como sincronizar so um bloco de codigo ex
        // synchronized (account) {}, isso faz com que so uma thread por vez pode acessar oq tem dentro dele
        if (account.getBalance() >= amount) {
            System.out.println(getThreadName() + " esta indo sacar dinheiro ");
            account.withdrawal(amount);
            System.out.println(getThreadName() + " completou o saque, valor atual da conta  " +account.getBalance());
        }else{
            System.out.println("sem dinherio para" + getThreadName() + "efetuar o saque" + account.getBalance());
        }

    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }


}
