package Core;

import java.io.IOException;

public class Output {
	
	/* Output-
	 * 
	 * Purpose: Object oriented representation of Output part of Core programming language. 
	 * Used for parsing, printing, and executing */
	
    private IdList idl;

    public Output() {
        idl = null;
    }

    void ParseOutput() throws IOException{
        Interpreter.tokenizer.skipToken(); //skip write
        idl = new IdList();
        idl.ParseIdList();
        Interpreter.tokenizer.skipToken(); //skip ;
    }

    void PrintOutput(){
        System.out.print("write ");
        idl.PrintIdList();
    }

    void ExecOutput(){
        idl.WriteIdList();
    }
}