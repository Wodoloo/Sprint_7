
package ru.praktikum.courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier random(){
        return new Courier(RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10));
    }
}
