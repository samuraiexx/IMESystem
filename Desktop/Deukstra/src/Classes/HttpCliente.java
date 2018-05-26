package Classes;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.*;

public class HttpCliente {

	
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws Exception {
       
//        Gson gson = new Gson();
//        
//        Obj ex = new Obj(1, 2, "oi", new Obj2(1,"Fabio",5));
//        
//        ArrayList<Obj> lobj = new ArrayList<>();              
//        
//        String json = gson.toJson(ex);
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url("http://samuraiexx.ddns.net")
//                .post(body)
//                .build();      
//        
//        Response response = client.newCall(request).execute();
//               
//        String resp = response.body().string();
//        
//        Obj ret = gson.fromJson(resp, Obj.class);
//
////        System.out.println(ret.a);
//        
//        System.out.println(resp);        
    }
}