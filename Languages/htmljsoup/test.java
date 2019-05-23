package htmljsoup;

import java.io.IOException;
import java.util.Scanner;

public class test {
	public static void main(String[] args) throws Exception {
		//check.checkerInitialize();
	
		String str2 ="";
		boolean state=true;
		while(state) {
		Scanner sc=new Scanner(System.in);
		 String str=null;
		 System.out.print("welcome to the greatest web search!!!\n");
		 System.out.println( "Engine what you would like to do today?\n");
		 System.out.println("1.Initialize");
		 System.out.println("2.finding patterns using regular expressions");
		 System.out.println("3.search key word in 100 htm file");
		 System.out.println("4.search by use InvertedIndx and TST");
		 System.out.println("exit(enter any of other key)");
		
		 str=sc.nextLine();
		 
		 switch(str){
		 		case "1" :
		 			spellchecking.checker.checkerInitialize();
	 				htmltotxt.toTxT("htm","txt");
	 				InvertedIndix.getII("txt");
	 				System.out.println("Down!!! See you later\n\n");
		 			break; //可选
		 		case "2" :
		 			System.out.print("What you want to do?\n");
		 			System.out.print("1.search phone number\n");
		 			System.out.print("2.search email\n");
		 			System.out.print("3.search all url end by .net\n");
		 			System.out.print("4.others you can enter your own regular expressions\n");
					str=sc.nextLine();
		 				
		 				switch(str) {
		 					case "1" :
		 						regularExpressions.RE("(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}");
		 						break;
		    	
		 					case "2" :
		 						regularExpressions.RE("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+");
		 						break;
		 					case "3" :
		 						regularExpressions.RE("([a-zA-Z0-9_-]+\\.)+net");
		 						break;	
		 					case "4" :
		 						
		 						str=sc.nextLine();
		 						
		 						regularExpressions.RE(str);
		 						break;
		 				}
		       
		 				break;
		 		case "3" :
		 			if (!spellchecking.checker.isOpened())
			 			spellchecking.checker.checkerOpen();
		 			System.out.print("what you would like to search today?");
		 			
		 			str=sc.nextLine();
		 			
		 			str2=spellchecking.checker.spellingCheck(str);
		 			
		 			if(str2!=str) {
		 				System.out.println("Are you searching "+str2+"(y/n)");
		 			
		 				String str3=sc.nextLine();

		 				if(str3.equals("y")) {
		 					
		 					search.searchINfolder("txt",str2);
			 			
		 					System.out.println("exit");
		 				}else {
		 					search.searchINfolder("txt",str);
		 					System.out.println("exit");
		 				}
		 			}else {
		 				long Start=System.currentTimeMillis();
		 				search.searchINfolder("txt",str);
		 			long End=System.currentTimeMillis();
		 				System.out.println("Time cost:"+(End-Start));
		 				
		 				
		 				System.out.println("exit");
		 				

		 			}

		 			break;
		 			
		 		case "4":
		 			if (!spellchecking.checker.isOpened())
		 			spellchecking.checker.checkerOpen();
		 			System.out.println("what you would like to search today?");
		 			
		 			str=sc.nextLine();
		 			
		 			str2=spellchecking.checker.spellingCheck(str);
		 			
		 			if(str2!=str) {
		 				System.out.println("Are you searching "+str2+"(y/n)");
		 			
		 				String str3=sc.nextLine();

		 				if(str3.equals("y")) {
		 					
		 					TST.IIsearch(str2);

			 			
		 					System.out.println("exit");
		 				}else {
		 					TST.IIsearch(str);

		 					System.out.println("exit");
		 				}
		 			}else {
		 				long Start=System.currentTimeMillis();
		 				TST.IIsearch(str);
		 				long End=System.currentTimeMillis();
		 				System.out.println("Time cost:"+(End-Start));
		 				System.out.println("exit");
		 			}
		    	
		    	break;
		 		case "5":
		 			
		 		
		 			break;
		       
		    default :
		    if (spellchecking.checker.isOpened())
		 		spellchecking.checker.checkerClose();
		    System.exit(0);
		    
		}
		 
		
		}
	
	}
}
