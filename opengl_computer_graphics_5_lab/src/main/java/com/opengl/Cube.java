package com.opengl;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

public class Cube implements GLEventListener {

    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
    private float rquad = 0.0f;
    private int slices = 30;
    private int stacks = 30;
    private GLUquadric quadObj;

    @Override
    public void display( GLAutoDrawable drawable ) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();
        gl.glTranslatef( 0f, 0f, -5.0f );

        // Rotate The Cube On X, Y & Z
        gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f);

        //giving different colors to different sides
        gl.glBegin(GL2.GL_QUADS); // Start Drawing The Cube
        gl.glColor3f(1f,0f,0f); //red color
        gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Top)
        gl.glVertex3f( -1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
        gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Bottom Left Of The Quad (Top)
        gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Bottom Right Of The Quad (Top)

        gl.glColor3f( 0f,1f,0f ); //green color
        gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Top Right Of The Quad
        gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Left Of The Quad
        gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
        gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad

        gl.glColor3f( 0f,0f,1f ); //blue color
        gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Front)
        gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Left Of The Quad (Front)
        gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad
        gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad

        gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
        gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
        gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
        gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Back)
        gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Back)

        gl.glColor3f( 1f,0f,1f ); //purple (red + green)
        gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Left)
        gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Left)
        gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
        gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad

        gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
        gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Right)
        gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad
        gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad
        gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
        gl.glEnd(); // Done Drawing The Quad

        gl.glBegin( GL2.GL_TRIANGLES );

        // 1 1 -1
        //drawing triangle in all dimensions
        // Front

        gl.glColor3f( 1f,0f,1f ); //purple (red + green)

        gl.glVertex3f( 1.2f, 1.4f, -1.0f ); // Top Of Triangle (Front)
        gl.glVertex3f( 0.8f, 0.8f, -0.8f ); // Left Of Triangle (Front)
        gl.glVertex3f( 1.2f, 0.8f, -0.8f ); // Right Of Triangle (Front)

        gl.glColor3f(1f,0f,0f); //red color
        // Right
        gl.glVertex3f( 1.2f, 1.4f, -1.0f ); // Top Of Triangle (Right)
        gl.glVertex3f( 1.2f, 0.8f, -0.8f ); // Left Of Triangle (Right)
        gl.glVertex3f( 1.2f, 0.8f, -1.2f ); // Right Of Triangle (Right)

        gl.glColor3f( 0f,1f,0f ); //green color
        // Left
        gl.glVertex3f( 1.2f, 1.4f, -1.0f ); // Top Of Triangle (Back)
        gl.glVertex3f( 1.2f, 0.8f, -1.2f ); // Left Of Triangle (Back)
        gl.glVertex3f( 0.8f, 0.8f, -1.2f ); // Right Of Triangle (Back)

        gl.glColor3f( 1f,0f,1f ); //purple (red + green)
        //left
        gl.glVertex3f( 1.2f, 1.4f, -1.0f ); // Top Of Triangle (Left)
        gl.glVertex3f( 0.8f, 0.8f, -1.2f ); // Left Of Triangle (Left)
        gl.glVertex3f( 0.8f, 0.8f, -0.8f ); // Right Of Triangle (Left)

        gl.glEnd();

        drawSolidCylinder(gl, glu, 0.3d, 0, slices, stacks);

        gl.glFlush();

        rquad -= 0.15f;
    }

    @Override
    public void dispose( GLAutoDrawable drawable ) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init( GLAutoDrawable drawable ) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel( GL2.GL_SMOOTH );
        gl.glClearColor( 0f, 0f, 0f, 0f );
        gl.glClearDepth( 1.0f );
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
    }

    @Override
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();

        if( height == 0 ) {
            height = 1;
        }

        final float h = ( float ) width / ( float ) height;
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();

        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
    }

    private void drawSolidCylinder(GL2 gl, GLU glu, double radius, double height, int slices, int stacks) {
        double[] x = new double[slices];
        double[] y = new double[slices];
        double angleDelta = Math.PI * 2 / slices;
        double angle = 0;
        for (int i = 0; i < slices; i++) {
            angle = i * angleDelta;
            x[i] = Math.cos(angle) * radius;
            y[i] = Math.sin(angle) * radius;
        }

        gl.glColor3f( 1f,0f,1f ); //purple (red + green)
        // Draw bottom cap
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glNormal3d(0, 0, -1);
        gl.glVertex3d(1.2, 1.2, 1.2);
        for (int i = 0; i < slices; i++) {
            gl.glVertex3d(x[i]+1, y[i]+1, 1.2);
        }
        gl.glVertex3d(x[0]+1, y[0]+1, 1.2);
        gl.glEnd();

        gl.glColor3f(1f,0f,0f); //red color
        // Draw top cap
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glNormal3d(1, 1, 2);
        gl.glVertex3d(1.2, 1.2, 1);
        for (int i = 0; i < slices; i++) {
            gl.glVertex3d(x[i]+1, y[i]+1, 1);
        }
        gl.glVertex3d(x[0]+1, y[0]+1, 1);
        gl.glEnd();

        gl.glColor3f( 0f,1f,0f ); //green color
        // Draw walls
        gl.glTranslatef( 1f, 1f, 1f );
        // Rotate The Cube On X, Y & Z
        quadObjInit(glu);
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        glu.gluCylinder(quadObj, radius, radius, 0.2, slices, stacks);
    }

    public static void main( String[] args ) {

        final GLProfile profile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities capabilities = new GLCapabilities( profile );

        // The canvas
        final GLCanvas glcanvas = new GLCanvas( capabilities );
        Cube cube = new Cube();

        glcanvas.addGLEventListener( cube );
        glcanvas.setSize( 400, 400 );

        final JFrame frame = new JFrame ( " Multicolored cube" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add( glcanvas );
        frame.setSize( frame.getContentPane().getPreferredSize() );
        frame.setVisible( true );
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);

        animator.start();
    }

    private void quadObjInit(GLU glu) {
        if (quadObj == null) {
            quadObj = glu.gluNewQuadric();
        }
        if (quadObj == null) {
            throw new GLException("Out of memory");
        }
    }

}

