package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

//9
// (-W/5, -H/2.5) (-W/5, H/2.5) 1 Сірий
// (-W/4, -H/4) (W/4, 0) 3 Зелений

public class Main  implements GLEventListener {

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
        
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
