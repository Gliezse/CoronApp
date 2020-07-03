package app.util;

import app.content.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
        public static String getTimeBasedGreeting(String name) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        int localTime = Integer.parseInt(dateTimeFormatter.format(now));
        String greet;
        if (localTime > 6 && localTime < 12) {
            greet = Constants.GREETING_MORNING;
        } else if (localTime >= 12 && localTime < 20) {
            greet = Constants.GREETING_AFTERNOON;
        } else {
            greet = Constants.GREETING_NIGHT;
        }
        greet += name;
        return greet;
    }
}
