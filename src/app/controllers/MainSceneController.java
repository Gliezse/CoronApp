package app.controllers;

import app.util.DateTimeUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Map;

public class MainSceneController {
    @FXML
    private Label greetLabel;

    private Map<String, String> superSecretDataStorage;

    public void init(Map<String, String> receivedDataStorage) {
        this.superSecretDataStorage = receivedDataStorage;
        String name = this.superSecretDataStorage.get("userName");
        this.greetLabel.setText(DateTimeUtil.getTimeBasedGreeting(name));
    }

    private void goToScene(Stage stage, String sceneName) {
        
    }

}
