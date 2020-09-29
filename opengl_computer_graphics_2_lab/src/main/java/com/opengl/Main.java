package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements GLEventListener{

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static final String windowTitle = "Computer graphics. Laboratory work #2";
    private static final int deepFraсtal = 6;

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

        Triangle triangle = new Triangle(new Point(0.3, 0.25),
                                         new Point(-0.3, 0.25),
                                         new Point(0, -0.25));

        drawDeepTringle(gl, triangle, Colors.GREEN,0);

        gl.glFlush();
    }

    private void drawDeepTringle(GL2 gl, Triangle triangle, Colors color, int deep) {
        if(deep >= deepFraсtal) {
            return;
        } else {
            drawTriangle(gl, triangle, color);

            deep++;

            List<Triangle> triangles = calcNewTringle(triangle);
            for(Triangle t : triangles) {
                drawDeepTringle(gl, t, color, deep);
            }
        }
    }

    private List<Triangle> calcNewTringle(Triangle triangle) {
        List<Triangle> triangles = new ArrayList<>();

        Triangle t1 = new Triangle();
        t1.setVertex1(triangle.getVertex1());
        t1.setVertex2( new Point(middPoints(triangle.getVertex1().getX(), triangle.getVertex2().getX()), middPoints(triangle.getVertex1().getY(), triangle.getVertex2().getY())) );
        t1.setVertex3( new Point(middPoints(triangle.getVertex1().getX(), triangle.getVertex3().getX()), middPoints(triangle.getVertex1().getY(), triangle.getVertex3().getY())) );

        triangles.add(t1);

        Triangle t2 = new Triangle();
        t2.setVertex1(triangle.getVertex2());
        t2.setVertex2( new Point(middPoints(triangle.getVertex2().getX(), triangle.getVertex1().getX()), middPoints(triangle.getVertex2().getY(), triangle.getVertex1().getY())) );
        t2.setVertex3( new Point(middPoints(triangle.getVertex2().getX(), triangle.getVertex3().getX()), middPoints(triangle.getVertex2().getY(), triangle.getVertex3().getY())) );

        triangles.add(t2);

        Triangle t3 = new Triangle();
        t3.setVertex1(triangle.getVertex3());
        t3.setVertex2( new Point(middPoints(triangle.getVertex3().getX(), triangle.getVertex1().getX()), middPoints(triangle.getVertex3().getY(), triangle.getVertex1().getY())) );
        t3.setVertex3( new Point(middPoints(triangle.getVertex2().getX(), triangle.getVertex3().getX()), middPoints(triangle.getVertex2().getY(), triangle.getVertex3().getY())) );

        triangles.add(t3);

        return triangles;
    }

    private double middPoints(double x, double x1) {
        return (x + x1) / 2.0;
    }

    private Point midPoint(Point p) {
        Point res = new Point();
        res.setX(p.getX()/2.0);
        res.setX(p.getY()/2.0);
        return res;
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    private void drawTriangle(GL2 gl, Triangle triangle, Colors c) {
        drawLine(gl, triangle.getVertex1(), triangle.getVertex2(), c);
        drawLine(gl, triangle.getVertex1(), triangle.getVertex3(), c);
        drawLine(gl, triangle.getVertex2(), triangle.getVertex3(), c);
    }

    private void drawLine(GL2 gl, Point start, Point end, Colors c) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glVertex3f((float) start.getX(), (float) start.getY(), 0);
        gl.glVertex3f((float) end.getX(), (float) end.getY(), 0);
        gl.glEnd();
    }
}
