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
public class SimboloTS {

  public String tipoDato;
  public Object valor;
  public String alcance;

  SimboloTS(String tipoDato_, Object valor_, String alcance_) {
    this.tipoDato = tipoDato_;
    this.valor = valor_;
    this.alcance = alcance_;
  }
}
