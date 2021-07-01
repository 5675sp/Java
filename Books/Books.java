/**
*This programs reads books. And in the process calcualtes the amount of unique words it holds compare to other seven books.
NOTE: When running this program you will need to change line 133 (Books.java)to represent the location of the books folder
 *@author Sergio Perez 
 *@version 1.0
 */
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.TreeSet;

public class Books {
     
    private static ArrayList<TreeSet<String>> sets;
    
    public static void main(String[] args) {
        
        sets = new ArrayList<TreeSet<String>>();
        
        for(int i = 0; i < 8; i++){
           sets.add(readFilewords(bookFiles[i]));
           System.out.println();            
        }
               
        Words(sets); // find the different words use among all sets 
        commonWords(sets);// find the common words in all eight books
        
        for(int i = 0; i < 8; i++){
         System.out.println("Book " + bookFiles[i] +" has " + DifferentAmongAll(i) + " words that are not found in the rest of the books.");
        }
            
        for(int i = 0; i < 8; i++){
            for(int ii = 0; ii < 8; ii++){
                
                //System.out.println(compareEachPair(i,ii));
                System.out.print("Book " + i + " and  book " + ii +" have ");
                System.out.print(compareEachPair(i,ii));
                System.out.println(" words in common.");
            }
            
        }

        
    }
    /**
	 *A subroutine that finds the different words use among all sets
	 *@param ArrayOfsets takes an ArrayList of TreeSets
	 */
    public static void Words(ArrayList<TreeSet<String>> ArrayOfsets){
        
        TreeSet<String> DifferentOnEightBooks = new TreeSet<String>();
         
         for(TreeSet<String> test : ArrayOfsets){
            DifferentOnEightBooks.addAll(test);
         }
        
        System.out.println("How many different words, in total, are used in all eight books combined?: " + DifferentOnEightBooks.size());
    }
    /**
	 *A subroutine that find the common words in all eight books.
	 *@param bookLocation takes an int 
	 */
    
    public static int DifferentAmongAll(int bookLocation){
       
        int counter = 0;
        
        TreeSet<String> unique = new TreeSet<String>();
        
        for(TreeSet<String> book : sets){
            
            if(!(counter == bookLocation)){
                
                  unique.addAll(book);
                
            }
            
            counter++;
            
        }
        
        TreeSet<String> pickBook = new TreeSet<String>();
        pickBook.addAll(sets.get(bookLocation));
        pickBook.removeAll(unique);
        return pickBook.size();
    }
    
     /**
	 *A subroutine that find compares two words and determines the similarity of it. 
	 *@param firstBook takes an int
	 *@param secondBook takes an int 
	 */
    public static int compareEachPair(int firstBook, int secondBook){
    
        TreeSet<String> first = new TreeSet<String>( sets.get(firstBook) );
        first.retainAll(sets.get(secondBook));
        
        return first.size();
        
    }
    
    /**
	 *A subroutine that find compares two words and determines the similarity of it. 
	 *@param commonWordsWriten takes an arraylist of treesets.
	 *
	 */
    public static void commonWords(ArrayList<TreeSet<String>> commonWordsWriten){

        TreeSet<String> setTest = new TreeSet<String>();
         
        int counter = 1;
        for( TreeSet<String>  neno : sets){
               if(counter == 1){
                    setTest.addAll(neno);
                }
               else {
                    setTest.retainAll(neno);
               }
               counter++;
         }
            
          System.out.println("How many words are common to all eight books?: " + setTest.size());
          String longestString = "";
            
          for (String longestWord : setTest){
               if(longestWord.length() > longestString.length()){
                    longestString = longestWord;
               }
          }
          
          System.out.println("The Longest Word is: " + longestString);
     }
    
    static final String DIRECTORY = "/Users/sergiodavidperez/Desktop/perez-225/Lab8/books/"; // change working directory as need it 
    
    static final String[] bookFiles = {
        DIRECTORY + "20000-leagues.txt",
                  // 20,000 Leagues Under the Sea (Jules Verne)
        DIRECTORY + "alice-in-wonderland.txt",
                  // Alice in Wonderland (Lewis Carroll)
        DIRECTORY + "bleak-house.txt",
                  // Bleak House (Charles Dickens)
        DIRECTORY + "hamlet.txt",
                  // Hamlet (William Shakespeare)
        DIRECTORY + "huckleberry-finn.txt",
                  // Huckleberry Finn (Mark Twain)
        DIRECTORY + "pride-and-prejudice.txt",
                  // Pride and Prejudice (Jane Austin)
        DIRECTORY + "something-new.txt",
                  // Something New (P.G. Wodehouse)
        DIRECTORY + "time-machine.txt"
                  // The Time Machine (H.G. Wells)
    };

    public  static TreeSet<String>  readFilewords(String name){
    
        TextIO.readFile(name);
        String word = readNextWord();
        TreeSet<String> words = new TreeSet<String>();
        TreeSet<String> duplicates = new TreeSet<String>();

        int numberOfwords = 0;

        while(word != null){
            word = word.toLowerCase();
            
            boolean added = words.add(word);
            
             if(added != true){
                duplicates.add(word);
            }
            word = readNextWord();
            numberOfwords++;
            
        }
  
        float percentage =      (words.size()) - (duplicates.size())   ;
        float divide =   (percentage / words.size() ) * 100;
        System.out.println("From the file: " + name);
        System.out.println("The total of words read form the file,counting duplicates: " + numberOfwords);
        System.out.println("The number of different words found in the file " + words.size());
        System.out.println("Number of words that occur only once: " + (  (words.size()) - (duplicates.size()) ) + "  " + divide );
     
        return words;
    }
    
    
    
  

/**
 * Read the next word from TextIO, if there is one.  First, skip past
 * any non-letters in the input.  If an end-of-file is encountered before 
 * a word is found, return null.  Otherwise, read and return the word.
 * A word is defined as a sequence of letters.  Also, a word can include
 * an apostrophe if the apostrophe is surrounded by letters on each side.
 * @return the next word from TextIO, or null if an end-of-file is encountered
 */
    private static String readNextWord() {
        char ch = TextIO.peek(); // Look at next character in input.
        while (ch != TextIO.EOF && ! Character.isLetter(ch)) {
            TextIO.getAnyChar();  // Read the character.
            ch = TextIO.peek();   // Look at the next character.
        }
        // Either the next char is a letter, or we are at end-of-file.
        if (ch == TextIO.EOF) // Encountered end-of-file
            return null;
            // At this point, we know that the next character is a letter, so read a word.
            String word = "";  // This will be the word that is read.
            while (true) {
                word += TextIO.getAnyChar();  // Append the letter onto word.
                ch = TextIO.peek();  // Look at next character.
                if ( ch == '\'' ) {
                    // The next character is an apostrophe.  Read it, and
                    // if the following character is a letter, add both the
                    // apostrophe and the letter onto the word and continue
                    // reading the word.  If the character after the apostrophe
                    // is not a letter, the word is done, so break out of the loop.
                TextIO.getAnyChar();   // Read the apostrophe.
                ch = TextIO.peek();    // Look at char that follows apostrophe.
                if (Character.isLetter(ch)) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();  // Look at next char.
                }
                else
                
                break;
            }
        
            if ( ! Character.isLetter(ch) ) {
                // If the next character is not a letter, the word is
                // finished, so break out of the loop.
                break;
            }
            // If we haven't broken out of the loop, next char is a letter.
        }
        
        return word;  // Return the word that has been read.
    }
    
}//end of Books class

