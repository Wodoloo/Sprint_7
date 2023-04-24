
package ru.praktikum.courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public Courier signInData(){
        return new Courier("jan03", "12345673", "Evgeni3");
    }
    public Courier loginData(){
        return new Courier("jan03","12345673");
    }
    public Courier random(){
        return new Courier(RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10));
    }
}
