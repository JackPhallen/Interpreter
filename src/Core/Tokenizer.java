package Core;

import java.io.IOException;
import java.util.StringTokenizer;

public class Tokenizer {

/* Tokenizer-
 * 
 * Purpose: Class to tokenize an input string in Core programming language into the correct tokens. It contains several methods,
 * the public ones being constructor, getToken, getTokenInt, tokensLeft, and skipToken */
	
	private String tokenString; //full tokenString that has not yet been tokenized
	private String currentToken; //currentToken string value
	private int currentTokenInt; //integer value of currentToken
	//private String currentTokenId; //possible implementation later
	private StringTokenizer st; //used to get rid of whitespace
	private String nextToken = ""; //only used when verifyToken() splits a token
	private boolean tokensLeft = true;; //will be false when no tokens are left
	
	/*Tokenizer constructor simply takes input, places it in the StringTokenizer, and calls skipToken to
	 * populate currentToken and currentTokenInt */
public Tokenizer(String input) throws IOException
{
	tokenString = input;
	st = new StringTokenizer(tokenString);
	skipToken(); //throws IOException if first token is invalid
}

/* endOfFile changes the Tokenizer state to reflect that the end of file is reached */
private void endOfFile() {
	currentToken = "";
	currentTokenInt = 33; //eof token
	tokensLeft = false;
}

public String getToken()
{
	return currentToken;
}


public int getTokenInt()
{
	return currentTokenInt;
}

public boolean tokensLeft()
{
	return tokensLeft;
}

/* skipToken will get replace the current token with the next token in line */
public void skipToken() throws IOException
{
	//if nextToken exists, it needs handled first. Put it in currentToken, verify it, and update the int value
	if (nextToken != "")
	{
		currentToken = nextToken;
		nextToken = "";
		verifyToken();
		setTokenValues();
		if(currentTokenInt == -1) //if token is invalid
		{
			throw new IOException();
		}
	}
	
	//else if eof
	else if(!st.hasMoreTokens()) endOfFile();
	
	//else if tokens remain, call stringTokenizer to give next token, verify it, and update integer values
	else
	{
		currentToken = st.nextToken();
		verifyToken();
		setTokenValues();
		if(currentTokenInt == -1) //if token is invalid
		{
			throw new IOException();
		}
	}
}

/*The StringTokenizer used only seperates tokens by whitespace. verifyToken will split up tokens by other
 * characters if necessary. For example, in the case A=3, there is no space, but should be three tokens. 
 * nextToken will be used to hold the extra characters if a token is split */
private void verifyToken() {
	
	//if it is an identifier, find the end of UpperCase characters, then the end of integers, and split the token
	if(Character.isUpperCase(currentToken.charAt(0)))
			{
				int i = 1;
				while(i < currentToken.length() && Character.isUpperCase(currentToken.charAt(i)))
				{
					i++;
				}
				while(i < currentToken.length() && Character.isDigit(currentToken.charAt(i)))
				{
					i++;
				}
				if(i != currentToken.length())
				{
					nextToken = currentToken.substring(i, currentToken.length());
					currentToken = currentToken.substring(0, i);
				}
			}
	
	//if a reserved word, find end of lowercase characters and then split
	else if(Character.isLowerCase(currentToken.charAt(0)))
	{
		int i = 1;
		while(i < currentToken.length() && Character.isLowerCase(currentToken.charAt(i)))
		{
			i++;
		}
		if(i != currentToken.length())
		{
			nextToken = currentToken.substring(i, currentToken.length());
			currentToken = currentToken.substring(0, i);
		}
	}
	
	//if an integer, find end of digits and then split
	else if(Character.isDigit(currentToken.charAt(0)))
	{
		int i = 1;
		while(i < currentToken.length() && Character.isDigit(currentToken.charAt(i)))
		{
			i++;
		}
		if(i != currentToken.length())
		{
			nextToken = currentToken.substring(i, currentToken.length());
			currentToken = currentToken.substring(0, i);
		}
	}
	
	//else if it is a symbol, call verifySymbolToken
	else if(isSymbolToken(currentToken.charAt(0)))
			{
				verifySymbolToken();
			}
	

	}


/*verifySymbolToken validates symbol tokens similarly to verifyToken does for identifiers, integers, and reserved words  */
private void verifySymbolToken() {
	
	//First handle symbols that start with = ! > or <
	if(currentToken.charAt(0) == '=' || currentToken.charAt(0) == '!' || currentToken.charAt(0) == '>' || currentToken.charAt(0) == '<')
	{
		if(currentToken.length() > 1)
		{
			if(currentToken.charAt(1) == '=') //if '==' '!=' '>=' or '<='
			{
				if(currentToken.length() > 2)
				{
				nextToken = currentToken.substring(2, currentToken.length());
				}
				currentToken = currentToken.substring(0,2);
			}
			else // if '=' '!' '<' '>'
			{
				nextToken = currentToken.substring(1, currentToken.length());
				currentToken = currentToken.substring(0,1);
			}
		}
	}
	
	//Handle ||
	else if(currentToken.charAt(0) == '|')
	{
			if(currentToken.length() > 2 && currentToken.charAt(1) == '|') //if '||'
			{
				nextToken = currentToken.substring(2, currentToken.length());
				currentToken = currentToken.substring(0,2);
			}
		
	}
	
	//Handle &&
	else if(currentToken.charAt(0) == '&')
	{
			if(currentToken.length() > 2 && currentToken.charAt(1) == '&') //if '&&'
			{
				nextToken = currentToken.substring(2, currentToken.length());
				currentToken = currentToken.substring(0,2);
			}
		
	}
	
	//any other single length symbol
	else
	{
		if(currentToken.length() > 1)
		{
				nextToken = currentToken.substring(1, currentToken.length());
				currentToken = currentToken.substring(0,1);
		}
	}
}

/*isSymbolToken returns true if the character passed is the first character of a valid symbol token */
private boolean isSymbolToken(char c) {
	if(c == ';' || c == ',' || c == '=' || c == '!' || c == ']' || c == '[' || c == '&' || c =='|' || c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c =='<' || c == '>')
	{ return true;
	}
	else return false;
}

private boolean isInt(String s)
{
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
}

private boolean isID(String s) {
	boolean upperCase = true;
	if(!Character.isUpperCase(s.charAt(0))) return false;
	for(int i = 0; i < s.length(); i++)
	{
	 if(upperCase == false)
	 {
		 if(!Character.isDigit(s.charAt(i)))
				 {
			 			return false;
				 }
	 }
	 else if(!Character.isUpperCase(s.charAt(i)))
	 {
 		upperCase = false;
 		if(!Character.isDigit(s.charAt(i))) return false;
	 }
	}
	return true;
}

/*setTokenValues sets the currentTokenInt property to the correct integer value */
private void setTokenValues()
{
	if (isInt(currentToken))
	{
		currentTokenInt = 31;
	}
	else if (isID(currentToken))
	{
		currentTokenInt = 32;
	}
	else switch (currentToken) {
	case "program": currentTokenInt = 1; break;
	case "begin": currentTokenInt = 2; break;
	case "end": currentTokenInt = 3; break;
	case "int": currentTokenInt = 4; break;
	case "if": currentTokenInt = 5; break;
	case "then": currentTokenInt = 6; break;
	case "else": currentTokenInt = 7; break;
	case "while": currentTokenInt = 8; break;
	case "loop": currentTokenInt = 9; break;
	case "read": currentTokenInt = 10; break;
	case "write": currentTokenInt = 11; break;
	case ";": currentTokenInt = 12; break;
	case ",": currentTokenInt = 13; break;
	case "=": currentTokenInt = 14; break;
	case "!": currentTokenInt = 15; break;
	case "[": currentTokenInt = 16; break;
	case "]": currentTokenInt = 17; break;
	case "&&": currentTokenInt = 18; break;
	case "||": currentTokenInt = 19; break;
	case "(": currentTokenInt = 20; break;
	case ")": currentTokenInt = 21; break;
	case "+": currentTokenInt = 22; break;
	case "-": currentTokenInt = 23; break;
	case "*": currentTokenInt = 24; break;
	case "!=": currentTokenInt = 25;  break;
	case "==": currentTokenInt = 26; break;
	case "<": currentTokenInt = 27;  break;
	case ">": currentTokenInt = 28;  break;
	case "<=": currentTokenInt = 29;  break;
	case ">=": currentTokenInt = 30; break;
	default: currentTokenInt = -1; //ERROR 
	 break;
	}


}


}
