package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
                buttonHandler();
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

    public void buttonHandler() {
        System.out.println("Hola " + login_name_info.getText());
    }
}
