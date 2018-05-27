package Telas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.http.client.ClientProtocolException;

import Classes.Aluno;
import Classes.AlunoRequest;
import Classes.Disciplina;
import Classes.DisciplinaNotas;
import Classes.DisciplinaNotasRequest;
import Classes.DisciplinasRequest;
import Classes.NotasDisciplina;
import Classes.PeriodoAlunos;
import Classes.PeriodoAlunosDisciplinas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;


public class MainController implements Initializable{

	@FXML
	ListView<Aluno> listAlunos;
	@FXML
	ComboBox<Integer> boxPeriodo;
	@FXML
	Button disciplinasButton;
	@FXML
	ListView<Disciplina> listDisciplinas;
	@FXML
	Button buscaButton;
	@FXML
	LineChart graficoLinha = new LineChart<>(new CategoryAxis(), new NumberAxis());
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listAlunos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listDisciplinas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		ObservableList<Disciplina> olDisciplina = FXCollections.observableArrayList();
		ObservableList<Aluno> olAluno = FXCollections.observableArrayList();
		
		AlunoRequest alunoRequest = new AlunoRequest();
		DisciplinasRequest disciplinaRequest = new DisciplinasRequest();
		
		try {
			ArrayList<Aluno> alunos = alunoRequest.getAlunos();
			for(Aluno al : alunos)
				olAluno.add(al);
			
			listAlunos.setItems(olAluno);
	
			listAlunos.setCellFactory(new Callback<ListView<Aluno>, ListCell<Aluno>>() {
				
				@Override
				public ListCell<Aluno> call(ListView<Aluno> param) {
					
					ListCell<Aluno> cell = new ListCell<Aluno>() {
						
						@Override
						protected void updateItem(Aluno t, boolean bln) {
							super.updateItem(t, bln);
							if(t != null){
								
								setText(t.nomeAluno);
							}
						}
						
					};
					
					return cell;
				}
			});
						
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		boxPeriodo.setItems(FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9,10
		));
		
		
		disciplinasButton.setOnAction(new EventHandler<ActionEvent>() {
			
			
			@Override
			public void handle(ActionEvent event) {							
				
				PeriodoAlunos pa = new PeriodoAlunos(boxPeriodo.getValue());
				try {
					
					listDisciplinas.getItems().clear();
					
					ArrayList<Disciplina> disciplinas = disciplinaRequest.getDisciplinas(pa);
					for(Disciplina d : disciplinas)
						olDisciplina.add(d);
					
					listDisciplinas.setItems(olDisciplina);
					
					listDisciplinas.setCellFactory(new Callback<ListView<Disciplina>, ListCell<Disciplina>>() {
						
						@Override
						public ListCell<Disciplina> call(ListView<Disciplina> param) {
							
							ListCell<Disciplina> cell = new ListCell<Disciplina>() {
								
								@Override
								protected void updateItem(Disciplina t, boolean bln) {
									super.updateItem(t, bln);
									if(t != null){
										
										setText(t.disciplinaNome);
									}
								}
								
							};
							
							return cell;
						}
					});
					
					
					
				} catch (Exception e) {
					e.printStackTrace();					
				}
				
				
			}
		});
		
		buscaButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {				 
								
				ArrayList<Integer> id = new ArrayList<>();
				PeriodoAlunos per = new PeriodoAlunos(boxPeriodo.getValue());
				ArrayList<String> disc = new ArrayList<>();
				
				for(Aluno al : listAlunos.getSelectionModel().getSelectedItems())
					id.add(al.alunoId);
				for(Disciplina d : listDisciplinas.getSelectionModel().getSelectedItems())
					disc.add(d.disciplinaNome);
				
				
				
				PeriodoAlunosDisciplinas pad = new PeriodoAlunosDisciplinas(id, per.periodo, disc);
				
				DisciplinaNotasRequest disciplinaNotasRequest = new DisciplinaNotasRequest();
				try {
//					ArrayList<DisciplinaNotas> disciplinaNotas = disciplinaNotasRequest.getDisciplinaNotas(pad);
					
					ArrayList<NotasDisciplina> disciplinaNotas = new ArrayList<>();
					disciplinaNotas.add(new NotasDisciplina("Fabio", 10.0));
					disciplinaNotas.add(new NotasDisciplina("Mateus", 9.0));
					disciplinaNotas.add(new NotasDisciplina("Luciano", 8.0));

					
//					LineChart graficoLinha = new LineChart<>(new CategoryAxis(), new NumberAxis());
					XYChart.Series prod1 = new XYChart.Series<>();
					
//					for(DisciplinaNotas dn : disciplinaNotas) {
						
//						System.out.println(dn.nomeDisciplina);
//						for(NotasDisciplina nd : dn.notasDisciplina) 
//							prod1.getData().add(new XYChart.Data<>(nd.nomeAluno, nd.notaAluno));
//							System.out.println(nd.nomeAluno + "tem media" + nd.notaAluno );
						
//					}
					
					for(NotasDisciplina nd : disciplinaNotas) {
						prod1.getData().add(new XYChart.Data<>(nd.nomeAluno, nd.notaAluno));
						
						
					}	
						
					graficoLinha.getData().addAll(prod1);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
				
			}
			
		});
		
	}
	
}
