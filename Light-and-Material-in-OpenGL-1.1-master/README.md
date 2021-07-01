# Light and Material in OpenGL 1.1
 
Lab6: Light and Material in OpenGL 1.1.
 * This program shows a square "stage" with a variety of objects
 * arranged on it.  The objects use several shapes and materials
 * and include a wireframe object that is drawn with lighting
 * turned off. The user can rotate the stage about the y-axis
 * by dragging the mouse horizontally.

 This repository makes some 3D objects and place them on a "stage." The user can rotate the stage about the y-axis by dragging the mouse horizontally. The scene uses lighting, and the objects will need materials. Initially, only basic lighting is turned on. As part of the repository, the lighting in the objects been draw have some light. Some of the objects will be GLUT shapes, which come with the necessary normal vectors. But some will be polyhedra that you need to draw yourself, using data from a collection of polyhedron objects.

 The program is in Java therefore, you will need to set up Eclipse to use the JOGL API. See Subsection 3.6.2 (http://math.hws.edu/graphicsbook/c3/s6.html#gl1geom.6.2) for instructions on doing that. The basic idea is to make a "User Library" containing the JOGL files. Then you can start a new project and add the JOGL user library to the project. There are copies of the JOGL .jar files and the native libraries for Linux in  /classes/cs424/JOGL-support. (Note: There is no need to make copies of these files, if you are using them on Linux. Your User Library can can refer directly to the copies in /classes/cs424/JOGL-support.) You can also get copies of the .jar files and the native libraries for Linux, Windows, or Mac from  http://math.hws.edu/eck/cs424/jogl_2_3_support/. Be sure to read all the instructions in the textbook and follow them carefully!
