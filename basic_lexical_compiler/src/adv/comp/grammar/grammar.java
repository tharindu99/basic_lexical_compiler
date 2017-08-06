package adv.comp.grammar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import adv.comp.Aprlang.token_cheacker;
import basic_lexical_compiler.tokens;

public class grammar {
	// public boolean state = true;
	token_cheacker tk = new token_cheacker();

	public boolean program(ArrayList<tokens> code) {
		System.out.println("code array length to <program> : " + code.size());
		boolean program_state = true;
		String ele_0 = code.get(0).token;
		String ele_1 = code.get(1).token;
		String ele_last = code.get(code.size() - 1).token;

		if (ele_0.equalsIgnoreCase("Start")) {
			if (ele_1.equalsIgnoreCase("{")) {
				if (ele_last.equalsIgnoreCase("}")) {
					code.remove(0);
					code.remove(0);
					code.remove(code.size() - 1);
					program_state = program_body(code);

				} else {
					System.out
							.println("Syntactical error in parse tree level : "
									+ ele_last);
				}
			} else {
				System.out.println("Syntactical error in parse tree level : "
						+ ele_1);
			}
		} else {
			System.out.println("Syntactical error in parse tree level : "
					+ ele_0);
		}

		// System.out.println(code.get(0));
		return program_state;
	}

	public boolean program_body(List<tokens> code) {
		System.out.println("<program_body> " + code.size());
		/*
		 * for (int i = 0; i < code.size(); i++) {
		 * System.out.print(code.get(i).token+" "); }
		 */
		boolean state = true;
		if (code.size() == 0) {
			// empty clouser
			state = true;
		} else if (code.get(0).token.equalsIgnoreCase("for")) { // loop occured
			code.remove(0);
			if (code.get(0).token.equalsIgnoreCase("(")) {
				int close_bracket_index = bracket_spliter(code, "(", ")");
				List<tokens> loop_header = code.subList(1, close_bracket_index);
				// oooo -> state = true & loop_header();
				System.out.print("loop header : ");
				for (tokens tokens : loop_header) {
					System.out.print(tokens.token + " ");
				}
				System.out.println();
				code = code.subList(close_bracket_index + 1, code.size());

				/*
				 * for (int i = 0; i <= close_bracket_index; i++) {
				 * if(code.size()>0)code.remove(0); }
				 */

				// loop body detector
				if (code.get(0).token.equalsIgnoreCase("{")) {
					int loop_close_bracket_index = bracket_spliter(code, "{",
							"}");
					List<tokens> loop_body = code.subList(1,
							loop_close_bracket_index);
					// System.out.println("loop body length "+loop_close_bracket_index);

					System.out.print("loop body[size "
							+ loop_close_bracket_index + "] : ");
					for (tokens tokens : loop_body) {
						System.out.print(tokens.token + " ");
					}
					System.out.println();

					// List<tokens> etc_arr =
					// code.subList(loop_close_bracket_index+1, code.size()-1);

					state = state & program_body(loop_body);
					code.remove(0);
					code.remove(0);

				} else {
					state = false;
					System.out
							.println("syntax error : loop body doesn't defined correctly.");
				}

			} else {
				state = false;
				System.out
						.println("syntax error : loop header doesn't defined correctly.");
			}

			// System.out.println("after loop code length : "+code.size());
			state = state & program_body(code);
		} else {
			// statement identify here
			int cnt = 0;
			for (int i = 0; i < code.size(); i++) {
				if (code.get(i).token.equalsIgnoreCase(";")) {
					cnt = i;
					break;
				}
			}
			List<tokens> statement = code.subList(0, cnt);

			System.out.print("statement : ");
			for (tokens tokens : statement) {
				System.out.print(tokens.token + " ");
			}
			System.out.println();
			state = state & statement_chk(statement);

			for (int i = 0; i <= cnt; i++) {
				code.remove(0);
			}
			state = state & program_body(code);
		}

		return state;
	}

	public boolean statement_chk(List<tokens> code) {
		System.out.print("statement validation ");
		boolean state = stmt_init(code);
		if(state)System.out.println("init state : " + state);
		state = stmt_operation(code);
		if(state)System.out.println("operation state : " + state);
		return state ;
	}

