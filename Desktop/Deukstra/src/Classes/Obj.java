package Classes;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Obj {
	
    public int a;
    public int b;
    public String s;
    public Obj2 obj2;

    public Obj(int a, int b, String s, Obj2 obj2){
        this.a = a;
        this.b = b;
        this.s = s;
        this.obj2 = obj2;
    }
}