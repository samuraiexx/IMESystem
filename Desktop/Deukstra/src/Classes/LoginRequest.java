package Classes;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class LoginRequest {

	
	public boolean getValidUser(Credentials credential) throws ClientProtocolException, IOException {
		
		RequestSender reqSender = new RequestSender();
		String json = reqSender.sendRequest(credential);	
		
		return Boolean.valueOf(json);		
	}
}
