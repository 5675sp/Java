/**
 *A program call SortTimes that test the speed of different methods for find a variable in different types of array.
 *@author Sergio Perez and Mark Curiel 
 *@version 1.0
 */
import java.util.Arrays;

public class SortTimes {
    /**
     * Sorts A[0], A[1], ..., A[count-1] into increasing order using insertion sort.
     * @param A  takes an array of type double
     * @param count that takes an interger parameter 
     */
    public static void insertionSort(double[] A, int count) {
        for (int top = 1; top < count; top++) {
            int pos = top;
            double temp = A[top];
            while (pos > 0 && A[pos-1] > temp) {
                A[pos] = A[pos-1];
                pos--;
            }
            A[pos] = temp;
        }
    }

/**
 * Sorts A[0], A[1], ..., A[count-1] into increasing order using insertion sort.
 * @param A takes an array of type string
 * @param count takes an integer 
 */
    public static void insertionSort(String[] A, int count) {
        for (int top = 1; top < count; top++) {
            int pos = top;
            String temp = A[top];
            while (pos > 0 && A[pos-1].compareTo(temp) > 0) {
                A[pos] = A[pos-1];
                pos--;
            }
            A[pos] = temp;
        }
    }

/**
 * Sorts A[0], A[1], ..., A[count-1] into increasing order using selection sort.
 * @param A takes an array of type double
 * @param count takes an integer parameter
 */
    public static void selectionSort(double[] A, int count) {
        for (int top = count-1; top > 0; top--) {
            int maxLoc = 0;
            for (int i = 1; i <= top; i++) {
                if (A[i] > A[maxLoc])
                    maxLoc = i;
                }
                double temp = A[maxLoc];
                A[maxLoc] = A[top];
                A[top] = temp;
            }
    }

/**
 * Sorts A[0], A[1], ..., A[count-1] into increasing order using selection sort.
 * @param A tha takes an array of type string
 * @param count takes an integer variable 
 */

    public static void selectionSort(String[] A, int count) {
        for (int top = count-1; top > 0; top--) {
            int maxLoc = 0;
            for (int i = 1; i <= top; i++) {
                if (A[i].compareTo(A[maxLoc]) > 0)
                    maxLoc = i;
                }
                String temp = A[maxLoc];
                A[maxLoc] = A[top];
                A[top] = temp;
            }
    }



    public static void main(String[] args){
        // Array of Strings, length 1000
        SortedWordList words = new SortedWordList();
        
        String[] identical1;
        String[] identical2;
        String[] identical3;
        //three arrays with similar length. The arrays will be use to test multiple search subroutines 
        identical1 = new String[10000]; // an array of 10000 strings 
        identical2 = new String[10000];// an array of 10000 strings 
        identical3 = new String[10000];// an array of 10000 strings 
    
        int sizeOflist = words.size();
        
         /**
         *Places different words in the string arrays 
         *@throws ArrayIndexOutOfBoundsException if index is greater than 10000
         */
        //random words are place in the three arrays of type string 
        for(int ii = 0; ii < 10000; ii++){
            SortedWordList.randomInt(0,sizeOflist);
            
            identical1[ii] = words.get(ii);
            identical2[ii] = words.get(ii);
            identical3[ii] = words.get(ii);
        }
        
        System.out.println("-----------Array of Strings, length 10000------insertionSort---");
       // calcualtes the time that it takes a certain subroutine to search for a word 
        long startTime, endTime;
        double elapsedTimeInSeconds;
        startTime = System.nanoTime();
        /////////]]]]]]]]]]]]]]]]]]]]]]~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~######////////////////     
        insertionSort(identical1, 10000);
        endTime = System.nanoTime();
        elapsedTimeInSeconds = (endTime - startTime) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds);   
        System.out.println("-----------Array of Strings, length 10000------selectionSort---");
        //initates the time were the search subroutine is initiated 
        long startTime2, endTime2;
        double elapsedTimeInSeconds2;
        
        startTime2 = System.nanoTime();    
        selectionSort(identical2, 10000);
         
