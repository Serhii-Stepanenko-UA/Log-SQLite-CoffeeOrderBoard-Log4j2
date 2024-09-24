package org.example.app.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.database.DBCheck;
import org.example.app.entity.Order;
import org.example.app.entity.OrderMapper;
import org.example.app.exceptions.OrderException;
import org.example.app.exceptions.DBException;
import org.example.app.repository.impl.OrderRepository;
import org.example.app.utils.Orders;
import org.example.app.utils.OrderValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


public class OrderService {

    OrderRepository repository;
    private static final Logger LOGGER =
            LogManager.getLogger(OrderService.class);

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public String create(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Orders.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.error(Orders.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new OrderValidator().validateOrderData(data);
        if (!errors.isEmpty()) {
            try {
                throw new OrderException("Check inputs", errors);
            } catch (OrderException e) {
                LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        return repository.create(new OrderMapper().mapOrderData(data));
    }

    public String read() {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Orders.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.error(Orders.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        // Отримуємо дані
        Optional<List<Order>> optional = repository.read();
        // Якщо Optional не містить null, формуємо виведення.
        // Інакше повідомлення про відсутність даних.
        if (optional.isPresent()) {
            // Отримуємо колекцію з Optional
            List<Order> list = optional.get();
            // Якщо колекція не порожня, формуємо виведення.
            // Інакше повідомлення про відсутність даних.
            if (!list.isEmpty()) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder sb = new StringBuilder();
                list.forEach((order) ->
                        sb.append(count.incrementAndGet())
                                .append(") ")
                                .append(order.toString())
                );
                return sb.toString();
            } else {
                LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
                return Orders.DATA_ABSENT_MSG;
            }
        } else {
            LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
            return Orders.DATA_ABSENT_MSG;
        }
    }

    public String readByNum(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Orders.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.error(Orders.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new OrderValidator().validateOrderData(data);
        if (!errors.isEmpty()) {
            try {
                throw new OrderException("Check inputs", errors);
            } catch (OrderException e) {
                LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        // Отримуємо дані
        Optional<Order> optional =
                repository.readByNum(Long.parseLong(data.get("num")));
        // Якщо Optional не містить null, формуємо виведення.
        // Інакше повідомлення про відсутність даних.
        if (optional.isPresent()) {
            // Отримуємо об'єкт з Optional
            Order order = optional.get();
            return order.toString();
        } else {
            LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
            return Orders.DATA_ABSENT_MSG;
        }
    }

    public String update(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Orders.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.error(Orders.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new OrderValidator().validateOrderData(data);
        if (!errors.isEmpty()) {
            try {
                throw new OrderException("Check inputs", errors);
            } catch (OrderException e) {
                LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        // Спершу перевіряємо наявність об'єкта в БД за таким num.
        // Якщо ні, повертаємо повідомлення про відсутність таких даних,
        // інакше оновлюємо відповідний об'єкт в БД
        Order order = new OrderMapper().mapOrderData(data);
        if (repository.readByNum(order.getNum()).isEmpty()) {
            return Orders.DATA_ABSENT_MSG;
        } else {
            return repository.update(order);
        }
    }

    public String delete(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Orders.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.error(Orders.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new OrderValidator().validateOrderData(data);
        if (!errors.isEmpty()) {
            try {
                throw new OrderException("Check inputs", errors);
            } catch (OrderException e) {
                LOGGER.warn(Orders.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        // Спершу перевіряємо наявність такого num в БД.
        // Якщо ні, повертаємо повідомлення про відсутність
        // таких даних в БД, інакше видаляємо відповідний об'єкт
        // із БД.
        Long num = new OrderMapper().mapOrderData(data).getNum();
        if (!repository.isNunExists(num)) {
            return Orders.DATA_ABSENT_MSG;
        } else {
            return repository.delete(num);
        }
    }
}
