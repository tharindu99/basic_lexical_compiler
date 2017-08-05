package adv.comp.Aprlang;

import java.util.ArrayList;

import basic_lexical_compiler.tokens;

public class token_cheacker {
	ArrayList<tokens> token_array = new ArrayList<tokens>();

	public token_cheacker() {

		String Keyword[] = { "Start", "int", "float", "for" };
		String Operator[] = { "==", "=<", ">=", "!=", "++", "--", "=", "<",
				">", "+", "-", "/", "*", "%" };
		String Separator[] = { "{", "}", ";", "(", ")" };
		int token_counter = 0;
		String seperator_symbols = "\\s";
		for (int i = 0; i < Separator.length; i++) {
			seperator_symbols = seperator_symbols + "," + Separator[i];
		}
		// System.out.println("ssss :"+seperator_symbols);
		for (int i = 0; i < Keyword.length; i++) {
			tokens tk = new tokens();
			tk.token_id = token_counter++;
			tk.token_group = "keyword";
			tk.token = Keyword[i];
			token_array.add(tk);
		}
		for (int i = 0; i < Operator.length; i++) {
			tokens tk = new tokens();
			tk.token_id = token_counter++;
			tk.token_group = "operator";
			tk.token = Operator[i];
			token_array.add(tk);
		}
		for (int i = 0; i < Separator.length; i++) {
			tokens tk = new tokens();
			tk.token_id = token_counter++;
			tk.token_group = "separator";
			tk.token = Separator[i];
			token_array.add(tk);
		}
	}

	public tokens[] Check_token_grp(String word) {
		String tokend_code_level1 = word;
		for (int i = 0; i < token_array.size(); i++) {
			if (tokend_code_level1.contains(token_array.get(i).token)) {
				tokend_code_level1 = tokend_code_level1.replace(
						token_array.get(i).token, "#$"
								+ token_array.get(i).token_id + "#");
			}
		}
		String temp2_word[] = tokend_code_level1.split("#");
		int tmp_cnt = 0;
		ArrayList<tokens> out_tokenchaker = new ArrayList<tokens>();
		for (int i = 0; i < temp2_word.length; i++) {
			if (temp2_word[i].length() != 0) {
				if (temp2_word[i].charAt(0) == '$') {
					int tmp_id = Integer.parseInt(temp2_word[i].substring(1));
					tokens tk = new tokens();
					tk.token_id = tmp_id;
					tk.token = token_array.get(tmp_id).token;
					tk.token_group = token_array.get(tmp_id).token_group;
					out_tokenchaker.add(tk);
				} else {
					// out_tokenchaker[tmp_cnt++].token = temp2_word[i];
					tokens tk = new tokens();
					if(identifier_cheacker(temp2_word[i])){
						tk.token = temp2_word[i];
						tk.token_group = "Identifier";
						tk.token_id = -1;
					}else if(literal_cheacker(temp2_word[i])){
						tk.token = temp2_word[i];
						tk.token_group = "Leterals";
						tk.token_id = -2;
					}else{
						System.out.println("Unusal syntax usage..");
						System.exit(0);
					}
					out_tokenchaker.add(tk);
				}
			}
		}
		
		
		for (int i = 0; i < out_tokenchaker.size(); i++) {
			System.out.println(out_tokenchaker.get(i).token + " : "
					+ out_tokenchaker.get(i).token_id + " : "
					+ out_tokenchaker.get(i).token_group );
		}

		return null;
	}

	public boolean identifier_cheacker(String word) {
		boolean alphebet_checker = true;
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isLetter(word.charAt(i))) {
				alphebet_checker = false;
			}
		}
		return alphebet_checker;
	}

	public boolean literal_cheacker(String word) {
		boolean numeric_checker = true;
		if (!isNumeric(word)) {
			numeric_checker = false;
		}
		return numeric_checker;
	}

	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			System.out
					.println("Error in literal : This is not valid literal = "
							+ str);
			return false;
		}
		return true;
	}

}