        endTime2 = System.nanoTime();
        elapsedTimeInSeconds2 = (endTime2 - startTime2) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds2); 
        System.out.println("-----------Array of Strings, length 10000------Arrays.sort---");
       
        long startTime3, endTime3;
        double elapsedTimeInSeconds3;
    
        startTime3 = System.nanoTime();  
        Arrays.sort(identical3);
        endTime3 = System.nanoTime();
        elapsedTimeInSeconds3 = (endTime3 - startTime3) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds3); 
        
        /////////////////end of string array of length 1000//////////////////////
        
        //array of strings, length 1500
        
        SortedWordList words2 = new SortedWordList();
        
        String[] same1;
        String[] same2;
        String[] same3;
        //three different types of arrays of type string 
        same1 = new String[1500];
        same2 = new String[1500];
        same3 = new String[1500];
    
        int sizeOflist2 = words2.size();
        
        /**
         *Places different words in the string arrays 
         *@throws ArrayIndexOutOfBoundsException if index is greater than 1500
         */
    
        for(int ii = 0; ii < 1500; ii++){
            SortedWordList.randomInt(0,sizeOflist2); // random words are beign produce 
            
            same1[ii] = words2.get(ii);
            same2[ii] = words2.get(ii);
            same3[ii] = words2.get(ii);
        }
        
        System.out.println("-----------Array of Strings, length 1500------insertionSort---");
       
        long startTime4, endTime4;
        double elapsedTimeInSeconds4;
        //identifies the time tha the earch subroutine initated 
        startTime4 = System.nanoTime(); 
        insertionSort(same1, 1500);
         
        endTime4 = System.nanoTime();
        elapsedTimeInSeconds4 = (endTime4 - startTime4) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds4);

        System.out.println("-----------Array of Strings, length 1500------selectionSort---");
       
        long startTime5, endTime5;
        double elapsedTimeInSeconds5;
        
        startTime5 = System.nanoTime(); 
        selectionSort(same2, 1500);
         
        endTime5 = System.nanoTime();
        elapsedTimeInSeconds5 = (endTime5 - startTime5) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds5);
        System.out.println("-----------Array of Strings, length 1500------Arrays.sort---");
       
        long startTime6, endTime6;
        double elapsedTimeInSeconds6;
    
        startTime6 = System.nanoTime(); 
        Arrays.sort(same3);
        endTime6 = System.nanoTime();
        elapsedTimeInSeconds6 = (endTime6 - startTime6) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds6);
          
        /////////end of string array of legth 1500/////////////
        ////////array of type string, length 1800
        SortedWordList words3 = new SortedWordList();
        
        String[] array1;
        String[] array2;
        String[] array3;
        //three arrays of type string with a length of 1800
        array1 = new String[1800];
        array2 = new String[1800];
        array3 = new String[1800];
    
        int sizeOflist3 = words3.size();
        
         /**
         *Places different words in the string arrays 
         *@throws ArrayIndexOutOfBoundsException if index is greater than 1800
         */
    
        for(int ii = 0; ii < 1800; ii++){
            SortedWordList.randomInt(0,sizeOflist3);//random words are being produce 
            
            array1[ii] = words3.get(ii);
            array2[ii] = words3.get(ii);
            array3[ii] = words3.get(ii);
        }
        
        System.out.println("-----------Array of Strings, length 1800------insertionSort---");
       
        long startTime7, endTime7;
        double elapsedTimeInSeconds7;
        
        startTime7 = System.nanoTime(); 
        insertionSort(array1, 1800);
            
        endTime7 = System.nanoTime();
        elapsedTimeInSeconds7 = (endTime7 - startTime7) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds7);
    
        System.out.println("-----------Array of Strings, length 1800------selectionSort---");
       
        long startTime8, endTime8;
        double elapsedTimeInSeconds8;
        
        startTime8 = System.nanoTime(); 
        selectionSort(array2, 1800); 
        endTime8 = System.nanoTime();
        elapsedTimeInSeconds8 = (endTime8 - startTime8) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds8);
        System.out.println("-----------Array of Strings, length 1800------Arrays.sort---");
       
        long startTime9, endTime9;
        double elapsedTimeInSeconds9;
        
        startTime9 = System.nanoTime(); 
        Arrays.sort(array3); 
        endTime9 = System.nanoTime();
        elapsedTimeInSeconds9 = (endTime9 - startTime9) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds9);
        //////end of array of type string, length 1800
        
        /////array of type int. length 1000
        double[] int1;
        double[] int2;
        double[] int3;
    
        int1 = new double[1000];
        int2 = new double[1000];
        int3 = new double[1000];
        
         /**
         *Places different numbers in the double arrays 
         *@throws ArrayIndexOutOfBoundsException if index is greater than 1000
         */
    
        for(int ii = 0; ii < 1000; ii++){
          double random =  SortedWordList.randomInt(0,sizeOflist);//random words being produce 
            int1[ii] = random;
            int2[ii] = random;
            int3[ii] = random;
        }
        
        System.out.println("-----------Array of Int, length 1000------insertionSort---");
       
        long startTime10, endTime10;
        double elapsedTimeInSeconds10;
        
        startTime10 = System.nanoTime(); 
        insertionSort(int1, 1000);
        endTime10 = System.nanoTime();
        elapsedTimeInSeconds10 = (endTime10 - startTime10) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds10);
        System.out.println("-----------Array of Int, length 1000------selectionSort---");
       
        long startTime11, endTime11;
        double elapsedTimeInSeconds11;
        
        startTime11 = System.nanoTime(); 
        selectionSort(int2, 1000);
        endTime11 = System.nanoTime();
        elapsedTimeInSeconds11 = (endTime11 - startTime11) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds11);
        System.out.println("-----------Array of Int, length 1000------ Arrays.sort---");
       
        long startTime12, endTime12;
        double elapsedTimeInSeconds12;
        
        startTime12 = System.nanoTime(); 
        Arrays.sort(int3);
        endTime12 = System.nanoTime();
        elapsedTimeInSeconds12 = (endTime12 - startTime12) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds12);
        ///end of array of type int,length 1000
        /////array of type int. length 1500
        double[] number1;
        double[] number2;
        double[] number3;
        //three arrays of type double with a length of 1500
        number1 = new double[1500];
        number2 = new double[1500];
        number3 = new double[1500];
        
        /**
         *Places different numbers in the double arrays 
         *@throws ArrayIndexOutOfBoundsException if index is greater than 1500
         */
    
        for(int ii = 0; ii < 1500; ii++){
          double random2 =  SortedWordList.randomInt(0,sizeOflist); //random words beig produce 
            
            number1[ii] = random2;
            number2[ii] = random2;
            number3[ii] = random2;
           // System.out.println(random);
        }
        
        System.out.println("-----------Array of Int, length 1500------insertionSort---");
       
        long startTime13, endTime13;
        double elapsedTimeInSeconds13;
        
        startTime13 = System.nanoTime(); 
        insertionSort(number1, 1500);
        endTime13 = System.nanoTime();
        elapsedTimeInSeconds13 = (endTime13 - startTime13) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds13);
        System.out.println("-----------Array of Int, length 1500------selectionSort---");
       
        long startTime14, endTime14;
        double elapsedTimeInSeconds14;
    
        startTime14 = System.nanoTime(); 
        selectionSort(number2, 1500);
        endTime14 = System.nanoTime();
        elapsedTimeInSeconds14 = (endTime14 - startTime14) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds14);
        System.out.println("-----------Array of Int, length 1500------Arrays.sort---");
       
        long startTime15, endTime15;
        double elapsedTimeInSeconds15;
        
        startTime15 = System.nanoTime(); 
        Arrays.sort(number3);
        endTime15 = System.nanoTime();
        elapsedTimeInSeconds15 = (endTime15 - startTime15) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds15);
        ///end of array of type int,length 1500
        /////array of type int. length 1600
            
        double[] decimal1;
        double[] decimal2;
        double[] decimal3;
        //three arrays of type double with a length of 100000
        decimal1 = new double[100000];
        decimal2 = new double[100000];
        decimal3 = new double[100000];
        
        
        /**
         *Places different numbers in the double arrays 
         *@throws ArrayIndexOutOfBoundsException if index is greater than 100000
         */
    
        for(int ii = 0; ii < 100000; ii++){
          double random3 =  SortedWordList.randomInt(0,sizeOflist);//random words beign produce 
            
            decimal1[ii] = random3;
            decimal2[ii] = random3;
            decimal3[ii] = random3;
           // System.out.println(random);
        }
        
        System.out.println("-----------Array of Int, length 100000------insertionSort---");
       
        long startTime16, endTime16;
        double elapsedTimeInSeconds16;
        
        startTime16 = System.nanoTime(); 
        insertionSort(decimal1,100000);
        endTime16 = System.nanoTime();
        elapsedTimeInSeconds16 = (endTime16 - startTime16) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds16);
        System.out.println("-----------Array of Int, length 100000------selectionSort---");
            
        long startTime17, endTime17;
        double elapsedTimeInSeconds17;
        
        startTime17 = System.nanoTime(); 
        selectionSort(decimal2, 100000);
        endTime17 = System.nanoTime();
        elapsedTimeInSeconds17 = (endTime17 - startTime17) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds17);
        System.out.println("-----------Array of Int, length 100000------ Arrays.sort---");
            
        long startTime18, endTime18;
        double elapsedTimeInSeconds18;
    
        startTime18 = System.nanoTime(); 
        Arrays.sort(decimal3);
        endTime18 = System.nanoTime();
        elapsedTimeInSeconds18 = (endTime18 - startTime18) / 1.0e9;
        System.out.println(""+ elapsedTimeInSeconds18);
      
        ///end of array of type int,length 1600
    
    }

}


