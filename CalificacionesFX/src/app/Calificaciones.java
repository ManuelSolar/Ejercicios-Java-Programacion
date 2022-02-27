package app;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


/**
 * Clase Calificaciones
 * 
 * @author Manuel Solar Bueno
 *
 */

public class Calificaciones {

 private ArrayList<Estudiante> alumnos = new ArrayList<>();
  
  public void cargaCSV(String nombreFichero) throws IOException, CSVCalificacionesException {
    var reader = new CalificacionesCSVLoader(alumnos);
    reader.cargaCSV(nombreFichero);
  }
  
  public boolean isEmpty() {
    return alumnos.isEmpty();
  }
  
  public Estudiante getEstudiante(int estudiante) {
    
    Estudiante alumno = alumnos.get(estudiante)  ;    
    
    return alumno;
  }
  
  public int cantidadAlumnosAprobados() {
    int alumnosAprobados = 0;
    for (Estudiante alumno : alumnos) {
      if (alumno.getMedia() >= 5) {
        alumnosAprobados++;
      }
    }
    return alumnosAprobados;
  }
  
  public int cantidadEjercicioEntregados(int ejercicio) {
    int ejercicioEntregados = 0;
    for (Estudiante alumno : alumnos) {
      if (alumno.getNota(ejercicio) > 0) {
        ejercicioEntregados++;
      }
    }
    return ejercicioEntregados;
  }
  
  public double mediaEjercicioEntregados(int ejercicio) {
    int numeroEntregas = 0;
    double mediaEjercicio = 0;
    for (Estudiante alumno : alumnos) {
      if (alumno.getNota(ejercicio) > 0) {
        mediaEjercicio = alumno.getNota(ejercicio) + mediaEjercicio;
        numeroEntregas++;
      }
    }
    return mediaEjercicio / numeroEntregas;
    
  }
  
  public double notaMasAlta(int ejercicio) {
    double mejorNota = 0;
    for (Estudiante alumno : alumnos) {
      if (alumno.getNota(ejercicio) > mejorNota) {
        mejorNota = alumno.getNota(ejercicio);
      } 
    }    
    return mejorNota;
  }
  
  public double mediaMasAlta() {
    double mejorMedia = 0;
    
    for (Estudiante alumno : alumnos) {
      if (alumno.getMedia() > mejorMedia) {
        mejorMedia = alumno.getMedia();
      }        
    }
      
    return mejorMedia;

  }
  
  public void guardaXML(String nombreFichero) throws IOException, XMLCalificacionesException, TransformerException, ParserConfigurationException {
    var writer = new CalificacionesXMLWriter(alumnos);
    writer.guarda(nombreFichero);
  }
  
  public String toString() {
    String toString = cabeceraToString() + "\n";
    for (int i = 0; i < alumnos.size(); i++) {
      toString += alumnoToString(alumnos.get(i)) +"\n"  ;    
    }
    return toString;
  }
  
  private String alumnoToString(Estudiante estudiante) {
    String alumno = String.format("%-20s ", estudiante.getNombre());
    for (int i = 1; i <= 5; i++) {
      alumno += ( String.format("%2d ",estudiante.getNota(i)));
    }
    return alumno;
  }

  private String cabeceraToString() {

    String cabecera = " ".repeat(30);   
    for (int i = 1; i <= 5; i++) {
      cabecera += ( String.format("%2d ", i ));
    }
    cabecera += "\n";
    cabecera += " ".repeat(30) + "-".repeat(6 * 3);

    return cabecera;
  }
  
  public int size() {
    return alumnos.size();
  }

}
