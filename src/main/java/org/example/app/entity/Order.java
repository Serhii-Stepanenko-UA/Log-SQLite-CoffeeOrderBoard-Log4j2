package org.example.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Сутність об'єкта, що є записом у БД.
// Набір змінних (властивостей об'єкта) відповідає
// стовпцям у таблиці БД.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    Long num;
    String name;

    @Override
    public String toString() {
        return  "num " + num +
                ", " + name + "\n";
    }
}
