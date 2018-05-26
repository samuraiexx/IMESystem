package Classes;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Obj2 {

	public int id;
	public String nome;
	public int anoGrad;
	
	public Obj2(int id, String nome, int anograd){
	
		this.id = id;
		this.nome = nome;
		this.anoGrad = anograd;
	}
}
	
	
