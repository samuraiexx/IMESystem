package Classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DisciplinasRequest {

	public ArrayList<Disciplina> getDisciplinas(ArrayList<Aluno> alunos, int periodo) throws ClientProtocolException, IOException{
		
		Gson gson = new Gson();
		ArrayList<Integer> idList = new ArrayList<>();
		for(Aluno al : alunos) {
			
			idList.add(al.AlunoId);
		}
		
		String filter = gson.toJson(idList);
		JsonModelRequest jsonmodel = new JsonModelRequest(filter, "disciplinas");
		
		RequestSender reqSender = new RequestSender();
		String json = reqSender.sendRequest(jsonmodel);
	
//		Se chegou aqui é pq ja enviou o request; json é o que o server enviou
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);		
		ArrayList<Disciplina> answer = new ArrayList<>();
		System.out.println();
		jsonReader.beginArray();
		
		while(jsonReader.hasNext()) {
			
			Disciplina al = gson.fromJson(jsonReader, Disciplina.class);
			answer.add(al);
		}
		
		return answer;
		
	}
}
