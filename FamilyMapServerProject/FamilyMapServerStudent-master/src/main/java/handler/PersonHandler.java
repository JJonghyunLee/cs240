package handler;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dataAccess.AuthtokenDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import model.Authtoken;
import result.PersonResult;
import request.PersonRequest;
import service.PersonService;

/*
	The ListGamesHandler is the HTTP handler that processes
	incoming HTTP requests that contain the "/games/list" URL path.

	Notice that ListGamesHandler implements the HttpHandler interface,
	which is define by Java.  This interface contains only one method
	named "handle".  When the HttpServer object (declared in the Server class)
	receives a request containing the "/games/list" URL path, it calls
	ListGamesHandler.handle() which actually processes the request.
*/
public class PersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String uri = exchange.getRequestURI().toString();
            String[] stringArray = uri.split("/");
            String personID = stringArray[2];
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present
                if (reqHeaders.containsKey("Authorization")) {
                    // Extract the auth token from the "Authorization" header
                    String authtoken = String.valueOf(reqHeaders.get("Authorization"));
                    authtoken = authtoken.substring(1, authtoken.length() - 1);
                    Database db = new Database();
                    db.openConnection();
                    AuthtokenDAO authtokenDAO = new AuthtokenDAO(db.getConnection());
                    Authtoken authtokenObject = authtokenDAO.findByAuthtoken(authtoken);
                    db.closeConnection(true);
                    PersonResult result;
                    if (authtokenObject != null) {
                        String username = authtokenObject.getUsername();
                        PersonService personService = new PersonService();
                        result = personService.doService(personID, username);
                        if (result.isSuccess()) exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        else exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                    } else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        result = new PersonResult("Error: Authtoken invalid", false);
                    }
                    Gson gson = new Gson();
                    String respData = gson.toJson(result);
                    OutputStream respBody = exchange.getResponseBody();
                    writeString(respData, respBody);
                    respBody.close();
                }
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}