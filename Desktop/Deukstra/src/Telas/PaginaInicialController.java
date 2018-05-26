package Telas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.http.client.ClientProtocolException;

import Classes.Aluno;
import Classes.AlunoRequest;
import Classes.Credentials;
import Classes.LoginRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PaginaInicialController implements Initializable{

	@FXML
	private TableView<Aluno> RegistrosTableView;
	@FXML
	private TableColumn IdTableColumn;
	@FXML
	private TableColumn NomeTableColumn;
	@FXML
	private TableColumn AnoTableColumn;
	@FXML
	private Button ListarAlunosButton;
	@FXML
	private Button OkButton;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		

		
		ListarAlunosButton.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
		
				IdTableColumn.setCellValueFactory(new PropertyValueFactory<>("AlunoId"));
				NomeTableColumn.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
				AnoTableColumn.setCellValueFactory(new PropertyValueFactory<>("anoGrad"));
				
				AlunoRequest ar = new AlunoRequest();
				try {

					ArrayList<Aluno> alunos = ar.getAlunos();
					ObservableList<Aluno> data = FXCollections.observableArrayList();
					
					for(Aluno al : alunos)
						data.add(al);
					RegistrosTableView.setItems(data);
					
					
					
				} catch (ClientProtocolException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
		
		OkButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					
//				IdTableColumn.setCellValueFactory(new PropertyValueFactory<>("AlunoId"));
//				NomeTableColumn.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
//				AnoTableColumn.setCellValueFactory(new PropertyValueFactory<>("anoGrad"));				
				
				try {
					
					ArrayList<Aluno> alunos = new ArrayList<>();
					alunos.add(new Aluno(0, "Fabio", 2019));
					alunos.add(new Aluno(1, "Mateus", 2019));
					alunos.add(new Aluno(2, "Luciano", 2019));
					ObservableList<Aluno> data = FXCollections.observableArrayList();
					
					for(Aluno al : alunos)
						data.add(al);
					RegistrosTableView.setItems(data);
					RegistrosTableView.getColumns().addAll(IdTableColumn, NomeTableColumn, AnoTableColumn);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
	}

	
	
}
