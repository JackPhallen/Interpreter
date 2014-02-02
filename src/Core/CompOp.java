package Core;

import java.io.IOException;

public class CompOp {

	/* CompOp-
	 * 
	 * Purpose: Object oriented representation of CompOp part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private String symbol;

    void ParseCompOp() throws IOException {
        String token = Interpreter.tokenizer.getToken();

        if (token.equals("!=")) {
            symbol = "!=";
        }
        if (token.equals("==")) {
            symbol = "==";
        }
        if (token.equals(">")) {
            symbol = ">";
        }
        if (token.equals("<")) {
            symbol = "<";
        }
        if (token.equals(">=")) {
            symbol = ">=";
        }
        if (token.equals("<=")) {
            symbol = "<=";
        }
        Interpreter.tokenizer.skipToken();    // comparison op
    }

    void PrintCompOp() {
    	System.out.print(symbol);
    }

    public String getOp() {
        return symbol;
    }
}