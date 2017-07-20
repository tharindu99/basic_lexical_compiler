package basic_lexical_compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class code_reader {
	
	public char[] reader(String file_name){
		char[] code_arr = null;
		try {
			FileReader fr=new FileReader(file_name);    
			int i,j=0;
			String code_compact = "";
			
			while((i=fr.read())!=-1){
				code_compact = code_compact+ (char)i;	 
			}
			String code_removeSpecs = code_compact.replace("\n", "")
												  .replace("\r", "")
												  .replaceAll("\\s", "");
			code_arr = code_removeSpecs.toCharArray();
			System.out.print("Compressed code : ");
			System.out.println(code_arr);
			
			  
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return code_arr;
	}
}
