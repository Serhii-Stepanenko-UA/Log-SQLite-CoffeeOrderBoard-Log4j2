package org.example.app.view;

import org.example.app.utils.AppStarter;
import org.example.app.utils.Orders;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppView {

    public int getOption() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        getMenu();
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(Orders.INCORRECT_VALUE_MSG);
            AppStarter.startApp();
        }
        return option;
    }

    private void getMenu() {
        System.out.print("""                
                
                OPTIONS:
                1 - Create order.
                2 - Read orders.
                3 - Update order.
                4 - Delete order.
                5 - Read order by num.
                0 - Close the App.
                """);
        System.out.print("Input your option: ");
    }

    public void getOutput(String output) {
        if (output.equals("0")) {
            System.out.println(Orders.APP_CLOSE_MSG);
            System.exit(0);
        } else System.out.println(output);
    }
}
