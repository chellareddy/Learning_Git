package com.eurochange.springboot.web.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eurochange.springboot.web.model.EchoModel;
import com.eurochange.springboot.web.model.ResponseModel;
import com.eurochange.springboot.web.swagger.EchoControllerSwagger;

import jakarta.validation.Valid;


@RestController
@Validated
public class EchoController implements EchoControllerSwagger {

    public static final String ECHO_MODEL_ENDPOINT = "/validate/userdetails";

    @PostMapping(value = ECHO_MODEL_ENDPOINT)
    public ResponseModel echoModel(@RequestBody @Valid EchoModel model) {
    	  // Create HttpClient
    	
    	System.out.println(model.getUserName());
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://172.20.0.22:8080/Webservices/rest/message/fnCheckUserPassword/"+model.getUserName()+"/"+model.getPassword()))
            .build();
        ResponseModel resModel=new ResponseModel();
        
        

        // Send HttpRequest and get HttpResponse
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print response
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            // Parse JSON response
            try {
                JSONObject json = new JSONObject(response.body());

                // Extract status and username using correct keys
                String status = json.optString("status:", "default_status");
                String username = json.optString("Username:", "default_username");
                resModel.setUserName(username);
                resModel.setStatus(status);

                System.out.println("Status: " + status);
                System.out.println("Username: " + username);

            } catch (Exception e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error during HTTP request: " + e.getMessage());
        }

       
        
       
        return resModel;
    }
}