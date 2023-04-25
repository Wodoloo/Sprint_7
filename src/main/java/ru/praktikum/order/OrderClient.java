package ru.praktikum.order;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.praktikum.courier.CourierClient;

import static io.restassured.RestAssured.given;

public class OrderClient {

    @Step("Создание заказа")
    public Response create(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(CourierClient.BASE_URI)
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получение заказа")
    public Response get(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(CourierClient.BASE_URI)
                .body(order)
                .when()
                .get("/api/v1/orders");
    }
}