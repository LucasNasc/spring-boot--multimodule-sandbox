package br.com.nascimento.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String formatOffsetDatetime(OffsetDateTime offsetDateTime) {
        return  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(offsetDateTime);
    }
}
