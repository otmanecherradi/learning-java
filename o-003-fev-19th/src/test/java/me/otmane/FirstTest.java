package me.otmane;

import lombok.extern.slf4j.Slf4j;
import me.otmane.feb19th.Gender;
import me.otmane.feb19th.Manager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class FirstTest {
    Manager manager1;
    Manager manager2;

    @Test
    @DisplayName("Should create the managers 'Manager 1' and 'Manager 2'")
    void createAppleCompany() {
        manager1 = new Manager("Manager 1", Gender.MALE);
        manager2 = new Manager("Manager 2", Gender.FEMALE, manager1);

        log.info("{}", manager1);
        log.info("{}", manager2);

        for (int idx = 0; idx < 10; idx++) {

        }
    }


}