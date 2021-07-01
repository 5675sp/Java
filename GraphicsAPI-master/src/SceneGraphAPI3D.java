import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.gl2.GLUgl2;
import com.jogamp.opengl.util.gl2.GLUT;

import SceneGraphAPI3D.BasicObject;
import SceneGraphAPI3D.CompoundObject;

/**
 * @author sergio Perez
 * @author Ally Doherty
 * 
 */
public class SceneGraphAPI3D extends GLJPanel implements GLEventListener {

	/**
	 * 
	 */
	// private double rotateY = 0;
	private Camera camera;

	private double frameNumber = 0.0;
	private BasicObject base, hemisphere, top;
	private CompoundObject helicopter, propeller;
	private BasicObject vane1, vane2;
	private BasicObject leg1, leg2, leg3;
	private BasicObject wheel1, wheel2, wheel3;
	private BasicObject back;
	private BasicObject backPropeller1, backPropeller2;
	private BasicObject ball;
	private BasicObject stage;

	GL2 gl2;

	public static void main(String[] args) {
		JFrame window = new JFrame("World");
		SceneGraphAPI3D panel = new SceneGraphAPI3D();
		window.setContentPane(panel);
		window.pack();
		window.setResizable(false);
		window.setLocation(50, 50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public SceneGraphAPI3D() {

		super(new GLCapabilities(null)); // Makes a panel with default OpenGL "capabilities".
		setPreferredSize(new Dimension(1000, 500));
		addGLEventListener(this); // This panel will respond to OpenGL events.
		// MouseHandler mouser = new MouseHandler();
		// addMouseListener(mouser); // The MouseHandler will respond to mouse events.
		createWorld();
		new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDraw();
				repaint();
			}
		}).start();
		camera = new Camera();

