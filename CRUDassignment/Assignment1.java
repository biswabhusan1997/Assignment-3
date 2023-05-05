package org.example.CRUDassignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Assignment1 {
    public static void main(String[] args) {

        /*Assignment 1 states that to do a post request and extract
         the booking id then using the booking id do the get request */

        //1. setting the base url
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");

        //2. payload for post request
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
        //3. do the post request
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        r.body(payloadForPost);
        Response responseForPost =r.when().post();
        ValidatableResponse vr = responseForPost.then().log().all();
        r.then().statusCode(200);
        System.out.println(responseForPost.asString());

        //4. Extract the booking id
        int bid = responseForPost.then().extract().path("bookingid");
        System.out.println(bid);

        // do get request with the extracted booking id
        r.basePath("/booking" +bid);
        Response responseForGet = r.when().get();
        vr= responseForGet.then().log().all();
        r.then().statusCode(200);
        System.out.println(responseForGet.asString());
        vr.statusCode(200);





    }
}
