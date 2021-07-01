import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.util.gl2.GLUT; 

/**
 Some objects in 3D.  The arrow keys
 * can be used to rotate the object.  The number keys 1 through 6
 * select the object.  The space bar toggles the use of anaglyph
 * stereo.
 */
public class Lab4 extends GLJPanel implements GLEventListener, KeyListener{

	/**
	 * A main routine to create and show a window that contains a
	 * panel of type Lab4.  The program ends when the user closes the 
	 * window.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Some Objects in 3D");
		Lab4 panel = new Lab4();
		window.setContentPane(panel);
		window.pack();
		window.setResizable(false);
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	/**
	 * Constructor for class Lab4.
	 */
	public Lab4() {
		super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
		setPreferredSize( new Dimension(700,700) );
		addGLEventListener(this); // This panel will respond to OpenGL events.
		addKeyListener(this);  // The panel will respond to key events.
	}

	//-------------------Data for stellated dodecahedron ------------------

	private final static double[][] dodecVertices  = {
			{-0.650000,0.000000,-0.248278},
			{0.401722,0.401722,0.401722},            // This array contains the coordinates
			{0.650000,0.000000,0.248278},            // for the vertices of the polyhedron
			{0.401722,-0.401722,0.401722},           // known as a stellated dodecahedron
			{0.000000,-0.248278,0.650000},
			{0.000000,0.248278,0.650000},            // Each row of the 2D array contains
			{0.650000,0.000000,-0.248278},           // the xyz-coordinates for one of
			{0.401722,0.401722,-0.401722},           // the vertices.
			{0.248278,0.650000,0.000000},
			{-0.248278,0.650000,0.000000}, 
			{-0.401722,0.401722,-0.401722},
			{0.000000,0.248278,-0.650000},
			{0.401722,-0.401722,-0.401722},
			{0.248278,-0.650000,0.000000},
			{-0.248278,-0.650000,0.000000},
			{-0.650000,0.000000,0.248278},
			{-0.401722,0.401722,0.401722},
			{-0.401722,-0.401722,-0.401722},
			{0.000000,-0.248278,-0.650000},
			{-0.401722,-0.401722,0.401722},
			{0.000000,1.051722,0.650000},
			{-0.000000,1.051722,-0.650000},
			{1.051722,0.650000,-0.000000},
			{1.051722,-0.650000,-0.000000},
			{-0.000000,-1.051722,-0.650000},
			{-0.000000,-1.051722,0.650000},
			{0.650000,0.000000,1.051722},
			{-0.650000,0.000000,1.051722},
			{0.650000,-0.000000,-1.051722},
			{-0.650000,0.000000,-1.051722},
			{-1.051722,0.650000,-0.000000},
			{-1.051722,-0.650000,0.000000}
	};

	private static final int GL_TRIANGLES = 0;

	private static int[][] dodecTriangles = {
			{16,9,20},
			{9,8,20},
			{8,1,20},              // This array specifies the faces of
			{1,5,20},              // the stellated dodecahedron.
			{5,16,20},
			{9,10,21},             // Each row in the 2D array is a list
			{10,11,21},            // of three integers.  The integers
			{11,7,21},             // are indices into the vertex array,
			{7,8,21},              // dodecVertices.  The vertices at
			{8,9,21},              // at those indices are the vertices
			{8,7,22},              // of one of the triangular faces of
			{7,6,22},              // the polyhedron.
			{6,2,22},
			{2,1,22},              // For example, the first row, {16,9,20},
			{1,8,22},              // means that vertices number 16, 9, and
			{6,12,23},             // 20 are the vertices of a face.
			{12,13,23}, 
			{13,3,23},             // There are 60 faces.
			{3,2,23},
			{2,6,23},
			{18,17,24},
			{17,14,24},
			{14,13,24},
			{13,12,24},
			{12,18,24},
			{14,19,25},
			{19,4,25},
			{4,3,25},
			{3,13,25},
			{13,14,25},
			{4,5,26},
			{5,1,26},
			{1,2,26},
			{2,3,26},
			{3,4,26},
			{15,16,27},
			{16,5,27},
			{5,4,27},
			{4,19,27},
			{19,15,27},
			{7,11,28},
			{11,18,28},
			{18,12,28},
			{12,6,28},
			{6,7,28},
			{10,0,29},
			{0,17,29},
			{17,18,29},
			{18,11,29},
			{11,10,29},
			{0,10,30},
			{10,9,30},
			{9,16,30},
			{16,15,30},
			{15,0,30},
			{17,0,31},
			{0,15,31},
			{15,19,31},
			{19,14,31},
			{14,17,31}
	};

