
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum.courier.Courier;
import ru.praktikum.courier.CourierChecking;
import ru.praktikum.courier.CourierClient;
import ru.praktikum.courier.CourierGenerator;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecking checks = new CourierChecking();
    private final CourierGenerator generator = new CourierGenerator();
    private int courierId;

    @Test
    @DisplayName("Verification of courier creation, then delete him")
    @Description( "- курьера можно создать; чтобы создать курьера, нужно передать в ручку все обязательные поля;" +
            "запрос возвращает правильный код ответа;\n" + "- успешный запрос возвращает ok: true.\n" + "-если создать пользователя с логином, который уже есть, возвращается ошибка.")

    public void createCourier() {
        Courier courier = generator.random();
        Response response = client.createCourier(courier);
        checks.createdSuccessfully(response);
        courier.setFirstName(null);
        Response responseId = client.loginCourier(courier);
        responseId.then().log().all();
        courierId = responseId.path("id");
    }

    @Test
    @DisplayName("Create courier twice")
    @Description("нельзя создать двух одинаковых курьеров;если создать пользователя с логином, который уже есть, возвращается ошибка.")
    public void createCourierTwice(){
        Courier courier = generator.random();
        client.createCourier(courier);
        Response response = client.createCourier(courier);
        checks.creationFailed(response);
    }

    @Test
    @DisplayName("Create courier without password")
    @Description("если одного из полей нет, запрос на создание курьера возвращает ошибку.")

    public void createWithoutPassword() {
        Courier courier = generator.random();
        courier.setPassword(null);
        Response response = client.createCourier(courier);
        checks.creationWithoutPasswordFailed(response);
    }
    @After
    public void deleteCourier(){
        if (courierId > 0){
            Response responseDelete = client.deleteCourier(courierId);
            checks.deleteSuccessfully(responseDelete);
        }
    }
}
