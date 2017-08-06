package adv.comp.Aprlang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import basic_lexical_compiler.tokenizer;
import basic_lexical_compiler.tokens;

public class Reader {

	private Scanner input;

	public ArrayList<tokens> word_reader(String file_name){
		token_cheacker tk_chk = new token_cheacker();
		
			try {
				input = new Scanner(new File(file_name));  
				while(input.hasNext()) {
				   String word = input.next();
				   tk_chk.Check_token_grp(word);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	return tk_chk.tokened_code;		
	}

}

