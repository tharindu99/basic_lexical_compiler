package basic_lexical_compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lexical_compiler {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Lexical compiler..");
		
		String file_name = "src/basic_lexical_compiler/code";
		code_reader cr = new code_reader();
		String compressed_code = cr.reader(file_name); 
		
		
		grammar grm = new grammar();
		grm.program(compressed_code);
		//System.out.println(syn.syntax_table.get("}"));
	 
	}
	

}
