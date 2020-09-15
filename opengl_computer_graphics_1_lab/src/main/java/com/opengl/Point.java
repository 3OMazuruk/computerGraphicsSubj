package com.opengl;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Point {
    private double x;
    private double y;

    public static List<Point> generatePoints(Point start, Point end, int steps){
        List<Point> points = new ArrayList<Point>();

        double fX = start.getX();
        double fY = start.getY();

        double stepX = countStep(start.getX(), end.getX(), steps);
        double stepY = countStep(start.getY(), end.getY(), steps);

        for(int i = 0; i <= steps; i++, fX += stepX, fY += stepY) {
            Point p = new Point();
            p.setX(fX);
            p.setY(fY);
            points.add(p);
        }

        return points;
    }

    private static double countStep(double start, double end, double steps) {
        return (end - start) / steps;
    }
}
