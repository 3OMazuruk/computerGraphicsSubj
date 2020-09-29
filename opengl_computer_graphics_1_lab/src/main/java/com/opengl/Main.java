package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

//9
// (-W/5, -H/2.5) (-W/5, H/2.5) 1 Сірий
// (-W/4, -H/4) (W/4, 0) 3 Зелений

public class Main implements GLEventListener, KeyListener {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static final String windowTitle = "Computer graphics. Laboratory work #1";
    private static final char PLUS = '+';
    private static final char MINUS = '-';

    private GL2 gl;
    private final GLCanvas gc;

    private float zoomX = 1f, zoomY = 1f, zoomZ = 1f;

    public Main(GLCanvas gc) {
        this.gc = gc;
    }

    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        final GLCanvas gc = new GLCanvas(cap);

        Main main = new Main(gc);

        gc.addGLEventListener(main);
        gc.addKeyListener(main);
        gc.setSize(windowWidth, windowHeight);

        final JFrame frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gc);
        //frame.addKeyListener(main);
        frame.setSize(windowWidth, windowHeight);
        frame.pack();
        frame.setVisible(true);
    }

    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        this.gl = gl;
        gl.glShadeModel(GL.GL_LINE_SMOOTH);
        gl.glEnable(GL2.GL_MULTISAMPLE);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glScalef(zoomX,zoomY,zoomZ);

        gl.glEnable(GL.GL_MULTISAMPLE);

        // line 1
        Point line_1_start = new Point();
        line_1_start.setX((windowWidth/-5.0)/1000);
        line_1_start.setY((windowHeight/-2.5)/1000);

        Point line_1_end = new Point();
        line_1_end.setX((windowWidth/-5.0)/1000);
        line_1_end.setY((windowHeight/2.5)/1000);

        drawLine(gl, line_1_start, line_1_end, Colors.YELLOW);

        // line 2
        Point line_2_start = new Point();
        line_2_start.setX((windowWidth/-4.0)/1000);
        line_2_start.setY((windowHeight/4.0)/1000);

        Point line_2_end = new Point();
        line_2_end.setX((windowWidth/4.0)/1000);
        line_2_end.setY(0);

        drawLine(gl, line_2_start, line_2_end, Colors.RED);

        final int steps = 9;
        final float sizePoint = 1f;

        List<Point> points1 = Point.generatePoints(line_1_start, line_1_end, steps);
        drawPoints(gl, points1, sizePoint, steps, Colors.GRAY);

        List<Point> points2 = Point.generatePoints(line_2_start, line_2_end, steps);
        drawPoints(gl, points2, sizePoint, steps, Colors.GREEN);

        gl.glFlush();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    private void drawLine(GL2 gl, Point start, Point end, Colors c) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glVertex3d(start.getX(), start.getY(), 0);
        gl.glVertex3d(end.getX(), end.getY(), 0);
        gl.glEnd();
    }

    private void drawPoints(final GL2 gl, List<Point> points, float pointSize, int pointSizeStep, Colors c) {
        for(int i = 1; i <= pointSizeStep; i++) {
            drawPoint(gl, points.get(i), pointSize * i, c);
        }

        return;
    }

    private void drawPoints(final GL2 gl, List<Point> points, float pointSize, Colors c) {
        points.forEach(p -> drawPoint(gl, p, pointSize, c));
        return;
    }

    private void drawPoint(GL2 gl, Point point, float pointSize, Colors c) {
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glPointSize(pointSize);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex3d(point.getX(),point.getY(), 0);
        gl.glEnd();
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent keyEvent) {
        if(PLUS == keyEvent.getKeyChar()) {
            zoomPlus();
        } else if(MINUS == keyEvent.getKeyChar()) {
            zoomMinus();
        }

        gc.repaint();
    }

    private void zoomMinus() {
        zoomX -= 0.1f;
        zoomY -= 0.1f;
        System.out.println("minus " + zoomX + " " + zoomY + " " + zoomZ);

    }

    private void zoomPlus() {
        zoomX += 0.1f;
        zoomY += 0.1f;
        System.out.println("plus " + zoomX + " " + zoomY + " " + zoomZ);
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent keyEvent) {

    }
}