	private boolean stmt_init(List<tokens> code) {
		boolean state = false;
		if (code.size() == 4) {
			if (code.get(0).token.equalsIgnoreCase("int")
					| code.get(0).token.equalsIgnoreCase("float")) {
				if (variable_name(code.get(1))) {
					if (code.get(2).token.equalsIgnoreCase("=")) {
						if (tk.isNumeric(code.get(3).token)) {
							state = true;
						}
					}
				}
			}
		}
		return state;
	}

	private boolean integer_init(List<tokens> code) {
		boolean state = false;
		if (code.size() > 0) {
			if (code.get(0).token.equalsIgnoreCase("int")) {
				code.remove(0);
				if (variable_name(code.get(0))) {
					code.remove(0);
					if (code.get(0).token.equalsIgnoreCase("=")) {
						code.remove(0);
						if (digit(code)) {
							state = true;
						}
					}
				}
			}
		}
		return state;
	}

	private boolean digit(List<tokens> code) {
		String str = "";
		for (int i = 0; i < code.size(); i++) {
			str = str + code.get(i).token;
		}
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	private boolean floating(List<tokens> code) {
		String base = "";
		for (int i = 0; i < code.size(); i++) {
			base = base + code.get(i).token;
		}

		return tk.isNumeric(base);
	}

	private boolean variable_name(tokens tokens) {
		boolean state = false;
		String wd = tokens.token;
		for (int i = 0; i < wd.length(); i++) {
			state = Character.isLetter(wd.charAt(i));
			if (!state)
				break;
		}
		return state;
	}

	private boolean floating_init(List<tokens> code) {
		boolean state = false;
		if (code.size() > 0) {
			if (code.get(0).token.equalsIgnoreCase("float")) {
				code.remove(0);
				if (variable_name(code.get(0))) {
					code.remove(0);
					if (code.get(0).token.equalsIgnoreCase("=")) {
						code.remove(0);
						if (floating(code)) {
							state = true;
						}
					}
				}
			}
		}
		return state;
	}

	public boolean stmt_operation(List<tokens> code) {
		boolean state = false;
		
		if(code.size()>2){
			if(variable_name(code.get(0))){
				if(code.get(1).token.equalsIgnoreCase("=")){
					if(math_operation(code.subList(2, code.size()))){
						state = true;
					}
				}
			}
		}
		return state;
	}

	private boolean math_operation(List<tokens> code) {
		//System.out.println("llll :"+code.size());
		boolean state = false;
		
		if(code.size()==2){
			state = false;
			System.out.println("statement not valied ");
			System.exit(0);
		}else {
		if(code.size()>=1){
			if(variable_name(code.get(0))){
				if(code.size()==1){
					state = true;
				}
				else if(code.size()>=3){		
					if(arithmatic_operator(code.get(1))){
						if(math_operation(code.subList(2, code.size()))){
							state = true;
						}
					}
				}
			}else if(tk.isNumeric(code.get(0).token)){
				state = true;
			}
		}
		}
		return state;
	}

	private boolean arithmatic_operator(tokens tokens) {
		boolean state = false;
		String arith[] = { "+","-","*","/"};
		for (int i = 0; i < arith.length; i++) {
			if(tokens.token.equalsIgnoreCase(arith[i])){
				state = true ;
				break;
			}
		}
		return state;
	}

	private boolean numeric(List<tokens> code) {
		return digit(code) | floating(code);
	}

	public int bracket_spliter(List<tokens> code, String startbracket,
			String endbracket) {
		// should send some thing like asdsf {} }
		/*
		 * System.out.println("bracketer"); for (int i = 0; i < code.size();
		 * i++) { System.out.print(code.get(i).token+" "); }
		 * System.out.println();
		 */
		int outline = 0;
		Stack<tokens> tokan_stk = new Stack<tokens>();
		for (int i = 0; i < code.size(); i++) {
			if (code.get(i).token.equalsIgnoreCase(startbracket)) {
				// System.out.println("outline :"+outline);
				tokan_stk.push(code.get(i));
			} else if (code.get(i).token.equalsIgnoreCase(endbracket)) {
				tokan_stk.pop();
			}
			if (tokan_stk.isEmpty()) {
				outline = i;
				break;
			}
		}
		if (outline == 0) {
			System.out.println("syntatic error : " + startbracket
					+ " not closed properly");
			System.exit(0);
		}
		return outline;
	}
}
