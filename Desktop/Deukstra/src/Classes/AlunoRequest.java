package Classes;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class AlunoRequest {
	
	
	
	public ArrayList<Aluno> getAlunos() throws ClientProtocolException, IOException {
		
		Gson gson = new Gson();
		ArrayList<Aluno> arr = new ArrayList<>();
		
		arr.add(new Aluno(0, "Fabio", 2019));
		arr.add(new Aluno(1, "Mateus", 2019));
		
		String teste = gson.toJson(arr);
		
		JsonModelRequest jsonmodel = new JsonModelRequest("", "alunos");
		
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
