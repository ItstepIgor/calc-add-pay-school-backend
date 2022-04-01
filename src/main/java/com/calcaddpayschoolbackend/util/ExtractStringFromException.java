package com.calcaddpayschoolbackend.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractStringFromException {

    public static String extractStringFromException(String string) {
        String mistake = null;
        String table = null;
//        String regex = ("(Подробности:)(\\s+\\D*\\(id\\)\\D*\\d+\\D*?)(\"+)(\\D*)(\")");
        String regex = ("(Подробности:\\s+\\D*\\(id\\)\\D*\\d+\\))(\\D*?)(\"+)(\\D*)(\")");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            mistake = matcher.group(2);
            table = matcher.group(4);
        }
        return "На удаляемую запись" + mistake + findRuNameTable(table);
    }

    private static String findRuNameTable(String string) {
        Map<String, String> tables = Map.of("add_pay", "Дополнительные оплаты", "add_pay_fund", "Фонды",
                "add_pay_result", "Результаты расчета", "percent_salary_result", "Премиальные",
                "staff_list", "Штатное расписание", "time_sheet", "Табель", "users", "Пользователи");

        return tables.entrySet().stream()
                .filter(stringStringEntry -> stringStringEntry
                        .getKey()
                        .equals(string))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(" ");
    }
}
