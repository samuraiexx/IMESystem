package Telas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.http.client.ClientProtocolException;

import Classes.Credentials;
import Classes.LoginRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private TextField LoginTextField;
	@FXML
	private PasswordField SenhaPasswordField;
	@FXML
	private Button EntrarButton;
	@FXML
	private Button CancelarButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		EntrarButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
				LoginRequest lr = new LoginRequest();
				Credentials credential = new Credentials(LoginTextField.getText(), SenhaPasswordField.getText(), true);
				
//				if(LoginTextField.getText().equals(credential.getUser()) &&
//					SenhaPasswordField.getText().equals(credential.getPassword())) {
				try {
					if(lr.getValidUser(credential)) {
						
						Stage stage = new Stage();
						Parent root = null;
						
						try {
							root = FXMLLoader.load(getClass().getResource("/Telas/Main.fxml"));
						} catch(Exception e) {
							e.printStackTrace();
						}
						
						Scene scene = new Scene(root);
						stage.setTitle("Pagina Inicial");
						stage.setScene(scene);
						stage.show();

						
						EntrarButton.getScene().getWindow().hide();
						
					}
					else {
						
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Usuario ou senha errados");
						alert.show();
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		CancelarButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				CancelarButton.getScene().getWindow().hide();				
			}
			
		});
		
	}
	
	
}
