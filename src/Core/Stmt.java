package Core;

import java.io.IOException;

public class Stmt {
	
	/* StmtSeq-
	 * 
	 * Purpose: Object oriented representation of Stmt part of Core programming language. 
	 * Used for parsing, printing, and executing */
	
    private Assign a;
    private If     i;
    private Loop   loop;
    private Input  in;
    private Output out;

    public Stmt() {
        a    = null;
        i    = null;
        loop = null;
        in   = null;
        out  = null;
    }

    void ParseStmt() throws IOException {
        String token = Interpreter.tokenizer.getToken();

        if (token.equals("if")) {
            i = new If();
            i.ParseIf();
        } else if (token.equals("while")) {
            loop = new Loop();
            loop.ParseLoop();
        } else if (token.equals("read")) {
            in = new Input();
            in.ParseInput();
        } else if (token.equals("write")) {
            out = new Output();
            out.ParseOutput();
        } else {
            a = new Assign();
            a.ParseAssign();
        }
    }

    void PrintStmt() {
        if (a != null) {
            a.PrintAssign();
        }
        if (i != null) {
            i.PrintIf();
        }
        if (loop != null) {
            loop.PrintLoop();
        }
        if (in != null) {
            in.PrintInput();
        }
        if (out != null) {
            out.PrintOutput();
        }
    }

    void ExecStmt() {
        if (a != null) {
            a.ExecAssign();
        }
        if (i != null) {
            i.ExecIf();
        }
        if (loop != null) {
            loop.ExecLoop();
        }
        if (in != null) {
            in.ExecInput();
        }
        if (out != null) {
            out.ExecOutput();
        }
    }
}
