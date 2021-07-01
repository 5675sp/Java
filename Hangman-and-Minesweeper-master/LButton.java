/*Sergio Perez
 *Computer Science 124
 *LButton
  Last modified:04/22/16
*/

import javax.swing.JButton;

public class LButton extends RCButton {

    private boolean ad;
    
    private boolean nextGeneration;
    

    
    public boolean isAd() {///returns true or false 
        
        return ad;
       
    }
    
    
    public boolean isNextGeneration () {//returns true or false 
       
       return nextGeneration;
        
    }
    
    
    public void setAd(boolean x) {///the boolean can be set true or false
        
       ad = x;
        
    }
    
    public void setNextGeneration(boolean v) {//sets the button life or death 
        
        nextGeneration = v;
        
    }
    
    public LButton(int r,int c){ ///constructor 
        
        super(r,c);
    }
    

}
