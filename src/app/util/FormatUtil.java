package app.util;

public class FormatUtil {
    public static boolean isNumeric(String str) {
        try {
            int check = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
