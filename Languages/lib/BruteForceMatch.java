package lib;

/***************************************************************
*
*  Compilation:  javac Brtue.java
*  Execution:    java Brute pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using brute force.
*
*  % java Brute abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java Brute rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab                         
* 
*  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad

*
*  % java Brute bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
* 
*  % java Brute abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class BruteForceMatch {

  /***************************************************************************
   *  String versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i+j) != pat.charAt(j))
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (txt.charAt(i) == pat.charAt(j)) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   }


  /***************************************************************************
   *  char[] array versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(char[] pattern, char[] text) {
       int M = pattern.length;
       int N = text.length;

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (text[i+j] != pattern[j])
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(char[] pattern, char[] text) { 
       int M = pattern.length;
       int N = text.length;
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (text[i] == pattern[j]) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   } 


   // test client
   public static void main(String[] args) {
       //String pat = args[0];
       //String txt = args[1];
	   
       // There are two implmentations of search
	   // one is with String and the other is an array of chars
	   
	   

   	long start, end;
   
   	long Time [] =new long [6];
   	In myfile=new In ("HardDisk.txt");
   	String file=myfile.readAll();
   	myfile.close();
       String[] keys = {"hard", "disk", "hard disk","hard drive","hard dist","xltpru"}; 
   	
       for(int j=0;j<keys.length;j++) {
       	System.out.println("The poss of "+keys[j]+" are (if there is not result below , that means there is not pat in HardDisk.txt):");
       	start=System.nanoTime();
   		String pat = keys[j];
   		for(int i=0;i<100;i++) {
   			String txt = file;
   			
   			int pos= 0;
   			int crrentpos=0;
       

   			while(txt.length()>=pat.length()) {
   			 pos = search1(pat,txt);

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
           
       System.out.println("BruteForceMatch: The avage time of <hard> is : "+Time[0]/100000);
       System.out.println("BruteForceMatch: The avage time of <disk> is : "+Time[1]/100000);
       System.out.println("BruteForceMatch: The avage time of <hard disk> is : "+Time[2]/100000);
       System.out.println("BruteForceMatch: The avage time of <hard drive> is : "+Time[3]/100000);
       System.out.println("BruteForceMatch: The avage time of <hard dist> is : "+Time[4]/100000);
       System.out.println("BruteForceMatch: The avage time of <xltpru> is : "+Time[5]/100000);
    	   //////////////
   	    	  /////////////
   	
//       String pat = "abracadabra";
//       String txt = "abacadabrabracabracadabrabrabracad";
//       char[] pattern = pat.toCharArray();
//       char[] text    = txt.toCharArray();
//
//       
//       int offset1a = search1(pat, txt);
//       int offset2a = search2(pat, txt);
//       int offset1b = search1(pattern, text);
//       int offset2b = search2(pattern, text);
//
//       // print results
//       StdOut.println("text:    " + txt);
//
//       // from brute force search method 1a
//       StdOut.print("pattern: ");
//       for (int i = 0; i < offset1a; i++)
//           StdOut.print(" ");
//       StdOut.println(pat + " at pos " + offset1a);
//
//       // from brute force search method 2a
//       StdOut.print("pattern: ");
//       for (int i = 0; i < offset2a; i++)
//           StdOut.print(" ");
//       StdOut.println(pat + " at pos " + offset2a);
//
//       // from brute force search method 1b
//       StdOut.print("pattern: ");
//       for (int i = 0; i < offset1b; i++)
//           StdOut.print(" ");
//       StdOut.println(pat + " at pos " + offset1b);
//
//       // from brute force search method 2b
//       StdOut.print("pattern: ");
//       for (int i = 0; i < offset2b; i++)
//           StdOut.print(" ");
//       StdOut.println(pat + " at pos " + offset2b);
   }
}