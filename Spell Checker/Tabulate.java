//Sergio Perez
//Computer Science 225
// A small program title Tabulate class so that instead of just printing out f(1), it will
//print out a table of values of f(x) for values of x increasing by tenths
//from 0.0 to 10.0 (that is, for x = 0.0, 0.1, 0.2, 0.3, ..., 9.9, 10.0).

public class Tabulate {

   public static void main(String[] args) {
   
      System.out.println("Enter a function of x..."); // the user if ask to input a value 
      System.out.print("f(x) = ");
      
      String definition = TextIO.getln(); // the x if obtain 
   
      Expr function = new Expr(definition);
      
      for(double i = 0;  i<= 10; i+=.1 ){ // the for loop is executed depending on the value of 10 
        
         System.out.printf("f(1) = %12.3f \n", function.value(i)); /// the chart if printed 
      }
   
   
   
   }
}