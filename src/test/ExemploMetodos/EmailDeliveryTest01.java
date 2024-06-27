package test.ExemploMetodos;

import javax.swing.*;

public class EmailDeliveryTest01 {
    public static void main(String[] args) {
        Members members = new Members();
        Thread t1 = new Thread(new EmailDeliveryService(members), "A");
        Thread t2 = new Thread(new EmailDeliveryService(members), "B");
        t1.start();
        t2.start();
        while (true){
            String email = JOptionPane.showInputDialog("Entre com seu email");
            if(email == null || email.isEmpty()){
                members.close();
                break;
            }
            members.addMemberEmail(email);
        }
    }
}
