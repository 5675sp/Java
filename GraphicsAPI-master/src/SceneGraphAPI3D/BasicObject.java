package SceneGraphAPI3D;

import java.awt.Color;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * @author Sergio Perez
 * @author Ally Doherty 
 * The class Basic Object allows for the API to be able to
 * draw basic objects.Moreover,will allow basic objects to have a
 * variety of materials.
 */



public class BasicObject extends SceneGraphNode {

	private String shape; // the user will be able to create a basic object by using a BasicOject form the
							// primitive pool
							// it can't be static becuase it will not allow for a change of change everyTime
							// a new basicObject is created
	private int material; // the material each basicObject has
	private boolean hasMaterial; // if the object has a material
	private boolean hasLight; // if the object emits light
	private float[] lightColor;
	private boolean texture;
	private String textureType;

	private final static float[][] materials = { // Materials that can be apply to objects
			{ /* "emerald" */ 0.0215f, 0.1745f, 0.0215f, 1.0f, 0.07568f, 0.61424f, 0.07568f, 1.0f, 0.633f, 0.727811f,
					0.633f, 1.0f, 0.6f * 128 },
			{ /* "jade" */ 0.135f, 0.2225f, 0.1575f, 1.0f, 0.54f, 0.89f, 0.63f, 1.0f, 0.316228f, 0.316228f, 0.316228f,
					1.0f, 0.1f * 128 },
			{ /* "obsidian" */ 0.05375f, 0.05f, 0.06625f, 1.0f, 0.18275f, 0.17f, 0.22525f, 1.0f, 0.332741f, 0.328634f,
					0.346435f, 1.0f, 0.3f * 128 },
			{ /* "pearl" */ 0.25f, 0.20725f, 0.20725f, 1.0f, 1.0f, 0.829f, 0.829f, 1.0f, 0.296648f, 0.296648f,
					0.296648f, 1.0f, 0.088f * 128 },
			{ /* "ruby" */ 0.1745f, 0.01175f, 0.01175f, 1.0f, 0.61424f, 0.04136f, 0.04136f, 1.0f, 0.727811f, 0.626959f,
					0.626959f, 1.0f, 0.6f * 128 },
			{ /* "turquoise" */ 0.1f, 0.18725f, 0.1745f, 1.0f, 0.396f, 0.74151f, 0.69102f, 1.0f, 0.297254f, 0.30829f,
					0.306678f, 1.0f, 0.1f * 128 },
			{ /* "brass" */ 0.329412f, 0.223529f, 0.027451f, 1.0f, 0.780392f, 0.568627f, 0.113725f, 1.0f, 0.992157f,
					0.941176f, 0.807843f, 1.0f, 0.21794872f * 128 },
			{ /* "bronze" */ 0.2125f, 0.1275f, 0.054f, 1.0f, 0.714f, 0.4284f, 0.18144f, 1.0f, 0.393548f, 0.271906f,
					0.166721f, 1.0f, 0.2f * 128 },
			{ /* "chrome" */ 0.25f, 0.25f, 0.25f, 1.0f, 0.4f, 0.4f, 0.4f, 1.0f, 0.774597f, 0.774597f, 0.774597f, 1.0f,
					0.6f * 128 },
			{ /* "copper" */ 0.19125f, 0.0735f, 0.0225f, 1.0f, 0.7038f, 0.27048f, 0.0828f, 1.0f, 0.256777f, 0.137622f,
					0.086014f, 1.0f, 0.1f * 128 },
			{ /* "gold" */ 0.24725f, 0.1995f, 0.0745f, 1.0f, 0.75164f, 0.60648f, 0.22648f, 1.0f, 0.628281f, 0.555802f,
					0.366065f, 1.0f, 0.4f * 128 },
			{ /* "silver" */ 0.19225f, 0.19225f, 0.19225f, 1.0f, 0.50754f, 0.50754f, 0.50754f, 1.0f, 0.508273f,
					0.508273f, 0.508273f, 1.0f, 0.4f * 128 },
			{ /* "cyan plastic" */ 0.0f, 0.1f, 0.06f, 1.0f, 0.0f, 0.50980392f, 0.50980392f, 1.0f, 0.50196078f,
					0.50196078f, 0.50196078f, 1.0f, .25f * 128 },
			{ /* "green plastic" */ 0.0f, 0.0f, 0.0f, 1.0f, 0.1f, 0.35f, 0.1f, 1.0f, 0.45f, 0.55f, 0.45f, 1.0f,
					.25f * 128 },
			{ /* "red plastic" */ 0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 0.0f, 0.0f, 1.0f, 0.7f, 0.6f, 0.6f, 1.0f, .25f * 128 },
			{ /* "cyan rubber" */ 0.0f, 0.05f, 0.05f, 1.0f, 0.4f, 0.5f, 0.5f, 1.0f, 0.04f, 0.7f, 0.7f, 1.0f,
					.078125f * 128 },
			{ /* "green rubber" */ 0.0f, 0.05f, 0.0f, 1.0f, 0.4f, 0.5f, 0.4f, 1.0f, 0.04f, 0.7f, 0.04f, 1.0f,
					.078125f * 128 },
			{ /* "red rubber" */ 0.05f, 0.0f, 0.0f, 1.0f, 0.5f, 0.4f, 0.4f, 1.0f, 0.7f, 0.04f, 0.04f, 1.0f,
					.078125f * 128 }, };

