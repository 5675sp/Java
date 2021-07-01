/**
 *A java program call SearchTimes that calculates the timing for certain methods to sort a list of words.
 *@author Sergio Perez
 *@version 1.0
 */
public class SearchTimes {

    public static void main(String[] args){
    
    WordList unsorted = new WordList();
    
    SortedWordList sorted = new SortedWordList();
    
    
    String names[];
    
    names = new String[1000];    
    
    
    int sizeOflist = unsorted.size();
    
    
    for(int ii = 0; ii < 1000; ii++){
        
        SortedWordList.randomInt(0,sizeOflist);
        names[ii] = unsorted.get(ii);
        
    }
    
    
    long startTime, endTime;
    double elapsedTimeInSeconds;
        
    
    startTime = System.nanoTime();
    
    for(int ii = 0; ii < 1000; ii++){
        
        unsorted.contains(names[ii]);
        
    }
      
    endTime = System.nanoTime();
    
    elapsedTimeInSeconds = (endTime - startTime) / 1.0e9;
    
    System.out.println(""+ elapsedTimeInSeconds);
    
    
    
    
    
    
    for(int ii = 0; ii < 1000; ii++){
        
        SortedWordList.randomInt(0,sizeOflist);
        
        names[ii] = sorted.get(ii);
        
    }
        
   long startTime2, endTime2;
   double elapsedTimeInSeconds2;
        
    
    startTime2 = System.nanoTime();
    
    for(int ii = 0; ii < 1000; ii++){
        
        sorted.contains(names[ii]);
        
    }
      
    endTime2 = System.nanoTime();
    
    elapsedTimeInSeconds2 = (endTime2 - startTime2) / 1.0e9;
    
    System.out.println(""+ elapsedTimeInSeconds2);
    
    
    }



}