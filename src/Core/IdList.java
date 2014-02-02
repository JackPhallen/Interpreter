package Core;

import java.io.IOException;

public class IdList {
	
	/* IdList-
	 * 
	 * Purpose: Object oriented representation of IdList part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private Id id;
    private IdList il;

    public IdList() {
        id = null;
        il = null;
    }

    void ParseIdList() throws IOException {
        id = Id.ParseId();
        Interpreter.tokenizer.skipToken(); // skip id
        if (!Interpreter.tokenizer.getToken().equals(";")) {
            Interpreter.tokenizer.skipToken(); // skip ,
            il = new IdList();
            il.ParseIdList();
        }
    }

    void PrintIdList() {
        id.PrintId();
        if (il != null) {
            System.out.print(",");
            il.PrintIdList();
        } else {
            System.out.println(";");
        }
    }

    void WriteIdList() {
        id.OutputId();
        if (il != null) {
            il.WriteIdList();
        }
    }

    public int ReadIdList(int pos) {
        id.setVal(Input.getVal(pos));
        if (il != null) {
            il.ReadIdList(pos + 1);
        }

        return pos + 1;
    }
}