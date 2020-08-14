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
public class RegistroSemantico_DataObject extends RegistroSemantico {

  public String tipoDO;
  public String tipoDato;
  public Object valor;

  RegistroSemantico_DataObject(String tipoDO_, String tipoDato_, Object valor_) {
    super();
    tipoDO = tipoDO_;
    tipoDato = tipoDato_;
    valor = valor_;
  }

  @Override
  public String toString() {
    return super.toString() + "Tipo: " + this.tipoDO + ", Tipo Dato: " + this.tipoDato + ", Valor: " + this.valor.toString();
  }
}
