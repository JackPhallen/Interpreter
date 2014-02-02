package Core;

import java.io.IOException;

public class If {
	

	/* If-
	 * 
	 * Purpose: Object oriented representation of If part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Cond    c;
    private StmtSeq ss1;
    private StmtSeq ss2;

    public If() {
        c   = null;
        ss1 = null;
        ss2 = null;
    }

    void ParseIf() throws IOException {
        Interpreter.tokenizer.skipToken(); //skip if
        c = new Cond();
        c.ParseCond();
        Interpreter.tokenizer.skipToken(); //skip then
        ss1 = new StmtSeq();
        ss1.ParseStmtSeq();
        if (!Interpreter.tokenizer.getToken().equals("end")) {
            Interpreter.tokenizer.skipToken(); //skip else
            ss2 = new StmtSeq();
            ss2.ParseStmtSeq();
            Interpreter.tokenizer.skipToken(); //skip end and semicolon
            Interpreter.tokenizer.skipToken();
        } else {
            Interpreter.tokenizer.skipToken(); //skip end and semicolon
            Interpreter.tokenizer.skipToken();
        }
    }

    void PrintIf() {
        System.out.print("if ");
        c.PrintCond();
        System.out.println(" then");
    	TabControl.increaseTabCount();
        ss1.PrintStmtSeq();
        if (ss2 != null) {
        	TabControl.printTabs();
            System.out.println("else");
            ss2.PrintStmtSeq();
        }
        TabControl.decreaseTabCount();
        TabControl.printTabs();
        System.out.println("end;");
    }

    void ExecIf() {
        if (c.EvalCond()) {
            ss1.ExecStmtSeq();
        } else {
            ss2.ExecStmtSeq();
        }

    }
}