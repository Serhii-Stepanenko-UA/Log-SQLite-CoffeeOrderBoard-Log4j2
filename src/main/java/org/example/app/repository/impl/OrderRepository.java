package org.example.app.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.database.DBConn;
import org.example.app.entity.Order;
import org.example.app.repository.AppRepository;
import org.example.app.utils.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderRepository implements AppRepository<Order> {

    private final static String TABLE_ORDERS = "orders";
    private static final Logger LOGGER =
            LogManager.getLogger(OrderRepository.class);

    @Override
    public String create(Order order) {
        // SQL-запит.
        // ? - заповнювач (placeholder) для параметра. Навіщо?
        // Захист від SQL-ін'єкцій.
        // Ефективність. Коли використовуємо підготовлені оператори (PreparedStatement),
        // базі даних не потрібно щоразу аналізувати/компілювати SQL-запит.
        // Використовується шаблон та просто підставляються в нього значення.
        String sql = "INSERT INTO " + TABLE_ORDERS + " (name) VALUES(?)";
        // PreparedStatement - підготовлений вираз (оператор), щоб уникнути SQL-ін'єкцій
        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            // Формування конкретних значень для певного заповнювача
            pstmt.setString(1, order.getName());
            // Виконання SQL-запиту
            pstmt.executeUpdate();
            // Повернення повідомлення при безпомилковому
            // виконанні SQL-запиту
            return Orders.DATA_INSERT_MSG;
        } catch (SQLException e) {
            LOGGER.warn(Orders.LOG_DB_ERROR_MSG);
            // Повернення повідомлення про помилку роботи з БД
            return e.getMessage();
        }
    }

    @Override
    public Optional<List<Order>> read() {
        try (Statement stmt = DBConn.connect().createStatement()) {
            // Колекція-контейнер для даних, які читаються з БД
            List<Order> list = new ArrayList<>();
            // SQL-запит
            String sql = "SELECT num, name FROM " + TABLE_ORDERS;
            // Отримання набору даних з БД через виконання SQL-запиту
            ResultSet rs = stmt.executeQuery(sql);
            // Наповнення колекції-контейнера об'єктами з БД
            while (rs.next()) {
                list.add(new Order(
                        rs.getLong("num"),
                        rs.getString("name"))
                );
            }
            // Повертаємо Optional-контейнер з колецією даних
            return Optional.of(list);
        } catch (SQLException e) {
            LOGGER.warn(Orders.LOG_DB_ERROR_MSG);
            // Якщо помилка, то повертаємо порожній Optional-контейнер
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> readByNum(Long num) {
        // SQL-запит.
        // ? - заповнювач (placeholder) для параметра. Навіщо?
        // Захист від SQL-ін'єкцій.
        // Ефективність. Коли використовуємо підготовлені оператори (PreparedStatement),
        // базі даних не потрібно щоразу аналізувати/компілювати SQL-запит.
        // Використовується шаблон та просто підставляються в нього значення.
        String sql = "SELECT num, name FROM " + TABLE_ORDERS +
                " WHERE num = ?";
        try (PreparedStatement pst = DBConn.connect().prepareStatement(sql)) {
            pst.setLong(1, num);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Order order = new Order();
            long entityNum = rs.getLong("num");
            String name = rs.getString("name");
            if (entityNum == 0 | name == null)
                order = null;
            else {
                order.setNum(entityNum);
                order.setName(name);
            }
            // Повертаємо Optional-контейнер з об'єктом
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            LOGGER.warn(Orders.LOG_DB_ERROR_MSG);
            // Якщо помилка або такого об'єкту немає в БД,
            // повертаємо порожній Optional-контейнер
            return Optional.empty();
        }
    }

    @Override
    public String update(Order order) {
        // SQL-запит.
        // ? - заповнювач (placeholder) для параметра. Навіщо?
        // Захист від SQL-ін'єкцій.
        // Ефективність. Коли використовуємо підготовлені оператори (PreparedStatement),
        // базі даних не потрібно щоразу аналізувати/компілювати SQL-запит.
        // Використовується шаблон та просто підставляються в нього значення.
        String sql = "UPDATE " + TABLE_ORDERS + " SET name = ? WHERE num = ?";
        // PreparedStatement - підготовлений вираз (оператор), щоб уникнути SQL-ін'єкцій
        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            // Формування конкретних значень для певного заповнювача
            pstmt.setString(1, order.getName());
            pstmt.setLong(2, order.getNum());
            // Виконання SQL-запиту
            pstmt.executeUpdate();
            // Повернення повідомлення при безпомилковому
            // виконанні SQL-запиту
            return Orders.DATA_UPDATE_MSG;
        } catch (SQLException e) {
            LOGGER.warn(Orders.LOG_DB_ERROR_MSG);
            // Повернення повідомлення про помилку роботи з БД
            return e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {
        // SQL-запит.
        // ? - заповнювач (placeholder) для параметра. Навіщо?
        // Захист від SQL-ін'єкцій.
        // Ефективність. Коли використовуємо підготовлені оператори (PreparedStatement),
        // базі даних не потрібно щоразу аналізувати/компілювати SQL-запит.
        // Використовується шаблон та просто підставляються в нього значення.
        String sql = "DELETE FROM " + TABLE_ORDERS + " WHERE num = ?";
        // PreparedStatement - підготовлений вираз (оператор), щоб уникнути SQL-ін'єкцій
        try (PreparedStatement stmt = DBConn.connect().prepareStatement(sql)) {
            // Встановлення відповідних параметрів
            stmt.setLong(1, id);
            // Виконання SQL-запиту
            stmt.executeUpdate();
            // Повернення повідомлення при безпомилковому
            // виконанні SQL-запиту
            return Orders.DATA_DELETE_MSG;
        } catch (SQLException e) {
            LOGGER.warn(Orders.LOG_DB_ERROR_MSG);
            // Повернення повідомлення про помилку роботи з БД
            return e.getMessage();
        }
    }

    // Перевірка наявності певного num у БД
    public boolean isNunExists(Long num) {
        String sql = "SELECT COUNT(num) FROM " + TABLE_ORDERS +
                " WHERE num = ?";
        try {
            PreparedStatement pst = DBConn.connect().prepareStatement(sql);
            pst.setLong(1, num);
            try (ResultSet rs = pst.executeQuery()) {
                // Очікуємо лише один результат
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(Orders.LOG_DB_ERROR_MSG);
            return false;
        }
        return false;
    }
}
