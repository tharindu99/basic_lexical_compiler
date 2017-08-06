package basic_lexical_compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import adv.comp.Aprlang.Reader;
import adv.comp.grammar.grammar;

public class lexical_compiler {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Lexical compiler..");
		
		String file_name = "src/basic_lexical_compiler/code";
		
		Reader red = new Reader();
		ArrayList<tokens> tokend_code = red.word_reader(file_name);
		System.out.println("Tokanizer passed..!!");
		
		grammar grm = new grammar();
		System.out.println("compiler state :"+grm.program(tokend_code));
		
		
		
	 
	}
	

}
