package EjerciciosPOO;

/**
 * 1. Crea la clase Tiempo. Los objetos de la clase Tiempo son intervalos de tiempo y se crean de la forma:
 * 
 * t = Tiempo(1, 20, 30)
 * 
 * donde los parámetros que se le pasan al constructor son las horas, los minutos y los segundos respectivamente. 

 * Crea métodos para:

 * Sumar y restar otro objeto de la clase Tiempo (el resultado es otro objeto).
 * Sumar y restar segundos, minutos y/o horas (se cambia el objeto actual).
 * Devolver una cadena con el tiempo almacenado, de forma que si lo que hay es (10 35 5) la cadena sea 10h 35m 5s.
 * Realiza un programa de prueba para comprobar que la clase funciona bien.
 * 
 * @author Manuel Solar Bueno
 *
 */

public class Tiempo {
  private int horas;
  private int minutos;
  private int segundos;
  
  /**
   * Constructor de la clase Tiempo
   * 
   * @param horas
   * @param minutos
   * @param segundos
   */
  public Tiempo (int horas, int minutos, int segundos) {
    this.horas=horas;
    this.minutos=minutos;
    this.segundos=segundos;
  }

  public int getHoras() {
    return horas;
  }

  public void setHoras(int horas) {
    this.horas = horas;
  }

  public int getMinutos() {
    return minutos;
  }

  public void setMinutos(int minutos) {
    this.minutos = minutos;
  }

  public int getSegundos() {
    return segundos;
  }

  public void setSegundos(int segundos) {
    this.segundos = segundos;
  }
  
  public Tiempo sumar(Tiempo t) {
   return new Tiempo(this.horas + t.horas, this.minutos + t.minutos, this.segundos + t.segundos);
  }
  
  public Tiempo restar(Tiempo t) {
    return new Tiempo(this.horas - t.horas, this.minutos + t.minutos, this.segundos + t.segundos);
   }
  
  public void sumaSegundos(int segundos) {
    this.segundos += segundos;
    
  }
  
  public void restaSegundos(int segundos) {
    this.sumaSegundos(-1 * segundos);
  }
  
  public void sumaMinutos(int minutos) {
    this.sumaSegundos(60 * minutos);
  }
  
  public void restaMinutos(int minutos) {
    this.sumaSegundos(-60 * minutos);
  }
  
  public void sumaHoras(int horas) {
    this.sumaSegundos(3600 * horas);
  }
  
  public void restaHoras(int horas) {
    this.sumaSegundos(-3600 * horas);
  }
  
  /**
   * Normaliza el estado del objeto haciendo que los valores de horas, minutos y segundos sean correctos.
   * Lo llamaremos después de cualquier operación que pueda dejar el estado incosistente.
   */
  public void normaliza() {
    int s = this.horas * 3600 + this.minutos * 60 + this.segundos; // segundos totales
    
    this.horas = s / 3600;
    this.minutos = (s % 3600) / 60; // el resto nos da los segundos sobrantes, al dividirlo entre 60 tenemos los minutos
    this.segundos = (s % 3600) %60; // el resto de la operaci�n anterior
  }

  @Override
  public String toString() {
    return this.horas + "h " + this.minutos + "m " + this.segundos + "s";
  }
  
  
  
}
