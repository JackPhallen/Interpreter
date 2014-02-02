package Core;

import java.io.IOException;

public class DeclSeq {
	

	/* DeclSeq-
	 * 
	 * Purpose: Object oriented representation of DeclSeq part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Decl    d;
    private DeclSeq ds;

    public DeclSeq() {
        d  = null;
        ds = null;
    }

    void ParseDeclSeq() throws IOException {
        d = new Decl();
        d.ParseDecl();
        Interpreter.tokenizer.skipToken();
        if (!Interpreter.tokenizer.getToken().equals("begin")) {
            ds = new DeclSeq();
            ds.ParseDeclSeq();
        }
    }

    void PrintDeclSeq() {
        d.PrintDecl();
        if (ds != null) {
            ds.PrintDeclSeq();
        }
    }

    void ExecDeclSeq() {}
}