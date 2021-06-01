package EjerciciosPOO;

/**
 * Crea una clase Fecha. Los objetos de la clase Fecha son fechas de tiempo y se crean de la forma:
 *
 * Fecha f = Fecha(1, 10, 2020);
 *
 * donde los parámetros que se le pasan al constructor son el día, el mes y el año respectivamente. Si la fecha creada es incorrecta mostraremos un mensaje de error en la salida estándar de errores (lo ideal sería lanzar una excepción, ya lo haremos cuando se vea en clase).
 * 
 * Crea métodos para:
 * 
 * Saber si la fecha almacenada es correcta.
 * Sumar y restar días a la fecha. 
 * Comparar la fecha almacenada con otra, el método devolverá un valor negativo si la fecha almacenada es ANTERIOR a la otra, 0 si son IGUALES y un valor positivo si es POSTERIOR.
 * Saber si el año de la fecha almacenada es bisiesto.
 * El método toString() devuelve una cadena con el formato "<día del mes> de <nombre del mes> de <año>".
 * Las opciones a partir de la segunda, solo se realizaron si la fecha almacenada es correcta, en caso de no serlo, se dará un aviso, por la salida estándar de errores, de que la operación no se puede realizar.
 * 
 * Puedes usar una variable de clase para almacenar el número de días de cada mes del año (que no sea bisiesto).
 *
 * 
 * @author Manuel Solar Bueno
 *
 */

public class Fecha implements Comparable<Fecha>, Cloneable {
  private int dia;
  private int mes;
  private int anyo;
  
  /**
   * Constructor de la clase.
   * @param dia
   * @param mes
   * @param anyo
   */
  public Fecha(int dia, int mes, int anyo) {
    assert esCorrecta(dia, mes, anyo): "Construcción de fecha incorrecta";
    this.dia = dia;
    this.mes = mes;
    this.anyo = anyo;
  }

  // Getters y Setters
  
  public int getDia() {
    return dia;
  }

  public void setDia(int dia) {
    assert esCorrecta(dia, this.mes, this.anyo): "Día incorrecto";
    this.dia = dia;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    assert esCorrecta(this.dia, mes, this.anyo): "Mes incorrecto";
    this.mes = mes;
  }

  public int getAnyo() {
    return anyo;
  }

  public void setAnyo(int anyo) {
    assert esCorrecta(this.dia, this.mes, anyo): "Año incorrecto";
    this.anyo = anyo;
  }
  
  // Métodos del objeto
  
  /**
   * @return Nombre del mes del objeto.
   */
  public String nombreMes() {
    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                      "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    return meses[this.mes-1];
  }
  
  /**
   * Clona el objeto actual.
   * Ojo, en Java podemos implementar la intefaz Cloneable y que tiene el método clone().
   * 
   * @return Copia del objeto actual
   */
  public Fecha clona() {
    return new Fecha(this.dia, this.mes, this.anyo);
  }
  
  /**
   * Suma un día a la fecha almacenada y la devuelve.
   * 
   * @return nueva fecha
   */
  public Fecha mas1Dia() {
    int dia = this.dia;
    int mes = this.mes;
    int anyo = this.anyo;
   
    // aumentamos el día
    dia++;
    if (dia > diasMes(mes, anyo)) {
      // mes siguiente
      dia = 1;
      mes++;
      if (mes>12) { // nos pasamos de diciembre, año siguiente
        mes = 1;
        anyo++;
      }
    }
    return new Fecha(dia, mes, anyo);
  }
  
  /**
   * Suma una serie de días a la fecha almacenada y la devuelve.
   * 
   * @param dias
   * @return nueva fecha
   */
  public Fecha masNDias(int dias) {
    Fecha fecha2 = this.clona();
    if (dias>=0) {
      for (int i=1; i<=dias; i++) {
        fecha2 = fecha2.mas1Dia();
      }
    } else {
      for (int i=1; i<=Math.abs(dias); i++) {
        fecha2 = fecha2.menos1Dia();
      }
    }
    return fecha2;
  }
  
  /**
   * Resta un día a la fecha almacenada y la devuelve.
   * 
   * @return nueva fecha
   */
  public Fecha menos1Dia() {
    int dia = this.dia;
    int mes = this.mes;
    int anyo = this.anyo;
    
    // disminuimos el día
    dia--;
    if (dia==0) { // mes anterior y último día de mes
      mes--;
      if (mes == 0) { // 31 de diciembre del año anterior
        mes = 12;
        anyo--;
        assert anyo>=0: "Año negativo"; // no puede haber años negativos
      } 
      dia = diasMes(mes, anyo); // último día del mes anterior
    }
    return new Fecha(dia, mes, anyo);
  }
  
  /**
   * Resta una serie de días a la fecha almacenada y la devuelve.
   * 
   * @param dias
   * @return nueva fecha
   */
  public Fecha menosNDias(int dias) {
    Fecha fecha2 = this.clona();
    if (dias>=0) {
      for (int i=1; i<=dias; i++) {
        fecha2 = fecha2.menos1Dia();
      }
    } else {
      for (int i=1; i<=Math.abs(dias); i++) {
        fecha2 = fecha2.mas1Dia();
      }
    }
    return fecha2;
  }
  
  // Métodos estáticos
  
  public static boolean esBisiesto(int anyo) {
    return anyo%400==0 || (anyo%4==0 && anyo%100!=0);
  }

  public static boolean esCorrecta(int dia, int mes, int anyo) {
    // año correcto
    if (anyo < 0) {
      return false;
    }   
    // mes correcto
    if (mes<1 || mes>12) {
      return false;
    }
    // día correcto
    return dia>0 && dia<=diasMes(mes, anyo);
  }
      
  /**
   * @param mes
   * @param anyo
   * @return Días del mes actual
   */
  public static int diasMes(int mes, int anyo) {
    int[] diasMes_ = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (esBisiesto(anyo)) {
      diasMes_[1] = 29;
    }
    return diasMes_[mes - 1];
  }
  
  @Override
  public String toString() {
    return this.dia + " de " + this.nombreMes() + " de " + this.anyo;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + anyo;
    result = prime * result + dia;
    result = prime * result + mes;
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
    Fecha other = (Fecha) obj;
    if (anyo != other.anyo)
      return false;
    if (dia != other.dia)
      return false;
    if (mes != other.mes)
      return false;
    return true;
  }

  @Override
  public int compareTo(Fecha fecha2) {
    int f1 = this.anyo*10000 + this.mes*100 + this.dia;
    int f2 = fecha2.anyo*10000 + fecha2.mes*100 + fecha2.dia;
    return f1-f2;
  }
  
  public Fecha clone() {
    Fecha clon = new Fecha(this.dia, this.mes, this.anyo);
    return clon;
  }

}
