package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Interpreter {

	/* Interpreter-
	 * 
	 * Purpose: Main class of interpreter. Will ask user for input, read input, and initialize
	 * tokenizer. Will also call Prog to parse, print and execute the program*/
	
	
	public static int tabCount;
    public static String dataString;
    public static Tokenizer tokenizer = null;

    public static void main(String[] args) {

    	String inputString = "";
		String fileName;
		String dataName;
		Scanner userInput = new Scanner(System.in);
		Scanner programScanner = null;
		Scanner dataScanner = null;
		
		//Get name of program inputted from user 
		System.out.println("Please enter program file name: ");
		fileName = userInput.nextLine();
		File programFile = new File(fileName);
		
		//Get name of data file inputted from user 
		System.out.println("Please enter data file name: ");
		dataName = userInput.nextLine();
		File dataFile = new File(dataName);
		
		//Try to read file and put into String
		try {
			programScanner = new Scanner(programFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Input File Not Found");
		}
		
		while(programScanner.hasNext())
		{
			inputString += programScanner.nextLine();
		}
		
		//Put Data File into String
		try {
			dataScanner = new Scanner(dataFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Data File Not Found");
		}
		while(dataScanner.hasNext())
		{
			dataString = "";
			dataString += dataScanner.nextLine();
		}
				
		
		//initialize Tokenizer with input string
		try {
			tokenizer = new Tokenizer(inputString);
		} catch (IOException e1) { //if first token is invalid
			e1.printStackTrace();
			System.out.println("Invalid Token Structure");
		}
		
		//Parse, Print, and Execute the program
		
		 Prog program = new Prog();
		 
		 try {
				program.ParseProg();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Invalid Token Structure");
			}
		 
		 program.PrintProg();
	     program.ExecProg();
    }
}
