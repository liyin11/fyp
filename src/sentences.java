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

public class sentences {

    public static void main(String[] args) throws IOException {

    	
    	try {
			//using 3 lines below if the amount of both sources and targets files is not same
    		//int x=0;
    		//int y=0;
    		//while(x!=37){	
    	BufferedReader en = new BufferedReader(new InputStreamReader(new FileInputStream("books/werkzeug/OutFile1.txt"),"UTF-8"));
        BufferedReader cn = new BufferedReader(new InputStreamReader(new FileInputStream("books/werkzeug/OutFile2.txt"),"UTF-8"));
        File bw = new File("books/werkzeugEn.txt");
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
        	    //String eng= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        	    //List<String> stringArr_cn = new ArrayList<String>();
        	    
        	    System.out.println(p_en.size());
        		System.out.println(p_cn.size());
        		int size=Math.min(p_en.size(),p_cn.size());
        		System.out.println(size);
        		int j=0;
        	   while(i<size){
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
        	    			&& !(p_cn.get(j).contains("[A-Za-z0-9 ]+"))
        	    			)
        	    	{
        	    		
	    	    		
	        	    	List<String> str_cn1 = Arrays.asList(p_cn.get(j));
	        	    	List<String> str_en1 = Arrays.asList(p_en.get(i));
	        	    	int l =0;
	        	    	count = count+str_cn1.size()+str_en1.size();
	        	    	while( l<Math.min(str_cn1.size(),str_en1.size())){
							//to get english sentences only
	        	    		stringArr = str_en1.get(l).replaceAll("<[A-Za-z1-9]+>", "")+"\n"
							//to get chinese sentences only
	        	    				//str_cn1.get(l).replaceAll("<[A-Za-z1-9]+>", "")+"\n"
	        	    		;
	        	    		out.write(stringArr);

	        	    	l++;}
        	    	}
        	    	System.out.println(count);
        	    	
        	    
        	    		j++;
        	    	i++;
        	    	
        	    	
    	}
        	
	    	out.flush();
	    	out.close();
	    	//x++;
	    	//y++;
    		
        	}catch (FileNotFoundException e) {
        	      e.printStackTrace();}
    	
    	
    	
    	
    
}}

                
    
    

