package app.controllers;

import app.util.DateTimeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class MainSceneController {
    @FXML
    private Label greetLabel;

    private Map<String, Object> superSecretDataStorage;

    public void init(Map<String, Object> receivedDataStorage) {
        this.superSecretDataStorage = receivedDataStorage;
        String name = (String) this.superSecretDataStorage.get("userName");
        this.greetLabel.setText(DateTimeUtil.getTimeBasedGreeting(name));
    }

    public void autodiagnosisClickHandler(ActionEvent actionEvent) {
        Node eventSourceNode = (Node) actionEvent.getSource();
        Scene currentScene = eventSourceNode.getScene();
        goToScene(currentScene, "autodiagnosisForm");
    }

    public void recommendationsClickHandler(ActionEvent actionEvent) {
        Node eventSourceNode = (Node) actionEvent.getSource();
        Scene currentScene = eventSourceNode.getScene();
        goToScene(currentScene, "recommendations");
    }

    private void goToScene(Scene scene, String sceneName) {
        try {
            URL targetSceneFileUrl = new File("src/app/scenes/"+sceneName+".fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(targetSceneFileUrl);
            Parent targetPageParent = loader.load();
            Scene targetPageScene = new Scene(targetPageParent);
            Stage appStage = (Stage) scene.getWindow();
            appStage.hide();
            appStage.setScene(targetPageScene);

            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
