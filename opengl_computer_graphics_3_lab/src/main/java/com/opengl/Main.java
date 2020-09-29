package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.awt.TextRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//  f(x) = |x^2 − 4*arctg(x)| , x є [2.5, 2.5]
public class Main implements GLEventListener{

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static final String windowTitle = "Computer graphics. Laboratory work #3";

    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        final GLCanvas gc = new GLCanvas(cap);

        Main main = new Main();

        gc.addGLEventListener(main);
        gc.setSize(windowWidth, windowWidth);

        gc.setSize(windowWidth, windowHeight);

        final JFrame frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gc);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.pack();
        frame.setVisible(true);
    }

    public void init(GLAutoDrawable glAutoDrawable) {
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        drawCoordinateAxis(gl, Colors.ORANGE);

        drawFunction(gl, Colors.PURPLE);

        gl.glFlush();
    }

    private void drawFunction(GL2 gl, Colors color) {
        List<Point> points = getPoints();
        for (int i = 0; i < points.size() - 1; i++) {
            drawLine(gl, points.get(i), points.get(i+1), color);
        }
        return;
    }

    private List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        double step = 0.01;
        double currentStateX = -5;

        while (currentStateX <= 2.5) {
            points.add( new Point(currentStateX/10.0+0.2, calcX(currentStateX)/10.0) );
            currentStateX += step;
        }

        return points;
    }

    private double calcX(double currentStateX) {
        return Math.pow(currentStateX, 2) + (4 * Math.atan(currentStateX));
    }

    private void drawCoordinateAxis(GL2 gl, Colors color) {
        drawLine(gl, new Point(-1, 0.0), new Point(1, 0.0), color);
        drawLine(gl, new Point(0, -1), new Point(0, 1), color);

        Point p1 = new Point(-1, 0.02);
        Point p2 = new Point(-1, -0.02);
        Point p3 = new Point(0.02, -1);
        Point p4 = new Point(-0.02, -1);

        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 12));
        textRenderer.beginRendering(windowWidth, windowHeight);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        int with = 0;
        int height = 0;

        for (int i = 0; i < 20; i++) {
            textRenderer.draw((i - 10) + "", with, (windowHeight/2-20));
            with += windowWidth/20;

            textRenderer.draw((i - 10) + "", windowWidth/2-25, height);
            height += windowHeight/20;
        }

        textRenderer.endRendering();

        for (int i = 0; i < 20; i++) {
            drawLine(gl, p1, p2, Colors.RED);
            p1.setX(p1.getX()+0.1);
            p2.setX(p2.getX()+0.1);

            drawLine(gl, p3, p4, Colors.RED);
            p3.setY(p3.getY()+0.1);
            p4.setY(p4.getY()+0.1);
        }
        return;
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    private void drawLine(GL2 gl, Point start, Point end, Colors c) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glVertex3f((float) start.getX(), (float) start.getY(), 0);
        gl.glVertex3f((float) end.getX(), (float) end.getY(), 0);
        gl.glEnd();
    }
}
