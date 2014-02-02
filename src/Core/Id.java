package Core;

import java.util.HashMap;

public class Id {
	

	/* Id-
	 * 
	 * Purpose: Object oriented representation of Id part of Core programming language. 
	 * Used for parsing, printing, and executing. */
	
    private static HashMap<String, Id> idSet = new HashMap<String, Id>();
    private String name;
    private Integer val;

    private Id(String n) {
        name = n;
        val = null;
    }

    public static Id ParseId() {
        String token = Interpreter.tokenizer.getToken();

        if (idSet.containsKey(token)) {
            return idSet.get(token);
        } else {
            Id id = new Id(token);
            idSet.put(token, id);

            return id;
        }
    }

    void PrintId() {
        System.out.print(name);
    }

    void OutputId() {
        System.out.println(name + " = " + val);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}

