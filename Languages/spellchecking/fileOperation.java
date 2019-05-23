package spellchecking;
import java.io.*;
import java.util.HashMap;

public class fileOperation {

	public static String[] read(String root) throws IOException {
		String[] keys = new String[69903];
		File f = new File(root);
		FileReader r = new FileReader(f);
		BufferedReader in = new BufferedReader(r);
		   String str = in.readLine();
		   int i = 0;
		   while(str != null && i < 69903) 
		   {				   
			   keys[i] = str;
			   str = in.readLine();
			   i++;
		   }
		   in.close();
		   return keys;
	}
	
	public static void initialize() throws IOException {
		final String root = "wordlist.txt";
		final String dic = "hashmap.txt";
		String[] str = read(root);
		File f = new File(dic);
		if (f.exists()) {
			f.delete();	
		}
		f.createNewFile();
		FileWriter fw = new FileWriter(dic);		
		String str1 = "";
		for (int i = 0; i < str.length ; i++) {
			str1 = str[i] + "=" + 0 + "\r\n";
			fw.write(str1);
		}
		fw.close();
	}
	
	public static void save(HashMap<String, Integer> map) throws IOException {
		PrintStream out = System.out;
		final String hash = "hash.txt";
		final String dic = "hashmap.txt";
		File f = new File(dic);
		File f1 = new File(hash);
		f1.createNewFile();
		if (f.exists()) {
		if (f.length() != 0) 
			f.delete();
			f.createNewFile();
			}
		else {
			f.createNewFile();
		}
		PrintStream ps = new PrintStream(hash);
		System.setOut(ps);
		System.out.println(map);
		FileReader fr = new FileReader(hash);
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		String line = br.readLine();
		while(line != null){
		str += line;
		line = br.readLine();
		}
		br.close();
		ps.close();
		if(!f1.delete()) System.out.println("fail to delete");;
		String[] a = str.split(", ");
		a[0] = a[0].substring(1);
		a[a.length - 1] = a[a.length - 1].substring(0, a[a.length - 1].length() - 1);
		FileWriter fw = new FileWriter(f);	
		String str1 = "";
		for (int i = 0; i < a.length; i++) {
			str1 = a[i] + "\r\n";
			fw.write(str1);
		}
		fw.close();
		System.setOut(out);
	}
	
	public static void main(String args[]) throws IOException {
		
	}
}
