import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum.order.Order;
import ru.praktikum.order.OrderChecking;
import ru.praktikum.order.OrderClient;
import org.junit.Test;

public class OrderListTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecking checks = new OrderChecking();

    @Test
    @DisplayName("Order list response")
    @Description("В тело ответа возвращается список заказов.")

    public void getOrderListTest(){
        Order order = new Order();
        Response response = client.get(order);
        checks.orderListNotNull(response);
    }
}