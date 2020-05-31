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
Noidentificador = (({unicode1} | {unicode2})-{Operadores})*

Comentario = "/**"([^\n\*]|(("*"|(\n(" "|\t)*"*"))("*"|(\n(" "|\t)*"*"))*[^\n\*\/]))*(("*"|(\n(" "|\t)*"*"))("*"|(\n(" "|\t)*"*"))*"/")
NoComentario1=(([^\n\*]|("*""*"*[^\n\*\/]))|((\n|("*""*"*\n))(("*""*"*\n)|(\t|" "))*("*""*"*[^\n\*\/])))*
NoComentario2=(([^\n\*]|("*""*"*[^\n\*\/]))*(\n|("*""*"*\n))((("*""*"*\n)|(\t|" "))|(("*""*"*[^\n\*\/])([^\n\*]|("*""*"*[^\n\*\/]))*(\n|("*""*"*\n))))*)
NoComentario3=(([^\n\*])*("*"|(\n(\t|" ")*))(("*"|(\n(\t|" ")*))|([^\n\*\/][^\n\*]*("*"|(\n(\t|" ")*))))*)
NoComentario4=({NoComentario1}((\n|("*""*"*\n))(("*""*"*\n)|(\t|" "))*[^\*\/\t" "])(.*|\n|\t)*)
NoComentario= {NoComentario1}|{NoComentario2}|{NoComentario3}|{NoComentario4}

Operadores = ("!" | "^" | "<" | ">" | "&" | "|" | "~" | "+" | "-" | "*" | "/" | "%" | "=" | "," | ";" | "." | "(" | ")" 
                | "[" | "]" | "?" | ":" | "{" | "}" | "&&" | "==" | "!=" | "||" | "<=" | ">=" | "**" | "<<" | ">>" | "+=" 
                | "-=" | "*=" | "/=")

Reservadas = ( "address" | "as" | "bool" | "break"  | "byte"  | "constructor"  | "continue"  | "contract" 
                | "delete" | "do" | "else" | "enum" | "false" | "for" | "from" | "function" | "hex" | "if" | "import" 
                | "int" | "internal" | "mapping" | "modifier"  | "modifier" | "payable" |  "Pragma"  | "private" 
                | "public" | "return" | "returns"| "solidity" |"string" | "string"  | "struct" | "this" | "true" 
                | "ufixed" | "uint" |"var" |"view" | "while" |"uint8" |"uint16" | "uint32" |"uint64" |"uint128"   
                |"uint256" |"bytes8" | "bytes16" |"bytes32" |"bytes64" | "bytes128" |"bytes256")

Trans = ("balance"  | "call" | "callcode" | "delegatecall" | "send" | "transfer" )

Unidades = ( "days" | "ether" | "finney" | "hours" | "minutes" | "seconds" | "szabo" | "weeks" | "wei" | "years")

WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/* ignore */}
"//".* {/* ignore */}

	{Operadores}            {lexeme=yytext()+" "+(yyline+1); return OPERADOR; }

        {Reservadas}		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }

    	{Trans} 		{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }

    	{Unidades}              {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }



{Letras}({Letras}|{Digitos})* {lexeme=yytext()+" "+(yyline+1); return IDENTIFICADOR;}
(-?{Digitos}+)|({Hexadecimal})|({NotacionCientifica})|(-?{Flotantes})|(\"{Str}\")|{Comentario} {lexeme=yytext()+" "+(yyline+1); return LITERAL;}

({Digitos}+{Letras}+) {lexeme=yytext()+" "+(yyline+1);return ERROR_IDENTIFICADOR;}
({NoHexadecimal}) {lexeme=yytext()+" "+(yyline+1);return ERROR_HEXADECIMAL;}
{Noidentificador} {lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;}
"/**"{NoComentario} {lexeme=yytext()+" "+(yyline+1);return ERROR_COMENTARIO;}
. {lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;}

