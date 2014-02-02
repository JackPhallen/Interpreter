package Core;

import java.io.IOException;

public class StmtSeq {
	
	/* StmtSeq-
	 * 
	 * Purpose: Object oriented representation of StmtSeq part of Core programming language. 
	 * Used for parsing, printing, and executing */
    private Stmt    st;
    private StmtSeq sq;

    public StmtSeq() {
        st = null;
        sq = null;
    }

    void ParseStmtSeq() throws IOException {
        st = new Stmt();
        st.ParseStmt();
        String token = Interpreter.tokenizer.getToken();
        if (!token.equals("end") &&!token.equals("else")) {
            sq = new StmtSeq();
            sq.ParseStmtSeq();
        }
    }

    void PrintStmtSeq() {
        TabControl.printTabs();
        st.PrintStmt();
        if (sq != null) {
            sq.PrintStmtSeq();
        }
    }

    void ExecStmtSeq() {
        st.ExecStmt();
        if (sq != null) {
            sq.ExecStmtSeq();
        }
    }
}
