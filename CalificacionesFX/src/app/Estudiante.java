package app;

import java.util.Arrays;

/**
 * Clase Estudiante
 * 
 * @author Manuel Solar Bueno
 *
 */

public class Estudiante implements Comparable<Estudiante>{
  
   private String nombre;
   private int[] notas = new int[5];
  
  public Estudiante (String nombre) {
    this.nombre = nombre;
    for (int i = 1; i <= 5; i++) {
      setNota(i, -1);
    }
  }
  
  public Estudiante(String nombre, int[] notas) {
    this.nombre = nombre;
    this.notas = notas;
  }
  
  private void setNota(int ejercicio, int nota) {
    notas[ejercicio-1] = nota;
  }

  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public int[] getNotas() {
    return notas;
  }
  public void setNotas(int[] notas) {
    this.notas = notas;
  }
  
  public double getMedia() {
    double suma = 0;
    for (int ejercicio = 1; ejercicio <= 5; ejercicio++) {
      if (getNota(ejercicio) == -1) {
        return 0;
      }
      suma += getNota(ejercicio);
    }
    return suma / 5;
  }

  int getNota(int ejercicio) {
    return notas[ejercicio-1];
  }
  
  public int getNota1() {
    return notas[0];
  }

  @Override
  public int compareTo(Estudiante estudiante) {
    return this.nombre.compareTo(estudiante.nombre);
  }

  @Override
  public String toString() {
    return "Estudiante [nombre=" + nombre + ", notas=" + Arrays.toString(notas) + "]";
  }
  
  
  
  

}
