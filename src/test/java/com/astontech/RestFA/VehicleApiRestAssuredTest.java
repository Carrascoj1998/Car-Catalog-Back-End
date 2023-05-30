package com.astontech.RestFA;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class VehicleApiRestAssuredTest {

    @Test
    public void endPointShouldReturn200(){
        //see if framework is set up
        get("/test")
                .then()
                .statusCode(200);
    }


    @Test
    public void whenUsePathParamValidId_thenOK(){
        given().pathParam("id", 1)
                .when().get("/vehicle/{id}")
                .then().statusCode(200);
    }

    @Test
    public void whenUsePathParamInvalidId_thenNOT_FOUND(){
        given().pathParam("id", 100)
                .when().get("/vehicle/{id}")
                .then().statusCode(404);
    }

    @Test
    public void getResponseTime(){
        System.out.println("Response Time: " + get("/vehicle/").timeIn(TimeUnit.MILLISECONDS) + "ms");
    }

    @Test
    public void responseContentType(){
        System.out.println("Content Type of reponse: " + get("/vehicle/").then().extract().contentType());
    }

    @Test
    public void saveVehicleShouldReturnId(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("year", "Dummy Update");
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .post("/vehicle/")
                .then().statusCode(201)
                .assertThat()
                .body("$", hasKey("id"))
                .body("color", equalTo("Gray"));

    }


    @Test
    public void patchVehicleWithInvalidIdShouldThrowException(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("year", "Dummy Update");
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 100)
                .when()
                .patch("/vehicle/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void patchVehicleWithInvalidFieldNameShouldThrowException(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("yearNNNN", "Dummy Update");
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 1)
                .when()
                .patch("/vehicle/{id}")
                .then()
                .statusCode(422);

    }

    @Test
    public void patchVehicleWithValidFieldsShouldUpdateResource(){


        JSONObject requestBody = new JSONObject();
        requestBody.put("year", "Dummy Update");
        requestBody.put("color", "Gray");
        requestBody.put("purchasePrice", "Dummy Update");

        given().header("Content-Type", "application/json")
                .body(requestBody.toJSONString())
                .pathParam("id", 1)
                .when()
                .patch("/vehicle/{id}")
                .then()
                .statusCode(202)
                .assertThat()
                .body("year", equalTo("Dummy Update"))
                .body("color", equalTo("Gray"))
                .body("year", equalTo("Dummy Update"));



    }



}
