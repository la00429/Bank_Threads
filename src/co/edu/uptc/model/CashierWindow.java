package co.edu.uptc.model;

import java.util.Random;

public class CashierWindow extends Thread{

    private int priorityWindow;
    private int countUserAttended;
    private boolean isAvailable;

    public CashierWindow(String name, int priority) {
        super(name);
        this.priorityWindow = priority;
        this.countUserAttended = 0;
        this.isAvailable = false;
    }

    @Override
    public void run() {
        super.run();
    }

    public synchronized boolean attendUser(User user){
        this.isAvailable = false;
        try {
            sleep(calculateTimeAttention(user.getTransactionAmount()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isAvailable = true;
        this.countUserAttended++;
        this.notify();
        return isAvailable;
    }
    public long calculateTimeAttention(int transactionAmount){
        Random random = new Random();
        long timeAttention = (long) transactionAmount / 10;
        long timeRandom = Math.abs(random.nextLong()) % 1001;
        return timeAttention + timeRandom;
    }

    public boolean isAvailable() {
        return this. isAvailable;
    }

    public int getPriorityWindow() {
        return this.priorityWindow;
    }


}
