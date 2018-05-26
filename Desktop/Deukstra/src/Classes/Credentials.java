package Classes;

public class Credentials {

	public String user;
	public String password;
	public boolean superUser;
	
	public Credentials() {
		
		this.user = "samuraiexx";
		this.password = "naru10";
		this.superUser = true;
	}
	
	public Credentials(String u, String pass, boolean t) {
		
		this.user = u;
		this.password = pass;
		this.superUser = t;
	}
	
	public String getUser() {
		
		return this.user;
	}
	
	public String getPassword() {
		
		return this.password;
	}
	
}
