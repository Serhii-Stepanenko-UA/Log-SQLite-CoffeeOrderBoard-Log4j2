package org.example.app.controller;

import org.example.app.service.OrderService;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Orders;
import org.example.app.view.OrderView;

import java.util.Map;

public class OrderController {

    OrderView view;
    OrderService service;

    public OrderController(OrderService service, OrderView view) {
        this.service = service;
        this.view = view;
    }

    public void createOrder() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getCreateData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.create(data);
        // Перевіряємо результат щодо обробки даних.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Orders.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput(res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void readOrders() {
        // Отримаємо результат запиту в БД
        String res = service.read();
        // Перевіряємо результат щодо запиту в БД.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Orders.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput("\nORDERS:\n" + res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void readOrderByNum() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getByNumData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.readByNum(data);
        // Перевіряємо результат щодо запиту в БД.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Orders.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput("\nORDER BY NUM:\n" + res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void updateOrder() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getUpdateData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.update(data);
        // Перевіряємо результат щодо обробки даних.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Orders.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput(res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void deleteOrder() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getDeleteData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.delete(data);
        // Перевіряємо результат щодо обробки даних.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Orders.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput(res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }
}
