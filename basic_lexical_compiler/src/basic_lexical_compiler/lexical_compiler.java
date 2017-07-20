package basic_lexical_compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lexical_compiler {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Lexical compiler..");
		
		String file_name = "src/basic_lexical_compiler/code";
		code_reader cr = new code_reader();
		char [] compressed_code = cr.reader(file_name); 
		
		syntax_library syn = new syntax_library();
		//System.out.println(syn.syntax_table.get("}"));
	 
	}
	

}
