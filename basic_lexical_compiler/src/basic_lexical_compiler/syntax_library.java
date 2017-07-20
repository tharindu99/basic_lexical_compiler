package basic_lexical_compiler;

import java.util.ArrayList;
import java.util.Hashtable;

public class syntax_library {
	Hashtable<String, String>  syntax_table = new Hashtable<String, String>();
	
	public syntax_library() {
		String []syntax_arr = {"Start","{","}"};

		for (int i = 0; i < syntax_arr.length; i++) {
			syntax_table.put(syntax_arr[i],syntax_arr[i]);
		}
		
		
		
	}
}
