/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

/**
 *
 * @author felip
 */
public class RegistroSemantico_Return extends RegistroSemantico {

  public String tipoDato;
  public String valor;

  public RegistroSemantico_Return(String tipoDato_, String valor_) {
    this.tipoDato = tipoDato_;
    this.valor = valor_;
  }
}
