package htmljsoup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;






public class search {
	public static void searchINfolder(String foldername,String pattern) {
	
		List<pages> list = new ArrayList<pages>();
		File file = new File(foldername);		//获取其file对象
		File[] fs = file.listFiles();
		String A [ ] = new String [fs.length];
		String B []=new String [fs.length];
		pages[]array=new pages[fs.length];
		for(int i=0;i<fs.length;i++) {
			A[i]=fs[i].toString();
			
			
			lib.In myfile=new lib.In (A[i]);
			String f=myfile.readAll();
			myfile.close();
			int rate=0;
		    int pos= 0;	
		  //  lib.BoyerMoore boyermoore1 = new lib.BoyerMoore(pattern);
    			while(f.length()>=pattern.length()) {
    			// pos = boyermoore1.search(f);;
    			 pos=lib.BruteForceMatch.search1(pattern, f);
    				if(pos==f.length()) {
    					break;
    				}
    				rate ++;
    				
    				f =  f.substring(pos+pattern.length(), f.length());
    			}
    			
    			array[i]=new pages(rate,A[i]);
    			list.add(array[i]);
    			
    	//	System.out.println(array[i].getname()+array[i].getRate());	
    		
    			
		}
	
		
		 Collections.sort(list,new Comparator<pages>() {

	            @Override
	            public int compare(pages o2, pages o1) {
	                // TODO Auto-generated method stub
	                return o1.getRate()-o2.getRate();
	            }
	        });
		 
		 System.out.println("排序后:");
	        for (pages page : list) {
	            System.out.println("Rank:"+page.getRate()+"     address:"+page.getname());
	        }
	      
	
	
	}
		
}
class pages{
	
	private int rate;
	private String name;
	
	pages(int rate , String name){
		this.rate=rate;
		this.name = name;
	}
	
	public String getname() {
		return name;
	}
 
	public void setname(String name) {
		this.name = name;
	}
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate=rate;
	}
	@Override
	public String toString() {
		return getRate()+"";
	}
}
