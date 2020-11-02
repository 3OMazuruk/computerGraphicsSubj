package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

//  f(x) = |x^2 − 4*arctg(x)| , x є [2.5, 2.5]
public class Main implements GLEventListener{

    public static final int WINDOW_WIDTH = 800;
    public static final int X_0 = WINDOW_WIDTH / 2;
    public static final int WINDOW_HEIGHT = 600;
    public static final int Y_0 = WINDOW_HEIGHT / 2;
    public static final double PIXEL_SIZE = 0.1;
    public static final double EPS = 0.000000001d;
    public static final String WINDOW_TITLE = "Computer graphics. Laboratory work #4";

    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        final GLCanvas gc = new GLCanvas(cap);

        Main main = new Main();

        gc.addGLEventListener(main);
        gc.setSize(WINDOW_WIDTH, WINDOW_WIDTH);

        gc.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        final JFrame frame = new JFrame(WINDOW_TITLE);
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

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        drawNewtonFunction(gl);

        gl.glFlush();

        System.out.println("Draw points: " + call);
    }

    private void drawNewtonFunction(GL2 gl) {
        Random random = new Random();
        BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < WINDOW_WIDTH; i++) {
            for (int j = 0; j < WINDOW_HEIGHT; j++) {
                double x = (i - X_0) * PIXEL_SIZE;
                double y = (j - Y_0) * PIXEL_SIZE;

                Complex z = new Complex(x,y);

                if( (x != 0d) || (y != 0) ) {
                    Complex tmp = new Complex();

                    do {
                        tmp.setReal(z.getReal());
                        tmp.setImag(z.getImag());

                        z = z.multiply(0.8d).add( z.pow(z, -4).multiply(0.2) );
                    } while (z.abs(z.subtract(tmp)) >= EPS);

                    Colors color = Colors.CYAN;
                    Color colorAwt = Color.DARK_GRAY;
                    switch ((int) ( z.carg(z) / (Math.PI/5d) ) ) {
                        case 0:
                            color = Colors.RED;
                            colorAwt = Color.RED;
                            break;
                        case 1:
                        case 2:
                            color = Colors.YELLOW;
                            colorAwt = Color.YELLOW;
                            break;
                        case 3:
                        case 4:
                            color = Colors.BLUE;
                            colorAwt = Color.BLUE;
                            break;
                        case -3:
                        case -4:
                            color = Colors.ORANGE;
                            colorAwt = Color.ORANGE;
                            break;
                        case -1:
                        case -2:
                            color = Colors.PURPLE;
                            colorAwt = Color.PINK;
                            break;
                    }

                    drawPixel(gl, i, j, color);

                    int c = colorAwt.getRGB();

                    image.setRGB(i, j, c);
                }
            }
        }

        File output = new File("4_lab" + random.nextLong() + ".jpeg");
        try {
            ImageIO.write(image, "jpeg", output);
        } catch (IOException e) {
            System.out.println("Fail write image!");
        }
    }

    static int call = 0;

    private void drawPixel(GL2 gl, int width, int height, Colors c) {
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        gl.glPointSize(1f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex3d(width, height, 1);
        gl.glEnd();
        call++;
    }

}
