/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

/**
 *
 * @author user
 */
public class RegistroSemantico_If extends RegistroSemantico{
  public String etiquetaCierre;

  RegistroSemantico_If ( String etiquetaCierre_){
    super();
    etiquetaCierre = etiquetaCierre_;
  }

  @Override
  public String toString() {
    return super.toString() + "Etiqueta de Cierre: " + this.etiquetaCierre;
  }
    
}
