package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//9
// (-W/5, -H/2.5) (-W/5, H/2.5) 1 Сірий
// (-W/4, -H/4) (W/4, 0) 3 Зелений

public class Main implements GLEventListener {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static final String windowTitle = "Computer graphics. Laboratory work #1";

    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        final GLCanvas gc = new GLCanvas(cap);
        Main main = new Main();
        gc.addGLEventListener(main);
        gc.setSize(windowWidth, windowHeight);

        final JFrame frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gc);
        frame.setSize(windowWidth, windowHeight);
        frame.pack();
        frame.setVisible(true);
    }

    public void init(GLAutoDrawable glAutoDrawable) {

    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        // line 1
        Point line_1_start = new Point();
        line_1_start.setX((windowWidth/-5.0)/1000);
        line_1_start.setY((windowHeight/-2.5)/1000);

        Point line_1_end = new Point();
        line_1_end.setX((windowWidth/-5.0)/1000);
        line_1_end.setY((windowHeight/2.5)/1000);

        drawLine(gl, line_1_start, line_1_end, Colors.GREEN);

        // line 2
        Point line_2_start = new Point();
        line_2_start.setX((windowWidth/-4.0)/1000);
        line_2_start.setY((windowHeight/4.0)/1000);

        Point line_2_end = new Point();
        line_2_end.setX((windowWidth/4.0)/1000);
        line_2_end.setY(0);

        drawLine(gl, line_2_start, line_2_end, Colors.RED);

        List<Point> points1 = Point.generatePoints(line_1_start, line_1_end, 10);
        drawPoints(gl, points1, 4f, Colors.ORANGE);

        List<Point> points2 = Point.generatePoints(line_2_start, line_2_end, 10);
        drawPoints(gl, points2, 4f, Colors.YELLOW);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    private void drawLine(GL2 gl, Point start, Point end, Colors c) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glVertex2d(start.getX(), start.getY());
        gl.glVertex2d(end.getX(), end.getY());
        gl.glEnd();
    }

    private void drawPoints(final GL2 gl, List<Point> points, float pointSize, Colors c) {
        points.forEach(p -> drawPoint(gl, p, pointSize, c));
        return;
    }

    private void drawPoint(GL2 gl, Point point, float pointSize, Colors c) {
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glPointSize(pointSize);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(point.getX(),point.getY());
        gl.glEnd();
    }
}
