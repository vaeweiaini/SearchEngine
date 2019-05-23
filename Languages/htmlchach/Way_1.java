package htmlchach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Way_1{
public static void way_1(String url) throws Exception {
    InputStream inputStream;//接收字节输入流
    InputStreamReader inputStreamReader;//将字节输入流转换成字符输入流
    BufferedReader bufferedReader;//为字符输入流加缓冲
    FileOutputStream fileOutputStream;//字节输出流
    OutputStreamWriter outputStreamWriter;//将字节输出流转换成字符输出流

    URL wangyi = new URL(url);
    inputStream = wangyi.openStream();
    inputStreamReader = new InputStreamReader(inputStream, "gb2312");
    bufferedReader = new BufferedReader(inputStreamReader);
    String s;
    String filename =url.replaceAll("\\W","");
    File dest = new File("htm/"+filename+".htm");
    fileOutputStream = new FileOutputStream(dest);
    outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gb2312");
    while ((s = bufferedReader.readLine()) != null) {
        outputStreamWriter.write(s);
    }

    outputStreamWriter.close();
    fileOutputStream.close();
    bufferedReader.close();
    inputStreamReader.close();
    inputStream.close();
}
public static void main(String[] args) throws Exception {
	
	  
	List<String> lst = new ArrayList<>( );
    	
    	In myfile=new In ("WebURL.txt");
    
    	String file=myfile.readAll();    	
    	
    	StringTokenizer StrTK=new StringTokenizer(file,"\n");
    	
    	while(StrTK.hasMoreTokens()) {
    		lst.add(StrTK.nextToken());	
    	}
        String [ ] URLS = new String[ lst.size( ) ];
        lst.toArray( URLS );

        for(int i=0;i<URLS.length;i++) {
        	//String S[]=URLS[i].split("	");
        	//URLS[i]=S[0];
        	System.out.println(URLS[i]);
        	try {
        	way_1(URLS[i].replaceAll(" ", ""));
        	}
        	catch (UnknownHostException e) {
        	}
        	catch(IOException e1) {       		
        	}
        }
        
	//way_1("http://www.163.com/");
}

}