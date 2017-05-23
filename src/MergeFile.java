package snippet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
public class MergeFile {


	


	    public static void main(String[] args) throws Exception {
	    	//new file that store the merged data
			String mergedFilePath = "paragraph_En.txt";
			
			
			//folder that contain the files which want to merge together
			File folder = new File("books/w/");
			File[] files = folder.listFiles();
			File mergedFile = new File(mergedFilePath);
			mergeFiles(files, mergedFile);
		}
	 
		public static void mergeFiles(File[] files, File mergedFile) {
	 

			BufferedWriter out = null;
			try {

				 out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mergedFile),"UTF-8"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	 
			for (File f : files) {
				System.out.println("merging: " + f.getName());
				try {
			
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
					
					String aLine;
					while ((aLine = in.readLine()) != null) {
						out.write(aLine);
						out.newLine();
					}
	 
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 
		}
	}