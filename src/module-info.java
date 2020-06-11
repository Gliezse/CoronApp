module CoronApp {
    requires javafx.fxml;
    requires javafx.controls;

    opens app.controllers to javafx.fxml;
    opens app;
}