package Core;

import java.io.IOException;

public class Cond {
	

	/* Cond-
	 * 
	 * Purpose: Object oriented representation of Cond part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Comp    comp;
    private Cond    c1;
    private Cond    c2;
    private Boolean and;
    private Boolean or;
    private Boolean negated;

    public Cond() {
        comp    = null;
        c1      = null;
        c2      = null;
        and     = false;
        or      = false;
        negated = false;
    }

    void ParseCond() throws IOException {
        String token = Interpreter.tokenizer.getToken();

        if (token.equals("(")) {
            comp = new Comp();
            comp.ParseComp();
        } else if (token.equals("!")) {
            Interpreter.tokenizer.skipToken(); //skip !
            negated = true;
            c1      = new Cond();
            c1.ParseCond();
        } else if (token.equals("[")) {
            Interpreter.tokenizer.skipToken(); //skip [
            c1 = new Cond();
            c1.ParseCond();
            token = Interpreter.tokenizer.getToken();
            if (token.equals("&&")) {
                Interpreter.tokenizer.skipToken(); //skip &&
                and = true;
                c2  = new Cond();
                c2.ParseCond();
                Interpreter.tokenizer.skipToken(); //skip ]
            } else if (token.equals("||")) {
                Interpreter.tokenizer.skipToken(); //skip ||
                or = true;
                c2 = new Cond();
                c2.ParseCond();
                Interpreter.tokenizer.skipToken(); //skip ]
            }
        }
    }

    void PrintCond() {
        if (negated) {
            System.out.print("!");
        }
        if (and) {
            System.out.print("[");
            c1.PrintCond();
            System.out.print("&&");
            c2.PrintCond();
            System.out.print("]");
        } else if (or) {
            System.out.print("[");
            c1.PrintCond();
            System.out.print("||");
            c2.PrintCond();
            System.out.print("]");
        } else {
            if (c1 != null) {
                c1.PrintCond();
            } else {
                comp.PrintComp();
            }
        }
    }

    public boolean EvalCond() {
        if (and) {
            return (c1.EvalCond() && c2.EvalCond());
        } else if (or) {
            return (c1.EvalCond() || c2.EvalCond());
        } else {
            if (c1 != null) {
                return c1.EvalCond();
            } else {
                return comp.EvalComp();
            }
        }
    }
}
