package SceneGraphAPI3D;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * @author Sergio Perez
 * @author Ally Doherty The class SceneGraphNode allows its children Compound
 *         and Basic objects to rotate,scale, and translate.
 */

public abstract class SceneGraphNode {

	double rotationInDegreesX = 0;
	double rotationInDegreesY = 0;
	double rotationInDegreesZ = 0;

	double scaleX = 1, scaleY = 1, scaleZ = 1;

	double translateX = 0, translateY = 0;
	double translateZ = 0;

	public void draw(GL2 g, GLUT glut) {

		g.glPushMatrix();
		// Translates the object however many units are specified
		if (translateX != 0 || translateY != 0 || translateZ != 0) {
			g.glTranslated(translateX, translateY, translateZ);
		}

		// Rotates the object in however many degrees are specified
		if (rotationInDegreesX != 0.0) {
			g.glRotated(rotationInDegreesX, 1, 0, 0);
		}
		if (rotationInDegreesY != 0.0) {
			g.glRotated(rotationInDegreesY, 0, 1, 0);
		}
		if (rotationInDegreesZ != 0.0) {
			g.glRotated(rotationInDegreesZ, 0, 0, 1);
		}

		// Scales the object in however many units are specified
		if (scaleX != 1.0 || scaleY != 1.0 || scaleZ != 1.0) {
			g.glScaled(scaleX, scaleY, scaleZ);
		}

		doDraw(g, glut);

		g.glPopMatrix();

	}

	GL g;
	GLUT glut;

	/**
	 * Method for setting the rotation of the object
	 *
	 * @param degreesX,
	 *            double
	 * @param degreesY,
	 *            double
	 * @param degreesZ,
	 *            double
	 * @return SceneGraphNode object rotation is being set to
	 */
	public SceneGraphNode setRotation(double degreesX, double degreesY, double degreesZ) {

		rotationInDegreesX = degreesX;
		rotationInDegreesY = degreesY;
		rotationInDegreesZ = degreesZ;
		return this;

	}

	/**
	 * Method for setting the translation of the object
	 *
	 * @param dx,
	 *            double
	 * @param dy,
	 *            double
	 * @param dz,
	 *            double
	 * @return SceneGraphNode object translation is being set to
	 */
	public SceneGraphNode setTranslation(double dx, double dy, double dz) {
		translateX = dx;
		translateY = dy;
		translateZ = dz;
		return this;

	}

	/**
	 * Method for setting the scale of the object
	 *
	 * @param sx,
	 *            double
	 * @param sy,
	 *            double
	 * @param sz,
	 *            double
	 * @return SceneGraphNode object scale is being set to
	 */
	public SceneGraphNode setScale(double sx, double sy, double sz) {
		scaleX = sx;
		scaleY = sy;
		scaleZ = sz;
		return this;
	}

	/**
	 * Calls the draw() method which will draw the object
	 *
	 * @param g,
	 *            GL2
	 * @param glut,
	 *            GLUT
	 */
	void doDraw(GL2 g, GLUT glut) {

		this.draw(g, glut);

	}
}
