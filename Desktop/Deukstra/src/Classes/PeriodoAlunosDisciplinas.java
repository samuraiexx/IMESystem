package Classes;

import java.util.ArrayList;

public class PeriodoAlunosDisciplinas extends PeriodoAlunos {
	
	public ArrayList<Integer> ids;
	public ArrayList<String> disciplinas;
	
	public PeriodoAlunosDisciplinas(ArrayList<Integer> id, int per, ArrayList<String> disc) {
		
		super(per);
		this.ids = id;
		this.disciplinas = disc;
	}

}
