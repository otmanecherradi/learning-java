package me.otmane.feb4th;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PointTest {
    private final Logger logger = LoggerFactory.getLogger(PointTest.class);

    @Test
    @DisplayName("Should create a Point with 10, 10")
    void createFirstPoint() {
        Point p = new Point(10, 10);
        logger.info("{}", p);
    }

    @Test
    @DisplayName("Should create a Point with default params")
    void createSecondPoint() {
        Point p = new Point();
        logger.info("{}", p);
    }

    @Test
    @DisplayName("Should create two points and calculate the distance")
    void calculateDistance() {
        Point p1 = new Point();
        logger.info("{}", p1);
        Point p2 = new Point(100, 23);
        logger.info("{}", p2);

        logger.info("Distance between {} and {} is {}", p1, p2, p1.distanceFrom(p2));
    }
}