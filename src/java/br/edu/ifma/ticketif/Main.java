package br.edu.ifma.ticketif;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro8.JMetro;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Splash.fxml"));
        new JMetro(JMetro.Style.LIGHT).applyTheme(root);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
