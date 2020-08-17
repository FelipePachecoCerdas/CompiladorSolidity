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
public class RegistroSemantico_If_Else extends RegistroSemantico{
  public String etiquetaElse;
  public String etiquetaCierre;

  RegistroSemantico_If_Else (String etiquetaElse_, String etiquetaCierre_){
    super();
    etiquetaElse = etiquetaElse_;
    etiquetaCierre = etiquetaCierre_;
  }

  @Override
  public String toString() {
    return super.toString() + "Etiqueta Else: " + this.etiquetaElse + ", Etiqueta de Cierre: " + this.etiquetaCierre;
  }
    
}
