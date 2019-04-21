package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.model.DAO.AlunoDAO;
import br.edu.ifma.ticketif.model.entity.database.Aluno;
import br.edu.ifma.ticketif.util.errors.Alerts;
import br.edu.ifma.ticketif.util.formatters.TextFieldFormatter;
import br.edu.ifma.ticketif.util.validar.ValidaCPF;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CadastroAlunoController implements Initializable {

    Alerts alert = new Alerts();

    @FXML
    private JFXTextField aluno_nome;
    @FXML
    private JFXTextField aluno_email;
    @FXML
    private JFXTextField aluno_matricula;
    @FXML
    private JFXTextField aluno_cpf;
    @FXML
    private JFXTextField aluno_fone;
    @FXML
    private JFXComboBox<String> aluno_sexo;
    @FXML
    private JFXDatePicker aluno_data_nasc;
    @FXML
    private JFXComboBox<String> aluno_curso;
    @FXML
    private JFXComboBox<String> aluno_tipo;
    @FXML
    private JFXComboBox<String> aluno_turma;
    @FXML
    private JFXComboBox<String> aluno_ano_entrada;
    @FXML
    private JFXComboBox<String> aluno_ano_saida;

    @FXML
    private TableView<Aluno> tabelaAlunos;
    @FXML
    private TableColumn<Aluno, String> colunaNome;
    @FXML
    private TableColumn<Aluno, String> colunaMatricula;
    @FXML
    private TableColumn<Aluno, String> colunaCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBoxCurso();
        comboBoxSexo();
        comboBoxTipoAluno();
        comboBoxTurma();
        comboBoxAnoSaida();
        comboBoxAnoEntrada();
    }

    public void comboBoxAnoEntrada() {
        ArrayList<String> anos = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int data = Integer.parseInt(dateFormat.format(date));
        int index = 0;
        for (int i = data; i >= data - 7; i--) {
            anos.add(index, String.valueOf(i));
        }

        aluno_ano_entrada.getItems().addAll(anos);
    }

    private void comboBoxAnoSaida() {
        aluno_ano_saida.getItems().addAll("2019", "2010", "2021");
    }

    private void comboBoxTurma() {
        aluno_turma.getItems().addAll("2019.1", "2019.2", "2010.1");
    }

    private void comboBoxCurso() {
        aluno_curso.getItems().addAll("Ciência da Computação", "Química", "Biologia");
    }

    private void comboBoxSexo() {
        aluno_sexo.getItems().addAll("Masculino", "Feminino");
    }

    private void comboBoxTipoAluno() {
        aluno_tipo.getItems().addAll("Externo", "Interno");
    }

    @FXML
    private void MascaraCPF() { //Mascara para formatar o texto do cpf
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##"); // Mascara de CPF
        tff.setCaracteresValidos("0123456789");  // Somente Numeros
        tff.setTf(aluno_cpf);
        tff.formatter();
    }

    @FXML
    private List<Aluno> clickCadastro() {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        ValidaCPF valid = new ValidaCPF();

        try {

            aluno.setNome(aluno_nome.getText());
            aluno.setMatricula(aluno_matricula.getText().toUpperCase());
            aluno.setEmail(aluno_email.getText());
            aluno.setFone(aluno_fone.getText());
            aluno.setSexo(aluno_sexo.getValue());
            aluno.setTurma(aluno_turma.getValue());
            aluno.setTipo(aluno_tipo.getValue());
            aluno.setCurso(aluno_curso.getValue());
            aluno.setAnoEntrada(Integer.parseInt(aluno_ano_entrada.getValue()));
            aluno.setAnoSaida(Integer.parseInt(aluno_ano_saida.getValue()));

            LocalDate localDate = aluno_data_nasc.getValue();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            aluno.setDataNasc(sqlDate);

        }catch(Exception e){
            e.printStackTrace();
        }

        String cpf = aluno_cpf.getText();

        if (aluno_nome.getText() == null
                || aluno_nome.getText().trim().equals("")
                || aluno_matricula.getText() == null
                || aluno_matricula.getText().trim().equals("")
                || aluno_cpf.getText() == null
                || aluno_cpf.getText().trim().equals("")
                || aluno_curso.getValue() == null
                || aluno_curso.getValue().trim().equals("")
                || aluno_email.getText() == null
                || aluno_email.getText().trim().equals("")
                || aluno_sexo.getValue() == null
                || aluno_sexo.getValue().trim().equals("")
                || aluno_turma.getValue() == null
                || aluno_turma.getValue().trim().equals("")
                || aluno_tipo.getValue() == null
                || aluno_tipo.getValue().trim().equals(""))
        {

            alert.infoAlert("TicketIF", "Os campos não podem ser vazios", "Deus é fiel");

        } else {

            cpf = cpf.replaceAll("[-.]", "");   //remove mascara
            if (valid.isValidCPF(cpf)) {             //cpf valido
                aluno.setCpf(aluno_cpf.getText());
                alunoDAO.inserirAluno(aluno);

                alert.infoAlert("TicketIF", "Aluno Cadastrado com Sucesso!", "Deus é fiel");

                aluno_nome.clear();
                aluno_cpf.clear();
                aluno_tipo.setValue("");
                aluno_turma.setValue("");
                aluno_sexo.setValue("");
                aluno_email.clear();
                aluno_fone.clear();
                aluno_ano_entrada.setValue("");
                aluno_ano_saida.setValue("");
                aluno_matricula.clear();
                aluno_curso.setValue("");
                //aluno_data_nasc.setValue(LocalDate.parse(null));

            } else {

                // emite alerta caso o cpf seja invalido
                alert.infoAlert("TicketIF", "CPF invalido", "Insira novamente um CPF válido");
                aluno_cpf.clear();
            }
        }

        return alunoDAO.obterListaAluno();
    }

    @FXML
    private void importaArquivoExcel() {
        List<String> dados = new ArrayList<>();
        ObservableList<Aluno> dadosTabela = FXCollections.observableArrayList();

        try {
            FileInputStream file = new FileInputStream("D:/leona/Documents/alunos.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                int count = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    System.out.println(cell.toString());
                    dados.add(count, cell.toString());
                    count++;
                }
                dadosTabela.add(new Aluno(dados.get(0), dados.get(1), dados.get(2)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tabelaAlunos.setItems(dadosTabela);


    }

}
