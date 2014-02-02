package Core;

import java.io.IOException;

public class Prog {
	
	/* StmtSeq-
	 * 
	 * Purpose: Object oriented representation of Prog - the main part of Core programming language. 
	 * Used for parsing, printing, and executing */
	
    private DeclSeq ds;
    private StmtSeq ss;

    public Prog(){
        ds = null;
        ss = null;
    }

    void ParseProg() throws IOException{
        Interpreter.tokenizer.skipToken(); //skip program
        ds = new DeclSeq();
        ds.ParseDeclSeq(); 
        Interpreter.tokenizer.skipToken(); //skip begin
        ss = new StmtSeq();
        ss.ParseStmtSeq();
    }

    void PrintProg(){
        System.out.println("program");
        TabControl.increaseTabCount();
        TabControl.printTabs();
        ds.PrintDeclSeq();
        System.out.println("begin");
        ss.PrintStmtSeq();
        TabControl.decreaseTabCount();
        System.out.println("end");
    }

    void ExecProg(){
    	ds.ExecDeclSeq();
        ss.ExecStmtSeq();
    }
}
