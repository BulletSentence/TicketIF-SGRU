package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.core.Window;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private BorderPane telaHome;
    @FXML
    private JFXTextField cadastro_alunoNome;

    @FXML
    private JFXTextField cadastro_alunoEmail;

    @FXML
    private JFXTextField cadastro_alunoMatricula;

    @FXML
    private JFXTextField cadastro_alunoCPF;

    @FXML
    private JFXTextField cadastro_alunoFone;

    @FXML
    private JFXComboBox<?> cadastro_alunoSexo;

    @FXML
    private JFXDatePicker cadastro_alunoDataNasc;

    @FXML
    private JFXComboBox<?> cadastro_alunoCurso;

    @FXML
    private JFXComboBox<?> cadastro_alunoTipo;

    @FXML
    private JFXComboBox<?> cadastro_alunoTurma;

    @FXML
    private JFXComboBox<?> cadastro_alunoAnoEntrada;

    @FXML
    private JFXComboBox<?> cadastro_alunoAnoSaida;

    @FXML
    private JFXButton btn_cadastrar;

    @FXML
    private JFXComboBox<?> gerenciaAlunoCurso;

    @FXML
    private JFXComboBox<?> gerenciaAlunoTipo;

    @FXML
    private JFXComboBox<?> GerenciaAlunoTurma;


    private Window window = new Window();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



    @FXML
    private void btnCadastrar() {
        window.loadWindow(telaHome, "/fxml/CadastroAluno.fxml");
    }
}
