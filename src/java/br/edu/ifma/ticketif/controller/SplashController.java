/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifma.ticketif.controller;

import br.edu.ifma.ticketif.util.errors.Alerts;
import br.edu.ifma.ticketif.core.GerenteLog;
import br.edu.ifma.ticketif.core.Window;
import br.edu.ifma.ticketif.model.DAO.UsuarioDAO;
import br.edu.ifma.ticketif.model.entity.User.Usuario;
import br.edu.ifma.ticketif.model.entity.User.UsuarioLogado;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SplashController implements Initializable {

    @FXML
    private Label lbFechar;
    @FXML
    private Label lbInfo;
    @FXML
    private Label lbErro1;
    @FXML
    private Label lbErro2;
    @FXML
    private GridPane gpLogin;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private Button btnEntrar;
    @FXML
    private JFXSpinner loading;

    Alerts alert = new Alerts();

    Stage stage;

    Service<List<Usuario>> service;

    protected List<Usuario> listaUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lbFechar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });

            }
        });
        startApp();
        lbInfo.setText("Carregando...");
        // TODO
    }

    public void startApp() {

        service = new Service<List<Usuario>>() {
            @Override
            protected Task<List<Usuario>> createTask() {
                return new Task<List<Usuario>>() {
                    @Override
                    protected List<Usuario> call() throws Exception {

                        Usuario user = new Usuario();
                        user.setNome("Usuario Padrão");
                        user.setLogin("");
                        user.setSenha("");

                       UsuarioDAO usuarioDAO = new UsuarioDAO();
                       try {
                           usuarioDAO.atualizar(user);
                           usuarioDAO.inserir(user);
                       }catch (Exception e){
                           System.out.println("Usuario padrão");
                       }
                        return usuarioDAO.obterLista();


                    }
                };
            }
        };

        service.start();

        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) { // Conectando no servidor
                lbInfo.setVisible(true);
                gpLogin.setVisible(false);
                btnEntrar.setVisible(false);
                loading.setVisible(true);
                listaUsuarios = service.getValue();
            }
        });

        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) { // Panes login após estar conectado
                listaUsuarios = service.getValue();
                lbInfo.setVisible(false);
                gpLogin.setVisible(true);
                loading.setVisible(false);
                btnEntrar.setVisible(true);
            }
        });

        service.setOnFailed(new EventHandler<WorkerStateEvent>() { // Caso não conecte
            @Override
            public void handle(WorkerStateEvent event) {
                lbInfo.setVisible(false);
                lbErro1.setVisible(true);
                loading.setVisible(false);
                lbErro2.setVisible(true);


                //Janela Erro
                Dialog dialog = new Dialog<>();
                dialog.setTitle("TicketIF Report System");
                dialog.setHeaderText("Erro de conexão");

                // Cria os botões
                ButtonType loginButtonType = new ButtonType("Reportar", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                // Cria o texto do lado da caixa de texto
                grid.add(new Label("# Não foi possivel conectar-se ao banco de dados"), 0, 0);
                grid.add(new Label(""), 0, 1);

                // Adiciona o gridpane(com o texto) na janela
                dialog.getDialogPane().setContent(grid);
                dialog.showAndWait();

                Exception execao = (Exception) event.getSource().getException();

                GerenteLog logFactory = new GerenteLog();
                System.out.println("Erro na chamada entre o Banco de Dados e o Programa! Verifique a pasta LOG nos arquivos do projeto");
                logFactory.registrarExecao(execao);
            }
        });

    }

    @FXML
    private void btnAcLogin(ActionEvent event) {

        UsuarioLogado usuarioLogado = new UsuarioLogado();
        usuarioLogado.setLogado(false);

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (tfUsuario.getText().equals(listaUsuarios.get(i).getLogin()) && tfSenha.getText().equals(listaUsuarios.get(i).getSenha())) {

                usuarioLogado.setId(listaUsuarios.get(i).getId());
                usuarioLogado.setLogin(listaUsuarios.get(i).getLogin());
                usuarioLogado.setNome(listaUsuarios.get(i).getNome());
                usuarioLogado.setLogado(true);
            }
        }

        if (usuarioLogado.isLogado()) {
            Window c = new Window();
            c.newSplash(stage, lbFechar, "/fxml/Home.fxml", "/styles/tab.css", "TicketIF", true, StageStyle.DECORATED, false);
            stage = (Stage) lbFechar.getScene().getWindow();
            stage.close();
        } else {
            alert.infoAlert("TicketIF", "Falha no Login", "Usuario ou Senha incorretos");
        }

    }
}