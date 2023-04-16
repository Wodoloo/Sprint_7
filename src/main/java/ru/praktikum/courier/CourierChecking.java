package ru.praktikum.courier;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static java.net.HttpURLConnection.*;

public class CourierChecking {

    // успешный запрос возвращает ok: true
    public void createdSuccessfully(Response response){
        response.then().log().all()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(HTTP_CREATED);
    }

    // Нельзя создать двух одинаковых курьеров
    public void creationFailed(Response response){
        response.then().log().all()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(HTTP_CONFLICT);
    }

    // Чтобы создать курьера, нужно передать в ручку все обязательные поля
    public void creationWithoutPasswordFailed(Response response){
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(HTTP_BAD_REQUEST);
    }

    // система вернёт ошибку, если неправильно указать логин или пароль
    public void loginFailed(Response response){
        response.then().log().all()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(HTTP_NOT_FOUND);
    }

    // если какого-то поля нет, запрос возвращает ошибку
    public void loginWithoutPasswordFailed(Response response){
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(HTTP_BAD_REQUEST);
    }

    // удаление курьера
    public  void deleteSuccessfully(Response response){
        response.then().log().all()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(HTTP_OK);
    }

    // курьер может авторизоваться. успешный запрос возвращает id
    // import static org.hamcrest.CoreMatchers.notNullValue;
    /* public void loginSuccessfully(Response response){
        response.then().log().all()
                .assertThat().body("id", notNullValue())
                .and()
                .statusCode(HTTP_OK);
    } */
}