	/**
	 * BasicObject will specify the object a user wants to draw
	 * 
	 * @param String
	 *            Object will specify the object to be draw
	 **/

	public BasicObject(String Object) { //
		shape = Object;

	}

	/**
	 * setMaterial will place a certain type of material into an object
	 * 
	 * @param Integer
	 *            m will specify a certain material apply to an object
	 * 
	 **/
	public void setMaterial(Integer m) {

		material = m;
		hasMaterial = true;

	}
	
	
	
	public void setTexture(String t) {
		textureType = t;
		texture= true;
		
	}

	/**
	 * doDraw will draw objects in a 3D scene
	 * 
	 * @param Gl2
	 *            will allow for objects to be drawn
	 * @GLUT will allow for the 3D affect to take place
	 **/
	@Override
	public void doDraw(GL2 g, GLUT glut) {
		// TODO Auto-generated method stub

		g.glPushMatrix();
		
		
		
		
		
		float[] zero = { 0, 0, 0, 1 };
		if (this.hasLight) {

			g.glPopMatrix();
			g.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, lightColor, 0);
			g.glEnable(GL2.GL_LIGHT1);

			g.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, zero, 0);
			g.glPushMatrix(); // should be place after lightFv
		} else {

		}

		g.glPushAttrib(g.GL_LIGHTING_BIT);
		if (this.hasMaterial) {
			g.glPushMatrix();
			g.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, materials[material], 0);
			g.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, materials[material], 4);
			g.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materials[material], 8);
			g.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, materials[material], 12);
			g.glPopMatrix();
		}

		if (shape == "Cube") { // will draw a cube
			
			glut.glutSolidCube(1);

		} else if (shape == "Sphere") { // will draw sphere

			glut.glutSolidSphere(1, 32, 32);

		} else if (shape == "Cylinder") { // will draw a Cylinder
			glut.glutSolidCylinder(1, 3, 32, 8);

		} else if (shape == "Tetrahedron") {// will draw a Tetrahedron

			glut.glutSolidTetrahedron();

		} else if (shape == "Cone") { // will draw a Cone

			glut.glutSolidCone(1, 3, 32, 8);

		} else if (shape == "Hemisphere") { // Will draw a hemisphere

			double radius = 1;
			double x, y, z;

			for (double i = 0.0; i < Math.PI / (2); i = i + 0.1) {

				g.glBegin(g.GL_QUAD_STRIP);

				for (double ii = 0.0; ii < Math.PI * (2) + 0.1; ii = ii + 0.1) {

					// first vertex
					x = radius * Math.sin(i) * Math.cos(ii);
					z = radius * Math.sin(i) * Math.sin(ii);
					y = (-radius) * Math.cos(i);

					g.glVertex3d(x, y, z);

					// second vertex
					x = radius * Math.sin(i + 0.1) * Math.cos(ii);
					z = radius * Math.sin(i + 0.1) * Math.sin(ii);
					y = (-radius) * Math.cos(i + 0.1);
					g.glVertex3d(x, y, z);

				}
				g.glEnd();

			}

		} else if (shape == "Prism") { // will draw a Prism

			double t = Math.sqrt(3) / 4;

			g.glBegin(GL2.GL_TRIANGLES); // top triangular face
			g.glNormal3d(0, 1, 0);

			g.glVertex3d(-t, 0.5, -0.25);

			g.glVertex3d(t, 0.5, -0.25);

			g.glVertex3d(0, 0.5, 0.5);
			g.glEnd();

			g.glBegin(GL2.GL_TRIANGLES); // bottom triangular
			g.glNormal3d(0, -1, 0);

			g.glVertex3d(t, -0.5, -0.25);

			g.glVertex3d(-t, -0.5, -0.25);

			g.glVertex3d(0, -0.5, 0.5);
			g.glEnd();

			g.glBegin(GL2.GL_TRIANGLE_FAN); // back face (side facing towards negative z-axis)
			g.glNormal3d(0, 0, -1);

			g.glVertex3d(-t, -0.5, -0.25);

			g.glVertex3d(-t, 0.5, -0.25);

			g.glVertex3d(t, 0.5, -0.25);

			g.glVertex3d(t, -0.5, -0.25);
			g.glEnd();

			g.glBegin(GL2.GL_TRIANGLE_FAN); // front left face
			g.glNormal3d(-0.75, 0, t);

			g.glVertex3d(-t, 0.5, -0.25);

			g.glVertex3d(-t, -0.5, -0.25);

			g.glVertex3d(0, -0.5, 0.5);

			g.glVertex3d(0, 0.5, 0.5);
			g.glEnd();

			g.glBegin(GL2.GL_TRIANGLE_FAN); // front right face
			g.glNormal3d(0.75, 0, t);

			g.glVertex3d(0, 0.5, 0.5);

			g.glVertex3d(0, -0.5, 0.5);

			g.glVertex3d(t, -0.5, -0.25);

			g.glVertex3d(t, 0.5, -0.25);
			g.glEnd();

		}
		g.glPopAttrib();
		g.glPopMatrix();

	}

}
