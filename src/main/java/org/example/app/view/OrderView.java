package org.example.app.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderView {

    Scanner scanner = new Scanner(System.in);

    // Отримуємо вхідні дані
    public Map<String, String> getCreateData() {
        System.out.println("\nCREATE FORM");
        Map<String, String> data = new HashMap<>();
        System.out.print("Enter name: ");
        data.put("name", scanner.nextLine().trim());
        return data;
    }

    // Отримуємо вхідні дані
    public Map<String, String> getByNumData() {
        System.out.println("\nREAD BY NUM FORM");
        Map<String, String> data = new HashMap<>();
        System.out.print("Input num: ");
        data.put("num", scanner.nextLine().trim());
        return data;
    }

    // Отримуємо вхідні дані
    public Map<String, String> getUpdateData() {
        System.out.println("\nUPDATE FORM");
        Map<String, String> data = new HashMap<>();
        System.out.print("Input num: ");
        data.put("num", scanner.nextLine().trim());
        System.out.print("Input name: ");
        data.put("name", scanner.nextLine().trim());
        return data;
    }

    // Отримуємо вхідні дані
    public Map<String, String> getDeleteData() {
        System.out.println("\nDELETE FORM");
        Map<String, String> data = new HashMap<>();
        System.out.print("Input num: ");
        data.put("num", scanner.nextLine().trim());
        return data;
    }

    // Виведення результату роботи програми
    public void getOutput(String output) {
        System.out.println(output);
    }
}
