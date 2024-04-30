package co.edu.uptc.view;

import java.util.Scanner;

public class View {
    private Scanner console;

    public View() {
        console = new Scanner(System.in);
    }

    public String readData(String message) {
        showMessage(message);
        return console.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
