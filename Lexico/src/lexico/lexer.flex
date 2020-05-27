package lexico;
import static lexico.Token.*;
%%
%class Lexer
%type Token
L=[a-zA-Z_]

D=[0-9]

F=({D}*.{D}+)|{D}+.{D}*

SH=[0-9_A-F]
H="hex"((\"{SH}+\")|("'"{SH}+"'"))

NC=-?{D}+|{F}"e"-?{D}+

WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/* ignore */}
"//".* {/* ignore */}

	"!"			{lexeme=yytext(); return OPERADOR; }
	"^"                	{lexeme=yytext(); return OPERADOR; }
	"<"                	{lexeme=yytext(); return OPERADOR; }
    	">"                	{lexeme=yytext(); return OPERADOR; }
    	"&"                	{lexeme=yytext(); return OPERADOR; }
    	"|"                	{lexeme=yytext(); return OPERADOR; }
	"~"                	{lexeme=yytext(); return OPERADOR; }
	"+"                	{lexeme=yytext(); return OPERADOR; }
	"-"                	{lexeme=yytext(); return OPERADOR; }
	"*"                	{lexeme=yytext(); return OPERADOR; }
    	"/"                	{lexeme=yytext(); return OPERADOR; }
    	"%"			{lexeme=yytext(); return OPERADOR; }
	"="                	{lexeme=yytext(); return OPERADOR; }
	","                	{lexeme=yytext(); return OPERADOR; }
	";"                	{lexeme=yytext(); return OPERADOR; }
	"."                	{lexeme=yytext(); return OPERADOR; }
	"("                	{lexeme=yytext(); return OPERADOR; }
	")"                	{lexeme=yytext(); return OPERADOR; }
	"["                	{lexeme=yytext(); return OPERADOR; }
	"]"                	{lexeme=yytext(); return OPERADOR; }
	"?"                	{lexeme=yytext(); return OPERADOR; }
	":"                	{lexeme=yytext(); return OPERADOR; }
	"{"                	{lexeme=yytext(); return OPERADOR; }
	"}"                	{lexeme=yytext(); return OPERADOR; }
 	"&&"                	{lexeme=yytext(); return OPERADOR; }
   	"=="			{lexeme=yytext(); return OPERADOR; }
    	"!="			{lexeme=yytext(); return OPERADOR; }
    	"||"			{lexeme=yytext(); return OPERADOR; }
    	"<="			{lexeme=yytext(); return OPERADOR; }
    	">="			{lexeme=yytext(); return OPERADOR; }
    	"**"			{lexeme=yytext(); return OPERADOR; }
    	"<<"			{lexeme=yytext(); return OPERADOR; }
    	">>"             	{lexeme=yytext(); return OPERADOR; }
    	"+="            	{lexeme=yytext(); return OPERADOR; }
	"-="                	{lexeme=yytext(); return OPERADOR; }
    	"*="            	{lexeme=yytext(); return OPERADOR; }
	"/="                	{lexeme=yytext(); return OPERADOR; }

   	
        "address"		{lexeme=yytext(); return PALABRA_RESERVADA; }
   	"as"			{lexeme=yytext(); return PALABRA_RESERVADA; }
   	"bool"			{lexeme=yytext(); return PALABRA_RESERVADA; }
	"break"			{lexeme=yytext(); return PALABRA_RESERVADA; }
	"byte"			{lexeme=yytext(); return PALABRA_RESERVADA; }
    	"bytes"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "constructor"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "continue"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "contract"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "delete"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "do"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "else"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "enum"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "false"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "for"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "from"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "function"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "hex"           	{lexeme=yytext(); return PALABRA_RESERVADA; }
        "if"    		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "import"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "int"   		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "internal"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "mapping"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "modifier"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "payable"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "Pragma"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "private"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "public"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "return"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "returns"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "solidity"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "string"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "struct"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "this"          	{lexeme=yytext(); return PALABRA_RESERVADA; }
        "true"  		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "ufixed"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint"  		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "var"   		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "view"   		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "while" 		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint8"			{lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint16"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint32"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint64"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint128"               {lexeme=yytext(); return PALABRA_RESERVADA; }
        "uint256"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "bytes8"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "bytes16"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "bytes32"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "bytes64"		{lexeme=yytext(); return PALABRA_RESERVADA; }
        "bytes128"              {lexeme=yytext(); return PALABRA_RESERVADA; }
        "bytes256"		{lexeme=yytext(); return PALABRA_RESERVADA; }





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
-?{D}|{H}|{NC}|-?{F} {lexeme=yytext();return LITERAL;}
. {return ERROR;}
