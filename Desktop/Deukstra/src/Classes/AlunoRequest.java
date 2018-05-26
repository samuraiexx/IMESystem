package Classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class AlunoRequest {
	
	
	
	public ArrayList<Aluno> getAlunos() throws ClientProtocolException, IOException {
		
		Gson gson = new Gson();

		Filter filter = new Filter(0, null, null);
		
		JsonModelRequest jsonmodel = new JsonModelRequest(filter, "alunos");
		
		//Envia o request ao servidor e retorna o json
		RequestSender reqSender = new RequestSender();
		String json = reqSender.sendRequest(jsonmodel);
		
		
		// Montar o Arraylist com os alunos;
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);		
		ArrayList<Aluno> arr2 = new ArrayList<Aluno>();
		jsonReader.beginArray();
		
		while(jsonReader.hasNext()) {
			
			Aluno al = gson.fromJson(jsonReader, Aluno.class);
			arr2.add(al);
		}
		
		return arr2;
		
	}
	
}
