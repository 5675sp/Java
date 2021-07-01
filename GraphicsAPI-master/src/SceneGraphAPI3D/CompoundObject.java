package SceneGraphAPI3D;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * @author Sergio Perez
 * @author Ally Doherty 
 * The class CompoundObject is a type of SceneGraphNode
 * that has the ability to contain multiple of both BasicObjects and
 * other CompoundObjects.
 */
public class CompoundObject extends SceneGraphNode {

	// An ArrayList of every BasicObject or CompoundObject inside of this
	// CompoundObject
	public ArrayList<SceneGraphNode> subObjects;

	/**
	 * Constructor that takes no parameters and initializes the subObjects arrayList
	 */
	public CompoundObject() {

		subObjects = new ArrayList<SceneGraphNode>();
	}

	/**
	 * Add method that adds a SceneGraphNode to this CompoundObject
	 *
	 * @param node,
	 *            SceneGraphNode
	 * @return this CompoundObject
	 */
	public CompoundObject add(SceneGraphNode node) {
		subObjects.add(node);
		return this;
	}

	/**
	 * Method that calls the draw method for every BasicObject or CompoundObject
	 * within this CompoundObject
	 *
	 * @param g
	 * @param glut
	 */
	public void doDraw(GL2 g, GLUT glut) {

		for (SceneGraphNode node : subObjects) {
			node.draw(g, glut);
		}
	}

}
