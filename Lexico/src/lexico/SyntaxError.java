/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.util.ArrayList;
import java.util.Stack;
import java_cup.runtime.Symbol;

/**
 *
 * @author felip
 */
public class SyntaxError {

  public ArrayList<Symbol> errores = new ArrayList<>();
  public ArrayList<String> erroresStr = new ArrayList<>();
  public Stack<Symbol> stack;

  public void syntaxError(Symbol s) {
    System.out.println("ERROR EN " + s.value + " " + Integer.toString(s.right + 1) + ": " + Integer.toString(s.left + 1));
    String cadena = "";

    if (s.left + 1 == 0 || s.right + 1 == 0) {
      cadena += "~" + "EOF" + "~";
      Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
      errores.add(sp2);
      erroresStr.add("Reached_EOF_While_Parsing");
      return;
    }

    for (int i = this.stack.size() - 1; i > 0; i--) {
      Symbol sp = this.stack.get(i);

      //System.out.println(sp.value + " " + Integer.toString(sp.right + 1) + ": " + Integer.toString(sp.left + 1) );
      cadena = ((sp.value == null) ? ".." : sp.value) + " " + cadena;
      //System.out.println(cadena );
      if (sym.T_dato == sp.sym) {
        String cadena2 = cadena;
        for (int j = i - 1; j > 0; j--) {
          sp = this.stack.get(j);
          cadena2 = ((sp.value == null) ? ".." : sp.value) + " " + cadena2;
          if ("struct".equals(sp.value)) {

            boolean set = false;

            for (int r = this.stack.size() - 1; r > i; r--) {
              Symbol toc = this.stack.get(r);

              if ((toc.sym == sym.Numero || s.sym == sym.Numero
                || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
                || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
                set = true;
                erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
                set = true;
                erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
                || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
                set = true;
                erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
                set = true;
                erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
                set = true;
                erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }

              if (toc.sym == sym.Not || s.sym == sym.Not) {
                set = true;
                erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
            }

            Symbol sp3 = (this.stack.get(j + 1).value == null ? this.stack.get(j + 2) : this.stack.get(j + 1));
            cadena = sp.value + " " + sp3.value + " ... " + cadena + "~" + s.value + "~ ...";
            Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
            errores.add(sp2);
            if (!set) {
              erroresStr.add("Struct_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
            }
            break;
          }
          if ("(".equals(sp.value)) {
            for (int k = j - 1; k > 0; k--) {
              sp = this.stack.get(k);
              cadena2 = ((sp.value == null) ? ".." : sp.value) + " " + cadena2;
              if ("function".equals(sp.value)) {

                cadena2 += "~" + s.value + "~ ...";
                Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena2);
                errores.add(sp2);
                erroresStr.add("Parametro_Function" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if ("returns".equals(sp.value)) {
                for (int l = k - 1; l > 0; l--) {
                  sp = this.stack.get(l);
                  cadena2 = ((sp.value == null) ? ".." : sp.value) + " " + cadena2;
                  if ("function".equals(sp.value)) {
                    cadena2 += "~" + s.value + "~ ...";
                    Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena2);
                    errores.add(sp2);
                    erroresStr.add("Returns_Function" + " (" + Integer.toString(sp.right + 1) + ")");
                    break;
                  }
                }
                break;
              }
            }
            break;
          }
          if ("returns".equals(sp.value)) {
            for (int k = j - 1; k > 0; k--) {
              sp = this.stack.get(k);
              cadena2 = ((sp.value == null) ? ".." : sp.value) + " " + cadena2;
              if ("function".equals(sp.value)) {
                cadena2 += "~" + s.value + "~ ...";
                Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena2);
                errores.add(sp2);
                erroresStr.add("Returns_Function" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
            }

            break;
          }

          if ("contract".equals(sp.value)) {

            boolean set = false;

            for (int r = this.stack.size() - 1; r > i; r--) {
              Symbol toc = this.stack.get(r);

              if ((toc.sym == sym.Numero || s.sym == sym.Numero
                || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
                || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
                set = true;
                erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
                set = true;
                erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
                || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
                set = true;
                erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
                set = true;
                erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
                set = true;
                erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }

              if (toc.sym == sym.Not || s.sym == sym.Not) {
                set = true;
                erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
            }

            Symbol sp3 = (this.stack.get(j + 1).value == null ? this.stack.get(j + 2) : this.stack.get(j + 1));
            cadena = sp.value + " " + sp3.value + " ... " + cadena + "~" + s.value + "~ ...";
            Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
            errores.add(sp2);
            if (!set) {
              erroresStr.add("Contract_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
            }
            break;

            //cadena += "~" + s.value + "~ ...";
            //Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
            //errores.add(sp2);
            //if (!set) erroresStr.add("If"+" (" + Integer.toString(sp.right+1) + ")");
            //break;
          }
          if ("function".equals(sp.value)) {

            boolean set = false;

            for (int r = this.stack.size() - 1; r > i; r--) {
              Symbol toc = this.stack.get(r);

              if ((toc.sym == sym.Numero || s.sym == sym.Numero
                || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
                || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
                set = true;
                erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
                set = true;
                erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
                || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
                set = true;
                erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
                set = true;
                erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
              if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
                set = true;
                erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }

              if (toc.sym == sym.Not || s.sym == sym.Not) {
                set = true;
                erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
                break;
              }
            }

            Symbol sp3 = (this.stack.get(j + 1).value == null ? this.stack.get(j + 2) : this.stack.get(j + 1));
            cadena = sp.value + " " + sp3.value + " ... " + cadena + "~" + s.value + "~ ...";
            Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
            errores.add(sp2);
            if (!set) {
              erroresStr.add("Function_Variable" + " (" + Integer.toString(sp.right + 1) + ")");
            }
            break;
          }
        }
        break;
      }
      if ("returns".equals(sp.value)) {
        //System.out.println("Es aquí la verdad");
        for (int j = i - 1; j > 0; j--) {
          sp = this.stack.get(j);
          cadena = ((sp.value == null) ? ".." : sp.value) + " " + cadena;
          if ("function".equals(sp.value)) {
            //System.out.println(cadena);
            cadena += "~" + s.value + "~ ...";
            Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
            errores.add(sp2);
            erroresStr.add("Returns_Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }
        //System.out.println("Toy aquí");
        break;
      }
      if ("enum".equals(sp.value)) {
        boolean set = false;
        //System.out.println("Soy un error de enum xd");
        ////System.out.println("Soy un error de enum xd");
        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);
          if (toc.sym == sym.Llave_c) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "enum ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Contract_Structure" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }

        cadena += "~" + s.value + "~ ...";
        //if (this.lookahead != null) for (Symbol x : this.lookahead) //System.out.println(x.value);
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("Enum" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;
      }
      if ("struct".equals(sp.value)) {

        ////System.out.println("Soy un error de struct chama");
        boolean set = false;

        boolean duperFound = false;

        for (int x = this.stack.size() - 1; x > 0; x--) {
          if ("function".equals(this.stack.get(x).value)) {
            break;
          }
          if ("{".equals(this.stack.get(x).value)) {
            duperFound = true;
          }
        }
        if (!duperFound) {
          cadena += "~" + s.value + "~ ...";
          Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
          errores.add(sp2);
          erroresStr.add("Function" + " (" + Integer.toString(sp.right + 1) + ")");
          break;
        }

        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);
          if (toc.sym == sym.Llave_c) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "struct ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Contract_Structure" + " (" + Integer.toString(toc.right + 1) + ")" + " on Struct" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }

        cadena += "~" + s.value + "~ ...";
        //if (this.lookahead != null) for (Symbol x : this.lookahead) //System.out.println(x.value);
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("Struct" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;
      }
      if ("function".equals(sp.value)) {
        //System.out.println("Soy un error de function chama!!!");

        boolean set = false;

        boolean duperFound = false;

        for (int x = this.stack.size() - 1; x > 0; x--) {
          if ("function".equals(this.stack.get(x).value)) {
            break;
          }
          if ("{".equals(this.stack.get(x).value)) {
            duperFound = true;
          }
        }
        if (!duperFound) {
          cadena += "~" + s.value + "~ ...";
          Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
          errores.add(sp2);
          erroresStr.add("Function" + " (" + Integer.toString(sp.right + 1) + ")");
          break;
        }

        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);

          if ((toc.sym == sym.Numero || s.sym == sym.Numero
            || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
            || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
            || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Llave_c) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Contract_Structure" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }

          if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }

          if (toc.sym == sym.Not || s.sym == sym.Not) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "function ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on Function" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }
        cadena += "~" + s.value + "~ ...";
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("Function" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;
      }
      if ("pragma".equals(sp.value)) {
        ////System.out.println("Soy un error de inicio");
        cadena += "~" + s.value + "~ ...";
        //if (this.lookahead != null) for (Symbol x : this.lookahead) //System.out.println(x.value);
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        erroresStr.add("File_Structure" + " (" + Integer.toString(sp.right + 1) + ")");
        break;
      }/*
      if (";".equals(sp.value)) {
        ////System.out.println("Soy un error de Expresion");
        cadena += "~" + s.value + "~ ...";
        //if (this.lookahead != null) for (Symbol x : this.lookahead) //System.out.println(x.value);
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        erroresStr.add("Expression"+" (" + Integer.toString(sp.right+1) + ")");
        break;
      }*/

      if ("contract".equals(sp.value)) {
        ////System.out.println("Soy un error de inicio");
        boolean set = false;
        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);
          if (toc.sym == sym.Llave_c) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("function".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "contract ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Contract_Structure" + " (" + Integer.toString(toc.right + 1) + ")" + " on Contract" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }

        cadena += "~" + s.value + "~ ...";
        //if (this.lookahead != null) for (Symbol x : this.lookahead) //System.out.println(x.value);
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("Contract" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;
      }
      if ("if".equals(sp.value)) {
        ////System.out.println("Soy un error de if");
        boolean set = false;

        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);
          if (toc.sym == sym.Else || s.sym == sym.Else) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            Symbol prev = (toc.sym == sym.Else) ? this.stack.get(r - 1) : this.stack.get(this.stack.size() - 1);
            if ("}".equals(prev.value)) {
              erroresStr.add("Wrong_Placement" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            } else {
              erroresStr.add("Missing_Closing_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            }
            set = true;

            break;
          }

          if ((toc.sym == sym.Numero || s.sym == sym.Numero
            || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
            || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "if ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "if ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "if ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
            || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "if ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "if ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }

          if (toc.sym == sym.Not || s.sym == sym.Not) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("if".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "if ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on If" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }
        cadena += "~" + s.value + "~ ...";
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("If" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;

      }
      if ("for".equals(sp.value)) {
        ////System.out.println("Soy un error de for");
        boolean set = false;

        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);

          if ((toc.sym == sym.Numero || s.sym == sym.Numero
            || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
            || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("for".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "for ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("for".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "for ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("for".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "for ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
            || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("for".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "for ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("for".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "for ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }

          if (toc.sym == sym.Not || s.sym == sym.Not) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("for".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "for ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on For" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }
        cadena += "~" + s.value + "~ ...";
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("For" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;

      }
      if ("while".equals(sp.value)) {
        ////System.out.println("Soy un error de while");
        boolean set = false;

        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);

          if ((toc.sym == sym.Numero || s.sym == sym.Numero
            || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
            || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("while".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "while ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("while".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "while ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("while".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "while ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
            || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("while".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "while ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("while".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "while ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }

          if (toc.sym == sym.Not || s.sym == sym.Not) {
            cadena = "";
            boolean superSet = false, found = false, found2 = false;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if (")".equals(this.stack.get(x).value)) {
                found2 = true;
              }
              if ("while".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "while ( .. ) ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on While" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }
        cadena += "~" + s.value + "~ ...";
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("While" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;
      }
      if ("do".equals(sp.value)) {
        ////System.out.println("Soy un error de do");
        boolean set = false;

        for (int r = this.stack.size() - 1; r > i; r--) {
          Symbol toc = this.stack.get(r);

          if ((toc.sym == sym.Numero || s.sym == sym.Numero
            || toc.sym == sym.String || s.sym == sym.String || toc.sym == sym.Identificador || s.sym == sym.Identificador
            || toc.sym == sym.Op_booleano || s.sym == sym.Op_booleano) && (toc.value != null)) {
            cadena = "";
            boolean superSet = false, found = false, found2 = true;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if ("do".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "do ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Operator_Or_Semicolon" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Dos_Puntos || s.sym == sym.Dos_Puntos || toc.sym == sym.Pregunta || s.sym == sym.Pregunta) {
            cadena = "";
            boolean superSet = false, found = false, found2 = true;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if ("do".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "do ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Wrong_Ternary_Operator" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_atribucion || s.sym == sym.Op_atribucion || toc.sym == sym.Igual || s.sym == sym.Igual) {
            cadena = "";
            boolean superSet = false, found = false, found2 = true;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if ("do".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "do ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Assignment_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Op_Aritmetico || toc.sym == sym.Op_logico || toc.sym == sym.Mas || toc.sym == sym.Menos
            || s.sym == sym.Op_Aritmetico || s.sym == sym.Op_logico || s.sym == sym.Mas || s.sym == sym.Menos || toc.sym == sym.Punto || s.sym == sym.Punto) {
            cadena = "";
            boolean superSet = false, found = false, found2 = true;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if ("do".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "do ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_Operand" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
          if (toc.sym == sym.Parentesis_c || s.sym == sym.Parentesis_c || toc.sym == sym.Parentesis_a || s.sym == sym.Parentesis_a) {
            cadena = "";
            boolean superSet = false, found = false, found2 = true;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if ("do".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "do ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Parenthesis_Or_Expression" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }

          if (toc.sym == sym.Not || s.sym == sym.Not) {
            cadena = "";
            boolean superSet = false, found = false, found2 = true;
            for (int x = this.stack.size() - 1; x > 0; x--) {
              if ("{".equals(this.stack.get(x).value)) {
                found = true;
              }
              if ("do".equals(this.stack.get(x).value)) {
                cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
                superSet = true;
                break;
              }
              if (";".equals(this.stack.get(x).value) || "{".equals(this.stack.get(x).value)) {
                break;
              }
              cadena = ((this.stack.get(x).value == null) ? ".." : this.stack.get(x).value) + " " + cadena;
            }
            if (!superSet) {
              cadena = "do ... " + cadena;
            }
            if (superSet && !found && found2) {
              set = true;
              erroresStr.add("Missing_Opening_Bracket" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
              break;
            }
            set = true;
            erroresStr.add("Missing_Expression_On_Not" + " (" + Integer.toString(toc.right + 1) + ")" + " on Do" + " (" + Integer.toString(sp.right + 1) + ")");
            break;
          }
        }
        cadena += "~" + s.value + "~ ...";
        Symbol sp2 = new Symbol(s.sym, s.left, s.right, cadena);
        errores.add(sp2);
        if (!set) {
          erroresStr.add("Do" + " (" + Integer.toString(sp.right + 1) + ")");
        }
        break;

      }
    }

  }
}
