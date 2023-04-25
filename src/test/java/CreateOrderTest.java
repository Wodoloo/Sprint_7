
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum.order.Order;
import ru.praktikum.order.OrderChecking;
import ru.praktikum.order.OrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)

public class CreateOrderTest {
    private final OrderClient data = new OrderClient();
    private final OrderChecking checks = new OrderChecking();
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] colorOfScooter(){
        return new Object[][]{
                { "Майкл", "Джексон", "Невский, 61", "2", "89123334455", 7, "2023-04-15", "Позвоните", Arrays.asList("GREY")},
                { "Анджелина", "Джоли", "Невский, 62", "6", "89521112233", 3, "2023-03-14", "Не звоните мне никогда", Arrays.asList("BLACK")},
                { "Питер", "Саямаки", "Ленинский 21", "4", "81111111111", 4, "2023-04-30", "Наконец-то", Arrays.asList("BLACK", "GREY")},
                { "Андрей", "Володин", "ул. Горохова", "5", "89213765454", 1, "2023-05-01", "Пожалуйста, поторопитесь!", Arrays.asList("")},
        };
    }

   @Test
    @DisplayName("Creation order")
    @Description( "можно указать один из цветов — BLACK или GREY; " +
            "можно  не указывать цвет; " +
            "можно указать оба цвета; " +
            "тело ответа содержит track.")

    public void creatingOrder(){
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = data.create(order);
        checks.orderCreatedSuccessfully(response);
    }
}
