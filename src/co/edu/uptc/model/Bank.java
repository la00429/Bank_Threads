package co.edu.uptc.model;

import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.structures.PriorittyQueue;

import java.util.ArrayList;
import java.util.List;

public class Bank implements Runnable{
    private Thread bank;
    private PriorittyQueue<User> queueUsers;
    private CashierWindow[] cashierWindows;


    public Bank(String name, List<Object> queueUsers, int quantityWindows){
        super(name);
        this.queueUsers = accessUser(queueUsers, quantityWindows);
        this.cashierWindows = new CashierWindow[quantityWindows];

    }

    private PriorittyQueue<User> accessUser(List<Object> queueUsers, int quantityWindows){
        this.queueUsers = new PriorittyQueue<>(quantityWindows);
        for (Object user : queueUsers) {
            accessUser((User) user);
        }
        return this.queueUsers;
    }
    public void accessUser(User user){
        queueUsers.push(user, user.getPriority());
    }


    @Override
    public void run() {
        super.run();
//        Presenter.loadUsers(this);
        openWindows();
        attendUser();
    }

    public void attendUser(){
        while (!queueUsers.isEmpty()) {
            try {
                attendUser(queueUsers.pull());
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }



    public void attendUser(User user) throws InterruptedException {
        User userToAttend = queueUsers.pull();
        System.out.println(userToAttend.getPriority());
        for (int i = 0; i < cashierWindows.length; i++) {
            if (cashierWindows[i].getPriorityWindow() == userToAttend.getPriority()) {
                if (cashierWindows[i].isAvailable()) {
                    cashierWindows[i].attendUser(userToAttend);
                } else {
                    this.bank.sleep(cashierWindows[i].calculateTimeAttention(userToAttend.getTransactionAmount()));
                }
            }else {
                if (cashierWindows[i].isAvailable()) {
                    cashierWindows[cashierWindows.length-1].attendUser(userToAttend);
                } else {
                    this.bank.sleep(cashierWindows[i].calculateTimeAttention(userToAttend.getTransactionAmount()));
                }
            }

        }

    }

    private void openWindows(){
        for (int i = 0; i < cashierWindows.length; i++) {
            cashierWindows[i].start();
        }
    }

    public void closeBank(){
        for (int i = 0; i < cashierWindows.length; i++) {
            cashierWindows[i].interrupt();
        }
        this.bank.interrupt();
    }


}
