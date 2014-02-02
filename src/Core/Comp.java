package Core;

import java.io.IOException;

public class Comp {
	

	/* Comp-
	 * 
	 * Purpose: Object oriented representation of Comp part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Op     op1;
    private Op     op2;
    private CompOp cop;

    public Comp() {
        op1    = null;
        op2    = null;
        cop = null;
    }

    void ParseComp() throws IOException {
        Interpreter.tokenizer.skipToken(); //skip left parentheses
        op1 = new Op();
        op1.ParseOp();
        cop = new CompOp();
        cop.ParseCompOp();
        op2 = new Op();
        op2.ParseOp();
        Interpreter.tokenizer.skipToken(); //skip right parentheses
    }

    void PrintComp() {
        System.out.print("(");
        op1.PrintOp();
        cop.PrintCompOp();
        op2.PrintOp();
        System.out.print(")");
    }

    public boolean EvalComp() {
        Boolean result = false;

        if (cop.getOp() == "==") {
            result = op1.getVal() == op2.getVal();
        } else if (cop.getOp() == "!=") {
            result = op1.getVal() != op2.getVal();
        } else if (cop.getOp() == "<") {
            result = op1.getVal() < op2.getVal();
        } else if (cop.getOp() == ">") {
            result = op1.getVal() > op2.getVal();
        } else if (cop.getOp() == "<=") {
            result = op1.getVal() <= op2.getVal();
        } else if (cop.getOp() == ">=") {
            result = op1.getVal() >= op2.getVal();
        }

        return result;
    }
}