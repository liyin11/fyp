package snippet;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public class crawl {
	
    public static void main(String[] args) throws IOException {
    	
    	ArrayList<java.lang.String> linkArray=new ArrayList<String>();
		//link for crawling
        String [] url = {"http://www.shoucewang.com/django-book/"};  
           
        for(String urls : url){
            System.out.print("Websites to be crawled\n " + urls);
        }
        
        System.out.printf("\nFetching %s...", url);
        Document doc = Jsoup.connect(url[0]).get();
        Elements links = doc.select("a");
        
        
      
        System.out.printf("\nLinks: (%d)", links.size());
        System.out.printf("%n");
        for (Element link : links) {
			if (link != null && !link.absUrl("href").contains("redirect%20") && !link.absUrl("href").contains("#") && !link.absUrl("href").isEmpty())
        	//if (link.absUrl("href").contains("www.shoucewang.com/django-book/"))
			{
		    	linkArray.add(link.absUrl("href"));
		    	System.out.printf(" (%s)", link.absUrl("href") /*link.attr("href")*/, trim(link.text(), 35));
		    	System.out.printf("%n");}}
       
		    Map<String, List<String>> data = new HashMap<String, List<String>>();
		   
        for(int i=0; i < linkArray.size(); i++){
        	
        	Document doc1 = Jsoup.connect(linkArray.get(i)).get();
        	try {
      	    	File bw = new File("OutFile"+i+".txt");
      	    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bw), "UTF8"));
	        	//select useful HTML tag to crawl
				Elements paragraphs = doc1.body().children().select("p,h1,h2");//dt,code
	 		    
	      	    	for(Element p : paragraphs){
	      	    		if (!p.html().replaceAll("&nbsp;", "").trim().equals("") && !p.html().contains("img") 
	      	    				&& !p.text().replaceAll("⁂","").trim().equals("") && !p.text().replaceAll("☜ ☞", "").trim().equals("")
	      	    				)
	      	    		{
	      	    			p.remove();
	      	    			if (p.text() !=(" ") ){
	      	    			out.write("<"+p.tagName()+">" +p.text().trim());
	      	    			out.write(System.getProperty("line.separator"));}
	      	    	}}
      	    	out.flush();
      	    	out.close();

          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }}}

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}