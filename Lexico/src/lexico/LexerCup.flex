package lexico;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%cup
%full
%line
%char
%type java_cup.runtime.Symbol
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

Tipo = ( "bool" | "byte" | "bytes" | "address" | "int" | "string" | "ufixed" | "uint" 
                | "uint8" |"uint16" | "uint32" |"uint64" |"uint128"  
                | "int8" |"int16" | "int32" |"int64" |"int128" | "int256" 
                | "uint256" |"bytes8" | "bytes16" |"bytes32" |"bytes64" | "bytes128" |"bytes256")

WHITE=[ \t\r\n]
%{
    private Symbol symbol (int type, Object value){
        return new symbol(type, yyline, yycolumn, value);
    }

    private Symbol symbol (int type){
        return new symbol(type, yyline, yycolumn);
    }
%}
%%


{WHITE} {/* ignore */}
"//".* {/* ignore */}
{Comentario} {/* ignore */}


	{Operadores}            {return new Symbol(sym.OPERADOR, yychar, yyline, yytext());}

        {Reservadas}		{return new Symbol(sym.PALABRA_RESERVADA, yychar, yyline, yytext());}

    	{Trans} 		{return new Symbol(sym.TRANSAC, yychar, yyline, yytext());}

    	{Unidades}              {return new Symbol(sym.UNIDAD, yychar, yyline, yytext());}



{Letras}({Letras}|{Digitos})* {return new Symbol(sym.IDENTIFICADOR, yychar, yyline, yytext());}


({Numero})|({Hexadecimal})|({NotacionCientifica})|({Flotantes}) {return new Symbol(sym.NUMERO, yychar, yyline, yytext());}

{Str} {return new Symbol(sym.STRING, yychar, yyline, yytext());}

{Tipo} {return new Symbol(sym.TIPO, yychar, yyline, yytext());}

"if"    {return new Symbol(sym.IF, yychar, yyline, yytext()); }
"while"    {return new Symbol(sym.WHILE, yychar, yyline, yytext()); }
"for"    {return new Symbol(sym.FOR, yychar, yyline, yytext()); }
"do"    {return new Symbol(sym.DO, yychar, yyline, yytext()); }
"else"    {return new Symbol(sym.ELSE, yychar, yyline, yytext()); }
"return"    {return new Symbol(sym.RETURN, yychar, yyline, yytext()); }
","    {return new Symbol(sym.COMA, yychar, yyline, yytext()); }
("true" | "false")  {return new Symbol(sym.VISIBILDAD, yychar, yyline, yytext()); }
("true" | "false" | "payable" | "internal" )  {return new Symbol(sym.MODIFICADOR, yychar, yyline, yytext()); }
"contract"    {return new Symbol(sym.CONTRACT, yychar, yyline, yytext()); }
"enum"    {return new Symbol(sym.ENUM, yychar, yyline, yytext()); }
"pragma"    {return new Symbol(sym.PRAGMA, yychar, yyline, yytext());}
"solodity"    {return new Symbol(sym.SOLIDITY, yychar, yyline, yytext());}
"="    {return new Symbol(sym.IGUAL, yychar, yyline, yytext()); }
"struct"    {return new Symbol(sym.STRUCT, yychar, yyline, yytext()); }
"function"    {return new Symbol(sym.FUNCTION, yychar, yyline, yytext()); }
("+" | "-" | "*" | "/" | "%" | "(" | ")" | "+=" | "-=" | "*=" | "/=")    {return new Symbol(sym.OP_ARITMETICO, yychar, yyline, yytext()); }
( "==" | ">=" | ">" | "<=" | "<" | "!=" | "||" | "&&" | "!" )  {return new Symbol(sym.OP_BOOLEANO, yychar, yyline, yytext()); }
";"    {return new Symbol(sym.PUNTOCOMA, yychar, yyline, yytext()); }
"("    {return new Symbol(sym.PARENTESIS_ABRE, yychar, yyline, yytext()); }
")"    {return new Symbol(sym.PARENTESIS_CIERRE, yychar, yyline, yytext()); }
"{"    {return new Symbol(sym.LLAVE_ABRE, yychar, yyline, yytext()); }
"}"    {return new Symbol(sym.LLAVE_CIERRE, yychar, yyline, yytext()); }

(({Digitos}*) | {NoNotacionCientifica1}) {return new Symbol(sym.ERROR_CEROS_A_LA_IZQUIERDA, yychar, yyline, yytext());}
{NoNotacionCientifica2} | {NoNotacionCientifica4} {return new Symbol(sym.ERROR_NOTACION_CIENTIFICA, yychar, yyline, yytext());}
{NoFlotante} | {NoNotacionCientifica3} | {NoNotacionCientifica5} {return new Symbol(sym.ERROR_CEROS_A_LA_IZQUIERDA, yychar, yyline, yytext());}
({Digitos}+{Letras}+) {return new Symbol(sym.ERROR_IDENTIFICADOR, yychar, yyline, yytext());}
({NoHexadecimal}) {return new Symbol(sym.ERROR_HEXADECIMAL, yychar, yyline, yytext());}
{Noidentificador} {return new Symbol(sym.ERROR_CARACTERES_NO_VALIDOS, yychar, yyline, yytext()); }
"/**"{NoComentario} {return new Symbol(sym.ERROR_COMENTARIO, yychar, yyline, yytext()); }
. {return new Symbol( sym.ERROR_CARACTERES_NO_VALIDOS, yychar, yyline, yytext());}
{NoStr} {return new Symbol(sym.ERROR_STRING, yychar, yyline, yytext());}



