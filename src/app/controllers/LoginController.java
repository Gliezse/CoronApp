package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField login_name_info;
    @FXML
    private Button login_btn;
    @FXML
    private Button exit_btn;

    public void keyTypedHandler(KeyEvent event) {
        if (!login_name_info.getText().isBlank()) {
            login_btn.setDisable(false);

            if (event.getCharacter().equals("\r")) {
                Node eventSourceNode = (Node) event.getSource();
                Scene currentScene = eventSourceNode.getScene();
                login(currentScene);
            }
        } else {
            login_btn.setDisable(true);
        }
    }

    public void exitButtonHandler() {
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        System.out.println("Adiossssss");
        stage.close();
    }

    public void buttonHandler(ActionEvent actionEvent) {
        Node eventSourceNode = (Node) actionEvent.getSource();
        Scene currentScene = eventSourceNode.getScene();
        login(currentScene);
    }

    private void login(Scene scene) {
        try {
            URL mainSceneFileURL = new File("src/app/scenes/main.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(mainSceneFileURL);
            Parent mainPageParent = loader.load();
            Scene mainPageScene = new Scene(mainPageParent);
            Stage appStage = (Stage) scene.getWindow();
            appStage.hide();
            appStage.setScene(mainPageScene);

            // Objeto para pasar datos desde login a siguientes pantallas
            Map<String, String> data = new HashMap<>();
            data.put("userName", this.login_name_info.getText());
            MainSceneController mainSceneController = loader.getController();
            // Seteo la data a la siguiente pantalla
            mainSceneController.init(data);

            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
