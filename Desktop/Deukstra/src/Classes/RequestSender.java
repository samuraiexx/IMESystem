package Classes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class RequestSender {

	public String sendRequest(JsonModelRequest jsonmodel) throws ClientProtocolException, IOException {
		
		Gson gson = new Gson();
		String postURL = "http://samuraiexx.ddns.net";
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(postURL);
		StringEntity postingString = new StringEntity(gson.toJson(jsonmodel));
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		String resp = EntityUtils.toString(entity, "UTF-8");
		return resp;
	}
}
