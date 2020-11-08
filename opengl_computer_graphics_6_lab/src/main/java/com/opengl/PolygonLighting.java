package com.opengl;

import javax.swing.JFrame;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class PolygonLighting implements GLEventListener {
    private float rquad = 0.0f;
    private GLU glu = new GLU();

    @Override

    public void display( GLAutoDrawable drawable ) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glColor3f(1f,0f,0f); //applying red

        // Clear The Screen And The Depth Buffer
        gl.glClear( GL2.GL_COLOR_BUFFER_BIT |
                GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();       // Reset The View

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

        gl.glFlush();

        rquad -= 0.15f;

        gl.glEnable( GL2.GL_LIGHTING );
        gl.glEnable( GL2.GL_LIGHT0 );
        gl.glEnable( GL2.GL_NORMALIZE );

        // weak RED ambient
        float[] ambientLight = { 0.1f, 0.f, 0.f,0f };
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0);

        // multicolor diffuse
        float[] diffuseLight = { 1f,2f,1f,0f };
        gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0 );
    }

    @Override
    public void dispose( GLAutoDrawable arg0 ) {
        //method body
    }

    @Override
    public void init( GLAutoDrawable arg0 ) {
        // method body
    }

    @Override
    public void reshape( GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4 ) {
        final GL2 gl = arg0.getGL().getGL2();

        if( arg4 == 0 ) {
            arg4 = 1;
        }

        final float h = ( float ) arg3 / ( float ) arg4;
        gl.glViewport( 0, 0, arg3, arg4 );
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();

        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
    }

    public static void main( String[] args ) {

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities capabilities = new GLCapabilities( profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas( capabilities );
        PolygonLighting polygonlighting = new PolygonLighting();
        glcanvas.addGLEventListener( polygonlighting );
        glcanvas.setSize( 400, 400 );

        //creating frame
        final JFrame frame = new JFrame (" Polygon lighting ");

        //adding canvas to it
        frame.getContentPane().add( glcanvas );
        frame.setSize( frame.getContentPane().getPreferredSize());
        frame.setVisible( true );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Instantiating and Initiating Animator
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true );
        animator.start();

    } //end of main

}
