package me.otmane.feb4th;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Tables {

    Logger logger = LoggerFactory.getLogger(Tables.class);

    public static void main(String[] args) {
        new Tables().run();
    }

    private void run() {
        regular();
        points();
    }


    private void regular() {
        float[] notes = new float[4];

        Scanner sc = new Scanner(System.in);

        for (int idx = 0; idx < 4; idx++) {
            logger.info("Note {} ?", idx + 1);
            notes[idx] = sc.nextFloat();
        }

        for (int idx = 0; idx < notes.length; idx++) {
            logger.info("Note {} = {}", idx + 1, notes[idx]);
        }

    }

    private void points() {
        Point[] points = new Point[4];
        points[0] = new Point();
        points[1] = new Point(12, 12);
        points[2] = new Point(199, 13);
        points[3] = new Point();


        for (int idx = 0; idx < points.length; idx++) {
            logger.info("Note {} = {}", idx + 1, points[idx]);
        }
    }
}
