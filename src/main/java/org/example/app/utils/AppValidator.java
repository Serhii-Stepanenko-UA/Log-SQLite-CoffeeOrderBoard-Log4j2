package org.example.app.utils;

public class AppValidator {

    // Regex для num.
    // Регулярний вираз для позитивних цілих чисел без нулів, мінусів або ком на початку.
    // Для максимум 1000 замовлень за зміну
    // Regex для номера замовлення 1000 або любе ціле число від 1 до 999
    // ^ вказує на початок рядка
    // [1-9] відповідає будь-якій цифрі від 1 до 9 і гарантує відсутність нулів на початку.
    // [0-9] відповідає будь-якій цифрі від 0 до 9
    // $ вказує на кінець рядка
    public final static String NUM_RGX = "^(1000|[1-9][0-9][0-9]?)$";

    // Валідація вхідного значення num
    public static boolean isNumValid(String num) {
        if (num != null)
            return num.isEmpty() || !num.matches(NUM_RGX);
        return false;
    }
}
