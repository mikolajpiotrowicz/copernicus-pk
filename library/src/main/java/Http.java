
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.minidev.json.JSONObject;
import types.Paginate;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SuppressWarnings("deprecation")
public class Http {
    public Application application;
    public String BASE_URL = "http://localhost:9090";
    public Http(Application application) {
        this.application = application;
    }


    public ArrayList<Book> fetchCurrentBooks() throws IOException {
        URL url = new URL(BASE_URL + "/books/student/" + application.student.getIndex());
        URLConnection request = url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray rootobj = root.getAsJsonArray();

        System.out.println(rootobj.toString());
        Gson gson = new Gson();
        ArrayList<Book> response = gson.fromJson(rootobj.toString(), new TypeToken<ArrayList<Book>>(){}.getType());

        return response;
    }



    public ArrayList<Book> fetchAvailableBooks() throws IOException {
        URL url = new URL(BASE_URL + "/books/no-paginate");
        URLConnection request = url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray rootobj = root.getAsJsonArray();

        System.out.println(rootobj.toString());
        Gson gson = new Gson();
        ArrayList<Book> response = gson.fromJson(rootobj.toString(), new TypeToken<ArrayList<Book>>(){}.getType());

        return response;
    }




    public void login(String indexNumber, String password) throws Exception {
        JSONObject obj = new JSONObject();

        obj.put("password",password);
        obj.put("indexNumber", indexNumber);

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(BASE_URL + "/login"))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(out)))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 403) {
            application.cl.show(application.panelCont, "1");
        }
        System.out.println(response.body());

        Gson gson = new Gson();
        application.cl.show(application.panelCont, "2");
        application.student.setIndex(indexNumber);
    }


    public void rent(String bookId) throws Exception {
        JSONObject obj = new JSONObject();

        obj.put("bookId",bookId);
        System.out.println("cos tu smierdzi" + application.student.getIndex());
        obj.put("studentIndex", application.student.getIndex());

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(BASE_URL + "/books/rent"))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(out)))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        application.cl.show(application.panelCont, "2");
        if(response.statusCode() == 200) {
            application.repository.rentBook(bookId);
        }
            application.cl.show(application.panelCont, "3");
        System.out.println(response.body());

        Gson gson = new Gson();

        Student student = gson.fromJson(response.body(), new TypeToken<Student>(){}.getType());

    }


    public void returnBook(String bookId) throws Exception {
        JSONObject obj = new JSONObject();

        obj.put("bookId", bookId);
        System.out.println("cos tu smierdzi" + application.student.getIndex());
        System.out.println("cos tu smierdzi BOOOOOOOOK ID  " + bookId);
        obj.put("studentIndex", application.student.getIndex());

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(BASE_URL + "/books/return"))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(out)))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        application.cl.show(application.panelCont, "2");
        if(response.statusCode() == 200) {
            application.repository.returnBook(bookId);
        }
        application.cl.show(application.panelCont, "3");
        System.out.println(response.body());

        Gson gson = new Gson();

        Student student = gson.fromJson(response.body(), new TypeToken<Student>(){}.getType());

    }
}