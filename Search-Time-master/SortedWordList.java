import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
import java.util.Arrays;

/**
 *  Represents a fixed list of words read from a resource file, "unsorted_words.txt".
 *  The constructor locates the resource and reads the words from the file.  The file
 *  is assumed to contain one word per line, and the words are assumed to be in lower
 *  case.  The words are assumed to be in alphabetical order.
 */

public class SortedWordList {

   private final String[] words;  // the list of words

   /**
    *  The constructor reads the list of words from the resource file "unsorted_words.txt".
    *  If an error occurs while attempting to load the words, an exception is thrown.
    *  @throws IllegalStateException if any error occurs.  The most likely error is that
    *  the resource file can't be located.
    */
   public SortedWordList() {
      try {
         URL listLocation = WordList.class.getClassLoader().getResource("unsorted_words.txt");
         Scanner in = new Scanner( listLocation.openStream() );
         ArrayList<String> wordList = new ArrayList<String>();
         while (in.hasNextLine())
            wordList.add(in.nextLine());
            words = wordList.toArray(new String[] {});
            Arrays.sort(words);
       
            in.close();
      }
      catch (Exception e) {
         throw new IllegalStateException("Can't load list of words from file 'unsorted_words.txt'");
      }
   }
   
   /**
    * Test whether a given word occurs in the list.
    * @param lowerCaseWord the word that will be searched for in the list.  The word is assumed
    * to be in lower case.  Since all the words in the list are lower case, the return value
    * will be false for any word that does not consist entirely of lower case letters.
    * @return true if lowerCaseWord is found in the list, false if not.
    */
   public boolean contains(String lowerCaseWord) {
      /*for (String s : words) {
         if (s.equals(lowerCaseWord))
            return true;
      }
      return false;*/
      
      
      if(Arrays.binarySearch(words, lowerCaseWord) == -1) {
      return false;
      }
      return true;
      
    
      
      
   }
   
   /**
    *  Returns the number of words in the list.
    */
   public int size() {
      return words.length;
   }
   
   /**
    * Returns one of the words in the list.
    * @param index the index in the list of the word to be returned.
    * @throws ArrayIndexOutOfBoundsException if index is less than zero or is greater than
    * or equal to the number of words in the list.
    */
   public String get(int index) {
      return words[index];
   }
   
   
   public static int randomInt(int intF, int intS){
      
      int random;
      random = (int)(((intS-intF)+1)*Math.random()+intF);
      
      return random;
      
      
      
   }
   

}