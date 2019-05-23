package spellchecking;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class checkingFunction {	

public static HashMap<String, Integer> generateDictionary(String root) throws IOException {
	String[] str = fileOperation.read(root);
	String[] keys = new String[str.length];
	int[] values = new int[str.length];
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    for(int i = 0; i < str.length; i++) {
 	   String[] a = str[i].split("=");
 	   keys[i] = a[0];
 	   values[i] = Integer.parseInt(a[1]);
    map.put(keys[i], values[i]);
    }
	return map;
}

public static int search(String str, HashMap<String, Integer> map) {
	int j = map.getOrDefault(str,-1);
	return j;
}

public static boolean find(String str, HashMap<String, Integer> map) {
	int flag = search(str, map);
	if (flag != -1) {
	flag++;
	map.put(str, flag);
	return true;
	}
	return false;
}

public static List<String> findSimilar(String str) {
	List<String> simlist = new ArrayList<String>();
	
	String simstr = "";
	//List<String> simlist = new ArrayList<String>();
	
	for(int i =0; i < str.length(); i++) {
		simstr = str.substring(0, i).concat(str.substring(i+1));//
		simlist.add(simstr);
		
		for(char a = 'a'; a <= 'z'; a++) {		
		simstr = str.substring(0, i).concat(String.valueOf(a)).concat(str.substring(i));//
		simlist.add(simstr);
		simstr = str.substring(0, i).concat(String.valueOf(a)).concat(str.substring(i+1));//
		simlist.add(simstr);
		}
		
		
		if(i == 0) {
			simstr = String.valueOf(str.charAt(1)).concat(String.valueOf(str.charAt(0))).concat(str.substring(2));
			simlist.add(simstr);
		}
		else if(i == str.length() - 1) {
			
		}
		else {
			simstr = str.substring(0, i).concat(String.valueOf(str.charAt(i+1))).concat(String.valueOf(str.charAt(i))).concat(str.substring(i+1));
			simlist.add(simstr);
		}
	}
	
	for(char a = 'a'; a <= 'z'; a++) {
		simstr = String.valueOf(a).concat(str);
		simlist.add(simstr);
		simstr = str.concat(String.valueOf(a));
		simlist.add(simstr);
	}
	return simlist;	
}

public static String suggestion(String str, HashMap<String, Integer> map) {
	if (find(str, map)) 
		return str;
	if (str.length() == 1) return str;
	List<String> list = findSimilar(str);
	String key = "";
	int value = -1;
	int largest = -1;
	for(int i = 0; i < list.size(); i++) {
	key = list.get(i);
	if (search(key, map) > largest) {
		largest = search(key, map);
		value = i;
		}
	}
	if (largest == -1)
		return leastDistance(str, map);
		//return str;
	find(list.get(value), map);
	return list.get(value);	
}

public static int  getDistance(String str1, String str2) {
	int len1 = str1.length();
	int len2 = str2.length(); 
	int[][] dp = new int[len1 + 1][len2 + 1];
 
	for (int i = 0; i <= len1; i++) {
		dp[i][0] = i;
	}
 
	for (int j = 0; j <= len2; j++) {
		dp[0][j] = j;
	}
 
	for (int i = 0; i < len1; i++) {
		char c1 = str1.charAt(i);
		for (int j = 0; j < len2; j++) {
			char c2 = str2.charAt(j);
 
			if (c1 == c2) {
				dp[i + 1][j + 1] = dp[i][j];
			} else {
				int replace = dp[i][j] + 1;
				int insert = dp[i][j + 1] + 1;
				int delete = dp[i + 1][j] + 1;
 
				int min = replace > insert ? insert : replace;
				min = delete > min ? min : delete;
				dp[i + 1][j + 1] = min;
			}
		}
	}
	return dp[len1][len2];	
}

public static String leastDistance(String str, HashMap<String, Integer> map) {
	int flag = 0, min = 100;
	String minvalue = "";
	String str1 = "";
	for (Entry<String, Integer> entry : map.entrySet()) {
		str1 = entry.getKey();
		flag = getDistance(str, str1);
		if (flag < min) {
			min = flag;
			minvalue = str1;			
		}
	}
	map.put(minvalue, map.get(minvalue) + 1);
	return minvalue;
}

public static void main(String args[]) throws IOException {


String dic = "hashmap.txt";
String word = "belll";
HashMap<String, Integer> map = generateDictionary(dic);
long time = System.nanoTime();
String str = leastDistance(word, map);
long total = System.nanoTime() - time;
System.out.println(str);
System.out.println(total);
time = System.nanoTime();
str = suggestion(word, map);
long total1 = System.nanoTime() - time;
System.out.println(str);
System.out.println(total1);
}
}