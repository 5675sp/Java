//Sergio Perez
//Computer Science 225
//A spell checker program that checks if a word is spell correctly. The program uses 5 subroutines that allow for a more presice
//grammer correctness
//The Spellcheck.java program uses the TextIO.java and WordList.java class.

public class Spellcheck {
    
    public static void main(String[] args){
        
        boolean word; //checks if the word from the use us in the list 
        
        WordList s = new WordList(); // allows for the use of contains inside the WordList.java class 
        
        System.out.println("Welcome to Sergio's spellchecker!");
        System.out.println("Please insert your word that you will love to spellcheck.");
        
        String theWord;
        
        theWord = TextIO.getlnString(); /// gets the word from the user 
        
       
        for(int i = 0; i < theWord.length(); i++){ /// checks if theirs any upper case letters 
            
            if (theWord.charAt(i) >= 'A' && theWord.charAt(i) <= 'Z' ) { // changes upper case letters to lower case letters 
                
               theWord = theWord.toLowerCase(); /// executes the cahnge of upper to lower case letters 
         
            }
        }
        
         
        StringBuffer trans = new StringBuffer(theWord);
        
        System.out.println("In lower case format: "+theWord);
        
        word = s.contains(theWord);
        
        String test;
        
        test = "";
       
        String get;
       
        get = "";
       
       
        System.out.println("Similar words that are in the list:");
        System.out.println("");
        System.out.println("");


        /////////11111111111111111////////////////////////
               
        get = trans.toString(); //converts the stringbuffer to a regular string 

        if (s.contains(get) == false){  //checks if the word in the list, if not in the list it will check for grammer correctness 

            System.out.println("-------------------------");
            System.out.println("OutPut of subroutine 1");
            System.out.println("-------------------------");
   
            for(int i = 0; i < theWord.length(); i++) { // goes around depending on the legth of the word 
        
                trans.deleteCharAt(i); ///  deletes one character at a time in the original word 
       
                get = trans.toString(); // convers the new change to a regualr string 
        
  
                if(s.contains(get) == true) { // checks if the new converstion is in the list of words 
            
                    System.out.println(get);
        
                }
        
                trans.replace(0, theWord.length(), theWord);
        
        }
      
        
        /////end of 111111111111111111/////////////
        
        
        ////////////222222222222222222222
        
        
        System.out.println("-------------------------");
        System.out.println("OutPut of subroutine 2");
        System.out.println("-------------------------");
        
        char testl;
    
        trans = new StringBuffer(theWord);
        
        get = trans.toString();
    
        testl = 'a'; /// will allow for the implementation of the letters in the alphabet 
    
        for(int i = 0; i <= theWord.length(); i++){

            testl = 'a';
            
            trans = new StringBuffer(theWord);
       
            for(int ii = 0; ii < 26; ii++){
        
                trans.insert(i, testl); // adds a new character to the original word
                
        
                get = trans.toString(); // converts the new word into a string 
        

                if(s.contains(get) == true) {// checks if the new convertion is in the list 
            
                    System.out.println(get);
            
                }
        
     
        
                trans = new StringBuffer(theWord);
        
                testl++; // changes the letter to another one exameple: a  to b 
        
        
            }
        }
    
      //////////////////////end of 2222222222222222
        
        System.out.println("-------------------------");
        System.out.println("OutPut of subroutine 3");
        System.out.println("-------------------------");  
        
        
        
        ////////3333333333333333333333//////////
                
        trans = new StringBuffer(theWord);
    
        get = trans.toString();
    
        testl = 'a';

        for(int ii = 0; ii < theWord.length(); ii++){ //goes around the loop depending on the legth of the word 

            testl = 'a'; // the original letter will start at a 

            trans = new StringBuffer(theWord);
    
            for(int i = 0; i < 26; i++) { // changes every letter in the original word everytime//  A - Z
        
                trans.setCharAt(ii,testl); // changes the letter depending on the value of testl
        
                get = trans.toString();
        
        
                if(s.contains(get) == true) { // checks if the new modification of the word is in the list 
            
                    System.out.println(get); // prints the new convertion if is in the list 
            
                }
         
        
                testl++;
    
                trans = new StringBuffer(theWord);
    
            }
    
      
        }
    
        //////////end of 333333333333333333///////

    
        //////////44444444444/////////////
        System.out.println("-------------------------");
        System.out.println("OutPut of subroutine 4");
        System.out.println("-------------------------");
     
        char one;
        char two;
     
     
        trans = new StringBuffer(theWord);
    
        get = trans.toString();
    
    
    	for(int i = 0; i < theWord.length()-1; i++) {  // goes around the word// the times it goes around is determine by the length
            
            one = get.charAt(i); // gets the first letter on the word
            two = get.charAt(i+1);//gets the second letter on the word 
         
            trans.setCharAt(i, two); // puts the first letter in the word in the seconf char of the \ word 
            trans.setCharAt(i+1, one); // puts the second letter on the word on the first char of the orinal word 
            
            get = trans.toString(); //converts the strignbuffer to a string so the word can be check on the wordlist.java class 
             
            if(s.contains(get) == true) { // checks if the new modification of the word is on the list 
            
            
                System.out.println(get);
            
            }
         
            trans = new StringBuffer(theWord);
        
            get = trans.toString();
            
        }
     
        ////////end of 444444444444444////////
     
        //////////////////555555555555///////////////////////////////555555555555/////////////
    
        System.out.println("-------------------------");
        System.out.println("OutPut of subroutine 5");
        System.out.println("-------------------------");
    
    
        trans = new StringBuffer(theWord);
        
        get = trans.toString();
    
        String testt;
    
        testt = theWord;
    
        String firstPart;
    
        firstPart = "";
    
    
        for(int i = 0; i < theWord.length(); i++) {
   
            testt =  get.substring(i); // splits the second half of the word 
   
            firstPart = get.substring(0,i); // splits the first half of the word 
   
    
            if(s.contains(testt) == true && s.contains(firstPart) == true ) { // if the new two words are in the list it will be printed 
            
                System.out.print(firstPart); // first part of the missspell word 
                System.out.print(" ");
                System.out.println(testt); // second part of the misspell word 
            
            }
         
         
        }
    
        } else {
    
            System.out.println("The word " + theWord + " is spell correctly " );
    
        }
    
       
    }


}