package basic_lexical_compiler;

import java.util.ArrayList;

public class tokenizer {
	ArrayList<tokens> token_array = new ArrayList<tokens>();
	public tokenizer() {
		String Keyword[] = {"Start","int","float","for"};
		String Operator[]= {"==","=<",">=","!=","++","--","=","<",">","+","-","/","*","%"};
		String Separator[]={"{","}" ,";" ,"(" ,")"};
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
	String tokend_code = comp_code;
	
	for (int i = 0; i < token_array.size(); i++) {
		if(tokend_code.contains(token_array.get(i).token)){
			tokend_code = tokend_code.replace(token_array.get(i).token, "#"+token_array.get(i).token_group+"#");
		}
	}
	System.out.println("tokanized code :"+tokend_code);	
	
	return null;
	}
	
	
}
