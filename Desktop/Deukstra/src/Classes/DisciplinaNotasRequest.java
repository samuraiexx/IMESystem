package Classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DisciplinaNotasRequest {

	
	public ArrayList<DisciplinaNotas> getDisciplinaNotas(PeriodoAlunosDisciplinas pad) throws ClientProtocolException, IOException{
		
		Gson gson = new Gson();
		
		String filter = gson.toJson(pad);
		JsonModelRequest jsonmodel = new JsonModelRequest(filter,"notas");
		
		RequestSender reqSender = new RequestSender();
		String json = reqSender.sendRequest(jsonmodel);
		
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);		
		ArrayList<DisciplinaNotas> answer = new ArrayList<>();
		System.out.println();
		jsonReader.beginArray();
		
		while(jsonReader.hasNext()) {
			
			DisciplinaNotas al = gson.fromJson(jsonReader, DisciplinaNotas.class);
			answer.add(al);
		}
		
		return answer;
		
	}
	
}
