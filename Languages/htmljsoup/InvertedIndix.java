package htmljsoup;





import java.io.*;
import java.util.*;

class InvertedIndix
{
    public static void getII(String file) throws IOException
    {   
        String filePath = file;
        String docIndex = "docIndex.txt";
        String wordIndex = "wordIndex.txt";
        getFileIndex(filePath , docIndex);
        getWordsFrequency(docIndex,wordIndex);
        System.out.println("Work Done!");
    }

    public static void getFileIndex(String filePath , String docIndex)
    {
        //通过传入的filePath找到文件所在，并将该文件下所有文件信息写到docIndex.txt中
        File file = new File(filePath);
        File[] fileList = file.listFiles();     
        BufferedWriter bufw = null;
        try 
        {   //将所有filePath下的文件路径写到docIndex文件中
            bufw = new BufferedWriter(new FileWriter(docIndex));
            for(int x = 0 ; x <fileList.length ; x++ )
            {
                String docPath = fileList[x].getAbsolutePath();         
                bufw.write("DocID_" + x + "\t" + docPath);
                bufw.newLine();bufw.flush();//刷新写入
            }
        }
        catch (IOException e)
        {
            System.out.println("打开文件失败" + e);
        }
        finally
        {
            try
            {
                if(bufw != null)
                    bufw.close();
            }
            catch (IOException ex)
            {
                System.out.println("关闭文件失败" + ex);
            }
        }
    }
    public static void getWordsFrequency(String docIndex , String wordIndex) throws IOException
    { //通过docIndex文件中的内容找到每个文件，并将文件中的内容做单词统计
       TreeMap<String,TreeMap<String,Integer>>  tmp = new TreeMap<String,TreeMap<String,Integer>>();//统计map
       BufferedReader bufr = new BufferedReader(new FileReader(docIndex));//读取docIndex.txt
       BufferedWriter bufw = new BufferedWriter(new FileWriter(wordIndex));//写入到wordIndex.txt
       BufferedReader bufrDoc = null;
       String docIDandPath = null;
       while( (docIDandPath = bufr.readLine()) != null)
        {
              String[] docInfo = docIDandPath.split("\t");
              String docID = docInfo[0]; String docPath = docInfo[1];//获取到docID和文件的路径
              bufrDoc = new BufferedReader(new FileReader(docPath));
              String  wordLine = null;   
              while( (wordLine = bufrDoc.readLine()) != null)
                {
                  String[] words = wordLine.split("\\W");
                  for(int i=0;i<words.length;i++) {
                	  String wordOfDoc=words[i];
                	 // System.out.print(wordOfDoc);
                	  if(!wordOfDoc.equals("")) {
                		  wordDeal(wordOfDoc,docInfo[1],tmp);
                	  }
                  }
                 
                	
                     //将从docIndex读取到对应文件内容对做统计处理                            
                }
              
        } 
        //将处理后的结果写入到wordIndex.txt文件中        
        String wordFreInfo = null;
        Set<Map.Entry<String,TreeMap<String,Integer>>> entrySet = tmp.entrySet();
        Iterator<Map.Entry<String,TreeMap<String,Integer>>> it = entrySet.iterator();
        FileWriter FN = new FileWriter("keywords.txt");
        while(it.hasNext())
        {
            Map.Entry<String,TreeMap<String,Integer>> em = it.next();
           String sort =em.getKey()+" " +em.getValue();
          // System.out.println(sort);
           
           String[] keyonelinerank = sort.split("[{}]");
          
           
//           for(int i=0;i<onelinerank.length;i++) {
//        	   System.out.println(onelinerank[i]);
//           }
           keyonelinerank[1]=keyonelinerank[1].replaceAll(" ","");
          String[] onelinerank=keyonelinerank[1].split(",");
          for (int k =0 ; k<onelinerank.length; k++) {
        	 String [] one= onelinerank[k].split("=");
        	 onelinerank[k]=one[1]+one[0];
          }
           
          String result=keyonelinerank[0];
          String keywords=keyonelinerank[0].replaceAll(" ", "");
          FN.write(keywords+"\n");
          Heap.sort(onelinerank);
          for(int i=0;i<onelinerank.length;i++) {
      	   result=result+onelinerank[i]+"* ";
          }
         // System.out.println("///////");
            
           
           
            bufw.write(result);
            bufw.newLine();bufw.flush();
            //FN.close();
        }
        FN.close();
        bufw.close();
        bufr.close();
        bufrDoc.close();
    }
    public static void wordDeal(String wordOfDoc,String docID,TreeMap<String,TreeMap<String,Integer>> tmp)
    {
        wordOfDoc = wordOfDoc.toLowerCase();
        if(!tmp.containsKey(wordOfDoc))
        {   
          //单词在统计中是首次出现 
            TreeMap<String , Integer> tmpST = new TreeMap<String , Integer>();
            tmpST.put(docID,1);
            tmp.put(wordOfDoc,tmpST);
          // System.out.println(" 1");
        }        
        else
        {//单词在tmp中已近存在获取该单词在对应docID中出现次数，若是首次出现
         //count = null，则将（docID ,1)加入到tmpST中；若不是首次出现，则将count++后，再将信息回写到tmpST中。
         TreeMap<String ,Integer> tmpST = tmp.get(wordOfDoc);
         Integer count = tmpST.get(docID);
         if(count == null) {
        	 count = 1;
        	// System.out.println(" 2");
         }  else {
        	 count++;
        	// System.out.println(" 3 " + count);
         }
         tmpST.put(docID,count);                
         tmp.put(wordOfDoc,tmpST);  //将最新结果回写到tmp中   
        }
    }
}
