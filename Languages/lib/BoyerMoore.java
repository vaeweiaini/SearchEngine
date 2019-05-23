package lib;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//import memoryManagement.In;

/***************************************************************
 *  Compilation:  javac BoyerMoore.java
 *  Execution:    java BoyerMoore pattern text
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  bad-character rule part of the Boyer-Moore algorithm.
 *  (does not implement the strong good suffix rule)
 *
 *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:               abracadabra
 *
 *  % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:         rab
 *
 *  % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:                                   bcara
 *
 *  % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ***************************************************************/

public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    // pattern provided as a string
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
       // try {
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
        //}
       // catch(IndexOutOfBoundsException e) {
        //	System.out.println();
        //}
    }

    // pattern provided as a character array
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    // return offset of first match; N if no match
    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }


    // return offset of first match; N if no match
    public int search(char[] text) {
        int M = pattern.length;
        int N = text.length;
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) {
            	return i; }   // found
        }
        return N;                       // not found
    }



    // test client
    public static void main(String[] args) {
        //String pat = args[0];
        //String txt = args[1];7777
 	   
        // There are two implmentations of search
 	   // one is with String and the other is an array of chars
    	long start, end;
List<String> lst = new ArrayList<>( );
//    	
//    	In myfile=new In ("Protein.txt");
//    	String file=myfile.readAll();
//    	StringTokenizer StrTK=new StringTokenizer(file," ");
//    	int i=0;
//    	while(StrTK.hasMoreTokens()) {
//    		lst.add(StrTK.nextToken());
//    		//System.out.println(StrTK.nextToken());
//    		
//    	}
    	
    	
    	
    
    	long Time [] =new long [6];
    	In myfile=new In ("HardDisk.txt");
    	String file=myfile.readAll();
    	myfile.close();
        String[] keys = {"Parallel", "ATA"}; 
    	
        for(int j=0;j<keys.length;j++) {
        	System.out.println("The poss of "+keys[j]+" are (if there is not result below , that means there is not pat in HardDisk.txt):");
        	start=System.nanoTime();
    		String pat = keys[j];
    		for(int i=0;i<1;i++) {
    			String txt = file;
    			BoyerMoore boyermoore1 = new BoyerMoore(pat);
    			int pos= 0;
    			int crrentpos=0;
    			
    			while(txt.length()>=pat.length()) {
    				pos = boyermoore1.search(txt);
    				if(pos==txt.length()) {
    					break;
    				}
    				crrentpos = crrentpos+pos;
    				System.out.println(crrentpos);
    				txt =  txt.substring(pos+pat.length(), txt.length());
    			}
    		}
    		 end= System.nanoTime();
    	        Time[j]= end-start;
        }
            
        System.out.println("BoyerMoore: The avage time of <hard> is : "+Time[0]/100000);
        System.out.println("BoyerMoore: The avage time of <disk> is : "+Time[1]/100000);
        System.out.println("BoyerMoore: The avage time of <hard disk> is : "+Time[2]/100000);
        System.out.println("BoyerMoore: The avage time of <hard drive> is : "+Time[3]/100000);
        System.out.println("BoyerMoore: The avage time of <hard dist> is : "+Time[4]/100000);
        System.out.println("BoyerMoore: The avage time of <xltpru> is : "+Time[5]/100000);
        
   
        
//         print results
//        StdOut.println("text:    " + txt);
//
//        StdOut.print("pattern: ");
//        //for (int i = 0; i < offset1; i++)
//          //  StdOut.print(" ");
//        StdOut.println(pat + " at pos " + offset1);
//       // System.out.println(pat + " at pos " + offset1);
//
//        StdOut.print("pattern: ");
//        //for (int i = 0; i < offset2; i++)
//          //  StdOut.print(" ");
//       StdOut.println(pat + " at pos " + offset2);
        
      }
}