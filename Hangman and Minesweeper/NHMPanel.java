//Sergio Perez
//Computer Science 124
//A hangman game that lets you guess computer science 124 vocabulary words 
//Require Files: Handy.java/
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

public class NHMPanel extends JPanel implements ActionListener {


    private JPanel left;
    private JPanel bottom;
    private JPanel right;
    
    
    
    private JButton[] r;
    private JButton[] d;
    private boolean f;
    
    private int w;
    
    private String g;
    private String gf;
    private String theWord;
    
    private char x;
    private char le;
    private boolean law;
    
    private JButton playAgain;
    
    private char[] howMany;
    
    private int secondCounter;
  
    
    private int water;
    
    private int ccounter;
        
    private String[] comp;
    
    
    private String[] sd;
            
    private char[] cv;
    
    
    private boolean win;
    
    private boolean lose;
    
    private boolean hangMann;
    
    private boolean expression;
    
    private boolean tank;
    
  
    public NHMPanel(String oo) {
        
        law = true; //tracks down the word
        tank = true;//will control the water level
        expression = false; //will count the trys 
        win = false;//will detect a win 
        lose = false;//will detect a lost 
        
        hangMann = false;//will display confetti
        
        secondCounter = 0; //will increase the water level
        
        water = 0; //water level will start low 
        
        ccounter = 0;//the counter will go up everytime the user guess a word
        
        theWord = oo; // will choose a word
        
        howMany = new char[theWord.length()]; //the length of the word will be 
                                                //string
        
        comp = new String[theWord.length()];
        
        
        for(int t = 0 ; t < howMany.length; t++){//will look at the letters of 
                                                 //the word
            
            howMany[t] = theWord.charAt(t);//will get the letter
            
            comp[t] = ""+ howMany[t];//will store the letter 
            
        }

        
        this.setLayout(new BorderLayout());
        
        r = new JButton[28]; //28 buttons on the right 
        
        d = new JButton[theWord.length()];
        
        
        Color newC = new Color(218,247,166); //light green 
        
        w = 65;
       
        g ="";
        
        
        /////////
       
        for(int i = 0; i < r.length; i++){ //depending on the length of the word
                                           //that will be the length of how many
                                           //buttons are created
            
            x= (char) w;
             
            g = ""+x;
           
            r[i] = new JButton(g);
            r[i].setBackground(newC);//the background will be light yellow
            r[i].setOpaque(true);
            r[i].addActionListener(this);
            
            w++;
             
        }
        
        
        for(int ii = 0; ii < theWord.length(); ii++){
            
           // le = theWord.charAt(ii);
            
            //gf = "" + le;
            
            
            
            sd = new String[theWord.length()];
            cv = new char[theWord.length()];
            
            
            for(int l = 0; l < theWord.length(); l++){
                
                cv[l] = theWord.charAt(l); //will look at the letter guess 
                
                sd[l] = "" + cv[l];
                
                
            }
            
            Color qw = new Color(247,220,111);
            
            d[ii] = new JButton("_");//the black button on the south will 
                                     //be the place where the letter guess will                                       //be place if the letter is in the word
            d[ii].setBackground(qw);
            d[ii].setOpaque(true);
            
        }
        
       // left = new JPanel();
       // middle = new JPanel();
        right = new JPanel();
        bottom = new JPanel();
        
        bottom.setLayout(new GridLayout(1,d.length));
        right.setLayout(new GridLayout(7,4));
        
        
        for(int ii = 0; ii < d.length; ii++){
             bottom.add(d[ii]);
        }
        
        
        for(int ii = 0; ii < r.length; ii++){
            right.add(r[ii]);
        }
        
        right.setPreferredSize(new Dimension(250, 400));
        bottom.setPreferredSize(new Dimension(50, 40));
        
      
        this.add(right, BorderLayout.EAST);//the letter buttons are place on the                                              right of the window
        this.add(bottom, BorderLayout.SOUTH);   
     
    }
    
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        int w = getWidth();
        int h = getHeight();
        
        
        if(law == true)  {
            g.drawString("Guess the Word / The word is a Java term",255,20);
        
        }
        
        
        
        
        g.setColor(Handy.randomColor());
        
        g.drawOval(283+55,h/2-150,80,80);//head
        
        g.setColor(Handy.randomColor());
        g.drawLine(283+55+40,h/2-70,283+55+40,320);//body
        g.setColor(Handy.randomColor());
        g.drawLine(283+55+40,320,283+55+10,380);//left leg
        g.setColor(Handy.randomColor());
        g.drawLine(283+55+40,320,283+55+10+55,380);//right leg
        
        g.setColor(Handy.randomColor());
        
