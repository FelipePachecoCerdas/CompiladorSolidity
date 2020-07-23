package lexico;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
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

Operadores = ( "<" | ">" | "&" | "|" | "~"
                | "[" | "]" | "**" | "<<" | ">>" )

Reservadas = ( "as"  | "constructor" 
                | "delete"  | "from" | "hex" | "import" 
                | "internal" | "mapping" | "modifier"  | "modifier" | "payable"  | "private" 
                | "public" | "returns" |"string"  
                | "var" |"view")

Trans = ("balance"  | "call" | "callcode" | "delegatecall" | "send" | "transfer" )

Unidades = ( "days" | "ether" | "finney" | "hours" | "minutes" | "seconds" | "szabo" | "weeks" | "wei" | "years")

Tipo = ( "bool" | "byte" | "bytes" | "address" | "int" | "string" | "ufixed" | "uint" 
                | "uint8" |"uint16" | "uint32" |"uint64" |"uint128"  
                | "int8" |"int16" | "int32" |"int64" |"int128" | "int256" 
                | "uint256" |"bytes8" | "bytes16" |"bytes32" |"bytes64" | "bytes128" |"bytes256")

WHITE=[ \t\r\n]
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{WHITE} {/* ignore */}

"//".* {/* ignore */}
{Comentario} {/* ignore */}

/*coma */
( "," ) {return new Symbol(sym.Coma, yychar, yyline, yytext());}

( ":" ) {return new Symbol(sym.Dos_Puntos, yychar, yyline, yytext());}
/* Comillas */
( "\"" ) {return new Symbol(sym.Comillas, yychar, yyline, yytext());}



/* Tipo de dato String */
( String ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}

/* Palabra reservada If */
( if ) {return new Symbol(sym.If, yychar, yyline, yytext());}

/* Palabra reservada Else */
( else ) {return new Symbol(sym.Else, yychar, yyline, yytext());}

/* Palabra reservada Do */
( do ) {return new Symbol(sym.Do, yychar, yyline, yytext());}

/* Palabra reservada While */
( while ) {return new Symbol(sym.While, yychar, yyline, yytext());}

/* Palabra reservada For */
( for ) {return new Symbol(sym.For, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}


/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" | "||" | "&&" ) {return new Symbol(sym.Op_logico, yychar, yyline, yytext());}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" | "=" ) {return new Symbol(sym.Op_atribucion, yychar, yyline, yytext());}


/*Operadores Booleanos*/
( true | false ) {return new Symbol(sym.Op_booleano, yychar, yyline, yytext());}

( "public" | "private" ) {return new Symbol(sym.Visibilidad, yychar, yyline, yytext());}

/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "main" ) {return new Symbol(sym.Main, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Operador caret */
( "^" ) {return new Symbol(sym.Caret, yychar, yyline, yytext());}

/* Operador punto */
( "." ) {return new Symbol(sym.Punto, yychar, yyline, yytext());}


("?") {return new Symbol(sym.Pregunta, yychar, yyline, yytext());}

{Tipo} {return new Symbol(sym.T_dato, yychar, yyline, yytext());}


"return"    {return new Symbol(sym.Return, yychar, yyline, yytext()); }
"returns"    {return new Symbol(sym.Returns, yychar, yyline, yytext()); }
"this"    {return new Symbol(sym.This, yychar, yyline, yytext()); }
"break"    {return new Symbol(sym.Break, yychar, yyline, yytext()); }
"continue"    {return new Symbol(sym.Continue, yychar, yyline, yytext()); }

("payable" | "internal" )  {return new Symbol(sym.Modificador, yychar, yyline, yytext()); }
"contract"    {return new Symbol(sym.Contract, yychar, yyline, yytext()); }
"enum"    {return new Symbol(sym.Enum, yychar, yyline, yytext()); }
"pragma"    {return new Symbol(sym.Pragma, yychar, yyline, yytext());}
"solidity"    {return new Symbol(sym.Solidity, yychar, yyline, yytext());}
"struct"    {return new Symbol(sym.Struct, yychar, yyline, yytext()); }
"function"    {return new Symbol(sym.Function, yychar, yyline, yytext()); }
("+" | "-" | "*" | "/" | "%" | "(" | ")" | "+=" | "-=" | "*=" | "/=")    {return new Symbol(sym.Op_Aritmetico, yychar, yyline, yytext()); }
( "==" | ">=" | ">" | "<=" | "<" | "!=" | "||" | "&&" | "!" )  {return new Symbol(sym.Op_logico, yychar, yyline, yytext()); }





	{Operadores}            {return new Symbol(sym.OPERADOR, yychar, yyline, yytext());}

        {Reservadas}		{return new Symbol(sym.PALABRA_RESERVADA, yychar, yyline, yytext());}

    	{Trans} 		{return new Symbol(sym.TRANSAC, yychar, yyline, yytext());}

    	{Unidades}              {return new Symbol(sym.UNIDAD, yychar, yyline, yytext());}



{Letras}({Letras}|{Digitos})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}


({Numero})|({Hexadecimal})|({NotacionCientifica})|({Flotantes}) {return new Symbol(sym.Numero, yychar, yyline, yytext());}

{Str} {return new Symbol(sym.String, yychar, yyline, yytext());}


(({Digitos}*) | {NoNotacionCientifica1}) {return new Symbol(sym.ERROR_CEROS_A_LA_IZQUIERDA, yychar, yyline, yytext());}
{NoNotacionCientifica2} | {NoNotacionCientifica4} {return new Symbol(sym.ERROR_NOTACION_CIENTIFICA, yychar, yyline, yytext());}
{NoFlotante} | {NoNotacionCientifica3} | {NoNotacionCientifica5} {return new Symbol(sym.ERROR_CEROS_A_LA_IZQUIERDA, yychar, yyline, yytext());}
({Digitos}+{Letras}+) {return new Symbol(sym.ERROR_IDENTIFICADOR, yychar, yyline, yytext());}
({NoHexadecimal}) {return new Symbol(sym.ERROR_HEXADECIMAL, yychar, yyline, yytext());}
{Noidentificador} {return new Symbol(sym.ERROR_CARACTERES_NO_VALIDOS, yychar, yyline, yytext()); }
"/**"{NoComentario} {return new Symbol(sym.ERROR_COMENTARIO, yychar, yyline, yytext()); }
/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
{NoStr} {return new Symbol(sym.ERROR_STRING, yychar, yyline, yytext());}

