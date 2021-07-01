/*Sergio Perez
 *Computer Science 124
 *A RCButton of buttons that who instance s contain private variables row and column and a public methods
 *getColumn() and getRow().
Last modified:04/22/16
*/
import java.awt.Button;

public class RCButton extends Button {

    private int row;
    private int col;

    
    public int getRow() { //will return a button 
        
        return row;
    }
    
    public int getColumn() {//will return a button 
        
        return col;
    }
    
    public RCButton (int r,int c){ //takes two integer parameters and initialize                                     "row" and "col"
      
        row = r;
        col = c;
    }
    
    //a  non-parameter constructor but r and c are initialize to 0 
    public RCButton (){
        this(0,0);
    }
}