        g.drawLine(283+55+40,210,283+55+10+65,270);//right arm
        
        g.setColor(Handy.randomColor());
        g.drawLine(283+55+40,210,283+55+10,270);//left arm
        
        
       
        
        if(expression == true){
        
         g.drawLine(283+55+40-20  ,h/2-130  ,283+55+40-10  ,h/2-110   );
                                                                     //left eye
                 
         g.drawLine(283+55+40-10  ,h/2-130  ,283+55+40-20  ,h/2-110   );
                                                                     //left eye
         
         
         
        g.drawLine(283+55+40+10  ,h/2-130  ,283+55+40+20  ,h/2-110   );
                                                                     //right eye
        
        g.drawLine(283+55+40+20  ,h/2-130  ,283+55+40+10  ,h/2-110   );
                                                                     //right eye
        
        g.fillOval(283+55+32,h/2-100,20,20);//mouth

       
         
        }
        
       
        
        Color fff = new Color(124,214,241,100);
        
        g.setColor(fff);
        
        int counter;
        
        counter = 328;
        
        
        for(int r = 0; r < water; r++){ //the water level will be control 
        
            g.fillRect(283,counter,190,65);
        
            counter = counter - 68;
        
        }
        
        if(tank == true){
            
            g.setColor(Color.BLACK);
        
            g.drawLine(283,328+65,283,60);//left line for water tank
            g.drawLine(283+190,328+65,283+190,60);//right line for water tank
            g.drawLine(283,328+65,283+190,328+65);//bottom line for tank 
        
         
        
        }
        
        
        g.setFont(Handy.plainFont);
         
        if(win == true){ // if the user wins display win panel 
        
        
        
            
            for(int a = 0; a < 200; a++){
               g.setColor(Handy.randomPastel()); //random colors 
               g.fillOval(Handy.randomInt(0,w), //random circles throughout the                                                  Window
               Handy.randomInt(0,h),20,20);
            }
            
            g.setColor(Color.BLACK);
       
            
            g.drawString("WIN/ ", w/2+100,h/2);
            
            g.drawString("The word was " + theWord,w/2+155,h/2);
            
            for(int k = 0; k < theWord.length(); k++){
                d[k].setBackground(Color.WHITE);
            }
        
        }
        
        Color losse = new Color(0,0,0,50);
        if(lose == true){
            
           this.setBackground(losse);
           
           Color z = new Color(255,255,255);
           
           g.setColor(z);
           
           g.drawString("You Drown/ ", w/2+40,h/2); //the game will be over 
           
           
           g.drawString("The word was " + theWord, w/2+155,h/2);
        }
        
        
        if(hangMann == true){
            
            
           g.drawArc(283+55+21,h/2-115, 40,30,0,-180 ); //smile
     
           g.fillOval(283+55+18  ,h/2-130  , 15  ,15  ); //left eye
     
     
           g.fillOval(283+55+48  ,h/2-130  , 15  ,15  ); //right eye 
        }
        
        
        
    }
    
    
    public void actionPerformed(ActionEvent m) {

        Object t = m.getSource();
        
        String tt = m.getActionCommand();
        
        if(t instanceof JButton) {
        
        
            ((JButton)t).setVisible(false);
            
            
            
            for(int i = 0; i < theWord.length(); i++){
                
               if(comp[i].equals(tt)) {//will conpare the quess letter to the                                           string
                  
                    d[i].setText(tt);
                    //if(ccounter < 2) { 
                    ccounter++;
                        
                    secondCounter++; //the water will go up
                 
                   
               } 
                
            }
          
        }
        
        
        if(ccounter == 0){
                
            water++;//the water will go up once again 
            repaint();//will make the new drawing 
            
        } else if( ccounter >= 1){
                
           ccounter = 0; // will control the win 
                        
        }
         
         
        if (secondCounter == theWord.length() && water < 5){ // will control the                                                             //lost 
            
            win = true;
            
            water = 0; // water level start at 0
            
            tank = false;
            
            hangMann = true; // the man is shown 
            law = false;
            right.setVisible(false);
            bottom.setVisible(false);
            
            repaint();
            
        }
        
        if(water == 5){ // if the water level is 5 than user loses 
            
            law = false;
            lose = true;
            
            expression = true; // will show the background black
            
            right.setVisible(false);// will take the buttons out 
            
            bottom.setVisible(false);
            
            repaint();
            
            
        }
        
        
         
         if( t instanceof JButton){
       
            ((JButton)t).setBackground(Color.RED); // the button will become red
            
            ((JButton)t).setOpaque(true);
            
            
            
         }
         
         
         
         
    }

}
