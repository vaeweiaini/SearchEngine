package spellchecking;

import java.io.IOException;
import java.util.HashMap;

public class checker {

	private static HashMap<String, Integer> hashmap = null;
	
	public static void checkerOpen() throws IOException{
		final String dic = "hashmap.txt";
		hashmap = checkingFunction.generateDictionary(dic);
	}

	public static String spellingCheck(String str) throws IOException {
		str = str.toLowerCase();
		String target = checkingFunction.suggestion(str, hashmap);
		return target;
	}

	public static void checkerClose() throws IOException{
		fileOperation.save(hashmap);
	}

	public static void checkerInitialize() throws IOException{
		fileOperation.initialize();
	}
	
	public static boolean isOpened() {
		if (hashmap == null) return false;
		return true;
	}
	
	public static void main(String args[]) throws IOException {
	}
}
