package org.example.app.service;

import org.example.app.controller.OrderController;
import org.example.app.exceptions.OptionException;
import org.example.app.repository.impl.OrderRepository;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Orders;
import org.example.app.view.AppView;
import org.example.app.view.OrderView;

public class AppService {

    OrderRepository repository = new OrderRepository();
    OrderService service = new OrderService(repository);
    OrderView view = new OrderView();
    OrderController controller = new OrderController(service, view);

    public void handleOption(int option) {
        switch (option) {
            case 1 -> controller.createOrder();
            case 2 -> controller.readOrders();
            case 3 -> controller.updateOrder();
            case 4 -> controller.deleteOrder();
            case 5 -> controller.readOrderByNum();
            case 0 -> new AppView().getOutput(Integer.toString(option));
            default -> {
                try {
                    throw new OptionException(Orders.INCORRECT_OPTION_MSG);
                } catch (OptionException e) {
                    new AppView().getOutput(e.getMessage());
                    AppStarter.startApp();
                }
            }
        }
    }
}
