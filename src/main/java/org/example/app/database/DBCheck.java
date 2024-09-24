package org.example.app.database;

import org.example.app.utils.Orders;

import java.io.File;

// Перевірка наявності файлу БД.
public class DBCheck {

    public static boolean isDBExists() {
        return !new File(Orders.DB_BASE_URL +
                Orders.DB_NAME).exists();
    }
}
