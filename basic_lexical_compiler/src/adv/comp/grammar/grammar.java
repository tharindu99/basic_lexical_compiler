package adv.comp.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import adv.comp.Aprlang.token_cheacker;
import basic_lexical_compiler.tokens;

public class grammar {
	public boolean state = false;

	public boolean program(ArrayList<tokens> code) {
		System.out.println("code array length to <program> : "+code.size());
		boolean program_state = false;
		String ele_0 = code.get(0).token;
		String ele_1 = code.get(1).token;
		String ele_last = code.get(code.size()-1).token;

		if(ele_0.equalsIgnoreCase("Start")){
			if(ele_1.equalsIgnoreCase("{")){
				if(ele_last.equalsIgnoreCase("}")){
					code.remove(0);
					code.remove(0);
					code.remove(code.size()-1);
					program_state = true & program_body(code); 
					
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

	private boolean program_body(ArrayList<tokens> code) {
		boolean state = false;
		System.out.println("calling to program_body length");
		/*for (int i = 0; i < code.size(); i++) {
			System.out.println("jj :"+code.get(i).token);
		}*/
		if(code.size() == 0){
			//empty clouser
			state = true;
		}else if (code.get(0).token.equalsIgnoreCase("for")) { //loop occured
			code.remove(0);
			if(code.get(0).token.equalsIgnoreCase("(")){
				code.remove(0);
				int close_bracket_index = bracket_spliter(code, "(",")");
				List<tokens> loop_header = code.subList(0, close_bracket_index);
				
					
				for (int i = 0; i <= close_bracket_index; i++) {
						code.remove(0);
					}
				
				//loop body detector	
				if(code.get(0).token.equalsIgnoreCase("{")){
					code.remove(0);
					int loop_close_bracket_index = bracket_spliter(code, "{", "}");
					List<tokens> loop_body = code.subList(0, loop_close_bracket_index);
					
					
					for (int i = 0; i <= loop_close_bracket_index; i++) {
						code.remove(0);
					}
					
					
					
				}else{
					System.out.println("syntax error : loop body doesn't defined correctly.");
				}
				
			}else{
				System.out.println("syntax error : loop header doesn't defined correctly.");
			}
			
			System.out.println("after loop : "+code.size());
			
		}else {
			
		}
		
		
		return state;
	}
	
	public int bracket_spliter(ArrayList<tokens> code,String startbracket, String endbracket){
		// should send some thing like { asdsf {} }
		System.out.println("bracket spliter");
		for (int i = 0; i < code.size(); i++) {
			System.out.print(code.get(i).token+" ");
		}
		System.out.println();
		int outline = 0;
		Stack<tokens> tokan_stk = new Stack<tokens>();
		tokan_stk.push(code.get(0));
		for (int i = 1; i < code.size(); i++) {
			 if(code.get(i).token.equalsIgnoreCase(startbracket)){
				System.out.println("outline :"+outline);
				tokan_stk.push(code.get(i));
			}else if(code.get(i).token.equalsIgnoreCase(endbracket)){
				tokan_stk.pop();
			}
			if(tokan_stk.isEmpty()){
					outline = i;
					break;
			}
		}
		if(outline==0){
			System.out.println("syntatic error : "+startbracket +" not closed properly");
			System.exit(0);
		}
		return outline;
	}
}
