package co.edu.uptc.presenter;

import co.edu.uptc.model.Bank;
import co.edu.uptc.model.User;
import co.edu.uptc.view.View;

public class Presenter{
    /*// cargar usuaurai
    new Bank().accessUsers();*/
    private Bank bank;
    private View view;

    public Presenter() {
        this.view = new View();
    }

    public void startSimulation(){
        long timeSimulation  = Long.parseLong( view.readData("Input time of simulation in miliseconds: "));

        int quantityWindows = Integer.parseInt(view.readData("Input number of cashiers: "));
        bank = new Bank("Bank Probe", quantityWindows);
        bank.start();
        loadUsers(bank);
        long timeInitial = System.currentTimeMillis();
        closeBank(timeSimulation, timeInitial);
        bank.closeBank();
        showStatistics(timeSimulation);
    }

    public boolean closeBank(long timeSimulation, long timeInitial){
        while (System.currentTimeMillis() - timeInitial < timeSimulation && bank.isAlive()) {
            try {
                Thread.sleep(100); // Esperar 100 milisegundos antes de verificar nuevamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public synchronized static void loadUsers(Bank bank){
        bank.accessUser(new User(0, 100));
        bank.accessUser(new User(1, 200));
        bank.accessUser(new User(2, 300));
        bank.accessUser(new User(0, 400));
        bank.accessUser(new User(0, 500));
        bank.accessUser(new User(1, 600));
        bank.accessUser(new User(2, 700));
        bank.accessUser(new User(0, 800));
        bank.accessUser(new User(1, 900));
        bank.accessUser(new User(2, 1000));
    }

    public void showStatistics(long timeSimulation){
        view.showMessage("Statistics");
        view.showMessage("Time of simulation: " + bank.getTimeSimulation());

    }
}
