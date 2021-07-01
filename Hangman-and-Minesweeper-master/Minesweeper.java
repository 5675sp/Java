//Sergio Perez
//Computer Science 124
//A minesweeper game that dis[plays a grid of buttons and few of the buttons are bomnbs. The user in the gam is able to change the level of difficulty of the game.
//-Last Time Modified: 05/7/16 at 1:31PM*/


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;


public class Minesweeper {

    public static void main(String[] args){
    
        JFrame first;
        //JFrame second;
        MSPanel one;
       // MSPanel two;
        
        String l;
        
        System.out.println("Enter your name");
        
        l = TextIO.getlnString();
        
        one = new MSPanel(6,6,"Good Luck "+l);//the minesweeper game will have 6                                                 rows and 6  columns 
        //two = new MSPanel(4,4,"Good Luck "+l);
        
        first = new JFrame();
       // second = new JFrame();

    
        first.setLocation(10,100);
        first.setSize(1400,1000);
        first.setResizable(true);
        first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        first.setContentPane(one);//sets the game on the frame 
        first.setVisible(true);
        
        
        // second.setLocation(100,100);
        // second.setSize(600,600);
        // second.setResizable(true);
        // second.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // second.setContentPane(two);//sets the game on the frame 
        // second.setVisible(true);
    
    }


}
