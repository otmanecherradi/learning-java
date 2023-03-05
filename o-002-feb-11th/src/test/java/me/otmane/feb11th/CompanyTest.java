package me.otmane.feb11th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    private final Logger logger = LoggerFactory.getLogger(CompanyTest.class);

    Company apple;

    @Test
    @DisplayName("Should create a company 'Apple'")
    void createAppleCompany() {
        apple = new Company("Apple");
        logger.info("{}", apple);
    }

}