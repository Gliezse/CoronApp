package app.controllers;

import app.content.Questions;
import app.util.DateTimeUtil;
import app.util.FormatUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AutodiagnosisController implements Initializable {
    private Map<String, Object> superSecretDataStorage;
    private int questionNumber = 0;

    @FXML
    private Label questionNumberLabel;
    @FXML
    private Label questionLabel;

    @FXML
    private TextField bodyTemperature;
    @FXML
    private Label bodyTemperatureLabel;


    public void handleTemperatureChange(KeyEvent keyEvent) {
        // TODO
    }

    public void nextQuestionHandler(ActionEvent actionEvent) {
        switch (this.questionNumber) {
            case 0:
                String bodyTemperature = this.bodyTemperature.getText();
                superSecretDataStorage.put("bodyTemperature", bodyTemperature);
                break;
        }
        questionNumber++;
        setNewQuestion();
    }

    private void setNewQuestion() {
        questionNumberLabel.setText("Pregunta " + (questionNumber + 1) + " de 5");
        String newQuestion = Questions.getQuestions().get(questionNumber);
        questionLabel.setText(newQuestion);
        hideAllFields();
    }

    public void init(Map<String, Object> receivedDataStorage) {
        this.superSecretDataStorage = receivedDataStorage;
        questionNumberLabel.setText("Pregunta " + (this.questionNumber + 1) + " de 5" );
    }

    private void hideAllFields() {
        bodyTemperature.setVisible(false);
        bodyTemperatureLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bodyTemperature.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            // TODO
            //bodyTemperature.setText(FormatUtil.formatTemperature(bodyTemperature.getText()));
        });
    }
}
