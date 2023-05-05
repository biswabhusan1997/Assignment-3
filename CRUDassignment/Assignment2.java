package org.example.CRUDassignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class Assignment2 {

    public static void main(String[] args) {

        /*in this assignment we do post request with
         auth and extract the token and booking id to use in put method
         */

        // setting the base url
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");

        //Do a auth req to get token
        String payloadAuth ="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payloadAuth);
        Response responseForAuth =r.when().post();
        ValidatableResponse vr= responseForAuth.then().log().all();
        //TC is to validate status code
        vr.statusCode(200);
        //Extract token
        String token = responseForAuth.then().extract().path("token");
        System.out.println(token);


        //Do a post request
        String payloadForPost= "{\n" +
                "    \"firstname\" : \"dipak10\",\n" +
                "    \"lastname\" : \"par\",\n" +
                "    \"totalprice\" : 1018,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        r.basePath("booking");
        r.cookie("token",token);
        r.body(payloadForPost);
        Response responseForPost = r.post();
        vr= responseForPost.then().log().all();
        //Extract booking id
        int bID = responseForPost.then().extract().path("bookingid");


        //Do a put request
        String payloadForPut="{\n" +
                "    \"firstname\" : \"DipakPardeshii\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        r.basePath("booking/"+bID);
        r.cookie("token", token);
        r.body(payloadForPut);
        Response responseForPut = r.put();
        vr= responseForPut.then().log().all();
        //TC#1
        //vr.statusCode(200);
        //TC#2
        vr.body("firstname", Matchers.equalTo("DipakPardeshii"));




    }
}
