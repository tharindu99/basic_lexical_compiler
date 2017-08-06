package adv.comp.grammar;

import java.util.ArrayList;

import adv.comp.Aprlang.token_cheacker;
import basic_lexical_compiler.tokens;

public class grammar {
	public boolean state = false;

	public grammar(ArrayList<tokens> tokend_arr) {
		state = program(tokend_arr);
		token_cheacker tk = new token_cheacker();
	}

	public boolean program(ArrayList<tokens> code) {
		boolean program_state = false;
		String ele_0 = code.get(0).token;
		String ele_1 = code.get(1).token;
		String ele_last = code.get(code.size()-1).token;

		if(ele_0.equalsIgnoreCase("Start")){
			if(ele_1.equalsIgnoreCase("{")){
				if(ele_last.equalsIgnoreCase("}")){
					program_state = true & program_body(); 
				}else {
					System.out.println("Syntactical error in parse tree level : "+ele_last);
				}
			}else {
				System.out.println("Syntactical error in parse tree level : "+ele_1);
			}
		}else{
			System.out.println("Syntactical error in parse tree level : "+ele_0);
		}
		
		
		// System.out.println(code.get(0));
		return program_state;
	}

	private boolean program_body() {
		
		return true;
	}
}
