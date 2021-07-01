//Sergio Perez
//Computer Science 124
//A Hangman program that displays a hangman game were you need to quess computer science words 
//Require Files: Handy.java/LButton.java/
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
  

class MGMSPanel extends JPanel implements ActionListener {

    private LButton[][] o;//the buttons at the middle
    private JPanel second;//the panel that will hold the lbuttons
    
    private int random;
    private int random2;
    private int win;
    private int rr;
    private int cc;
    private int yeah;
    private int where;
    private int pleasec;
    private int howMany;
    
    private int when;
    private int wwhere;
    
 
    public MGMSPanel(int r, int c) {
    
    

        
        rr = r; //stores the number of rows so it could be use in action                        listener
        cc = c;//stores the number of colums so it could be use in action                        listener
        
        when = 0;
        wwhere = 0;
        howMany = 0;
        pleasec = 0;
        yeah = 0;
        win = 0;
        where = 0;
        
        this.setLayout(new BorderLayout());
        
        second = new JPanel();
        second.setLayout(new GridLayout(r,c));//sets rows and columns
    
        o = new LButton[r+2][c+2];//creates the LButtton
        
        for(int i = 0; i < o.length; i++){
            
            for(int ii = 0; ii < o[0].length; ii++) {
            
                o[i][ii] = new LButton(i,ii);//creates the LButtons
                o[i][ii].setAd(false);
                o[i][ii].setBackground(Color.WHITE);
                o[i][ii].addActionListener(this);//the bottons will listen when                                                     click
                
            }
        }
         
        howMany = ((r)*(c))/7; //determines how many bombs are created
        
        for(int w = 0; w < howMany; w++){//goes through the buttons and picks a 
                                          //a random button to place the bomb
                
            //win++;//will track down how many bombs will be
               
            random =  Handy.randomInt(1,r);
            random2 = Handy.randomInt(1,c);
     
            if( o[random][random2].isAd() == false){//sets the bombs
                o[random][random2].setAd(true);
            
              
            }else if(o[random][random2].isAd() == true){//if theirs already a                                                             bomb then look for                                                               another available spot
            
                w--; //looks for available spot 
            }
        }
             
        for(int i = 1; i < o.length-1; i++){ //adds the buttons to panel
                 
            
            for(int ii = 1; ii < o[0].length-1; ii++) {

                second.add(o[i][ii]);
            
            }
        }
        
        this.add(second, BorderLayout.CENTER);//all the buttons will go in the                                                   center
    
    }
    
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        //smile face if the user wins 
        g.setColor(Color.YELLOW);
        g.fillOval(getWidth()/2-250,getHeight()/2-250  ,500,500);
        g.setColor(Color.BLACK);
        g.drawArc(500,370, 300,200,0,-180 ); //smile
        g.fillOval( getWidth()/2-185,getHeight()/2-190,130,130);//left eye
        g.fillOval( getWidth()/2+60,getHeight()/2-190,130,130);//left eye
        
        g.drawString("You Won / Play a new Gamw",getWidth()/2-70,getHeight()/2);
        
    }

    public void actionPerformed(ActionEvent m) {
        
        Object t = m.getSource(); //gets what button was press 
        String tt = m.getActionCommand();//gets the string on the button 
        
        int r = ((LButton)t).getRow();//gets the row number of a button
        int c = ((LButton)t).getColumn();//gets the col of a number 
        
        
        ((LButton)t).setEnabled(false);//when the button is press the button                                               will no longer work
        
        
        int counterr = 0;
        
        yeah = ((rr)*(cc))-howMany; //knows how many bombs are 
        
        if( ((LButton)t).isAd() == true){//if bomb then display all the buttons                                            with a black background 
            
            for(int ry = 0; ry < o.length; ry++){
                
                for(int ch = 0; ch < o[0].length; ch++){
                
                    if(o[ry][ch].isAd() == true){ //if bomb display thelocation
                        
                        o[ry][ch].setBackground(Color.BLACK);
                        o[ry][ch].setLabel("BOMB");
                   
                    }
                    if(o[ry][ch].isAd() == false){//if not a bomb just set its                                                         visibility false
                    
                        o[ry][ch].setVisible(false);
                    }
                }
            }
        }
        
        
        if( t instanceof LButton){//counts neighbors
            
            if( ((LButton)t).isAd() == false){
                
                pleasec++;
            
                if (o[r-1][c-1].isAd() == false) { //top left 
                    counterr++;
                }
                if (o[r][c-1].isAd() == false ) { //middle left 
                    counterr++;
                }
                if(o[r+1][c-1].isAd() == false) {//bottom left
                    counterr++;
                }
                if (o[r-1][c].isAd() == false) {//top middle
                    counterr++;
                }
                if(o[r+1][c].isAd() == false) {//bottom middle
                    counterr++;
                }
                if(o[r-1][c+1].isAd() == false) {//top right
                    counterr++;
                }
                if(o[r][c+1].isAd() == false) {//middle right
                    counterr++;
                }
                if(o[r+1][c+1].isAd() == false) {//bottom left 
                    counterr++;
                }
                
                counterr = 8 - counterr;
                    
                ((LButton)t).setLabel(""+counterr);                    
                   
            }
            
        }
        
        if(pleasec == yeah) {// if the user press all non bomb buttons the panel          in the middle will be set disable and the use will se a happy face 
            
            for(int ry = 0; ry < o.length; ry++){
                        
                for(int ch = 0; ch < o[0].length; ch++){
                            
                    second.setVisible(false);
                    o[ry][ch].setVisible(false);
            
                }
            }
        }
        
        
    }

}
