//Sergio Perez
//Computer Science 124
//A HMPanel that displays a hangman game
//Require Files: Handy.java/
//-Last Time Modified: 05/7/16 at 1:31PM*/

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class HMPanel extends JPanel implements ActionListener {

    private NHMPanel one;
    private JButton first;//the button at the bottom that starts a game 
    private String what;//takes the word and that will be the word to guess 
    private String[] words;//all the words will be store in the word variable 
    
    public HMPanel(){
        
       words = new String[] 
    
       {"CONTINUE","DOUBLE","BOOLEAN ","FINALLY","PRIVATE","FLOAT","CHAR",
       "BREAK","CATCH","EQUALS","CLASS","JAVA","ELSE","BYTE","IMPORT","SUPER",
        "SUBCLASS","WHILE","RETURN","COMPUTER","SCIENCE","HOBART","SMITH",
        "ORACLE","SWITCH","NEW","AIR","PEN","LAW","MATH"};


       what = words[Handy.randomInt(0,29)];//a number between  0 - 29


       this.setLayout(new BorderLayout());


       one = new NHMPanel(what); // the hangman panel 

       add(one,BorderLayout.CENTER);//the Hangman panel will go in the middle

       first = new JButton("New Game");//the button at the south 


       Color f = new Color(241,148,138);//light blue 

       first.setBackground(f);
       first.setOpaque(true);

       add(first,BorderLayout.SOUTH);//the button is place at the bottom

       first.addActionListener(this);
        
       
    }
    
    public void actionPerformed(ActionEvent r){
        
        one.setVisible(false); //when the button at the bottom is click then 
                              //the panel at the middle is click
        this.remove(one);//panel is remove
        
        
        what = words[Handy.randomInt(0,29)];//new word
        one = new NHMPanel(what);//create the hangman panel
        
        this.add(one,BorderLayout.CENTER);//the new hangman game is place at the
                                          //the middle
        this.validate();
        
    }


}
