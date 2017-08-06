package adv.comp.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import adv.comp.Aprlang.token_cheacker;
import basic_lexical_compiler.tokens;

public class grammar {
	//public boolean state = true;

	public boolean program(ArrayList<tokens> code) {
		System.out.println("code array length to <program> : "+code.size());
		boolean program_state = true;
		String ele_0 = code.get(0).token;
		String ele_1 = code.get(1).token;
		String ele_last = code.get(code.size()-1).token;

		if(ele_0.equalsIgnoreCase("Start")){
			if(ele_1.equalsIgnoreCase("{")){
				if(ele_last.equalsIgnoreCase("}")){
					code.remove(0);
					code.remove(0);
					code.remove(code.size()-1);
					program_state =  program_body(code); 
					
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

	public boolean program_body(List<tokens> code) {
		System.out.println("<program_body> "+code.size());
		boolean state = true;
		if(code.size() == 0){
			//empty clouser
			state = true;
		}else if (code.get(0).token.equalsIgnoreCase("for")) { //loop occured
			code.remove(0);
			if(code.get(0).token.equalsIgnoreCase("(")){
				int close_bracket_index = bracket_spliter(code, "(",")");
				List<tokens> loop_header = code.subList(1, close_bracket_index);
	//oooo ->   state = true & loop_header();	
					System.out.print("loop header : ");
					for (tokens tokens : loop_header) {
						System.out.print(tokens.token +" ");
					}
					System.out.println();
					code = code.subList(close_bracket_index+1, code.size());
					
					/*for (int i = 0; i <= close_bracket_index; i++) {
					if(code.size()>0)code.remove(0);
					}*/
				
				//loop body detector	
				if(code.get(0).token.equalsIgnoreCase("{")){
					int loop_close_bracket_index = bracket_spliter(code, "{", "}");
					List<tokens> loop_body = code.subList(1, loop_close_bracket_index);
					//System.out.println("loop body length "+loop_close_bracket_index);
					
					System.out.print("loop body[size "+loop_close_bracket_index+"] : ");
					for (tokens tokens : loop_body) {
						System.out.print(tokens.token +" ");
					}
					System.out.println();

					//List<tokens> etc_arr = code.subList(loop_close_bracket_index+1, code.size()-1);

					state = state & program_body(loop_body);
					code.remove(0);
					code.remove(0);
					
				}else{
					state = false;
					System.out.println("syntax error : loop body doesn't defined correctly.");
				}
				
			}else{
				state = false;
				System.out.println("syntax error : loop header doesn't defined correctly.");
			}
			
			//System.out.println("after loop code length : "+code.size());
			state = state & program_body(code);
		}else {
			//statement identify here 
			int cnt = 0;
			for (int i = 0; i < code.size(); i++) {
				if(code.get(i).token.equalsIgnoreCase(";")){
					cnt = i;
					break;
				}
			}
			List<tokens> statement = code.subList(0, cnt);
			
				System.out.print("statement : ");
				for (tokens tokens : statement) {
					System.out.print(tokens.token);
				}
				System.out.println();
				state = state & statement(statement);
			
			for (int i = 0; i <= cnt; i++) {
				code.remove(0);
			}
			state = state & program_body(code);
		}
		
		return state;
	}

	public boolean statement(List<tokens> code) {
		System.out.println("statement validation");
		for (int i = 0; i < code.size(); i++) {
			System.out.print(code.get(i).token+"");
		}
		System.out.println();
		return true;
	}
	
	public int bracket_spliter(List<tokens> code,String startbracket, String endbracket){
		// should send some thing like  asdsf {} }
		/*System.out.println("bracketer");
		for (int i = 0; i < code.size(); i++) {
			System.out.print(code.get(i).token+" ");
		}
		System.out.println();*/
		int outline = 0;
		Stack<tokens> tokan_stk = new Stack<tokens>();
		for (int i = 0; i < code.size(); i++) {
			 if(code.get(i).token.equalsIgnoreCase(startbracket)){
				//System.out.println("outline :"+outline);
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
