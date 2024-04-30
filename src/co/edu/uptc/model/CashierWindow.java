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

    public  void attendUser(User user){
        this.isAvailable = false;
        try {
            Thread.sleep(calculateTimeAttention(user.getTransactionAmount()));
        } catch (InterruptedException e) {
        }
        this.isAvailable = true;
        this.countUserAttended++;
        this.notify();
    }
    public long calculateTimeAttention(int transactionAmount){
        Random random = new Random();
        long timeAttention = (long) transactionAmount / 100 * 100;
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
