package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.core.Window;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    DateTimeFormatter dateformater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate localDate = LocalDate.now();

    @FXML
    private Label label_dataatual;

    @FXML
    private BorderPane telaHome;

    private Window window = new Window();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_dataatual.setText(dateformater.format(localDate));
        System.out.println("Data de conexão: " + dateformater.format(localDate));
        btnCadastrar();

    }

    @FXML
    private void btnCadastrar() {
        window.loadWindow(telaHome, "/fxml/CadastroAluno.fxml");
    }

    @FXML
    private void btnRegistro() {
        window.loadWindow(telaHome, "/fxml/Registro.fxml");
    }
}
