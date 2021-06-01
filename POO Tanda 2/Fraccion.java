package EjerciciosPOO;

/**
 * Crea una clase Fraccion de forma que podamos hacer las siguientes operaciones:

 * Contruir un objeto Fraccion pasándole el numerador y el denominador.
 * Obtener la fracción como cadena de caracteres.
 * Obtener y modificar numerador y denominador. No se puede dividir por cero.
 * Obtener resultado de la fracción (número real).
 * Multiplicar la fracción por un número (el resultado es otro objeto fracción).
 * Multiplicar, sumar y restar fracciones (el resultado es otro objeto fracción).
 * Simplificar la fracción (cambia el objeto actual).
 * 
 * @author Manuel Solar Bueno
 *
 */

public class Fraccion implements Comparable<Fraccion>, Cloneable {
  
  // atributos
  
  private int num;
  private int den;
  
  // comportamiento
  
  /**
   * Constructor de la clase.
   * 
   * Construye una fracción al pasarle un numerador y un denominador.
   * 
   * @param num
   * @param den
   */
  
  public Fraccion(int num, int den) {
    this.num = num;
    this.den = den;
  }
  
  /**
   * Método toString
   * 
   * @return
   */
  public String toString() {
    String texto = num + " / " + den;
    return texto;
  }
  
  /**
   * getter del numerador
   * 
   * @return
   */
  public int getNum() {
    return num;
  }
  
  /**
   * setter del numerador
   * 
   * @param num
   */
  public void setNum(int num) {
    this.num = num;
  }
  
  /**
   * getter del denominador
   * 
   * @return
   */
  public int getDen() {
    return den;
  }
  
  /**
   * setter del denominador
   * 
   * @param den
   */
  public void setDen(int den) {
    if (den != 0) {
      this.den = den;
    }
  }
  
  /**
   * Obtener el resultado de la fracción.
   * 
   * @return
   */
  public int resultado( ) {
    int resultado = (num/den);
    return resultado;
  }
  
  public Fraccion multiplicar(int n) {
    return new Fraccion(this.num*n , this.den);
  }
  
  public Fraccion multiplicar(Fraccion a) {
    return new Fraccion(this.num * a.num, this.den * a.den);
  }
  
  public Fraccion sumar(Fraccion a) {
    return new Fraccion(this.num * a.den + this.den * a.num, this.den * a.den);
  }
  
  public Fraccion restar(Fraccion a) {
    return new Fraccion(this.num * a.den - this.den * a.num, this.den * a.den);
  }
  
  @Override
  public int compareTo(Fraccion a) {
    return (this.num*a.den - a.num*this.den);
  }
  
  public int compareTo(int a) {
    return this.compareTo(new Fraccion(a, 1));
  }
  
  public Fraccion clone() {
    Fraccion clon = new Fraccion(this.num, this.den);
    return clon;
  }
}
