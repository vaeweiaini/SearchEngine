package htmljsoup;

import java.util.Random;

public class Heap {
private static String[] heap;
private static int[] array;
	
	public static void swap(int i, int j) {
		String temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		int temp1 = array[i];
		array[i] = array[j];
		array[j] = temp1;
	}
	
	public static void buildheap(String[] str) {
		heap = str;	
		array = new int[str.length];
		String[] substr = new String[10];
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null) ;//System.out.println("error");
			else {
			substr = str[i].split("/");
			//System.out.println(substr[0]);
			array[i] = Integer.parseInt(substr[0]);
			}
		}
	}
	
	public static void adjustHeap(int i,int length) {
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length && array[k] > array[k+1]){
                k = k + 1;
            }
            if(array[k] < array[i]){
                swap(k,i);
                i = k;
            }else{
                break;
            }
            }
	}
	
    public static void sort(String[] str){
    	buildheap(str);
        for(int i=array.length/2-1;i>=0;i--){
            adjustHeap(i, heap.length);
        }
        for(int j=array.length-1;j>0;j--){
            swap(0,j);
            adjustHeap(0,j);
        }
    }
    
    public static void main(String[] arg) {
    	
    	Random r = new Random();
       	int a;
       	a=r.nextInt(100);
    	
    	String str=a+"/A.txt";
	a=r.nextInt(100);
    	
    	String str2=a+"/B.txt";
	a=r.nextInt(100);
    	
    	String str3=a+"/C.txt";
	a=r.nextInt(100);
    	
    	String str4=a+"/D.txt";
	a=r.nextInt(100);
    	
    	String str5=a+"/E.txt";
	a=r.nextInt(100);
    	
    	String str6=a+"/F.txt";
    	
	a=r.nextInt(100);
    	
    	String str7=a+"/G.txt";
	a=r.nextInt(100);
    	
    	String str8=a+"/H.txt";
    	
	a=r.nextInt(100);
    	
    	String str9=a+"/I.txt";
	a=r.nextInt(100);
    	
    	String str10=a+"/J.txt";
	a=r.nextInt(100);
    	
    	String str11=a+"/K.txt";
    	
    	
    	String[] str0 = {str,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11};
    	sort(str0);
    	for(int i = 0; i < str0.length; i++) {
    		System.out.print(str0[i] + " ");
    	}
    }
}
