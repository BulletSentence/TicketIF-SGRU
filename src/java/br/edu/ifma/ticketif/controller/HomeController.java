package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.core.Window;
import br.edu.ifma.ticketif.model.DAO.AlunoDAO;
import br.edu.ifma.ticketif.model.entity.database.Aluno;
import br.edu.ifma.ticketif.util.errors.Alerts;
import br.edu.ifma.ticketif.util.formatters.TextFieldFormatter;
import br.edu.ifma.ticketif.util.validar.ValidaCPF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.List;
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
    private JFXComboBox<String> cadastro_alunoCurso;

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

    @FXML
    private TableView<Aluno> tabelaAlunos;
    @FXML
    private TableColumn<Aluno, String> nomeAluno;
    @FXML
    private TableColumn<Aluno, String> matriculaAluno;


    Alerts alert = new Alerts();
    private Window window = new Window();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBoxGrupo();
    }

    @FXML
    private void FormatadorMascaraCPF(){ //Mascara para formatar o texto do cpf
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##"); // Mascara de CPF
        tff.setCaracteresValidos("0123456789");  // Somente Numeros
        tff.setTf(cadastro_alunoCPF);
        tff.formatter();
    }

    @FXML
    public List<Aluno> clickCadastro(){
        AlunoDAO alunoDAO = new AlunoDAO(); // Cria a conexão com o banco de dados
        Aluno aluno = new Aluno(); // Cria a classe do aluno
        ValidaCPF valid = new ValidaCPF();

        aluno.setNome(cadastro_alunoNome.getText());
        aluno.setMatricula(cadastro_alunoMatricula.getText().toUpperCase());
        aluno.setEmail(cadastro_alunoEmail.getText());
        aluno.setFone(Integer.parseInt(cadastro_alunoFone.getText()));

//        LocalDate value = cadastro_alunoDataNasc.getValue();
//        aluno.setDataNasc(value);

        String cpf = cadastro_alunoCPF.getText();

        //Verifica se é nulo
        if(cadastro_alunoNome.getText()==null || cadastro_alunoNome.getText().trim().equals("")
                || cadastro_alunoMatricula.getText()==null || cadastro_alunoMatricula.getText().trim().equals("")) {

            alert.infoAlert("TicketIF", "Os campos não podem ser vazios", "Deus é fiel");

        } else {

            cpf = cpf.replaceAll("[-.]", "");   //remove mascara
            if (valid.isValidCPF(cpf)) {             //cpf valido
                aluno.setCpf(cadastro_alunoCPF.getText());
                alunoDAO.inserirAluno(aluno);

                // Janela de Aviso apos ser cadastrado um aluno;
                alert.infoAlert("TicketIF", "Aluno Cadastrado com Sucesso!", "Deus é fiel");

            } else {
                // emite alerta caso o cpf seja invalido
                alert.infoAlert("TicketIF", "CPF invalido", "Insira novamente um CPF válido");
                cadastro_alunoCPF.clear();
            }
        }

        return alunoDAO.obterListaAluno();
    }

    public void ChoiceBoxGrupo(){

        //cadastro_alunoCurso.getItems().add("CIENCIA");
    }


    @FXML
    private void btnCadastrar() {
        window.loadWindow(telaHome, "/fxml/CadastroAluno.fxml");
    }
}
