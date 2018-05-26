package Telas;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class Login extends Application{

	@Override
	public void start (Stage stage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("/Telas/login.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setTitle("Deukstra Project");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void main(String[] args) {
	
		launch(args);
	}

}
