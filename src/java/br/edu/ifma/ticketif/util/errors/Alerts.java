package br.edu.ifma.ticketif.util.errors;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.Optional;

public class Alerts {

    public void infoAlert(String title, String head, String context) {
        Alert feedback = new Alert(Alert.AlertType.INFORMATION);
        feedback.setTitle(title);
        feedback.setHeaderText(head);
        feedback.setContentText(context);
        feedback.showAndWait();
    }

    public boolean confirmAlert(String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("TicketIF");
        alert.setHeaderText("Você tem certeza?");
        alert.setContentText(context);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
            // ... user chose CANCEL or closed the dialog
        }
    }

    public static void createDialog(Alert.AlertType alertType, String titulo, String mensagem) {
        Alert alert = new Alert(alertType, mensagem);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Notificação");
        alert.setHeaderText(titulo);
        alert.showAndWait();
    }

}
