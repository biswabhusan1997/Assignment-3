package org.example.RestfullbookerCrud;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class crudOperationsGet {
    public static void main(String[] args) {

        // CRUD get https://restful-booker.herokuapp.com/booking
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        Response response=  r.when().get();
        System.out.println(response.asString());
        r.then().statusCode(200);




    }


}
