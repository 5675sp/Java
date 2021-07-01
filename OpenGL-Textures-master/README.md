# OpenGL Textures
 

 Image Textures in OpenGL/JOGL.
 When run as an application, the program shows a window with
 two panels:  on the left, a PaintPanel where the user can
 draw 2D images and on the right a GLJPanel that shows a single
 object to which a texture can be applied.  There is a menu
for selecting the object and one for selecting the texture.
 It is possible to apply the image from the paint panel as
 a texture on the 3D object, and it possible to copy the
 image from the OpenGL panel to the paint panel.
 This program depends on PaintPanel.java, TexturedShapes.java,
 Camera.java, and three resource files (brick.jpg, earth.jpg,
 and clouds.jpg) that contain images used as textures.

  The program is in Java therefore, you will need to set up Eclipse to use the JOGL API. See Subsection 3.6.2 (http://math.hws.edu/graphicsbook/c3/s6.html#gl1geom.6.2) for instructions on doing that. The basic idea is to make a "User Library" containing the JOGL files. Then you can start a new project and add the JOGL user library to the project. There are copies of the JOGL .jar files and the native libraries for Linux in  /classes/cs424/JOGL-support. (Note: There is no need to make copies of these files, if you are using them on Linux. Your User Library can can refer directly to the copies in /classes/cs424/JOGL-support.) You can also get copies of the .jar files and the native libraries for Linux, Windows, or Mac from  http://math.hws.edu/eck/cs424/jogl_2_3_support/. Be sure to read all the instructions in the textbook and follow them carefully!
