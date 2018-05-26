package Classes;

public class JsonModelRequest extends Credentials {

	public Filter filter;
	public String query;
	
	public JsonModelRequest() {
		
		super();
	};
	
	public JsonModelRequest(Filter f, String q) {
		super();
		this.filter = f;
		this.query = q;
	}
}
