# Assignment 3 - Semantic Analysis (refactor from the Assignment 2)

## Syntax-directed translation

* Semantic Attribute

The process of semantic values are gathered and accumulated in semantic records until they can be processed by further semantic actions. 

* Semantic Action

Semantic actions are functions whose goal is to accumulate and process semantic information about the program as the parsing is done. 

* Semantic Migration

Some of the information that must be gathered spans over the application of several syntactical rules. 
This raises the need to migrate this information across the parse tree, a process known as attribute migration. 


## Run

* Run **SymbolTableGen.java** to parsing program_symbol.txt
	
* Run test case **SymbolTest.java** to test all the situation, the test method is
	
```
	testProgram()

	testProgramErrorRecovery()
```

* Following is the example of adding action on a rule. When the parser meet the CreateClassScope that is the time to create a Class table. And meet the EndScope exit this table scope.
```
1	prog → classDecl_list progBody	class, program
2	classDecl → class id sym_CreateClassScope { varDeclFunctDef_list } ; sym_EndScope	class
3	progBody → program sym_CreateProgram funcBody ; funcDef_list	program
4	funcDef → type sym_StoreType id sym_StoreId ( sym_StartFunction fParams ) sym_CreateFunction funcBody ;	id, float, int
5	funcBody → { funcBody_ } sym_EndScope	{
 ...
```


***
# Assignment 2 - Parser (refactor from the Assignment 1)

## code structure

* Parser function implement in package **comp6421.parser**

	Scanner move to comp6421.scanner

* This is a table driven grammar parser tools, main function in Parser.java

	maintain a stack keep the symbols and use table to do the derivation

	finally, out put the success information to console and file.

* first, follow, production and table file

	all the files are in **res/parser** dictionary

* Program file is bellow

	**program.txt**	The simple program to verify the algorithm

	**program_full.txt** A full version to test all the grammar rules

	**program_error_recovery.txt**  To do the error recovery

## Run

* Run **parser.java** to parsing program_full.txt
	
* Run test case **ParserTest.java** to test all the three situation, the test method is
	
```
	testProgram()
	
	testProgramFull()
	
	testProgramErrorRecovery()
```

* Derivation information will write in **res/parser/out/*_derivation.txt**, the style is
```
 $ prog                                 		r0:prog → A progBody   				A progBody
 $ progBody A                           		r1:A → classDecl A     				classDecl A progBody
 $ progBody A classDecl                 		r2:classDecl → class id { V } ;		class id { V } ; A progBody
 $ progBody A ; } V { id class          		class
 $ progBody A ; } V { id                		id
 $ progBody A ; } V {                   		{
 ...
```

* Error Recovery will write in **/res/parser/out/*_error.txt**, the simple is
```
line 8 parser error, Token [TYPE=ID, value=public, position=8]                  	RECOVERY: SCAN
line 10 parser error, Token [TYPE=SEMICOLON, value=;, position=10]              	RECOVERY: POP
line 11 parser error, Token [TYPE=FLOAT, value=10.0, position=11]               	RECOVERY: POP
line 11 parser error, Token [TYPE=FLOAT, value=10.0, position=11]               	RECOVERY: POP
line 11 parser error, Token [TYPE=FLOAT, value=10.0, position=11]               	RECOVERY: SCAN
line 11 parser error, Token [TYPE=CLOSEBRACKET, value=], position=11]           	RECOVERY: SCAN
...
```

# Reference
[Reference](http://hackingoff.com/compilers/ll-1-parser-generator)


***
# Assignment 1 - Scanner Version
There are Five Class in this project, it Etype.java, Scanner.java is the main process, Token.java, Utils.java
ScannerTest.java is a Junit test class

1. The first test file is test.txt in res directory. Just run the Scanner.java class

2. To do a wide test case, you can run the ScannerTest test case,
	it will test all the test files written in different language in res directory
	
All the info will display to console and written into *_token.txt and *_error.txt

The first one is save the valid token, the second is save the error elements.


Thank you!

[GITHUB]: https://github.com/JenkinDU/CompilerDesign_Scanner