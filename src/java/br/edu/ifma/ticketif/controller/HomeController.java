package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.core.Window;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML
    private BorderPane telaHome;

    private Window window = new Window();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCadastrar();
    }

    @FXML
    private void btnCadastrar() {
        window.loadWindow(telaHome, "/fxml/CadastroAluno.fxml");
    }

}
