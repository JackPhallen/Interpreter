package Core;

import java.io.IOException;

public class Loop {
	
	/* Loop-
	 * 
	 * Purpose: Object oriented representation of Loop part of Core programming language. 
	 * Used for parsing, printing, and executing */
	
    private Cond    c;
    private StmtSeq ss;

    public Loop() {
        c  = null;
        ss = null;
    }

    void ParseLoop() throws IOException {
        Interpreter.tokenizer.skipToken(); //skip while
        c = new Cond();
        c.ParseCond();
        Interpreter.tokenizer.skipToken(); //skip loop
        ss = new StmtSeq();
        ss.ParseStmtSeq();
        Interpreter.tokenizer.skipToken(); //skip end and semicolon
        Interpreter.tokenizer.skipToken();  
    }

    void PrintLoop() {
        System.out.print("while ");
        c.PrintCond();
        System.out.println(" loop");
        TabControl.increaseTabCount();
        ss.PrintStmtSeq();
        TabControl.decreaseTabCount();
        TabControl.printTabs();
        System.out.println("end;");
    }

    void ExecLoop() {
        while (c.EvalCond()) {
            ss.ExecStmtSeq();
        }
    }
}