package by.itakademy.akulov.utils;

import lombok.experimental.UtilityClass;

import static java.util.Objects.isNull;

@UtilityClass
public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isEmpty(String value) {
        return isNull(value) || EMPTY.equals(value);
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }
}
