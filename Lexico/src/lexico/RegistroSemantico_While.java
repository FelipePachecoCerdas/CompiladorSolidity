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
public class RegistroSemantico_While extends RegistroSemantico{
  public String etiquetaInicio;
  public String etiquetaCierre;

  RegistroSemantico_While (String etiquetaInicio_, String etiquetaCierre_){
    super();
    etiquetaInicio = etiquetaInicio_;
    etiquetaCierre = etiquetaCierre_;
  }

  @Override
  public String toString() {
    return super.toString() + "Etiqueta de Inicio: " + this.etiquetaInicio + ", Etiqueta de Cierre: " + this.etiquetaCierre;
  }
    
}
