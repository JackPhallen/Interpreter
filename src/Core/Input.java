package Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Input {
	
	/* Input-
	 * 
	 * Purpose: Object oriented representation of Input part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private static int pos   = 0;
    private static List<String> input = new ArrayList<String>();

    static {
    	/*First place all ints from input into arrayList*/
        	input = Arrays.asList(Interpreter.dataString.split("\\s+")); 
    }

    private IdList idl;

    public Input() {
        idl = null;
    }

    void ParseInput() throws IOException {
        Interpreter.tokenizer.skipToken(); //skip read
        idl = new IdList();
        idl.ParseIdList();
        Interpreter.tokenizer.skipToken(); //skip ;
    }

    void PrintInput() {
        System.out.print("read ");
        idl.PrintIdList();
    }

    void ExecInput() {
        pos = idl.ReadIdList(pos);
    }

    public static int getVal(int p) {
        if (p >= input.size()) {
            System.out.println("Error in data file.");
        }

        return Integer.valueOf(input.get(p));
    }
}