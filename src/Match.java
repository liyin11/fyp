package snippet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.Elements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Match {

    public static void main(String[] args) throws IOException {

    	try {
        	
		//english file source
    	BufferedReader en = new BufferedReader(new InputStreamReader(new FileInputStream("books/NLTK book/NLTK_en/OutFile10.txt"),"UTF-8"));
        //chinese file target
		BufferedReader cn = new BufferedReader(new InputStreamReader(new FileInputStream("books/NLTK book/NLTK_cn/OutFile11.txt"),"UTF-8"));
        File bw = new File("books/NLTK book/sentences/S.txt");
	    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bw), "UTF8"));
	    
    	String str_en;
    	String str_cn;
    

        List<String> p_en = new ArrayList<String>();
        List<String> p_cn = new ArrayList<String>();
        
        while((str_en = en.readLine()) != null)
        {
        	p_en.add(str_en);
        	}
        while((str_cn = cn.readLine()) != null )
        {
		    p_cn.add(str_cn);
		    }
        		
        	    String stringArr= "";
        	    int i=0;
        	    int count=0;
        	    
        	    //System.out.println(p_en.size());
        		//System.out.println(p_cn.size());
        		int size=Math.min(p_en.size(),p_cn.size());
        		//System.out.println(size);
        		int j=0;
        	    for(i=0;i<size;i++){
        	    	String string_cn = p_cn.get(j);
        	    
        	    	List<String> string_cn1 = Arrays.asList(string_cn.replaceAll("[^A-Za-z0-9 ]+", "").split(" "));
        
        	    	List<String> string_en = Arrays.asList(p_en.get(i).split(" "));
        	 
        	    	boolean isValid = false;
        	    	for (int k = 0; k<string_cn1.size(); k++){
        	    		if (string_cn1.get(k).equals(string_en)) {    	
        	            	
        	                isValid = true;
        	            }
        	    		else{
        	    			isValid = false;
        	    			
        	    		}
        	    		}

        	    	if((p_en.get(i).substring(0, p_en.get(i).indexOf(">")).equals(p_cn.get(j).substring(0, p_cn.get(j).indexOf(">"))))== true
        	    			//&& !(p_cn.get(j).contains("[A-Za-z0-9 ]+"))
        	    			)
        	    	{
        	    		
	    	    		List<String> str_cn1 = Arrays.asList(p_cn.get(j).split("[。！？；]([.]\\s[0-9])*"));
	        	    	List<String> str_en1 = Arrays.asList(p_en.get(i).split("(?<=[.!;?])\\s[0-9]*"));
	        	    	int l =0;
	        	    	count = count+str_cn1.size()+str_en1.size();
	        	    	while( l<Math.min(str_cn1.size(),str_en1.size())){
	        	    		stringArr = str_en1.get(l).replaceAll("<[A-Za-z1-9]+>", "")+"\n"+str_cn1.get(l).replaceAll("<[A-Za-z1-9]+>", "");
	        	    		out.write(stringArr);out.write(System.getProperty("line.separator"));
	            	    	out.write(System.getProperty("line.separator"));
	            	    	
	            	    	
	        	    	l++;}
        	    	}
        	    	System.out.println(count);
        	    	
        	    	if((p_en.get(i).substring(0, p_en.get(i).indexOf(">")).equals(p_cn.get(j).substring(0, p_cn.get(j).indexOf(">"))))== false
        	    			//&& !(p_cn.get(j).contains("[A-Za-z0-9 ]+"))
        	    			&& p_en.size()<p_cn.size())
        	    	{
        	    		
        	    		j++;
        	    	}
        	    	if((p_en.get(i).substring(0, p_en.get(i).indexOf(">")).equals(p_cn.get(j).substring(0, p_cn.get(j).indexOf(">"))))== false
        	    			//&& !(p_cn.get(j).contains("[A-Za-z0-9 ]+"))
        	    			&& p_en.size()>p_cn.size())
        	    	{
        	    		
        	    		i++;
        	    	}
        	    
        	    		j++;
 	
    	}
        	
	    	out.flush();
	    	out.close();
	    	
        	}catch (FileNotFoundException e) {
        	      e.printStackTrace();}
    	
    	
    	
    	}
    
    
}



