/*
 *@author Sergio Perez
 *@version 1.0
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class finalProject extends JPanel   {
    
    private JMenuRadioGroup overlayColors; // the colors of the pixels will be store here
    private JMenuRadioGroup drawing; // The button that will allow the user to draw on an image
    private final String[] menuStuffss = {"Open...","Save..","Close.."}; // The options for the Menu option bar
    private final String[] colorNames = {"Select-One-Above", "Pencil-Drawing", "Cubisim", "Circle-Eff", "Melting"};//The options of the effect menu bar
    private final String[] affects = { "Select-One-Above","Invert","Half", "Threshold","Greenish","Reddish","Bluish","Purple","Jamaica","OutLine"};//The options for the filter menu bar
    private JCheckBoxMenuItem filters;  // The menu that will hold the options for the filter menu bar
    
    static int width;  // gets the width of the image being use
    static int height; // gets the height of the image being use
    static boolean draw = false; // will not draw at the beginning of the program
    static boolean analyze = false; // will check for progress of the image being drawn
    
    BufferedImage paintImage = null; // the image holders will start as null
    BufferedImage newImages = null; // the image holders will start as null
    
    Color[][] cols; // will hold the color of every pixel in the picture being use
    File img; // will get the image that will be use in the program
    
    
    static JButton q = new JButton(); // The button that will hold the label that will hold the gif
    static JButton welcomeIcon = new JButton(); // the button that will hold the label that say's "Sergio's Pixel Manipulator"
    static JLabel g = null;  //The label at the middle of the porgram when it starts
    
    BufferedImage reset = null; // the images that will allow for the pixel manipulation
    BufferedImage prereset = null; // will hold all the current paint things in a panel
    
    int value = 0;  //will obtain the current stage of the program
    int valor = 0;  // will obtain the value of images being use
    
    public static void main(String arg[]){
        
        JFrame hold = new JFrame();// the frame that will hold the panel that will allow the user to use the filters
        hold.setTitle("Pixel");
        
        hold.setSize(1000,2000); // the size of the frame
        
        finalProject panel = new finalProject(); // the panel were the images will be drawn
        
        hold.setJMenuBar( panel.getMenuBar() ); // will but the menu in the frame
        
        panel.setLayout(null); // the frame will be created
        
        hold.setContentPane(panel); // the panel is place in the frame
        
        finalProject two = new finalProject(); // the panel that will hold the image
        
        q.setSize(1100,750); // the size of the panel
        q.setLocation(-10,0); // the location of the panel on the screen
        q.setVisible(true); // the frame will be visible to the user
        
        q.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20)); // the boder layout of the buttons are remove
        ImageIcon loading = new ImageIcon("2560x1440-black-solid-color-background.jpg"); // the colorfull ball is place in the icon
        q.add(new JLabel("", loading, JLabel.CENTER)); // the icon created above is place in a new JLabel that will hodl the colorfull ball
        
        
        welcomeIcon.setSize(500,500); // the colorfullball will have a panel that will be 500 by 500 for its size.
        welcomeIcon.setLocation(300,0); // the location will be place in the screen
        welcomeIcon.setVisible(true); // the button will be visible to the user
        
        welcomeIcon.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20)); // the buttons layout is remove
        ImageIcon icon = new ImageIcon("3d-egg-art-animated-gif.gif"); // the icon is created with an image gif
        welcomeIcon.add(new JLabel("", icon, JLabel.CENTER));  // the icon will be place to a new icon
        
        g = new JLabel("Sergio's Pixel Editor"); // the label tha will be place at the middle of the screen
        g.setFont(new Font("Courier New", Font.ITALIC, 30)); // the font of the writing that hold the tittle of the program in the middle of the screen
        g.setLocation(310,100);// the label will be place at the middle of the screen
        g.setSize(9000,800); // the size of the label at the middle of the screen
        
        
        g.setForeground(Color.WHITE); // the color of the label at the middle of the text
        
        panel.add(g); // the button is place on the screen
        panel.add(welcomeIcon); // the colorfull ball is place at the middle of the screen
        panel.add(q); // the  buttont holds the label will be place on the muiddle of the page
        
        hold.add(two); // the panel will be place in the screen next to the label
        hold.setVisible(true); // the panel will be visible to the user that trys to use the button
        
    }
    
    private class MenuHandler implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
            
            if(command.equals("Save..")){ // when the save option is click on the program the image being manipulated will be save
                doSaveFile(newImages,"png"); // the method that will save the image it will call the parameter that is at the bottom of this script of code
            }
            
            if(command.equals("Open...")){ // when the open option is click on the program the image the user selects will be display in the program panel.
                q.setVisible(true);// the image will beocome visible in the panel on the screen
                doOpen(); //The method open will get an image for the user
                
                try {
                    paintImage = ImageIO.read(img); //the image selected
                    width = paintImage.getWidth(); // the width of the image
                    height = paintImage.getHeight(); // the height of the image
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                cols = new Color[paintImage.getWidth()][paintImage.getHeight()]; // the array of colors. colors that will be obtain from an image
                for(int z = 0;z < paintImage.getWidth();z++){
                    width++;
                    for(int a = 0;a < paintImage.getHeight();a++){
                        int color = paintImage.getRGB(z, a); // the color of the pixel will be store
                        int  red = (color & 0x00ff0000) >> 16; // the  amount of red in one pixel
                        int  green = (color & 0x0000ff00) >> 8; // the amount of green in one pixel
                        int  blue = color & 0x000000ff; // the amount of blue in one pixel
                        int alpha = (color>>24) & 0xff; // the amount of alpha in one pixel
                        Color col = new Color(red,green,blue,alpha); // the full color of a pixel is place in the col colors array.
                        cols[z][a] = col; // the color is place in the array
                        height++; // the height will increate that will get the other pixel color
                    }
                }
                
                value = paintImage.getWidth(); // the width of the image
                valor = paintImage.getHeight(); // the height of the image
                q.setVisible(false);
                g.setVisible(false); // the panel will remove form the panel
                welcomeIcon.setVisible(false);// the button will be remove
                repaint(); // the panel will be repainted
                
                try{
                    File get = new File("2560x1440-black-solid-color-background.jpg"); // the image will be obtain
                    prereset = ImageIO.read(get); // the image will be obtain
                    
                }catch (IOException e) {
                    e.printStackTrace(); // catches for any erron when reading the pixels in the image
                }
                
                newImages = new BufferedImage(paintImage.getWidth(), paintImage.getHeight(),BufferedImage.TYPE_INT_ARGB); // the image that will be manipulated.
                Graphics2D g2 = newImages.createGraphics(); // the image will be created and will be allow to be manipualated
                
                g2.setColor(Color.WHITE); // white
                g2.fillRect(0,0,1000,2000);// a big white rect will be draw before drawing an image
                
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        
                        g2.setColor(cols[z][a]); // the colors of the pixels
                        
                        g2.fillRect(z,a,1,1); // the rect will be drawn
                    }
                }
                
                paintImage = newImages; // the manipuated image is place in the original image
                repaint(); // repaints the panel
                
            }
            
            if(command.equals("Close..")){ // when the close option is clicked the program will exit
                System.exit(0);
            }
            
            
            //The image will be drawn with a pecil drawn effect//
            if(command.equals("Pencil-Drawing")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        random = (int) (Math.random() * 10) + 1;
                        g2.setColor(cols[z][a]);
                        if(random == 5){
                            g2.drawLine(z,a,z+10,a+10);
                        }
                    }
                }
                paintImage = newImages;
                repaint();

            }
            //The image will be drawn with a green filter
            if(command.equals("Greenish")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(new Color(cols[z][a].getRed(),255,cols[z][a].getBlue()));
                        g2.fillOval(z,a,1,1);

                    }
                }

                //paintImage = newImages;
                //repaint();
                   g2.setColor(Color.GREEN);
                   paintImage = newImages;
                    repaint();
              
            }
            
            //The image will be drawn with a red filter
            if(command.equals("Reddish")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(new Color(255,cols[z][a].getGreen(),cols[z][a].getBlue()));
                        g2.fillOval(z,a,1,1);
                    }
                }
                paintImage = newImages;
                repaint();
            }
            
            
            //The image will be drawn with a purple filter
            if(command.equals("Purple")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(new Color(255,cols[z][a].getGreen(),255));
                        g2.fillOval(z,a,1,1);
                    }
                }
                paintImage = newImages;
                repaint();
            }
            
            //The image will be drawn with a blue filter
            if(command.equals("Bluish")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(new Color(cols[z][a].getRed(),cols[z][a].getGreen(),255));
                        g2.fillOval(z,a,1,1);
                    }
                }
                paintImage = newImages;
                repaint();
            }
            
            
            //The image will be drawn with circles only
            if(command.equals("Circle-Eff")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        random = (int) (Math.random() * 10) + 1;
                        g2.setColor(cols[z][a]);
                        if(random == 5){
                            g2.fillOval(z,a,10,10);
                        }
                    }
                }
                paintImage = newImages;
                repaint();
            }
            
            //The image will be drawn with hald of it's colors
            
            if(command.equals("Half")){
                repaint();
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int red = 0;
                int green = 0;
                int blue = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        red = cols[z][a].getRed()/2;
                        green =  cols[z][a].getGreen()/2;
                        blue =  cols[z][a].getBlue()/2;
                        g2.setColor(new Color(red,green,blue));
                        g2.fillOval( z,a ,1,1); // Draw a line from (10,10) to (150,150)
                        System.out.println("Draw");
                    }
                }
                paintImage = newImages;
                repaint();
            }
            //The image will drawn with squares
            if(command.equals("Cubisim")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random  = 0;
                int cube = 0;
                int palo = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(cols[z][a]);
                        random = (int) (Math.random() * 50) + 1;
                        cube =  (int) (Math.random() * 50) + 1;
                        palo = (int) (Math.random() * 50) + 1;
                        if(random == 5){
                            g2.fillRect( z,a ,10,10); // Draw a line from (10,10) to (150,150)
                            g2.setColor(new Color(0,0,0));
                            g2.drawRect(z,a,cube,palo);
                        }
                    }
                }
                paintImage = newImages;
                repaint();
            }
            //The image will be drawn with a melting effect
            if(command.equals("Melting")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random  = 0;
                int cube = 0;
                int palo = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(cols[z][a]);
                        random = (int) (Math.random() * 50) + 1;
                        cube =  (int) (Math.random() * 50) + 1;
                        palo = (int) (Math.random() * 50) + 1;
                        if(random == 5){
                            g2.fillRect( z,a ,cube,palo); // Draw a line from (10,10) to (150,150)
                        }
                    }
                }
                paintImage = newImages;
                repaint();
            }
            //The image will be drawn but it's colors will be shift
            if(command.equals("Invert")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        g2.setColor(new Color(     (255)-(cols[z][a].getRed()) ,     (255)-(cols[z][a].getGreen())   ,  (255)-(cols[z][a].getBlue())   )  );
                        g2.fillOval( z,a ,1,1); // Draw a line from (10,10) to (150,150)
                        System.out.println("Draw");
                    }
                }
                paintImage = newImages;
                repaint();
            }
            //The image is drawn but the colors are made a bit more strong
            if(command.equals("Threshold")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random2;
                int red = 0;
                int green = 0;
                int blue = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        if(cols[z][a].getRed() >= 150 ){
                            red = 255;
                        }  else {
                            red = 0;
                        }
                        if(cols[z][a].getGreen() >= 150 ){
                            green = 255;
                        }  else {
                            green = 0;
                        }
                        if(cols[z][a].getBlue() >= 150 ){
                            blue = 255;
                        }  else {
                            blue = 0;
                        }
                        g2.setColor(new Color(red,green,blue));
                        random2 = (int) (Math.random() * 50) + 1;
                        g2.fillOval( z,a ,1,1); // Draw a line from (10,10) to (150,150)
                    }
                }
                paintImage = newImages;
                repaint();
            }
            
            
            
            
            //The image is drawn but the colors are set to colors from the Jamaican Flag
            if(command.equals("Jamaica")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random2;
                int red = 0;
                int green = 0;
                int blue = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        if(cols[z][a].getRed() >= 150 ){
                            red = 255;
                        }  else {
                            red = 0;
                        }
                        if(cols[z][a].getGreen() >= 50 ){
                            green = 255;
                        }  else {
                            green = 0;
                        }
                        if(cols[z][a].getBlue() >= 234 ){
                            blue = 255;
                        }  else {
                            blue = 0;
                        }
                        g2.setColor(new Color(red,green,blue));
                        random2 = (int) (Math.random() * 50) + 1;
                        g2.fillOval( z,a ,1,1); // Draw a line from (10,10) to (150,150)
                    }
                }
                paintImage = newImages;
                
                repaint();
               
            }
            
            //test
            //The image is drawn but the colors are made a bit more strong
            if(command.equals("OutLine")){
                Graphics2D g2 = newImages.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,1000,2000);
                repaint();
                int random2;
                int red = 0;
                int green = 0;
                int blue = 0;
                for(int z = 0;z < paintImage.getWidth();z++){
                    for(int a = 0;a < paintImage.getHeight();a++){
                        if(cols[z][a].getRed() >= 0 ){
                            red = 255;
                        }  else {
                            red = 100;
                        }
                        if(cols[z][a].getGreen() >= 50 ){
                            green = 255;
                        }  else {
                            green = 23;
                        }
                        if(cols[z][a].getBlue() >= 0 ){
                            blue = 255;
                        }  else {
                            blue = 165;
                        }
                        g2.setColor(new Color(red,green,blue));
                        random2 = (int) (Math.random() * 50) + 1;
                        g2.fillOval( z,a ,1,1); // Draw a line from (10,10) to (150,150)
                    }
                }
                paintImage = newImages;
                repaint();
            }
        }
    }
    /**
     * Create and return a menu bar containing menus for use with this panel.
     */
    // The following methods are instance methods.
    /* Create a paint() method to override the one in JFrame.
     This is where the drawing happens.
     We don't have to call it in our program, it gets called
     automatically whenever the frame needs to be redrawn,
     like when it it made visible or moved or whatever.*/
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int center = ((1000)- (value));
        int vertical = ((500)- (valor));
        System.out.println(""+vertical);
        if(paintImage != null){
            g.drawImage(paintImage,center/2,0,null);
        }
    }
    
    /*The menu created will be place in the panel. The menu will allow the user to impose any filters and image effect one wishes.
     */
    public JMenuBar getMenuBar() {
        JMenuBar menubar = new JMenuBar();
        MenuHandler listener = new MenuHandler();
        JMenu menu = new JMenu("Menu");
        for(String stuffs : menuStuffss){
            JMenuItem menuStuffs = new JMenuItem(stuffs);
            menuStuffs.addActionListener(listener);
            menu.add(menuStuffs);
        }
        menubar.add(menu);
        JMenu overlay = new JMenu("Effects");
        overlayColors = new JMenuRadioGroup( colorNames, 0 );
        overlayColors.addToMenu(overlay);
        menubar.add(overlay);
        overlayColors.addListener(listener);
        JMenu affect = new JMenu("Filters");
        drawing = new JMenuRadioGroup( affects, 0 );
        drawing.addToMenu(affect);
        menubar.add(affect);
        drawing.addListener(listener);
        return menubar;
    }
    
    /**
     * scale image
     *
     * @param sbi image to scale
     * @param imageType type of image
     * @param dWidth width of destination image
     * @param dHeight height of destination image
     * @param fWidth x-factor for transformation / scaling
     * @param fHeight y-factor for transformation / scaling
     * @return scaled image
     */
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
    
    /**
     * Attempts to save an image to a file selected by the user.
     * @param image the BufferedImage to be saved to the file
     * @param format the format of the image, probably either "PNG" or "JPEG"
     */
    private void doSaveFile(BufferedImage image, String format) {
        if (fileDialog == null)
            fileDialog = new JFileChooser();
        fileDialog.setSelectedFile(new File("image." + format.toLowerCase()));
        fileDialog.setDialogTitle("Select File For Saved Image");
        int option = fileDialog.showSaveDialog(this);
        if (option != JFileChooser.APPROVE_OPTION)
            return;  // User canceled or clicked the dialog's close box.
        File selectedFile = fileDialog.getSelectedFile();
        if (selectedFile.exists()) {  // Ask the user whether to replace the file.
            int response = JOptionPane.showConfirmDialog( null,
                                                         "The file \"" + selectedFile.getName()
                                                         + "\" already exists.\nDo you want to replace it?",
                                                         "Confirm Save",
                                                         JOptionPane.YES_NO_OPTION,
                                                         JOptionPane.WARNING_MESSAGE );
            if (response != JOptionPane.YES_OPTION)
                return;  // User does not want to replace the file.
        }
        try {
            boolean hasFormat = ImageIO.write(image,format,selectedFile);
            if ( ! hasFormat )
                throw new Exception(format + " format is not available.");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Sorry, an error occurred while trying to save image.");
            e.printStackTrace();
        }
    }
    
    //-------------------- Code for the file dialogs ---------------------------
    
    JFileChooser fileDialog;
    public void doOpen() {
        if (fileDialog == null)
            fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Select File to be Opened");
        fileDialog.setSelectedFile(null);  // No file is initially selected.
        int option = fileDialog.showOpenDialog(this);
        if (option != JFileChooser.APPROVE_OPTION)
            return;  // User canceled or clicked the dialog's close box.
        File selectedFile = fileDialog.getSelectedFile();
        Scanner in;  // a scanner for reading text from the file
        try {
            in = new Scanner( selectedFile );
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                                          "Sorry, an error occurred while trying to open the file:\n" + e);
            return;
        }
        try {
            // ...
            // ...   (Read data from the file, using the scanner.)
            // ...
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                                          "Sorry, an error occurred while trying to read from the file:\n" + e);
        }
        finally {
            in.close();
        }
        img = new File(selectedFile.getPath());
    }
}