package EjerciciosPOO;

/**
 * Crea una clase que represente objetos de tipo Rectángulo, de forma que:

 * En el estado de cada objeto guardemos el ancho y el alto del mismo. Usaremos estos datos para construirlo.
 * 
 * Las acciones que podemos realizar con objetos de esta clase son: 
 * C�lculo del per�metro.
 * C�lculo del área.
 * Dibujarlo (con *).
 * Compararlo con otros.
 * Devolver una copia del mismo en otro objeto (clonarlo).
 * Transformar su estado a una cadena (toString()).
 * 
 * @author Manuel Solar Bueno
 */

public class Rectangulo implements Comparable<Rectangulo>, Cloneable {
  private int largo;
  private int ancho;
  
  /**
   * Constructor de la clase
   * 
   * @param largo
   * @param ancho
   */
  public Rectangulo(int largo, int ancho) {
    this.largo = largo;
    this.ancho = ancho;
  }
  
  // Getters y Setters
  
  public int getLargo() {
    return largo;
  }

  public void setLargo(int largo) {
    this.largo = largo;
  }

  public int getAncho() {
    return ancho;
  }

  public void setAncho(int ancho) {
    this.ancho = ancho;  
  }
  
  // M�todos del Objetos
  
  /**
   * Cálculo del perímetro
   * 
   * @param largo
   * @param ancho
   * 
   * @return perimetro
   */
  public int getPerimetro() {
    return 2*this.largo + 2*this.ancho;
  }
  
  /**
   * Cálculo del área.
   * 
   * @param largo
   * @param ancho
   * 
   * @return area
   */
  public int getArea() {
    return this.largo*this.ancho;
  }
  
  /**
   * Representar graficamente con * el rectángulo
   * 
   */
  public void dibujar() {
    int j=0;
    int i=0;
    
    while(i < largo) {
      while(j < ancho) {
        System.out.println("*");
        j++;
      }
     System.out.println(" ");
     j = 0;
     i++;
    }
  }
  
  /**
   * Comparar dos rectangulos.
   * 
   * @param a
   * @return
   */
  @Override
  public int compareTo(Rectangulo a) {
    return (this.getArea() - a.getArea());
  }
  
  /**
   * Clona un rectangulo.
   */
  public Rectangulo clone() {
    return new Rectangulo(this.largo, this.ancho);
  }

  @Override
  public String toString() {
    return "Rectangulo [largo=" + largo + ", ancho=" + ancho + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ancho;
    result = prime * result + largo;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Rectangulo other = (Rectangulo) obj;
    if (ancho != other.ancho)
      return false;
    if (largo != other.largo)
      return false;
    return true;
  }  
   
  
}
