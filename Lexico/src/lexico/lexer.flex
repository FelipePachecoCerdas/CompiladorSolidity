package lexico;
import static lexico.Token.*;
%%
%class Lexer
%line
%type Token
Letras=[a-zA-Z]

Digitos=[0-9]

Flotantes=({Digitos}*"."{Digitos}+)|({Digitos}+"."{Digitos}*)

SimbolosHexadecimales=[0-9a-fA-F]
Hexadecimal="hex"((\"{SimbolosHexadecimales}+\")|("'"{SimbolosHexadecimales}+"'"))

Str=([^\\\"\n]|\\\\|\\\"|\\n|\\t)*

SimbolosNoHexadecimales =[^0-9a-fA-F]
NoHexadecimal="hex"(("\""{SimbolosHexadecimales}* {SimbolosNoHexadecimales}+ {SimbolosHexadecimales}* "\"")|("'"{SimbolosHexadecimales}* {SimbolosNoHexadecimales}+ {SimbolosHexadecimales}* "'"))

NotacionCientifica=-?({Digitos}+|{Flotantes})"e"-?{Digitos}+
unicode1 = [\u0021-\u003A] 
unicode2 = [\u003C-\u1EF3]
Noidentificador = {Letras}({unicode1} | {unicode2} | "ñ")*

Comentario = ("/**"([^\n]|("\n"(" "*)("*")))*(("\n")(" "*)"*/"|"*/"))
NoComentario1=(([^\n\*]|("*""*"*[^\n\*\/]))|((\n|("*""*"*\n))(("*""*"*\n)|(\t|" "))*("*""*"*[^\n\*\/])))*
NoComentario2=(([^\n\*]|("*""*"*[^\n\*\/]))*(\n|("*""*"*\n))((("*""*"*\n)|(\t|" "))|(("*""*"*[^\n\*\/])([^\n\*]|("*""*"*[^\n\*\/]))*(\n|("*""*"*\n))))*)
NoComentario3=(([^\n\*])*("*"|(\n(\t|" ")*))(("*"|(\n(\t|" ")*))|([^\n\*\/][^\n\*]*("*"|(\n(\t|" ")*))))*)
NoComentario4=({NoComentario1}((\n|("*""*"*\n))(("*""*"*\n)|(\t|" "))*[^\*\/\t" "])(.*|\n|\t)*)
NoComentario= {NoComentario1}|{NoComentario2}|{NoComentario3}|{NoComentario4}

WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/* ignore */}
"//".* {/* ignore */}

	"!"			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"^"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"<"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	">"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"&"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"|"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"~"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"+"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"-"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"*"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"/"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"%"			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"="                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	","                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	";"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"."                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"("                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	")"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"["                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"]"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"?"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	":"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"{"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"}"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
 	"&&"                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
   	"=="			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"!="			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"||"			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"<="			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	">="			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"**"			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"<<"			{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	">>"             	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"+="            	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"-="                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
    	"*="            	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }
	"/="                	{lexeme=yytext()+" "+(yyline+1); return OPERADOR; }

   	
        "address"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
   	"as"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
   	"bool"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
	"break"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
	"byte"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
    	"bytes"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "constructor"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "continue"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "contract"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "delete"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "do"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "else"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "enum"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "false"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "for"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "from"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "function"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "hex"           	{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "if"    		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "import"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "int"   		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "internal"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "mapping"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "modifier"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "payable"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "Pragma"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "private"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "public"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "return"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "returns"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "solidity"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "string"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "struct"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "this"          	{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "true"  		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "ufixed"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint"  		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "var"   		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "view"   		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "while" 		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint8"			{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint16"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint32"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint64"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint128"               {lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "uint256"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "bytes8"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "bytes16"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "bytes32"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "bytes64"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "bytes128"              {lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }
        "bytes256"		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }





        "balance"               {lexeme=yytext()+" "+(yyline+1); return TRANSAC; }
   	"call"			{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }
   	"callcode"		{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }
	"delegatecall"		{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }
	"send"			{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }
    	"transfer"		{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }

        "days"                  {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
   	"ether"			{lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
   	"finney"		{lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
	"hours"                 {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
	"minutes"		{lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
    	"seconds"		{lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
   	"szabo"                 {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
	"weeks"                 {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
	"wei"                   {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }
    	"years"                 {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }



{Letras}({Letras}|{Digitos})* {lexeme=yytext()+" "+(yyline+1); return IDENTIFICADOR;}
(-?{Digitos}+)|({Hexadecimal})|({NotacionCientifica})|(-?{Flotantes})|(\"{Str}\")|{Comentario} {lexeme=yytext()+" "+(yyline+1); return LITERAL;}

({Digitos}+{Letras}+) {lexeme=yytext()+" "+(yyline+1);return ERROR_IDENTIFICADOR;}
({NoHexadecimal}) {lexeme=yytext()+" "+(yyline+1);return ERROR_HEXADECIMAL;}
{Noidentificador} {lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;}
"/**"{NoComentario} {lexeme=yytext()+" "+(yyline+1);return ERROR_COMENTARIO;}
. {lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;}

