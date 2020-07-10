package app.controllers;

import app.FXMLLoaderFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class RecommendationsController {

    private Map<String, Object> superSecretDataStorage;

    public void init(Map<String, Object> receivedDataStorage) {
        this.superSecretDataStorage = receivedDataStorage;
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoaderFunctions().getLoader("main");

            Parent targetPageParent = loader.load();
            Node eventSourceNode = (Node) actionEvent.getSource();
            Scene targetPageScene = new Scene(targetPageParent);
            Scene scene = eventSourceNode.getScene();

            Stage appStage = (Stage) scene.getWindow();
            appStage.hide();
            appStage.setScene(targetPageScene);

            MainSceneController mainSceneController = loader.getController();
            mainSceneController.init(superSecretDataStorage);

            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
