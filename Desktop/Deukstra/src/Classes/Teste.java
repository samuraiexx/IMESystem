package Classes;

import java.util.ArrayList;

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
		ArrayList<Aluno> alunos = alreq.getAlunos();
		
		ArrayList<Integer> idsAlunos = new ArrayList<Integer>();
		for(Aluno al : alunos)
			idsAlunos.add(al.AlunoId);
		
		PeriodoAlunos pa = new PeriodoAlunos(0);
		
		DisciplinasRequest disreq = new DisciplinasRequest();
		
		ArrayList<Disciplina> disciplinas = disreq.getDisciplinas(pa);
		
		PeriodoAlunosDisciplinas pad = new PeriodoAlunosDisciplinas(idsAlunos, 0, disciplinas);
		
		DisciplinaNotasRequest dnr = new DisciplinaNotasRequest();
		ArrayList<DisciplinaNotas> disciplinanotas = dnr.getDisciplinaNotas(pad);
		
		for(DisciplinaNotas dn : disciplinanotas) {
			
			System.out.println(dn.nomeDisciplina + ":");
			for(NotasDisciplina nd : dn.notasDisciplina) {
				System.out.println(nd.nomeAluno + " tem nota = " + nd.notaAluno);
				
			}
		}
		
	}

}
