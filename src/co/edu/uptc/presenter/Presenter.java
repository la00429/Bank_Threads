package co.edu.uptc.presenter;

import co.edu.uptc.model.Bank;
import co.edu.uptc.model.User;
import co.edu.uptc.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Presenter {
    private Bank bank;
    private View view;

    public Presenter() {
        this.view = new View();
    }

    public void startSimulation() {
        long timeSimulation = Long.parseLong(view.readData("Input time of simulation in miliseconds: "));
        int quantityWindows = Integer.parseInt(view.readData("Input number of cashiers: "));
        long timeInitial = System.currentTimeMillis();
        bank = new Bank(loadUsers(quantityWindows), quantityWindows);
        new Thread(this::accessUser).start();
        closeBank(timeSimulation, timeInitial);
        showStatistics(timeSimulation, quantityWindows);
    }

    private void accessUser() {
        while (bank.isAliveBank()) {
            User user = new User(new Random().nextInt(4), new Random().nextInt(1000));
            bank.accessUser(user);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> loadUsers(int quantityPriority) {
        List<User> users = new ArrayList<User>();
        int quantityUsers = (int) (Math.random() * 100);
        for (int i = 0; i < quantityUsers; i++) {
            User user = new User(new Random().nextInt(quantityPriority + 1), new Random().nextInt(1000));
            users.add(user);
        }
        return users;
    }

    public boolean closeBank(long timeSimulation, long timeInitial) {
        while (System.currentTimeMillis() - timeInitial < timeSimulation && bank.isAliveBank()) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bank.closeBank();
        return true;
    }

    public synchronized static void randomUsers(Bank bank) {
        int quantityUsers = (int) (Math.random() * 10);
        for (int i = 0; i < quantityUsers; i++) {
            User user = new User(new Random().nextInt(4), new Random().nextInt(1000));
            bank.accessUser(user);
        }
    }

    public void showStatistics(long timeSimulation, int quantityWindows) {
        view.showMessage("-----------------Statistics------------------");
        view.showMessage("1. Time of simulation: " + timeSimulation + " miliseconds");
        view.showMessage("2. Number of cashiers windows opened: " + quantityWindows);
        view.showMessage("3. Number of users with access: " + bank.getCountUserAccess());
        view.showMessage("4. Number of users attended: " + bank.getCountUserAttended());
        view.showMessage("5. Number of users not attended: " + bank.countUserNotAttended());

    }
}
