package app;

import javafx.fxml.FXMLLoader;

public class FXMLLoaderFunctions {
    public FXMLLoader getLoader(String resourceName) {
        return new FXMLLoader(getClass().getResource("scenes/"+ resourceName +".fxml"));
    }
}
