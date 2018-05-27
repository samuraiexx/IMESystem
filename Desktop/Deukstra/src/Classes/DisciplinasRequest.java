package Classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class DisciplinasRequest {

	public ArrayList<Disciplina> getDisciplinas(PeriodoAlunos pa) throws ClientProtocolException, IOException{
		


		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
		
		Filter filter = new Filter(pa.periodo, null, null);
		JsonModelRequest jsonmodel = new JsonModelRequest(filter, "disciplinas");
	
		RequestSender reqSender = new RequestSender();
		String json = reqSender.sendRequest(jsonmodel);
	
//		Se chegou aqui é pq ja enviou o request; json é o que o server enviou
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);		
		ArrayList<Disciplina> answer = new ArrayList<>();
		jsonReader.beginArray();
		
		while(jsonReader.hasNext()) {
			
			Disciplina al = gson.fromJson(jsonReader, Disciplina.class);
			answer.add(al);
		}
		
		return answer;
		
	}
}
