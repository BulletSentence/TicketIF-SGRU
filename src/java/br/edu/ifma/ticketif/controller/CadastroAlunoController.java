package br.edu.ifma.ticketif.controller;

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
import javafx.scene.control.TableView;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroAlunoController implements Initializable {

    @FXML
    protected TableView<?> tabelaAlunos;
    Alerts alert = new Alerts();
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
    private JFXComboBox<String> cadastro_alunoSexo;
    @FXML
    private JFXDatePicker cadastro_alunoDataNasc;
    @FXML
    private JFXComboBox<String> cadastro_alunoCurso;
    @FXML
    private JFXComboBox<String> cadastro_alunoTipo;
    @FXML
    private JFXComboBox<String> cadastro_alunoTurma;
    @FXML
    private JFXComboBox<String> cadastro_alunoAnoEntrada;
    @FXML
    private JFXComboBox<String> cadastro_alunoAnoSaida;
    @FXML
    private JFXButton btn_cadastrar;
    @FXML
    private JFXComboBox<String> gerenciaAlunoCurso;
    @FXML
    private JFXComboBox<?> gerenciaAlunoTipo;
    @FXML
    private JFXComboBox<?> GerenciaAlunoTurma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxCurso();
        comboBoxSexo();
        comboBoxTipoAluno();
        comboBoxTurma();
        comboBoxAnoSaida();
        comboBoxAnoEntrada();
    }

    /**
     * ITENS DAS COMBOBOX DA TELA DE CADASTRO DO ALUNO
     ******************************/
    public void comboBoxAnoEntrada() {
        ArrayList<String> anos = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int data = Integer.parseInt(dateFormat.format(date));
        int index = 0;
        for (int i = data; i >= data - 7; i--) {
            anos.add(index, String.valueOf(i));
        }

        cadastro_alunoAnoEntrada.getItems().addAll(anos);
    }

    private void comboBoxAnoSaida() {
        cadastro_alunoAnoSaida.getItems().addAll("2019", "2010", "2021");
    }

    private void comboBoxTurma() {
        cadastro_alunoTurma.getItems().addAll("2019.1", "2019.2", "2010.1");
    }

    private void comboBoxCurso() {
        cadastro_alunoCurso.getItems().addAll("Ciência da Computação", "Química", "Biologia");
    }

    @FXML
    private void comboBoxSexo() {
        cadastro_alunoSexo.getItems().addAll("Masculino", "Feminino");
    }

    /**
     * FIM DOS METODOS DOS ITENS DA COMBOBOX DA TELA DE CADASTRO
     ********************/


    @FXML
    private void comboBoxTipoAluno() {
        cadastro_alunoTipo.getItems().addAll("Externo", "Interno");
    }

    /**
     * Mascara validadora de cpf
     */

    @FXML
    private void FormatadorMascaraCPF() { //Mascara para formatar o texto do cpf
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##"); // Mascara de CPF
        tff.setCaracteresValidos("0123456789");  // Somente Numeros
        tff.setTf(cadastro_alunoCPF);
        tff.formatter();
    }

    @FXML
    public List<Aluno> clickCadastro() {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno(); // Cria a classe do aluno
        ValidaCPF valid = new ValidaCPF();

        aluno.setNome(cadastro_alunoNome.getText());
        aluno.setMatricula(cadastro_alunoMatricula.getText().toUpperCase());
        aluno.setEmail(cadastro_alunoEmail.getText());
        //aluno.setFone(Integer.parseInt(cadastro_alunoFone.getText()));

//        LocalDate value = cadastro_alunoDataNasc.getValue();
//        aluno.setDataNasc(value);

        String cpf = cadastro_alunoCPF.getText();

        //Verifica se é nulo
        if (cadastro_alunoNome.getText() == null || cadastro_alunoNome.getText().trim().equals("")
                || cadastro_alunoMatricula.getText() == null || cadastro_alunoMatricula.getText().trim().equals("")) {

            alert.infoAlert("TicketIF", "Os campos não podem ser vazios", "Deus é fiel");

        } else {

            cpf = cpf.replaceAll("[-.]", "");   //remove mascara
            if (valid.isValidCPF(cpf)) {             //cpf valido
                aluno.setCpf(cadastro_alunoCPF.getText());
                alunoDAO.inserirAluno(aluno);

                /**
                 * Janela de Aviso apos ser cadastrado um aluno*/
                alert.infoAlert("TicketIF", "Aluno Cadastrado com Sucesso!", "Deus é fiel");

            } else {
                // emite alerta caso o cpf seja invalido
                alert.infoAlert("TicketIF", "CPF invalido", "Insira novamente um CPF válido");
                cadastro_alunoCPF.clear();
            }
        }

        return alunoDAO.obterListaAluno();
    }


}
