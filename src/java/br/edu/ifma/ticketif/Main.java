package br.edu.ifma.ticketif;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Carrega o FXML
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Splash.fxml"));
        Scene scene = new Scene(root);
        
        // Retira a barra superior de tarefas
        stage.initStyle(StageStyle.UNDECORATED);

        // Gera a splash
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
