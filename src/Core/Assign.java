package Core;

import java.io.IOException;

public class Assign {
	

	/* Assign-
	 * 
	 * Purpose: Object oriented representation of Assign part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Id  id;
    private Exp exp;

    public Assign() {
        id  = null;
        exp = null;
    }

    void ParseAssign() throws IOException {
        id = Id.ParseId();
        Interpreter.tokenizer.skipToken(); //skip id
        Interpreter.tokenizer.skipToken(); //skip =
        exp = new Exp();
        exp.ParseExp();
        Interpreter.tokenizer.skipToken(); //skip semicolon
    }

    void PrintAssign() {
        id.PrintId();
        System.out.print(" = ");
        exp.PrintExp();
        System.out.println(";");
    }

    void ExecAssign() {
        id.setVal(exp.getVal());
    }
}
