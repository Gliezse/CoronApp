package app.util;

import app.content.Constants;

import java.util.Map;

public class AppUtil {
    public static int calculateResult (Map<String, Object> data) {
        double result = 0;
        int temperature = Integer.parseInt(String.valueOf(data.get("bodyTemperature")));
        result += (temperature > 37 ? 40 : 0) + (int) data.get("sympthomsScore");

        // Por cada "Si" que seleccionó entre las preguntas 3 y 5 se le suma un 20% al puntaje obtenido hasta el momento
        if ((boolean) data.get("fromForeign")) result = result + result * 0.2;
        if ((boolean) data.get("nearConfirmed")) result = result + result * 0.2;
        if ((boolean) data.get("riskGroup")) result = result + result * 0.2;

        if (result < 33) {
            return 0;
        }
        if (result < 66){
            return 1;
        }
        return 2;
    }

    public static String getResultTitle(int level) {
        switch (level) {
            case 0:
                return Constants.LEVEL_0_TITLE;
            case 1:
                return Constants.LEVEL_1_TITLE;
            case 2:
                return Constants.LEVEL_2_TITLE;
            default:
                return "";
        }
    }

    public static String getResultRecommendation(int level) {
        switch (level) {
            case 0:
                return Constants.LEVEL_0_RECOMMENDATION;
            case 1:
                return Constants.LEVEL_1_RECOMMENDATION;
            case 2:
                return Constants.LEVEL_2_RECOMMENDATION;
            default:
                return "";
        }
    }
}
