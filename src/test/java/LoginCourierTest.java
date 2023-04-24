import io.qameta.allure.Description;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum.courier.Courier;
import ru.praktikum.courier.CourierData;

import ru.praktikum.courier.CourierChecking;
import ru.praktikum.courier.CourierClient;
import ru.praktikum.courier.CourierGenerator;
import org.junit.Test;

public class LoginCourierTest {

    private final CourierClient client = new CourierClient();
    private final CourierChecking checks = new CourierChecking();
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierData data = new CourierData();
    private int courierId;


    @Test
    @DisplayName("Courier login")
    @Description("курьер может авторизоваться;" +
            "для авторизации нужно передать все обязательные поля;" +
            "успешный запрос возвращает id.")

     public void loginCourier() throws JsonProcessingException {
        Courier courier = data.signInData();
        client.createCourier(courier);

        Response responseId = client.loginCourier(courier);
        responseId.then().log().all();
        courierId = responseId.path("id");

        Response responseDelete = client.deleteCourier(courierId);
        checks.deleteSuccessfully(responseDelete);
    }

    @Test
    @DisplayName("Login without password")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")

    public void loginWithoutPassword(){
        Courier courier = data.loginData();
        courier.setPassword("");
        Response response = client.loginCourier(courier);
        checks.loginWithoutPasswordFailed(response);
    }

    @Test
    @DisplayName("Wrong login")
    @Description("система вернёт ошибку, если неправильно указать логин;" +
            "если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")

    public void wrongLoginCourier() {
        Courier courier = data.loginData();
        courier.setLogin("jane_a");
        Response response = client.loginCourier(courier);
        checks.loginFailed(response);
    }

    @Test
    @DisplayName("Wrong password")
    @Description("система вернёт ошибку, если неправильно указать пароль")

    public void wrongPasswordCourier() {
        Courier courier = data.loginData();
        courier.setPassword("1234");
        Response response = client.loginCourier(courier);
        checks.loginFailed(response);
    }
}
