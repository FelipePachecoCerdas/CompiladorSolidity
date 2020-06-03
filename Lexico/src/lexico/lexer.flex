package lexico;
import static lexico.Token.*;
%%
%class Lexer
%line
%type Token
Letras=[a-zA-Z]

Digitos=[0-9]
DigitosSinCero=[1-9]
Numero = ({DigitosSinCero}+{Digitos}*|0)

Flotantes=({Numero}?"."{Digitos}+)|({Numero}"."{Digitos}*)
NoFlotante=({Digitos}*)"."({Digitos}*)

SimbolosHexadecimales=[0-9a-fA-F]
Hexadecimal="hex"((\"{SimbolosHexadecimales}+\")|("'"{SimbolosHexadecimales}+"'"))

SecuenciasEscape=\\\\|\\'|\\\"|\\b|\\f|\\n|\\r|\\t|\\v|(\\x{SimbolosHexadecimales}{2})|(\\u{SimbolosHexadecimales}{4})
NoSimbolosDeEscape=[^\\"'"\""b""f""n""r""t""v"]
NoSimbolos2=\\x([^0-9a-fA-F]|([^0-9a-fA-F][^0-9a-fA-F]))

Str=(\"([^\\\"\n]|{SecuenciasEscape})*\")|("'"([^\\"'"\n]|{SecuenciasEscape})*"'")

NoStr1=\"([^\"\\\n]|{SecuenciasEscape})*(((\\({NoSimbolosDeEscape}?))([^\n\\\"]|{SecuenciasEscape})*((\"?)|(\n?)))|\n)
NoStr2="'"([^"'"\\\n]|{SecuenciasEscape})*(((\\({NoSimbolosDeEscape}?))(([^\n\\"'"]|{SecuenciasEscape})*("'"?|\n?)))|\n)
NoStr={NoStr1}|{NoStr2}

SimbolosNoHexadecimales =[^0-9a-fA-F]
NoHexadecimal="hex"(("\""{SimbolosHexadecimales}* {SimbolosNoHexadecimales}+ {SimbolosHexadecimales}* "\"")|("'"{SimbolosHexadecimales}* {SimbolosNoHexadecimales}+ {SimbolosHexadecimales}* "'"))

NotacionCientifica=({Numero}|{Flotantes})"e"-?{Numero}
NoNotacionCientifica1=({Numero}|{Flotantes})"e"-?{Digitos}+
NoNotacionCientifica4=({Numero}|{Flotantes})"e"-?
NoNotacionCientifica5=({Digitos})*"e"-?({Numero}|{Flotantes}|{NoFlotante})
NoNotacionCientifica2=({Numero}|{Flotantes})"e"-?{Flotantes}
NoNotacionCientifica3=((({NoFlotante})"e"-?{Digitos}*)|(({NoFlotante})"e"-?{Flotantes}))

unicode1 = [[[\u0021-\u003A] || [\u003C-\u1EF3]] -- [!\^<>&|~+\-*/%=,;.()\[\]?:{}\""'"]] 
Noidentificador = (({unicode1}))+

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
                | "int" | "internal" | "mapping" | "modifier"  | "modifier" | "payable" |  "pragma"  | "private" 
                | "public" | "return" | "returns"| "solidity" |"string" | "string"  | "struct" | "this" | "true" 
                | "ufixed" | "uint" |"var" |"view" | "while" |"uint8" |"uint16" | "uint32" |"uint64" |"uint128"  
                | "int8" |"int16" | "int32" |"int64" |"int128" | "int256" 
                | "uint256" |"bytes8" | "bytes16" |"bytes32" |"bytes64" | "bytes128" |"bytes256")

Trans = ("balance"  | "call" | "callcode" | "delegatecall" | "send" | "transfer" )

Unidades = ( "days" | "ether" | "finney" | "hours" | "minutes" | "seconds" | "szabo" | "weeks" | "wei" | "years")

WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%


{WHITE} {/* ignore */}
"//".* {/* ignore */}
{Comentario} {/* ignore */}


	{Operadores}            {lexeme=yytext()+" "+(yyline+1); return OPERADOR; }

        {Reservadas}		{lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA; }

    	{Trans} 		{lexeme=yytext()+" "+(yyline+1); return TRANSAC; }

    	{Unidades}              {lexeme=yytext()+" "+(yyline+1); return UNIDAD; }



{Letras}({Letras}|{Digitos})* {lexeme=yytext()+" "+(yyline+1); return IDENTIFICADOR;}
({Numero})|({Hexadecimal})|({NotacionCientifica})|({Flotantes})|{Str} {lexeme=yytext()+" "+(yyline+1); return LITERAL;}

(({Digitos}*) | {NoNotacionCientifica1}) {lexeme=yytext()+" "+(yyline+1);return ERROR_CEROS_A_LA_IZQUIERDA;}
{NoNotacionCientifica2} | {NoNotacionCientifica4} {lexeme=yytext()+" "+(yyline+1);return ERROR_NOTACION_CIENTIFICA;}
{NoFlotante} | {NoNotacionCientifica3} | {NoNotacionCientifica5} {lexeme=yytext()+" "+(yyline+1);return ERROR_CEROS_A_LA_IZQUIERDA;}
({Digitos}+{Letras}+) {lexeme=yytext()+" "+(yyline+1);return ERROR_IDENTIFICADOR;}
({NoHexadecimal}) {lexeme=yytext()+" "+(yyline+1);return ERROR_HEXADECIMAL;}
{Noidentificador} {lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;}
"/**"{NoComentario} {lexeme=yytext()+" "+(yyline+1);return ERROR_COMENTARIO;}
. {lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;}
{NoStr} {lexeme=yytext()+" "+(yyline+1);return ERROR_STRING;}


