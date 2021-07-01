//Sergio Perez
//Computer Science 124
//A minesweeper game that displays a grid of buttons and few of the buttons are bomnbs. The user in the gam is able to change the level of difficulty of the game.
//Require Files: Handy.java/HMPanel.java/LButton.java/
//RCButton.java
//-Last Time Modified: 05/7/16 at 1:31PM*/


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.ImageIcon;

class MSPanel extends JPanel implements ActionListener {




         
    
    private MGMSPanel one; //minesweeper game panel
    private JButton first;//the button at the bottom that will allow the user 
                        //to play again 
    private int rr;
    private int cc;
    
    private JLabel topleft;///the label that will displauy how many bombs are in                            the game
    private JLabel topright;//the label that will display the name of the user 
    private JButton Easy;//the button in the middle top
    private JButton Intermittent;//the button in the middle middle
    private JButton Hard;//the button in the middle bottom
    private JPanel toppanel;
    private JPanel topmiddle;//the panel that will hold 3 buttons

    private  int check;
    
    private JLabel east;
    private JLabel west;
    private JLabel east2;
    private JLabel west2;
    
    private JPanel theEast;
    private JPanel theWest;
    
    private int checkE;
    private int checkM;
    private int checkH;
    private int checkN;
    private ImageIcon theImage;
    
    public MSPanel(int r, int c,String fgds){
    
         
        theEast = new JPanel();
        theWest = new JPanel();
        
        
         
        theEast.setLayout(new GridLayout(2,1)); 
        theWest.setLayout(new GridLayout(2,1)); 
        
        
        theEast.setPreferredSize(new Dimension(60, 60));
        theWest.setPreferredSize(new Dimension(60, 60));
        
        
        theImage = new ImageIcon("tumblr_n7o5atqTDt1qhu3vxo1_500.gif");
        
        east = new JLabel(theImage);
         
        //east.setPreferredSize(new Dimension(60, 60));
         
        west = new JLabel(theImage);
        
        //west.setPreferredSize(new Dimension(60, 60));
        
        east2 = new JLabel(theImage);
        
        
        west2 = new JLabel(theImage);
        
        
        theEast.add(east);
        theEast.add(east2);
        theWest.add(west);
        theWest.add(west2);
        
        
        
        
        checkN = 0;
        checkE = 0;
        checkM = 0;
        checkH = 0;
        
        rr = r;
        cc = c;
        
        check = (rr)*(cc)/7; //checks how many bombs are in the game 
        
        Color forBb = new Color(174,214,241);
        
        topleft = new JLabel();///the label on the top left 
        topleft.setText("There's "+check+" Bombs");
        topleft.setHorizontalAlignment(0);
        topleft.setBackground(forBb);
        topleft.setOpaque(true);
        
        topmiddle = new JPanel();
        
        topright = new JLabel();//the label on the top right 
        topright.setText(fgds);
        topright.setHorizontalAlignment(0);
        topright.setBackground(forBb);
        topright.setOpaque(true);
        
        Easy = new JButton();//the button on the middle at the top 
        Easy.setBackground(forBb);
        Easy.setOpaque(true);
        Easy.setText("Easy");
        Easy.addActionListener(this);
        
        Intermittent = new JButton();//the button on the middle at the middle
        Intermittent.setBackground(forBb);
        Intermittent.setOpaque(true);
        Intermittent.setText("Intermediate");
        Intermittent.addActionListener(this);
        
        Hard = new JButton();//the button on the middle at the bottom 
        Hard.setBackground(forBb);
        Hard.setOpaque(true);
        Hard.setText("Hard");
        Hard.addActionListener(this);
        
        topmiddle.setLayout(new GridLayout(3,1));//all the 3 buttons are place 
                                                //on the middle panel 
        topmiddle.add(Easy);
        topmiddle.add(Intermittent);
        topmiddle.add(Hard);
        
        topleft.setPreferredSize(new Dimension(50, 60));
        topright.setPreferredSize(new Dimension(50, 60));
        
        toppanel = new JPanel();
        toppanel.setLayout(new GridLayout(1,3));//adds the 3 panels on the top 
        
        toppanel.add(topleft);
        toppanel.add(topmiddle);
        toppanel.add(topright);
        
        this.setLayout(new BorderLayout());
        
        one = new MGMSPanel
        (rr,cc);
        
        add(one,BorderLayout.CENTER);
        
        Color forB = new Color(174,214,241); //light blue color 

        first = new JButton("New Game");//the button that will allow the user to                                            play a new game 
        first.setBackground(forB);
        first.setOpaque(true);
        
        add(first,BorderLayout.SOUTH);//the button that will allow the user to                                             win will go on the bottom 
        
        first.addActionListener(this);
        
        this.add(toppanel,BorderLayout.NORTH);
        this.add(theEast,BorderLayout.EAST);
        this.add(theWest,BorderLayout.WEST);
        
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        String source = e.getActionCommand();
        
        if(source.equals("Easy")) {
            
            one.setVisible(false);
            
            this.remove(one); // the old panel is remove 
        
            one = new MGMSPanel(rr*2,cc*2); //new game is created 
            
            checkE = (rr*2)*(cc*2)/7;
            
            topleft.setText("There's "+checkE+" Bombs");
        
            this.add(one,BorderLayout.CENTER);//the new game is place at the                                                     center of the frame 
            this.validate();
            
        }
        
        if(source.equals("Intermediate")) {
            
            one.setVisible(false);
            this.remove(one);//removes the old game 
        
            one = new MGMSPanel(rr*3,cc*3);//creates a new game 
              
            checkM = (rr*3)*(cc*3)/7;
            topleft.setText("There's "+checkM+" Bombs");
        
            this.add(one,BorderLayout.CENTER);
            this.validate();
            
        }
        
        if(source.equals("Hard")) {
            
            one.setVisible(false);
            this.remove(one);//removes the old game 
        
            one = new MGMSPanel(rr*4,cc*4);//creates a new game 
            
            checkH = (rr*4)*(cc*4)/7;
            topleft.setText("There's "+checkH+" Bombs");
        
            this.add(one,BorderLayout.CENTER);//places the new game on the                                                       center
            this.validate();
            
        }
        
        
        if(source.equals("New Game")) {// if the button at the bottom is press
                                       //a news game will be created with the 
                                       //its original rows and columns 
        
            one.setVisible(false);
            this.remove(one);//removes the current  game 
        
            one = new MGMSPanel(rr,cc);//news game 
        
            checkN = (rr)*(cc)/7;
            topleft.setText("There's "+checkN+" Bombs");
        
            this.add(one,BorderLayout.CENTER);//puts the new game of the center 
                                             //of the frame 
            this.validate();
        }
        
        
        
    }
    
}
