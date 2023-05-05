package org.example.RestfullbookerCrud;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class crudOperationsPost {

    public static void main(String[] args) {

        // CRUD post https://restful-booker.herokuapp.com/booking

        String payload= "{\n" +
                "    \"firstname\" : \"Kylie\",\n" +
                "    \"lastname\" : \"john\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        r.body(payload);
       Response response= r.when().post();
        System.out.println(response.asString());


    }
}
