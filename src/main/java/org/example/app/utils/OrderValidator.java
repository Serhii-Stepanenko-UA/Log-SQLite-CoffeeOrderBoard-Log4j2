package org.example.app.utils;

import java.util.HashMap;
import java.util.Map;

public class OrderValidator {

    public Map<String, String> validateOrderData(Map<String, String> data) {

        Map<String, String> errors = new HashMap<>();

        if (data.containsKey("num") & AppValidator.isNumValid(data.get("num")))
            errors.put("num", Orders.WRONG_NUM_MSG);

        if (data.containsKey("name")) {
            if (data.get("name") != null & data.get("name").isEmpty())
                errors.put("name", Orders.INPUT_REQ_MSG);
        }

        return errors;
    }
}
