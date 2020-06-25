package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RecommendationsController {
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
