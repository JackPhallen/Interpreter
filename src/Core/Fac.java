package Core;

import java.io.IOException;

public class Fac {

	/* Fac-
	 * 
	 * Purpose: Object oriented representation of Fac part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Op      op;
    private Fac     fac;
    private Boolean mult;

    public Fac() {
        op   = null;
        fac  = null;
        mult = false;
    }

    void ParseFac() throws IOException {
        op = new Op();
        op.ParseOp();
        if (Interpreter.tokenizer.getToken().equals("*")) {
            mult = true;
            Interpreter.tokenizer.skipToken(); //skip *
            fac = new Fac();
            fac.ParseFac();
        }
    }

    void PrintFac() {
        if (op != null) {
            op.PrintOp();
        }
        if (mult) {
            System.out.print("*");
        }
        if (fac != null) {
            fac.PrintFac();
        }
    }

    public int getVal() {
        if (!mult) {
            return op.getVal();
        } else {
            return op.getVal()*fac.getVal();
        }
    }
}