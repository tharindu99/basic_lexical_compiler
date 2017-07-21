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
		
		tokenizer tk = new tokenizer();
		String [] tokenized_code = tk.code_tokenizer(compressed_code);
		//System.out.println(tk.token_array.get(0).token_group);
		
	 
	}
	

}
