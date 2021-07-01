/**
 * This program call SpikeyMain.java draws multiple spikeys in a panel.
 *@author Sergio Perez 
 *@version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * This class contains a main routine that will open a window containing
 * a panel of type SpikeyMain.  The panel initially shows one Spikey object.
 */
public class SpikeyMain extends JPanel {
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Spikey!");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int size = Math.min(screen.width - 80, screen.height - 120);
		if (size > 1000)
			size = 1000;
		SpikeyMain panel = new SpikeyMain(size);
		window.setContentPane(panel);
		window.setLocation(30,50);
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	
	private ArrayList<Spikey> list  = new ArrayList<Spikey>();  // The Spikey shown in this panel
	 
	 /**
	 * Create a panel whose preferred size is a square whose dimension 
	 * is given by the size parameter to this constructor.  The panel
	 * contains one "Spikey" located at the center of the window with
	 * a radius that is 1/4 of the panel size.  The constructor installs
	 * a MouseListener that will call the doMouseDown() method when the
	 * panel is clicked.  And it starts a timer that will call the
	 * doTimeStep() method every 30 milliseconds.
	 */
	 
	 /**
	 *A subroutine that creates Spikeys
	 *@param size takes an int.
	 *
	 */
	public SpikeyMain(int size) {
		setPreferredSize( new Dimension(size,size) );
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		setBackground(Color.BLACK);
		Spikey spikey;
		spikey = new Spikey(size/4,5,3);
		spikey.setCenterX(size/2);
		spikey.setCenterY(size/2);
		list.add(spikey);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				doMouseDown( evt.getX(), evt.getY(), evt );
			}
		});
		Timer timer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doTimeStep();
			}
		});
		timer.setInitialDelay(500);
		timer.start();
	}
	
	/**
	 * The paint component method draws the Spikey by calling its draw() method.
	 */
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		// If the next line is uncommented, the drawing will use antialiasing, which will
		// make it look nicer.  But it will slow things down when the drawing gets complicated.
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2); // fill with background color
		for(int i = 0; i < list.size(); i++){
			list.get(i).draw(g2);
		}
	}
	
	/**
	 * This method is called every 30 milliseconds.  It animates the panel by calling
	 * the spikey's timeStep() function and repainting the panel.  It also applies a
	 * rotation animation to the Spikey by changing its startAngle.
	 */
	private void doTimeStep() {
		for(int i = 0; i < list.size(); i++){
			list.get(i).timeStep();
			list.get(i).setStartAngle( list.get(i).getStartAngle() - 0.005 );
		}
		repaint();
	}
	
	/**
	 * This method is called when the user clicks the panel.  The parameters x and y
	 * are the coordinates of the point where the mouse was clicked.  The full mouse event
	 * is also passed as a parameter, in case properties such as evt.isShiftDown() are
	 * needed.
	 */
	
	private void doMouseDown(int x, int y, MouseEvent evt) {
		
		double r;
		double cx;
		double cy;
		boolean found = false;
		
		for(int i = 0; i < list.size(); i++){
		
			r = list.get(i).getBallRadius();
			cx = list.get(i).getCenterX();
			cy = list.get(i).getCenterY();
			///if the x and y when click is in the center of the circle of any spikey than remove it 
			if ( Math.sqrt( (cx-x)*(cx-x) + (cy-y)*(cy-y) ) < r ) {
				list.remove(i);
				found = true;
			} 
		}
		//if the x and y are outside any spikey than reapint the spikeys that are on the list 
		if(!found) {
			Spikey spikey = new Spikey(200,5,3);
			if (evt.isShiftDown()) {
				spikey.setCenterX(x);
				spikey.setCenterY(y);
				spikey.setBallColor(Handy.randomColor());
				spikey.setSpikeColor(Handy.randomColor());
				list.add(spikey);
		
			}else{
				spikey.setCenterX(x);
				spikey.setCenterY(y);
				list.add(spikey);
			}
		}
		repaint();
	}

}