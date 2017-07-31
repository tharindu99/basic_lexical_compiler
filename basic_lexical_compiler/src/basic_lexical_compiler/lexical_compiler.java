package basic_lexical_compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import adv.comp.Aprlang.Reader;

public class lexical_compiler {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Lexical compiler..");
		
		String file_name = "src/basic_lexical_compiler/code";
		
		Reader red = new Reader();
		red.word_reader(file_name);
		
		
		
		
		/*code_reader cr = new code_reader();
		String compressed_code = cr.str_reader(file_name); 
		
		tokenizer tk = new tokenizer();
		String [] tokenized_code = tk.code_tokenizer(compressed_code);
		
		
		code_to_tokens c_tk_obj = new code_to_tokens();
		ArrayList<code_to_tokens>  c_tk = new ArrayList<code_to_tokens>();
		
		for (int i = 0; i < tokenized_code.length; i++) {
			
			
		}*/
		
		
		
	 
	}
	

}
