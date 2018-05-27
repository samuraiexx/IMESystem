package Classes;

import java.util.ArrayList;

public class Filter {

	public int periodo;
	public ArrayList<String> disciplinas;
	public ArrayList<Integer> id;
	
	public Filter(int p, ArrayList<String> disc, ArrayList<Integer> id) {
		
		this.periodo = p;
		this.disciplinas = disc;
		this.id = id;
		
	}
	
}
