/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author usuario
 */
class SingletoneEscaner {

  private static SingletoneEscaner single_instance = null;

  private SingletoneEscaner() {
  }

  public static SingletoneEscaner getInstance() {
    if (single_instance == null) {
      single_instance = new SingletoneEscaner();
    }

    return single_instance;
  }

  public LinkedHashMap<String, LinkedHashMap<String, Integer>> usarJflex(Lexer lexer) throws IOException {
    String Resultados = "";
    LinkedHashMap<String, LinkedHashMap<String, Integer>> palabras = new LinkedHashMap<>();

    while (true) {
      Token token = lexer.yylex();
      if (token == null) {
        break;
        //Resultados = Resultados + "FIN";
        //return Resultados;
      }//termina evaluacion
      switch (token) {

        case IDENTIFICADOR:
        case OPERADOR:
        case PALABRA_RESERVADA:
        case TRANSAC:
        case UNIDAD:
        case LITERAL:
          System.out.println(lexer.lexeme);
          Integer ultimoEspacio = lexer.lexeme.lastIndexOf(' ');
          String stringToken = token + " " + lexer.lexeme.substring(0, ultimoEspacio);
          String numeroLinea = lexer.lexeme.substring(ultimoEspacio + 1);
          if (palabras.get(stringToken) == null) {
            LinkedHashMap<String, Integer> lineas = new LinkedHashMap<>();
            lineas.put(numeroLinea, 1);
            palabras.put(stringToken, lineas);
          } else {
            palabras.get(stringToken).put(numeroLinea, palabras.get(stringToken).getOrDefault(numeroLinea, 0) + 1);
          }
          System.out.println(palabras);
          System.out.println(token + " " + lexer.lexeme);
          //Resultados = Resultados + "Token:" + token + " " + lexer.lexeme + "\n";
          break;
        case ERROR_IDENTIFICADOR:
        case ERROR_COMENTARIO:
        case ERROR_STRING:
        case ERROR_CARACTERES_NO_VALIDOS:
        case ERROR_HEXADECIMAL:
        case ERROR_CEROS_A_LA_IZQUIERDA:
        case ERROR_NOTACION_CIENTIFICA:
          ultimoEspacio = lexer.lexeme.lastIndexOf(' ');
          stringToken = token + " " + lexer.lexeme.substring(0, ultimoEspacio);
          numeroLinea = lexer.lexeme.substring(ultimoEspacio + 1);
          if (palabras.get(stringToken) == null) {
            LinkedHashMap<String, Integer> lineas = new LinkedHashMap<>();
            lineas.put(numeroLinea, 1);
            palabras.put(stringToken, lineas);
          } else {
            palabras.get(stringToken).put(numeroLinea, palabras.get(stringToken).getOrDefault(numeroLinea, 0) + 1);
          }
          System.out.println(palabras);
          System.out.println(token + " " + lexer.lexeme);
        //Resultados = Resultados + "Token:" + token + " " + lexer.lexeme + "\n";                    
      }
    }
    for (String palabra : palabras.keySet()) {
      Resultados = Resultados + palabra;
      for (String linea : palabras.get(palabra).keySet()) {
        Resultados = Resultados + " " + linea + "(" + palabras.get(palabra).get(linea) + "),";
      }
      Resultados = Resultados.substring(0, Resultados.length() - 1) + "\n";
    }
    Resultados = Resultados + "FIN";
    return palabras;
  }
}
