package basic_lexical_compiler;

public class grammar {
	syntax_library syn = new syntax_library();
	public boolean program(String code) {
		boolean state = false;
		if(code.substring(0,5).equalsIgnoreCase(syn.syntax_table.get("Start"))){
			if((code.charAt(5)+"").equalsIgnoreCase(syn.syntax_table.get("{"))){
				if((code.charAt(code.length()-1)+"").equalsIgnoreCase(syn.syntax_table.get("}"))){
					state = program_body(code.substring(6, code.length()-1));
				}else{
					System.out.println("Syntax Error in : "+code.charAt(code.length()-1));
					System.out.println("Correct Syntax : "+syn.syntax_table.get("}"));
				}
			}else{
				System.out.println("Syntax Error in : "+code.charAt(5));
				System.out.println("Correct Syntax : "+syn.syntax_table.get("{"));
			}
		}else{
			System.out.println("Syntax Error in : "+code.substring(0,5));
			System.out.println("Correct Syntax : "+syn.syntax_table.get("Start"));
		}
		return state;
	}
	
	public boolean program_body(String code) {
		boolean state = false;
		
		return state;
	}
}
