/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

/**
 *
 * @author usuario
 */
public enum Token {
  IDENTIFICADOR, OPERADOR, PALABRA_RESERVADA, TRANSAC, UNIDAD, LITERAL, 
  ERROR_IDENTIFICADOR, ERROR_COMENTARIO, ERROR_STRING, ERROR_CARACTERES_NO_VALIDOS, ERROR_HEXADECIMAL, 
  ERROR_CEROS_A_LA_IZQUIERDA, ERROR_NOTACION_CIENTIFICA,
  
  //Tokens para el parseo 
  NUMERO, STRING,TIPO,IF, WHILE, FOR, DO, ELSE, RETURN, COMA, VISIBILDAD, MODIFICADOR, 
  CONTRACT, ENUM, PRAGMA, SOLIDITY, IGUAL, STRUCT, FUNCTION, OP_ARITMETICO, OP_BOOLEANO, PUNTOCOMA,
  PARENTESIS_ABRE, PARENTESIS_CIERRE, LLAVE_ABRE, LLAVE_CIERRE
  
  ;
}
