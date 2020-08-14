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
public class RegistroSemantico_Operador extends RegistroSemantico {

  public String tipoOperador;
  public String operador;

  RegistroSemantico_Operador(String tipoOperador_, String operador_) {
    super();
    tipoOperador = tipoOperador_;
    operador = operador_;
  }

  @Override
  public String toString() {
    return super.toString() + "Tipo operador: " + this.tipoOperador + ", Operador: " + this.operador;
  }

}
