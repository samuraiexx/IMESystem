package Classes;

import java.util.ArrayList;

public class PeriodoAlunosDisciplinas extends PeriodoAlunos {
	
	public ArrayList<Integer> ids;
	public ArrayList<Disciplina> disciplinas;
	
	public PeriodoAlunosDisciplinas(ArrayList<Integer> id, int per, ArrayList<Disciplina> disc) {
		
		super(per);
		this.ids = id;
		this.disciplinas = disc;
	}

}
