package EjerciciosPOO;

/**
 * Crea la clase Cuadrado partiendo de la clase Rectángulo (hereda de esta) del ejercicio anterior. Consideraciones:

 * Un cuadrado es un rectángulo con base==altura.
 * Modificar el lado.
 * El constructor de la clase Cuadrado solo tendrá un parámetro, su lado.
 * No hay que crear atributos nuevos.
 */


public class Cuadrado extends Rectangulo {

  public Cuadrado(int lado) {
    super(lado, lado);
  }
  
  public int getLado() {            
    return this.getLargo();
  }
  
  public void setLado(int lado) {   
    super.setLargo(lado);
    super.setAncho(lado);
  }
  
  @Override
  public void setLargo(int altura) {   
    this.setLado(altura);
  }
  
  @Override
  public void setAncho(int anchura) { 
    this.setLado(anchura);
  }

  @Override
  public String toString() {
    return "Cuadrado [lado=" + getLado() + "]";
  }

}
