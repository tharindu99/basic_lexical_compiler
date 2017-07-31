package basic_lexical_compiler;

import java.util.ArrayList;

public class tokenizer {
	ArrayList<tokens> token_array = new ArrayList<tokens>();

	public tokenizer() {
		String Keyword[] = { "Start", "int", "float", "for" };
		String Operator[] = { "==", "=<", ">=", "!=", "++", "--", "=", "<",
				">", "+", "-", "/", "*", "%" };
		String Separator[] = { "{", "}", ";", "(", ")" };
		int token_counter = 0;

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

	public String[] code_tokenizer(String comp_code) {
		String tokend_code_level1 = comp_code;
		String duplicate_tokend_code_level1 = comp_code;
		
		for (int i = 0; i < token_array.size(); i++) {
			
			if (tokend_code_level1.contains(token_array.get(i).token)) {
				tokend_code_level1 = tokend_code_level1.replace(
						token_array.get(i).token, "#"
								+ token_array.get(i).token_group+"#");
				
			}
		}
		System.out.println("tokanized code level1 :" + tokend_code_level1);
		
		
		
		String tmp_tok_level1[] = tokend_code_level1.split("#", -1);
		String[] tmp_tok_level1_arr = remove_empty_element(tmp_tok_level1);

		for (int i = 0; i < tmp_tok_level1_arr.length; i++) {
			if (!tmp_tok_level1_arr[i].equalsIgnoreCase("keyword")
					&& !tmp_tok_level1_arr[i].equalsIgnoreCase("operator")
					&& !tmp_tok_level1_arr[i].equalsIgnoreCase("separator")
					&& !tmp_tok_level1_arr[i].equalsIgnoreCase("identifier")
					&& !tmp_tok_level1_arr[i].equalsIgnoreCase("literal")) {
				
				boolean identi_chk = identifier_cheacker(tmp_tok_level1_arr[i]);
					if (identi_chk) tmp_tok_level1_arr[i] = "identifier";
				
					if(!identi_chk){
						boolean leteral_chk = literal_cheacker(tmp_tok_level1_arr[i]);
							if(leteral_chk) tmp_tok_level1_arr[i] = "literal";
					}
				
				//System.out.println("identity check " + identi_chk + " : "+ tmp_tok_level1_arr[i]);
			}

		}
		
		
		
		
		
		/*for (int i = 0; i < tmp_tok_level1_arr.length; i++) {
			System.out.println(i+" "+tmp_tok_level1_arr[i]);
			c_tk_obj.pos_id = i;
			c_tk_obj.token_cat = tmp_tok_level1_arr[i];
		}*/
		
		return tmp_tok_level1_arr;
	}

	public String[] remove_empty_element(String[] arr) {
	
		ArrayList<String> out_arrlist = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].isEmpty()) {
				out_arrlist.add(arr[i]);
			}
		}
		String out_arr[] = out_arrlist.toArray(new String[0]);
		return out_arr;
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

	public boolean  literal_cheacker(String word) {
		boolean numeric_checker = true;
			if(!isNumeric(word)){
				numeric_checker = false;
			}
		return numeric_checker; 
	}
	
	public  boolean isNumeric(String str){
	    try{
	      double d = Double.parseDouble(str);
	    }
	    catch(NumberFormatException nfe){
	    	System.out.println("Error in literal : This is not valid literal = "+str);
	      return false;
	    }
	    return true;
	  }

	
}
