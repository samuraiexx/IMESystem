package Telas;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.NotasDisciplina;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

public class ChartController implements Initializable{

	
	@FXML
	LineChart<String, Double> graficoLineChart;

	public ArrayList<NotasDisciplina> notasDisciplina;
	
	public void setData(ArrayList<NotasDisciplina> nd) {
		
		this.notasDisciplina = nd;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		
	}
	
	
	
}
