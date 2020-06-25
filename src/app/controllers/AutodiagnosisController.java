package app.controllers;

import app.content.Questions;
import app.util.DateTimeUtil;
import app.util.FormatUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import app.util.AppUtil;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AutodiagnosisController implements Initializable {
    private Map<String, Object> superSecretDataStorage = new HashMap<>();
    private int questionNumber = 0;

    @FXML private Label questionNumberLabel;
    @FXML private Label questionLabel;

    @FXML private AnchorPane questionsPane;
    @FXML private AnchorPane resultsPane;

    // PREGUNTA 1
    @FXML private AnchorPane firstQuestionPane;
    @FXML private TextField bodyTemperature;

    // PREGUNTA 2
    @FXML private AnchorPane secondQuestionPane;
    @FXML private CheckBox tosCheck;
    @FXML private CheckBox cansancioCheck;
    @FXML private CheckBox molestiaPechoCheck;
    @FXML private CheckBox gargantaCheck;
    @FXML private CheckBox dolorCabezaCheck;
    @FXML private CheckBox aireCheck;

    // PREGUNTA 3
    @FXML private AnchorPane thirdQuestionPane;
    @FXML private ToggleGroup thirdQuestionToggleGroup;

    // PREGUNTA 4
    @FXML private AnchorPane fourthQuestionPane;
    @FXML private ToggleGroup fourthQuestionToggleGroup;

    // PREGUNTA 5
    @FXML private AnchorPane fifthQuestionPane;
    @FXML private ToggleGroup fifthQuestionToggleGroup;

    // RESULTADOS
    @FXML private Label resultTitle;
    @FXML private Label recommendationText;

    public void handleTemperatureChange(KeyEvent keyEvent) {
        // TODO
    }

    public void nextQuestionHandler(ActionEvent actionEvent) {
        switch (this.questionNumber) {
            case 0:
                String bodyTemperature = this.bodyTemperature.getText();
                superSecretDataStorage.put("bodyTemperature", bodyTemperature);
                break;
            case 1:
                int score = 0;
                if (tosCheck.isSelected()) score += 10;
                if (cansancioCheck.isSelected()) score += 10;
                if (gargantaCheck.isSelected()) score += 10;
                if (dolorCabezaCheck.isSelected()) score += 10;
                if (molestiaPechoCheck.isSelected()) score += 20;
                if (aireCheck.isSelected()) score += 20;

                superSecretDataStorage.put("sympthomsScore", score);
                break;
            case 2:
                boolean foreign = false;
                RadioButton selectedForeign = (RadioButton) thirdQuestionToggleGroup.getSelectedToggle();
                if ("Si".equals(selectedForeign.getText())) {
                    foreign = true;
                }
                superSecretDataStorage.put("fromForeign", foreign);
                break;
            case 3:
                boolean near = false;
                RadioButton selectedNear = (RadioButton) fourthQuestionToggleGroup.getSelectedToggle();
                if ("Si".equals(selectedNear.getText())) {
                    near = true;
                }
                superSecretDataStorage.put("nearConfirmed", near);
                break;
            case 4:
                boolean risk = false;
                RadioButton selected = (RadioButton) fifthQuestionToggleGroup.getSelectedToggle();
                if ("Si".equals(selected.getText())) {
                    risk = true;
                }
                superSecretDataStorage.put("riskGroup", risk);
                break;
            default:
                break;
        }

        if (this.questionNumber == 4) {
            showResultsScreen();
        } else {
            questionNumber++;
            setNewQuestion();
        }

    }

    private void setNewQuestion() {
        questionNumberLabel.setText("Pregunta " + (questionNumber + 1) + " de 5");
        String newQuestion = Questions.getQuestions().get(questionNumber);
        questionLabel.setText(newQuestion);
        hideAllFields();
        showCurrentQuestionFields();
    }

    private void hideAllFields() {
        firstQuestionPane.setVisible(false);
        secondQuestionPane.setVisible(false);
        thirdQuestionPane.setVisible(false);
        fourthQuestionPane.setVisible(false);
        fifthQuestionPane.setVisible(false);
    }

    private void showCurrentQuestionFields() {
        switch (this.questionNumber) {
            case 0:
                firstQuestionPane.setVisible(true);
                break;
            case 1:
                secondQuestionPane.setVisible(true);
                break;
            case 2:
                thirdQuestionPane.setVisible(true);
                break;
            case 3:
                fourthQuestionPane.setVisible(true);
                break;
            case 4:
                fifthQuestionPane.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void showResultsScreen() {
        questionsPane.setVisible(false);
        int level = AppUtil.calculateResult(superSecretDataStorage);
        resultTitle.setText(AppUtil.getResultTitle(level));
        recommendationText.setText(AppUtil.getResultRecommendation(level));
        resultsPane.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.questionNumber = 0;
        System.out.println("init");
        resultsPane.setVisible(false);
        questionsPane.setVisible(true);

        setNewQuestion();
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        try {
            URL targetSceneFileUrl = new File("src/app/scenes/main.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(targetSceneFileUrl);
            Parent targetPageParent = loader.load();
            Node eventSourceNode = (Node) actionEvent.getSource();
            Scene targetPageScene = new Scene(targetPageParent);
            Scene scene = eventSourceNode.getScene();

            Stage appStage = (Stage) scene.getWindow();
            appStage.hide();
            appStage.setScene(targetPageScene);

            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
