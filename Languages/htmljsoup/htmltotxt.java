package htmljsoup;


import java.io.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;



public class htmltotxt {

	public static void toTxT(String Hfolder,String Tfolder) throws IOException {
		//org.jsoup.nodes.Document doc = Jsoup.connect("http://luisrueda.cs.uwindsor.ca/researchint/transcriptomics").get();
		//org.jsoup.nodes.Document doc = Jsoup.connect("http://blogs.windsorstar.com/news/woman-to-be-charged-with-child-abandonment-after-infants-found-in-apartment-stairwell").get();
		String path = Hfolder;	
		File file = new File(path);		
		File [] fs = file.listFiles();
		String A []= new String[fs.length];
		for(int i=0;i<fs.length;i++) {
			if(!fs[i].isDirectory()) {
				A[i]=fs[i].toString();
				File myhtmlfile =new File(A[i]);
				Document doc=Jsoup.parse(myhtmlfile,"UTF-8");
		//Documents
				A[i] =  A[i].substring(Hfolder.length(), A[i].length()-3);
				String s = "./"+Tfolder+A[i]+"txt";
				String text = doc.text();
				text=text.toLowerCase();
				PrintWriter out = new PrintWriter(s);
				out.println(text);
				out.close();
			}
		}
//		String html = doc.html();
//		out = new PrintWriter("jsoupHTML.html");
//		out.println(html);
//		out.close();
//    	String program = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe jsoupHTML.html";
//    	Process p = Runtime.getRuntime().exec(program);
	}
}
