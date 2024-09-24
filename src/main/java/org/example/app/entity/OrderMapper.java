package org.example.app.entity;

import java.util.Map;

public class OrderMapper {

    public Order mapOrderData(Map<String, String> data) {
        Order order = new Order();
        if (data.containsKey("num"))
            order.setNum(Long.parseLong(data.get("num")));
        if (data.containsKey("name"))
            order.setName(data.get("name"));
        return order;
    }
}
