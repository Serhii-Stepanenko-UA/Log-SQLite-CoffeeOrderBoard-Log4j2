package org.example.app.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.utils.Orders;
import org.example.app.view.AppView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Конектор до БД.
public class DBConn {

    private static final Logger LOGGER =
            LogManager.getLogger(DBConn.class);

    public static Connection connect() {

        AppView appView = new AppView();
        Connection conn = null;

        try {
            // Реалізуємо з'єднання з БД
            conn = DriverManager.getConnection(Orders.DB_DRIVER +
                            Orders.DB_BASE_URL + Orders.DB_NAME);
            // Для виклику виняткової ситуації
            // можемо вказати невірний шлях до файлу БД.
            // Відповідне повідомлення запишеться в файл логів.
//            conn = DriverManager.getConnection(Orders.DB_DRIVER +
//                    Orders.DB_BASE_URL + "abc/" + Orders.DB_NAME);
        } catch (SQLException e) {
            LOGGER.error(Orders.LOG_DB_ERROR_MSG);
            // Виведення повідомлення про помилки
            // роботи з БД
            appView.getOutput(e.getMessage());
        }
        return conn;
    }
}
