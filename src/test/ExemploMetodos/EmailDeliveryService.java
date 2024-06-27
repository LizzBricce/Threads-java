package test.ExemploMetodos;

public class EmailDeliveryService implements Runnable{

    private final Members members;

    public EmailDeliveryService(Members members){
        this.members = members;
    }
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " começando a enviar emails !!");
        while (members.isOpen() || members.pendingEmails() > 0){
            try {
                String email = members.reatrieveEmail();
                if(email == null ) continue;
                System.out.println(threadName + " enviando email para" + email);
                Thread.sleep(200);
                System.out.println(threadName + " enviou email com sucesso para" + email);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("todos os emails foram enviados com sucesso !!");
    }
}
