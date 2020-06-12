package app.content;

import java.util.LinkedList;
import java.util.List;

import static app.content.Constants.*;

public class Questions {
    public static List<String> getQuestions() {
        List<String> questions = new LinkedList<>();

        questions.add(QUESTION_1);
        questions.add(QUESTION_2);
        questions.add(QUESTION_3);
        questions.add(QUESTION_4);
        questions.add(QUESTION_5);

        return questions;
    }
}
