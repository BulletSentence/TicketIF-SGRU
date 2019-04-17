package br.edu.ifma.ticketif.core;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class Window {

    public void loadWindow(BorderPane localTela, String endecoNovaTela) {
        try {
            JFXTabPane pane = FXMLLoader.load(getClass().getResource(endecoNovaTela));
            localTela.getChildren().remove(localTela.getCenter());
            localTela.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void newSplash(Stage stage, Label lb, String load, String stylesheet, String title, boolean resize, StageStyle style, boolean maximized) {
        try {
            Stage st = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            System.out.println(this.getClass().getResource(stylesheet).toExternalForm());

            try {
                File f = new File(this.getClass().getResource(stylesheet).toExternalForm());

            } catch (Exception e) {
                System.out.println("Arquivo não encontrado!");
            }
            st.setMinHeight(700);
            st.setMinWidth(800);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(title);
            st.setScene(scene);
            st.show();
            stage.close();

        } catch (Exception e) {
        }
    }

}
