package Core;

import java.io.IOException;

public class Exp {
	

	/* Exp-
	 * 
	 * Purpose: Object oriented representation of Exp part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Fac     fac;
    private Exp     exp;
    private Boolean plus;
    private Boolean minus;

    public Exp() {
        fac   = null;
        exp   = null;
        plus  = false;
        minus = false;
    }

    void ParseExp() throws IOException {
        fac = new Fac();
        fac.ParseFac();

        String token = Interpreter.tokenizer.getToken();

        if (token.equals("+")) {
            plus = true;
            Interpreter.tokenizer.skipToken(); //skip +
            exp = new Exp();
            exp.ParseExp();
        } else if (token.equals("-")) {
            minus = true;
            Interpreter.tokenizer.skipToken(); //skip -
            exp = new Exp();
            exp.ParseExp();
        }
    }

    void PrintExp() {
        if (fac != null) {
            fac.PrintFac();
        }
        if (plus) {
            System.out.print("+");
        }
        if (minus) {
            System.out.print("-");
        }
        if (exp != null) {
            exp.PrintExp();
        }
    }

    public int getVal() {
        if (exp == null) {
            return fac.getVal();
        } else {
            if (plus) {
                return fac.getVal() + exp.getVal();
            } else if (minus) {
                return fac.getVal() - exp.getVal();
            }
        }

        return 0;
    }
}