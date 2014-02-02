package Core;

import java.io.IOException;

public class Decl {
	

	/* Decl-
	 * 
	 * Purpose: Object oriented representation of Decl part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private IdList il;

    public Decl() {
        il = null;
    }

    void ParseDecl() throws IOException {
        if(Interpreter.tokenizer.getToken().equals("int"))
            Interpreter.tokenizer.skipToken();  //skip int
        il = new IdList();
        il.ParseIdList();
    }

    void PrintDecl() {
        System.out.print("int ");
        il.PrintIdList();
    }

    void ExecDecl() {}
}