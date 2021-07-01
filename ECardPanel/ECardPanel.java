/**
 *A GUI program that allows users to place images and text on a panel and to move them around. 
 *@author Sergio Perez
 *@author Mark Curiel
 *@version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class ECardPanel extends JPanel {
	
	private JMenuRadioGroup overlayColorGroup; // conpartments 
	private JMenuRadioGroup fontS; // font width 
	private JMenuRadioGroup fontSS;// font size
	private JMenuRadioGroup fontColors; // font color 
	private JMenuRadioGroup pointFont; // stores the conpartments for fonts 
	
	//--------------- Main program, really should be in its own class ----------
	
	/** Open a window on the screen showing a panel of type ECardPanel
	 *  and its menu bar.  The window title is "ECard Designer" */
	public static void main(String[] args) {
		ECardPanel panel = new ECardPanel();
		JFrame window = new JFrame("ECard Designer");
		window.setContentPane(panel);
		window.setJMenuBar( panel.getMenuBar() );
		window.pack(); // Resize window based on content's preferred size
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // User can't change window size.
		window.setVisible(true);  // Open the window on the screen.
	}

	
	//-------------------- Code for the panel itself ---------------------------	private JCheckBoxMenuItem useOverlay, translucentOverlay
	
	private JCheckBoxMenuItem useOverlay, translucentOverlay;
	
	private Paint backgroundPaint; // Background color or texture for panel.
	
	private ArrayList<ECardItem> items; // Items displayed in the panel.
	
	private final Color[] backgroundColors = {
	     Color.WHITE, Color.BLACK, Color.YELLOW, Color.PINK, Color.LIGHT_GRAY,
	     new Color(200,200,255), new Color(180,255,180), new Color(240,240,220)
	};
	private final String[] backgroundColorNames = {
		 "White Background", "Black Background", "Yellow Background", "Pink Background",
		 "Gray Background", "Blue Background", "Green Background", "Beige Background"
	};
	
	private final String[] textureFileNames = {
			 "beige_paper.jpg", "blue_paper.jpg", "bolts.gif", "brushed-metal.jpg", "celtic-knot.jpg",
			 "cinderblock.jpg", "gray_mosaic.jpg", "marble.jpg", "pink_marble.gif", 
			 "pink_roses.jpg", "sky.jpg", "wood_floor.jpg"
		};
	private final String[] imageFileNames = {
			 "canyon.jpg", "coxe.jpg", "earth.png", "earthrise.jpg", "galaxy.jpg", "hws.jpg", "mandelbrot1.jpg", 
			 "mandelbrot2.jpg", "mandelbrot3.jpg",  "quad.jpg", "seneca.jpg", "space_station.jpg"
		};
		
	private final static Color[] colors =  { Color.BLACK, Color.WHITE, Color.GRAY,
                        Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA };
						
    private final String[] colorNames = { "Black", "White", "Gray",
                        "Red", "Blue", "Green", "Yellow", "Cyan", "Magenta"
		};
		
	private final String[] fontStyle = {"Serif","SansSerif"}; // style of the text
	
	private final int[] styles = { Font.PLAIN,  Font.BOLD }; // font of the text
	
	private final String[] styleNames = {"Plain", "Bold"};
	
	private final static Color[] fontHue =  { Color.CYAN, Color.MAGENTA, Color.RED,Color.YELLOW, Color.GREEN }; //colors for the font
	
	private final String[] fontColor = {  "Cyan", "Magenta", "Red","Yellow","Green"
		};
		
	private final String[] point = {"12 point", "16 point","20 point","24 point","30 point", "36 point","48 point", "60 point"};
	
	private final int[] heightOftext = {12,16,20,24,30,36,48,60}; //font for the text
	
	//the class ECardPanel 
	public ECardPanel() {
		
		backgroundPaint = Color.WHITE; //color of the background
		items = new ArrayList<ECardItem>();
		setPreferredSize( new Dimension(800,600) );
		addMouseListener(new MouseHandler());
		
	}
	
	///Draws the images, text, comparmtent 
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(backgroundPaint);
		g2.fillRect(0, 0, getWidth(), getHeight());
		if ( useOverlay.isSelected() ) {
			Color color = colors[ overlayColorGroup.getSelectedIndex() ];
			if ( translucentOverlay.isSelected() )
				g2.setColor( new Color(color.getRed(), 
                color.getGreen(), color.getBlue(), 150) );
			else
				g2.setColor(color);
				g2.fillRoundRect(30, 30, getWidth()-60, getHeight()-60, 30, 30);
				g2.setColor(color);
				g2.setStroke( new BasicStroke(2));
				g2.drawRoundRect(30, 30, getWidth()-60, getHeight()-60, 30, 30);
		}
		for ( ECardItem item : items ) {
			item.draw(g2);
		}
		
		//overlayColorGroup.setSelectedIndex(overlayColorGroup.getSelectedIndex())
	}
	
	//--------------- Menu Handling ---------------------------------------
	
	/**
	 * Create and return a menu bar containing menus for use with this panel.
	 */
	public JMenuBar getMenuBar() {
		
		JMenuBar menubar = new JMenuBar(); // adds the menu bar at the top, that allows for the set of menu to
		//be inplemented 
		MenuHandler listener = new MenuHandler();
		JMenu backgroundMenu = new JMenu("Background");
		for (String cmd : backgroundColorNames) { // add color backgrounds
			JMenuItem item = new JMenuItem(cmd);
			item.addActionListener(listener);
			backgroundMenu.add(item);
		}
		JMenu edit = new JMenu("Edit");
		menubar.add(edit);
		JMenuItem customBG = new JMenuItem("Custom Background Color...");
		customBG.addActionListener(listener);
		edit.add(customBG);
		backgroundMenu.addSeparator(); // line inside the menu
		for (String cmd : textureFileNames) { // add texture backgrounds
			JMenuItem item = new JMenuItem(cmd);
			item.addActionListener(listener);
			backgroundMenu.add(item);
		}
		
		menubar.add(backgroundMenu);
		JMenu imageMenu = new JMenu("Add Image");
		for (String Im : imageFileNames) {
			JMenuItem item = new JMenuItem(Im);
			item.addActionListener( listener );
			imageMenu.add(item);
		}
		menubar.add(imageMenu);
		JMenu font = new JMenu("Text");
		menubar.add(font);
		JMenuItem itemFont = new JMenuItem("Add Text item");
		itemFont.addActionListener(listener);
		font.add(itemFont);
		font.addSeparator(); // line inside the menu
		fontS = new JMenuRadioGroup( fontStyle , 0);
	
		fontS.addListener(listener);
		fontS.addToMenu(font);
		font.addSeparator(); // line inside the menu
		fontSS = new JMenuRadioGroup( styleNames , 0);
		fontSS.addListener(listener);
		fontSS.addToMenu(font);
		font.addSeparator(); // line inside the menu
		fontColors = new JMenuRadioGroup( fontColor , 0);
		fontColors.addListener(listener);
		fontColors.addToMenu(font);
		font.addSeparator(); // line inside the menu
		pointFont = new JMenuRadioGroup( point , 0);
		pointFont.addListener(listener);
		pointFont.addToMenu(font);

		JMenu overlayMenu = new JMenu("Overlay");
		useOverlay = new JCheckBoxMenuItem("Use Background Overlay", false);
		useOverlay.addActionListener(listener);
		overlayMenu.add(useOverlay);
		overlayMenu.addSeparator(); // line inside the menu
		//menubar.add(overlayMenu);
		translucentOverlay = new JCheckBoxMenuItem("Translucent Overlay", false);
		translucentOverlay.addActionListener(listener);
		overlayMenu.add(translucentOverlay);
		overlayMenu.addSeparator(); // line inside the menu
		menubar.add(overlayMenu);
		overlayColorGroup = new JMenuRadioGroup( colorNames , 0);
		overlayColorGroup.addListener(listener);
		overlayColorGroup.addToMenu(overlayMenu);
	
		return menubar;
	}
	
	/**
	 * This class defines the ActionListener that is used to respond to all menu commands.
	 */
	
	String fontSer;
	int fontInt;
	int wordSize;
	int hue;
	int size;
	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String command = evt.getActionCommand();
			for (int i = 0; i < backgroundColorNames.length; i++) {
				if (command.equals(backgroundColorNames[i])) {
					backgroundPaint = backgroundColors[i];
					repaint();
					return;
				}
			}
			for (String filename : textureFileNames) {
				if (command.equals(filename)) {
					try {
						ClassLoader cl = getClass().getClassLoader();
						BufferedImage texture = ImageIO.read(cl.getResource("textures/" + filename) );
						backgroundPaint = new TexturePaint(texture, new Rectangle2D.Double(
								0, 0, texture.getWidth(), texture.getHeight()));
						repaint();
					}
					catch (Exception e) {
						System.out.println("Didn't find " + filename); // not expected
					}
					return;
				}
			}
			
			if (command.equals("Custom Background Color...")) {
				Color c = JColorChooser.showDialog(ECardPanel.this, "Select Background Color", null);
				if (c != null) {
					backgroundPaint = c;
					repaint();
				}
				return;
			}
			
			for(String filename : imageFileNames){
				if (command.equals(filename)) {
					try {
						ClassLoader cl = getClass().getClassLoader();
						BufferedImage image = ImageIO.read(cl.getResource("image/" + filename) );
						//backgroundPaint = new TexturePaint(texture, new Rectangle2D.Double(
						//		0, 0, texture.getWidth(), texture.getHeight()));
						ImageItem phit = new ImageItem(image);
						items.add(phit);
						repaint();
					}
					catch (Exception e) {
						System.out.println("Didn't find " + filename); // not expected
					}
					return;
				}
			}
			
			String input;
			
			if (command.equals("Add Text item")) {
				input = JOptionPane.showInputDialog(ECardPanel.this, "Enter the text for the item.");
				if(input == null){
					JOptionPane.showMessageDialog(null,"Option Cancel");
				}else{
					JOptionPane.showMessageDialog(null,"Thanks for your input");
					Text entry = new Text(input);
					items.add(entry);
					repaint();
					return;
				}
			}
			for (String typeOfstyle : fontStyle){ // check if the font selected is an option 
				if (command.equals(typeOfstyle)) {
					fontSer = typeOfstyle;
					System.out.println(fontSer);
				}
			}
			
			for (String fontSyle : styleNames){ // sees if theirs a stylename for the text 
				if (command.equals(fontSyle)) {
					fontInt = fontSS.getSelectedIndex();
				}
			}
			//gets the value in font menu item 
			for (String colorfont : fontColor){
				if (command.equals(colorfont)) {
					hue = fontColors.getSelectedIndex();
					//fontColor.getSelectedIndex();
				}
			}
			//gets the value in font menu item 
			for (String tamano : point){
				if (command.equals(tamano)) {
					size = pointFont.getSelectedIndex();
				}
			}
		
			/////end of color panels///
			//	return;
			repaint();  // For any other command, we will just repaint, to make any change visible.
		}
	}
	//--------------- Mouse Handler ---------------------------------------
	
	/**
	 * This class defines the mouse listener that is used to respond to mouse actions.
	 */
	private class MouseHandler extends MouseAdapter {
		boolean dragging = false;
		int prevX, prevY;  // Previous location of mouse.
		public void mousePressed(MouseEvent evt) {
			int x = evt.getX();
			int y = evt.getY();
			// TODO: test if this mouse event starts a mouse drag operation; 
			//       if so, set dragging to true.
			ECardItem dragItem;
			for (  int i = items.size() - 1; i >= 0; i-- ) {
				ECardItem it = items.get(i);
				if ( x >= it.x && x <= (it.x + it.w) 
							&& y >= it.y && y <= (it.y + it.h)) {
					dragging = true;
					dragItem = it;
					items.remove(it);
					items.add(it);
					repaint();
					break;
				}
			}
			if (dragging) {
				addMouseMotionListener(this);
				prevX = x;
				prevY = y;
				
			}
		}
		
		//informs the system about when a click was finally excuted 
		public void mouseReleased(MouseEvent evt) {
			if (dragging) {
				dragging = false;
				removeMouseMotionListener(this);
			}
		}
		
		//it detects movement form the user 
		public void mouseDragged(MouseEvent evt) {
			if (dragging) {
				int x = evt.getX();
				int y = evt.getY();
				
				int dx = x - prevX;
				int dy = y - prevY;
				ECardItem drag = items.get(items.size()-1);
				drag.x += dx;
				drag.y += dy;
				// TODO:  Implement drag from (prevX,prevY) to (x,y)
				prevX = x;
				prevY = y;
				
			
				repaint();
			}
		}
		
	}
	
	//--------------- Graphical items for adding to the eCard --------------
	
	/**
	 * Abstract base class for items that can be displayed in this panel.
	 */
	abstract private class ECardItem {
		int x,y; // location of the item's upper right corner
		int w,h;
		abstract void draw(Graphics2D g);
	}
	private class ImageItem extends ECardItem {
		
		BufferedImage image;
		ImageItem(BufferedImage newImage) {
		    image = newImage;
			w = newImage.getWidth();
			h = newImage.getHeight();
		}
		void draw (Graphics2D g){
			g.drawImage(image, x, y, null);
		}
	}
	//////sub class for string
	private Font plainFont = new Font("Serif", Font.BOLD, 60);
	private Font masterFont;
	private class Text extends ECardItem  {
		Font masterFont = new Font(fontSer, styles[fontInt], heightOftext[size]);
		String input;
		Color testColor = fontHue[hue];
		Text(String newInput) {
		    input = newInput;
			FontMetrics metrics = getFontMetrics(masterFont);
			w = metrics.stringWidth(input);
			h = metrics.getAscent();
			
		}
		void draw (Graphics2D g){
				
			g.setColor(testColor); ////
			g.setFont(masterFont);//////
			g.drawString(input, x, y+h);
		}
	}
	//////end of subclass string 
}
