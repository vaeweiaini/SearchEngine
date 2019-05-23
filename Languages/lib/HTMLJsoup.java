package lib;


import java.io.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;



public class HTMLJsoup {

	public static void main(String[] args) throws IOException {
		//org.jsoup.nodes.Document doc = Jsoup.connect("http://luisrueda.cs.uwindsor.ca/researchint/transcriptomics").get();
		//org.jsoup.nodes.Document doc = Jsoup.connect("http://blogs.windsorstar.com/news/woman-to-be-charged-with-child-abandonment-after-infants-found-in-apartment-stairwell").get();
		String path = "W3C Web Pages";	
		File file = new File(path);		
		File [] fs = file.listFiles();
		String A []= new String[fs.length];
		for(int i=0;i<fs.length;i++) {
			if(!fs[i].isDirectory()) {
				A[i]=fs[i].toString();
				File myhtmlfile =new File(A[i]);
				Document doc=Jsoup.parse(myhtmlfile,"UTF-8");
		//Documents
				A[i] =  A[i].substring(14, A[i].length()-3);
				String s = "./text/"+A[i]+"txt";
				String text = doc.text();
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
