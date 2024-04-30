package co.edu.uptc.model;

public class User {
    private int priority;
    private int transactionAmount;

    public User(int priority, int transactionAmount) {
        this.priority = priority;
        this.transactionAmount = transactionAmount;
    }

    public int getPriority() {
        return priority;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

}
