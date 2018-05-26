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
	
	public String getUser() {
		
		return this.user;
	}
	
	public String getPassword() {
		
		return this.password;
	}
	
}