	//------------------- TODO: Complete this section! ---------------------

	private int objectNumber = 1;        // Which object to draw (1 ,2, 3, 4, 5, or 6)?
	                                     //   (Controlled by number keys.)

	private boolean useAnaglyph = false; // Should anaglyph stereo be used?
	                                     //    (Controlled by space bar.)

	private int rotateX = 0;    // Rotations of the cube about the axes.
	private int rotateY = 0;    //   (Controlled by arrow, PageUp, PageDown keys;
	private int rotateZ = 0;    //    Home key sets all rotations to 0.)

	private GLUT glut = new GLUT(); // An object for drawing GLUT shapes.


	/**
	 * The method that draws the current object, with its modeling transformation.
	 */
	private void draw(GL2 gl2) {
		
		gl2.glRotatef(rotateZ,0,0,1);   // Apply rotations to complete object.
		gl2.glRotatef(rotateY,0,1,0);
		gl2.glRotatef(rotateX,1,0,0);
		// TODO: Draw the currently selected object, number 1, 2, 3, 4, 5, or 6.
		// (Objects should lie in the cube with x, y, and z coordinates in the
		// range -5 to 5.)
		
		
		if (objectNumber == 1) {
			gl2.glBegin(gl2.GL_QUAD_STRIP); //specifies the type of shape
			gl2.glColor3d(255,0,0);  //red
			gl2.glVertex2f( -5, 5 ); // top left
			gl2.glColor3d(255,255,0);  //
			gl2.glVertex2f( 5, 5 );  // top right 
			gl2.glColor3d(0,255,0);  //green
			gl2.glVertex2f( -3, 0 ); // middle left
			gl2.glColor3d(0,255,255);  //red
			gl2.glVertex2f( 3, 0 ); // middle right
			gl2.glColor3d(0,0,255);  //blue
			gl2.glVertex2f( -5, -5 );  // bottom left
			gl2.glColor3d(255,12,47);  //red
			gl2.glVertex2f( 5,-5 ); // bottom right 		
			gl2.glEnd();
		} else if (objectNumber == 2) {
			
			gl2.glDisable(GL2.GL_LIGHTING); // the lights in the 3d world are disable
			
			gl2.glScaled(4, 4, 4);
			for (int i = 0; i < dodecTriangles.length; i++ ) {
			
				gl2.glBegin(GL2.GL_LINE_LOOP);
			
				for (int ii = 0; ii < dodecTriangles[i].length; ii ++) {
					
					int vertexNum = dodecTriangles[i][ii];
					double[] vertexCoords = dodecVertices[vertexNum];
				
					gl2.glVertex3dv( vertexCoords, 0 );  // draws the polyhedron
				}
				gl2.glEnd();
			}
			
			gl2.glEnable(GL2.GL_LIGHTING);
			
		} else if (objectNumber == 3) { // the 3d tree
		
			gl2.glPushMatrix();
			gl2.glColor3d(0.50, 0.25, 0); // brown 
			gl2.glTranslated(0,0,-5);
			glut.glutSolidCylinder(1.5,6,32,8); // the brown trunk 
			gl2.glPopMatrix();
			gl2.glColor3d(0,1,0);  // green 
			gl2.glPushMatrix();
			glut.glutSolidCone(3.5,8,32,8); // the top of the tree 
			gl2.glPopMatrix();
		} else if (objectNumber == 4) {
			gl2.glPushMatrix();
			bar(gl2); // Object number 4 is a "bar" consisting of a cylinder with a translated sphere at each end. 
			gl2.glPopMatrix();
		} else if (objectNumber == 5) {
			gl2.glPushMatrix();
			square(gl2); //Object number 5 is square shape that can be made out of two translated copies of the bar, plus two additional cylinders.
			gl2.glPopMatrix();
		} else if (objectNumber == 6) {
			gl2.glPushMatrix();
			cage(gl2); //object number 6 is the full cage, which can be made from two translated copies of the square, plus four additional cylinders. 
			gl2.glPopMatrix();
		}
	}
	
