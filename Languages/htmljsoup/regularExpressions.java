package htmljsoup;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regularExpressions
{
    public static void RE( String s){

      // String to be scanned to find the pattern.
    	String path = "txt";	
		File file = new File(path);		
		File[] fs = file.listFiles();
		String A [ ] = new String [fs.length];
		for(int i=0;i<fs.length;i++) {
			A[i]=fs[i].toString();		
			lib.In myfile=new lib.In (A[i]);
			String f=myfile.readAll();
			myfile.close();
    
//			String pattern ="[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
//			String pattern2 = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
//			String pattern3="([a-zA-Z0-9_-]+\\.)+w3.org";
//			String pattern8="www.w3.org(/[a-zA-Z0-9_-]+)+/";
//			String pattern9="www.w3.org(/[a-zA-Z0-9_-]+)+/#[a-zA-Z0-9_-]+";
//			String pattern5="([a-zA-Z0-9_-]+\\.)+com";
//			String pattern6="([a-zA-Z0-9_-]+\\.)+net";
//			String pattern7="([a-zA-Z0-9_-]+\\.)+org";
			
	
			String pattern10=s;
			Pattern r = Pattern.compile(pattern10);
	
		
			// Now create matcher object.
			Matcher m = r.matcher(f);
			while (m.find( )) {
				System.out.println(A[i]);
				System.out.println("Found value: " + m.group(0) + " at " + m.start(0));
			} 
		}
    }
   }
