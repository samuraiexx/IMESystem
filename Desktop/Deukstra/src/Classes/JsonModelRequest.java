package Classes;

public class JsonModelRequest extends Credentials {

	public String filter;
	public String query;
	
	public JsonModelRequest() {
		
		super();
	};
	
	public JsonModelRequest(String f, String q) {
		super();
		this.filter = f;
		this.query = q;
	}
}
