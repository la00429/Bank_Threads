package co.edu.uptc.model;

import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.structures.PriorittyQueue;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Bank implements Runnable{
    private Thread bank;
    private PriorittyQueue<User> queueUsers;
    private CashierWindow[] cashierWindows;
    private int countUserAccess;
    private int countUserAttended;


    public Bank(List<User> queueUsers, int quantityWindows){
        super();
        this.queueUsers = accessUser(queueUsers, quantityWindows);
        this.cashierWindows = new CashierWindow[quantityWindows];
        bank = new Thread(this);
        bank.start();

    }

    private PriorittyQueue<User> accessUser(List<User> queueUsers, int quantityWindows){
        this.queueUsers = new PriorittyQueue<>(quantityWindows);
        for (User user : queueUsers) {
            accessUser(user);
        }
        return this.queueUsers;
    }
    public synchronized void accessUser(User user){
        queueUsers.push(user, user.getPriority());
        countUserAccess++;
    }


    @Override
    public void run() {
        openWindows();
        attendUser();
    }

    public synchronized void attendUser(){
        while (!queueUsers.isEmpty()) {
            try {
                attendUser(queueUsers.peek());
                sleep(3000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }


    public void attendUser(User user) throws InterruptedException {
        if (user != null && !queueUsers.isEmpty()) {
            User userToAttend = queueUsers.pull();
            countUserAttended++;
            for (int i = 0; i < cashierWindows.length ; i++) {
                synchronized (cashierWindows[i]) {
                    if (cashierWindows[i].getPriorityWindow() == userToAttend.getPriority()) {
                        if (cashierWindows[i].isAvailable()) {
                            cashierWindows[i].attendUser(userToAttend);
                            break;
                        }
                    } else {
                        if (cashierWindows[i].isAvailable()) {
                            searchCashierEnable().attendUser(userToAttend);
                            break;
                        }
                    }
                }
            }
        }
    }


    private CashierWindow searchCashierEnable(){
        CashierWindow cashierWindowEnable = new CashierWindow("",0);
        for (CashierWindow cashierWindow : cashierWindows){
            if (cashierWindow.isAvailable()){
                cashierWindowEnable = cashierWindow;
            }
        }
        return cashierWindowEnable;
    }



    private void openWindows(){
        for (int i = 0; i < cashierWindows.length; i++) {
            cashierWindows[i] = new CashierWindow("Cashier " + i, i);
            cashierWindows[i].start();
        }
    }

    public void closeBank(){
        for (int i = 0; i < cashierWindows.length; i++) {
            cashierWindows[i].interrupt();
        }
        this.bank.interrupt();
    }

    public int getCountUserAccess() {
        return countUserAccess;
    }
    public int getCountUserAttended() {
        return countUserAttended;
    }

    public int countUserNotAttended() {
        return countUserAccess - countUserAttended;
    }

    public boolean isAliveBank() {
        return bank.isAlive();
    }
}
