//Sergio Perez
//Computer Science 124
//A Hangman program that displays a hangman game were you need to quess computer science words 
//Require Files: Handy.java/HMPanel.java/LButton.java/NHMPanel.java/
//RCButton.java
//-Last Time Modified: 05/7/16 at 1:31PM*/

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;


public class Hangman {


    public static void main(String[] args){
        

        JFrame first;
        JFrame second;
        
        first = new JFrame();
        second = new JFrame();
        
        HMPanel one;
        HMPanel two;
        
        
        one = new HMPanel();//The hangman gridpanel

        first.setLocation(100,100);
        first.setSize(1000,500);
        first.setResizable(true);
        first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        first.setContentPane(one);///the hangman grid is place in the frame 
        first.setVisible(true);
        
        
        // two = new HMPanel();//The hangman gridpanel

        // second.setLocation(Handy.randomInt(1,300),Handy.randomInt(1,800));
        // second.setSize(1000,500);
        // second.setResizable(true);
        // second.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // second.setContentPane(two);///the hangman grid is place in the frame 
        // second.setVisible(true);

    }


}

