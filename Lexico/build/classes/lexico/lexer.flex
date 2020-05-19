package lexico;
import static lexico.Token.*;
%%
%class Lexer
%type Token
L=[a-zA-Z_]
D=[0-9]
S=[* + / - ( ) { } ]


WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/* ignore */}
"//".* {/* ignore */}
	"+"			{lexeme=yytext(); return OPERADOR; }
	"-"                	{lexeme=yytext(); return OPERADOR; }
	"*"                	{lexeme=yytext(); return OPERADOR; }
    	"/"                	{lexeme=yytext(); return OPERADOR; }
    	"("                	{lexeme=yytext(); return OPERADOR; }
    	")"                	{lexeme=yytext(); return OPERADOR; }
	"{"                	{lexeme=yytext(); return OPERADOR; }
	"}"                	{lexeme=yytext(); return OPERADOR; }
    	";"                	{lexeme=yytext(); return OPERADOR; }
    	"="			{lexeme=yytext(); return OPERADOR; }
    	"++"			{lexeme=yytext(); return OPERADOR; }
    	"=="			{lexeme=yytext(); return OPERADOR; }
    	"!="			{lexeme=yytext(); return OPERADOR; }
    	"<"			{lexeme=yytext(); return OPERADOR; }
    	"<="			{lexeme=yytext(); return OPERADOR; }
    	">"			{lexeme=yytext(); return OPERADOR; }
    	">="			{lexeme=yytext(); return OPERADOR; }
    	"!"             	{lexeme=yytext(); return OPERADOR; }
    	"||"            	{lexeme=yytext(); return OPERADOR; }
    	"&&"            	{lexeme=yytext(); return OPERADOR; }


   	"if"			{lexeme=yytext(); return PALABRA_RESERVADA; }
   	"else"			{lexeme=yytext(); return PALABRA_RESERVADA; }
   	"do"			{lexeme=yytext(); return PALABRA_RESERVADA; }
	"while"			{lexeme=yytext(); return PALABRA_RESERVADA; }
	"for"			{lexeme=yytext(); return PALABRA_RESERVADA; }
    	"print"			{lexeme=yytext(); return PALABRA_RESERVADA; }

        "balance"               {lexeme=yytext(); return TRANSAC; }
   	"call"			{lexeme=yytext(); return TRANSAC; }
   	"callcode"		{lexeme=yytext(); return TRANSAC; }
	"delegatecall"		{lexeme=yytext(); return TRANSAC; }
	"send"			{lexeme=yytext(); return TRANSAC; }
    	"transfer"		{lexeme=yytext(); return TRANSAC; }

        "days"                  {lexeme=yytext(); return UNIDAD; }
   	"ether"			{lexeme=yytext(); return UNIDAD; }
   	"finney"		{lexeme=yytext(); return UNIDAD; }
	"hours"                 {lexeme=yytext(); return UNIDAD; }
	"minutes"		{lexeme=yytext(); return UNIDAD; }
    	"seconds"		{lexeme=yytext(); return UNIDAD; }
   	"szabo"                 {lexeme=yytext(); return UNIDAD; }
	"weeks"                 {lexeme=yytext(); return UNIDAD; }
	"wei"                   {lexeme=yytext(); return UNIDAD; }
    	"years"                 {lexeme=yytext(); return UNIDAD; }



{L}({L}|{D})* {lexeme=yytext(); return IDENTIFICADOR;}
{S}?{D}+ {lexeme=yytext();return LITERAL;}
. {return ERROR;}
