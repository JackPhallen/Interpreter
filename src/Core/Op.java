package Core;

import java.io.IOException;

public class Op {
	
	/* Op-
	 * 
	 * Purpose: Object oriented representation of Op part of Core programming language. 
	 * Used for parsing, printing, and executing */
	
    private Integer i;
    private Id id;
    private Exp exp;

    public Op() {
        i = null;
        id = null;
        exp = null;
    }

    void ParseOp() throws IOException{
        String token = Interpreter.tokenizer.getToken();
        if(token.equals("(")){
            Interpreter.tokenizer.skipToken(); //skip left parentheses
            exp = new Exp();
            exp.ParseExp();
            Interpreter.tokenizer.skipToken(); //skip right parentheses
        }
        else if (token.matches("\\d*")) {
            i = Integer.valueOf(token);
            Interpreter.tokenizer.skipToken(); 
        }
        else {
            id = Id.ParseId();
            Interpreter.tokenizer.skipToken(); //skip id
        }
    }

    void PrintOp(){
        if(i != null) System.out.print(i);
        if(id != null) id.PrintId();
        if(exp != null) {
            System.out.print("(");
            exp.PrintExp();
            System.out.print(")");
        }
    }

    public int getVal() {
        if (i != null) {
            return i;
        } else if(id != null){
            return id.getVal();
        } else if (exp != null) {
            return exp.getVal();
        }

        return 0;
    }
}
