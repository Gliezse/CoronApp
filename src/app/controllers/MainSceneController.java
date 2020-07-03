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

    // Map que actua como un state de la aplicación y se pasa de pantalla en pantalla
    private Map<String, Object> superSecretDataStorage;

    public void init(Map<String, Object> receivedDataStorage) {
        this.superSecretDataStorage = receivedDataStorage;
        String name = (String) this.superSecretDataStorage.get("userName");

        // Se setea el saludo en base a la hora del sistema
        this.greetLabel.setText(DateTimeUtil.getTimeBasedGreeting(name));
    }

    // Click en ir a autodiagnóstico
    public void autodiagnosisClickHandler(ActionEvent actionEvent) {
        Node eventSourceNode = (Node) actionEvent.getSource();
        Scene currentScene = eventSourceNode.getScene();
        goToScene(currentScene, "autodiagnosisForm");
    }

    // Click en ir a recomendaciones
    public void recommendationsClickHandler(ActionEvent actionEvent) {
        Node eventSourceNode = (Node) actionEvent.getSource();
        Scene currentScene = eventSourceNode.getScene();
        goToScene(currentScene, "recommendations");
    }

    // Handler para cambiar de escena casi semi-dinàmicamente
    private void goToScene(Scene scene, String sceneName) {
        try {
            URL targetSceneFileUrl = new File("src/app/scenes/"+sceneName+".fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(targetSceneFileUrl);
            Parent targetPageParent = loader.load();
            Scene targetPageScene = new Scene(targetPageParent);
            Stage appStage = (Stage) scene.getWindow();
            appStage.hide();
            appStage.setScene(targetPageScene);

            // Se inicializa el controller de la siguiente escena y se le persisten los datos del state improvisado
            if (sceneName.equals("autodiagnosisForm")) {
                AutodiagnosisController autodiagnosisController = loader.getController();
                autodiagnosisController.init(superSecretDataStorage);
            } else if (sceneName.equals("recommendations")) {
                RecommendationsController recommendationsController = loader.getController();
                recommendationsController.init(superSecretDataStorage);
            }

            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
