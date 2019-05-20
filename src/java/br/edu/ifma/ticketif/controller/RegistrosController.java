package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.model.DAO.AlunoDAO;
import br.edu.ifma.ticketif.model.entity.database.Aluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrosController implements Initializable {

    @FXML
    private TableView<Aluno> tabelaAlunos;
    @FXML
    private TableColumn<Aluno, String> colunaNome;
    @FXML
    private TableColumn<Aluno, String> colunaMatricula;
    @FXML
    private TableColumn<Aluno, String> colunaCurso;

    @FXML
    private JFXTextField textf_matricula;

    @FXML
    private TableView<Aluno> tabelaAlunosBusca;
    @FXML
    private TableColumn<?, ?> colunaNomeBusca;
    @FXML
    private TableColumn<?, ?> colunaMatriculaBusca;
    @FXML
    private TableColumn<?, ?> colunaCursoBusca;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizaTabela();
    }


    private void atualizaTabela() {
        AlunoDAO aluno = new AlunoDAO();
        ObservableList<Aluno> dadosTabela = FXCollections.observableArrayList();

        List<Aluno> listaAluno = aluno.obterListaAluno();
        for (Aluno a : listaAluno) {
            dadosTabela.add(new Aluno(a.getNome(), a.getMatricula(), a.getCurso()));
        }

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tabelaAlunos.setItems(dadosTabela);

    }


    public void buscarAluno(){
        AlunoDAO alunoDao = new AlunoDAO();
        ObservableList<Aluno> dadosTabelaBusca = FXCollections.observableArrayList();

        for (Aluno a : alunoDao.obterListaAluno()) {

            if(textf_matricula.getText().toUpperCase().equals(alunoDao.buscarAluno(a).getMatricula())){
                dadosTabelaBusca.add(new Aluno(a.getNome(), a.getMatricula(), a.getCurso()));

            }
        }

        colunaNomeBusca.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaMatriculaBusca.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaCursoBusca.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tabelaAlunosBusca.setItems(dadosTabelaBusca);

    }

}
