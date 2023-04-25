package ru.praktikum.courier;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";

    @Step("Запрос на создание курьера")
    public Response createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
    @Step("Запрос на логин курьера")
    public Response loginCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Запрос на удаление курьера")
    public Response deleteCourier(int courierId) throws JsonProcessingException {
        Courier courier = new Courier(courierId);
        String json = new ObjectMapper().writeValueAsString(courier);

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(json)
                .when()
                .delete("/api/v1/courier/" + courierId);
    }
}

