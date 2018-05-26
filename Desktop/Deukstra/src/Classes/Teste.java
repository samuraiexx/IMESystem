package Classes;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Teste {

	public static void main(String[] args) throws Exception {

		AlunoRequest alreq = new AlunoRequest();
		ArrayList<Aluno> resp = alreq.getAlunos();
		
		DisciplinasRequest disreq = new DisciplinasRequest();
		ArrayList<Disciplina> disciplinas = disreq.getDisciplinas(resp, 0);
		
		for(Disciplina d : disciplinas) {
			
			System.out.println(d.getNome());
		}
		
	}

}