		camera.lookAt(0, 8, 40, 0, 2, 0, 0, 1, 0); // viewing transform
		camera.setLimits(-10, 10, -10, 10, -30, 30);
		camera.installTrackball(this);

	}

	public void createWorld() {

		stage = new BasicObject("Cube");
		stage.setTranslation(0, -3.50, 0);
		stage.setScale(20, 1, 20);
		stage.setMaterial(7);

		base = new BasicObject("Sphere");
		base.setScale(2.2, 2, 2.2);

		hemisphere = new BasicObject("Hemisphere");
		hemisphere.setRotation(180, 0, 0);
		hemisphere.setTranslation(0, 1.95, 0);
		hemisphere.setScale(0.75, 0.75, 0.75);
		top = new BasicObject("Cone");
		top.setRotation(-90, 0, 0);
		top.setTranslation(0, 2.25, 0);
		top.setScale(0.30, 0.30, 0.5);
		top.setMaterial(6);
		hemisphere.setMaterial(3);

		propeller = new CompoundObject();
		vane1 = new BasicObject("Cylinder");
		vane1.setScale(0.5, 0.05, 3);
		vane1.setTranslation(0, 0, -4.25);
		vane1.setMaterial(13);
		vane2 = new BasicObject("Cylinder");
		vane2.setScale(0.5, 0.05, 3);
		vane2.setTranslation(-4.25, 0, 0);
		vane2.setRotation(0, 90, 0);
		vane2.setMaterial(13);
		propeller.add(vane1);
		propeller.add(vane2);
		propeller.setTranslation(0, 4, 0);
		leg1 = new BasicObject("Cube");
		leg1.setScale(0.25, 1, 0.25);
		leg1.setTranslation(0.75, -2, 0.75);
		leg1.setMaterial(10);
		leg2 = new BasicObject("Cube");
		leg2.setScale(0.25, 1, 0.25);
		leg2.setTranslation(0.75, -2, -0.75);
		leg2.setMaterial(10);
		leg3 = new BasicObject("Cube");
		leg3.setScale(0.25, 1, 0.25);
		leg3.setTranslation(-0.75, -2, 0);
		leg3.setMaterial(10);

		wheel1 = new BasicObject("Cylinder");
		wheel2 = new BasicObject("Cylinder");
		wheel3 = new BasicObject("Cylinder");
		wheel1.setTranslation(0.75, -2.5, 0.60);
		wheel1.setScale(0.35, 0.35, 0.10);
		wheel1.setMaterial(17);
		wheel2.setTranslation(0.75, -2.5, -0.85);
		wheel2.setScale(0.35, 0.35, 0.10);
		wheel2.setMaterial(17);
		wheel3.setTranslation(-0.75, -2.5, -0.10);
		wheel3.setScale(0.35, 0.35, 0.10);
		wheel3.setMaterial(17);

		back = new BasicObject("Prism");
		back.setTranslation(4, 0, 0);
		back.setScale(1, 5, 0.90);
		back.setRotation(-60, -10, 90);
		back.setMaterial(11);

		backPropeller1 = new BasicObject("Cylinder");

		backPropeller1.setScale(0.15, 0.05, 0.5);
		backPropeller1.setTranslation(5.75, 0.15, 0.50);
		backPropeller1.setRotation(-90, 0, 0);
		backPropeller1.setMaterial(13);
		backPropeller2 = new BasicObject("Cylinder");
		backPropeller2.setScale(0.15, 0.05, 0.5);
		backPropeller2.setTranslation(5.75, 0.15, 0.50);
		backPropeller2.setRotation(-90, 0, 0);
		backPropeller2.setMaterial(13);

		ball = new BasicObject("Sphere");
		ball.setTranslation(-1.70, 0, 0);
		ball.setScale(1.20, 1.30, 1.50);
		ball.setMaterial(17);

		helicopter = new CompoundObject();
		helicopter.add(base);
		helicopter.add(hemisphere);
		helicopter.add(top);
		helicopter.add(propeller);
		helicopter.add(leg1);
		helicopter.add(leg2);
		helicopter.add(leg3);
		helicopter.add(wheel1);
		helicopter.add(wheel2);
		helicopter.add(wheel3);
		helicopter.add(back);
		helicopter.add(backPropeller1);
		helicopter.add(backPropeller2);
		helicopter.add(ball);

	}

	public void updateDraw() {

		frameNumber++;
		propeller.setRotation(0, frameNumber * 20, 0);
		if (frameNumber > 100) {
			helicopter.setTranslation((frameNumber - 100) / 20, (frameNumber - 100) / 20, 0);
		}
		backPropeller1.setRotation(-90, frameNumber * 20, 0);
		backPropeller2.setRotation(-90, frameNumber * 20 - 190, 0);

	}

	private GLUT glut = new GLUT(); // An object for drawing GLUT shapes.
	private GLU glu = new GLU(); // An object for calling GLU functions.

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jogamp.opengl.GLEventListener#display(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override

	// why is it going through display twice?
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

		GL2 gl3 = drawable.getGL().getGL2();
		gl3.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		camera.apply(gl3);

		helicopter.draw(gl3, glut);
		stage.draw(gl3, glut);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jogamp.opengl.GLEventListener#dispose(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jogamp.opengl.GLEventListener#init(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void init(GLAutoDrawable drawable) {

		// TODO Auto-generated method stub
		GL2 gl2;
		gl2 = drawable.getGL().getGL2();
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();
		glu.gluPerspective(20, (double) getWidth() / getHeight(), 1, 100);
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glEnable(GL2.GL_DEPTH_TEST);
		gl2.glEnable(GL2.GL_NORMALIZE);
		gl2.glEnable(GL2.GL_LIGHTING);
		gl2.glEnable(GL2.GL_LIGHT0);

		float[] red1 = { 1f, 1f, 0f, 1 };
		float[] red2 = { 1f, 1f, 0f, 1 };
		float[] red3 = { 0f, 0f, 0f, 1 };
		float[] red4 = { 0, 5, 0, 0 };
		gl2.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, red1, 0);
		gl2.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, red2, 0);
		gl2.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, red3, 0);
		gl2.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, red4, 0);
		gl2.glEnable(GL2.GL_LIGHT1);

		float[] blue1 = { 0f, 0f, 1f, 1 };
		float[] blue2 = { 0f, 0f, 1f, 1 };
		float[] blue3 = { 0f, 0f, 0f, };
		float[] blue4 = { 5, 0, 0, 0 };
		gl2.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, blue1, 0);
		gl2.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, blue2, 0);
		gl2.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, blue3, 0);
		gl2.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, blue4, 0);
		gl2.glEnable(GL2.GL_LIGHT2);
		//

		gl2.glLoadIdentity();
		glu.gluLookAt(0, 8, 40, 0, 1, 0, 0, 1, 0); // viewing transform

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jogamp.opengl.GLEventListener#reshape(com.jogamp.opengl.GLAutoDrawable,
	 * int, int, int, int)
	 */
	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

}
