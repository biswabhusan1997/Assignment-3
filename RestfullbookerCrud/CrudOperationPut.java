package org.example.RestfullbookerCrud;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CrudOperationPut {

    public static void main(String[] args) {

        String payload = "{\n" +
                "    \"firstname\" : \"amit\",\n" +
                "    \"lastname\" : \"Dutta\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        int id= 1242;
        r.basePath("/booking" +id);
        r.contentType(ContentType.JSON);
        r.body(payload);
        Response response = r.when().put();
        r.then().statusCode(200);
        System.out.println(response.asString());
    }
}
