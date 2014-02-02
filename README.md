Interpreter
===========

Interpreter, written in Java, is an interpreter for programs written in a simple programming language called Core

This was a project completed for CSE 3341, Programming Languages. The goal of this project was to build an interpreter for a version of the Core language discussed in class. 

Core Programming Language
-------------------------

The BNF for the Core programming language is as follows:

<prog>	::= program <decl seq> begin <stmt seq> end
<decl seq>	::= <decl> | <decl> <decl seq>
<stmt seq>	::= <stmt> | <stmt> <stmt seq>
<decl>		::=	int  <id list>;
<id list>		::=	<id> | <id>, <id list>
<stmt>		::=	<assign>|<if>|<loop>|<in>|<out>
<assign>		::=	<id> = <exp>;
<if>				::=	if <cond> then <stmt seq> end; | if <cond> then <stmt seq> else <stmt seq> end;
<loop>		::=	while <cond> loop <stmt seq> end;
<input>		::=	read <id list>;	
<output>		::=	write <id list>;
<cond>	::=	 <comp>|!<cond>	|  [<cond> && <cond>] | [<cond> or <cond>]
<comp>	::= (<op> <comp op> <op>)
<exp>	::= <fac>|<fac>+<exp>|<fac>-<exp>
<fac>	::= <op> | <op> * <fac>
<op>	::= <int> | <id> | (<exp>)
<comp op> ::= != | == | < | > | <= | >=
<id>			::= <let> | <let><id> | <let><int>
<let>	::=		A | B | C | ... | X | Y | Z
<int>	::=		<digit> | <digit><int>
<digit>	::=		0 | 1 | 2 | 3 | ... | 9		

Approach
--------

The interpreter is written in an object oriented approach. Each piece of the Core programming language, from <prog> to <id>, has a Java class devoted to it. The Core program, and any data values, are input by being placed in seperate text files that are read by the Interpreter. There is a tokenizer class that takes care of tokenizing the input.
