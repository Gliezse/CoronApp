package app.content;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static app.content.Constants.*;

public class Questions {
    public static List<String> getQuestions() {
        List<String> questions = new LinkedList<>();

        questions.add(QUESTION_1);
        questions.add(QUESTION_2);

        return questions;
    }
}