	void bar ( GL2 gl2 ) {
		gl2.glPushMatrix();
		gl2.glColor3d(1, 1, 0);
		gl2.glTranslated(-4,0,0);
		glut.glutSolidSphere( 1.5, 32, 32 ); // yellow sphere on the left 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glColor3d(0,1,0);  
		gl2.glTranslated(-4,0,0);
		gl2.glRotatef(90,0,1,0);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // middle green cylinder
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glColor3d(1, 1, 0);
		gl2.glTranslated(4,0,0);
		glut.glutSolidSphere( 1.5, 32, 32 ); // the right yellow sphere 
		gl2.glPopMatrix();	 	
	}
	
	void square (GL2 gl2) {
		gl2.glPushMatrix();
		gl2.glTranslated(0,-4,0);
		bar(gl2);       // bar at the top of the window 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glTranslated(0,4,0);
		bar(gl2);  // bar at the bottom of the window 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glColor3d(0,1,0);  
		gl2.glTranslated(-4,4,0);
		gl2.glRotatef(90,1,0,0);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // left cylinder 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glColor3d(0,1,0);  
		gl2.glTranslated(4,4,0);
		gl2.glRotatef(90,1,0,0);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // right cylinder 
		gl2.glPopMatrix();
	}
	
	void cage (GL2 gl2) {
		gl2.glPushMatrix();
		gl2.glTranslated(0,0,0);
		
		gl2.glPushMatrix();
		gl2.glTranslated(0,0,4);
		square(gl2);  // front square on the window 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glTranslated(0,0,-4);
		square(gl2); // back square on the window 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glTranslated(-4,-4,-4);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // left bottom cylinder 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glTranslated(4,4,-4);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // right top cylinder 
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glTranslated(-4,4,-4);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // left top cylinder
		gl2.glPopMatrix();
		
		gl2.glPushMatrix();
		gl2.glTranslated(4,-4,-4);
		glut.glutSolidCylinder( 0.50, 8, 32, 8 ); // right bottom cylinder 
		gl2.glPopMatrix();
		
		gl2.glPopMatrix(); // end of translation at 0,0,0;
	}
	
	//-------------------- Draw the Scene  -------------------------

	/**
	 * The display method is called when the panel needs to be drawn.
	 * It's called when the window opens and it is called by the keyPressed
	 * method when the user hits a key that modifies the scene.
	 */
	public void display(GLAutoDrawable drawable) {    

		GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.

		if (useAnaglyph) {
			gl2.glDisable(GL2.GL_COLOR_MATERIAL); // in anaglyph mode, everything is drawn in white
			gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, new float[]{1,1,1,1}, 0);
		}
		else { 
			gl2.glEnable(GL2.GL_COLOR_MATERIAL);  // in non-anaglyph mode, glColor* is respected
		}        	
		gl2.glNormal3f(0,0,1); // (Make sure normal vector is correct for object 1.)

		gl2.glClearColor( 0, 0, 0, 1 ); // Background color (black).
		gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );


		if (useAnaglyph == false) {
			gl2.glLoadIdentity(); // Make sure we start with no transformation!
			gl2.glTranslated(0,0,-15);  // Move object away from viewer (at (0,0,0)).
			draw(gl2);
		}
		else {
			gl2.glLoadIdentity(); // Make sure we start with no transformation!
			gl2.glColorMask(true, false, false, true);
			gl2.glRotatef(4,0,1,0);
			gl2.glTranslated(1,0,-15); 
			draw(gl2);  // draw the current object!
			gl2.glColorMask(true, false, false, true);
			gl2.glClear(GL2.GL_DEPTH_BUFFER_BIT);
			gl2.glLoadIdentity();
			gl2.glRotatef(-4,0,1,0);
			gl2.glTranslated(-1,0,-15); 
			gl2.glColorMask(false, true, true, true);
			draw(gl2);
			gl2.glColorMask(true, true, true, true);
		}

	} // end display()

	/** The init method is called once, before the window is opened, to initialize
	 *  OpenGL.  Here, it sets up a projection, turns on some lighting, and enables
	 *  the depth test.
	 */
	public void init(GLAutoDrawable drawable) {
		GL2 gl2 = drawable.getGL().getGL2();
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glFrustum(-3.5, 3.5, -3.5, 3.5, 5, 25);
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glEnable(GL2.GL_LIGHTING);  
		gl2.glEnable(GL2.GL_LIGHT0);
		gl2.glLightfv(GL2.GL_LIGHT0,GL2.GL_DIFFUSE,new float[] {0.7f,0.7f,0.7f},0);
		gl2.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, 1);
		gl2.glEnable(GL2.GL_DEPTH_TEST);
		gl2.glLineWidth(3);  // make wide lines for the stellated dodecahedron.
	}

	public void dispose(GLAutoDrawable drawable) {
		// called when the panel is being disposed
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// called when user resizes the window
	}

	// ----------------  Methods from the KeyListener interface --------------

	/**
	 * Responds to keypressed events.  The four arrow keys control the rotations
	 * about the x- and y-axes.  The PageUp and PageDown keys control the rotation
	 * about the z-axis.  The Home key resets all rotations to zero.  The number
	 * keys 1, 2, 3, 4, 5, and 6 select the current object number.  Pressing the space
	 * bar toggles anaglyph stereo on and off.  The panel is redrawn to reflect the
	 * change.
	 */
	public void keyPressed(KeyEvent evt) {
		int key = evt.getKeyCode();
		boolean repaint = true;
		if ( key == KeyEvent.VK_LEFT )
			rotateY -= 6;
		else if ( key == KeyEvent.VK_RIGHT )
			rotateY += 6;
		else if ( key == KeyEvent.VK_DOWN)
			rotateX += 6;
		else if ( key == KeyEvent.VK_UP )
			rotateX -= 6;
		else if ( key == KeyEvent.VK_PAGE_UP )
			rotateZ += 6;
		else if ( key == KeyEvent.VK_PAGE_DOWN )
			rotateZ -= 6;
		else if ( key == KeyEvent.VK_HOME )
			rotateX = rotateY = rotateZ = 0;
		else if (key == KeyEvent.VK_1)
			objectNumber = 1;
		else if (key == KeyEvent.VK_2)
			objectNumber = 2;
		else if (key == KeyEvent.VK_3)
			objectNumber = 3;
		else if (key == KeyEvent.VK_4)
			objectNumber = 4;
		else if (key == KeyEvent.VK_5)
			objectNumber = 5;
		else if (key == KeyEvent.VK_6)
			objectNumber = 6;
		else if (key == KeyEvent.VK_SPACE)
			useAnaglyph = ! useAnaglyph;
		else
			repaint = false;
		if (repaint)
			repaint();
	}

	public void keyReleased(KeyEvent evt) {
	}

	public void keyTyped(KeyEvent evt) {
	}

} // end class Lab4