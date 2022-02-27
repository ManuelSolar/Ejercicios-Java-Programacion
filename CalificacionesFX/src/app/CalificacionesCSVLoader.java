package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;






/**
 * Clase con los métodos necesarios para la carga de archivos csv en el programa Calificaciones.
 * 
 * @author Manuel Solar Bueno
 *
 */

public class CalificacionesCSVLoader {
  
  private static final String CABECERA_CSV = "Nombre,Nota1,Nota2,Nota3,Nota4,Nota5";
  private ArrayList<Estudiante> alumnos;
  private BufferedReader fichero;
  
  public CalificacionesCSVLoader(ArrayList<Estudiante> alumnos) {
    this.alumnos = alumnos;
  }
  
  public void cargaCSV(String nombreFichero) throws IOException, CSVCalificacionesException {
    fichero = Files.newBufferedReader(Paths.get(nombreFichero));
    validaCabecera();
    
    String alumno;
    while ((alumno=fichero.readLine()) != null) {
      String nombre = getNombreEstudiante(alumno);
      int[] notas = getNotasEstudiante(alumno);
      alumnos.add(new Estudiante(nombre, notas));
    }

    fichero.close();
    Collections.sort(alumnos);
  }


  private int[] getNotasEstudiante(String alumno) {
    String[] camposEstudiante = alumno.split(",");
    int primerIndiceNotas = camposEstudiante.length - 5;
    int[] notas = new int[5];
    for (int i = primerIndiceNotas; i < camposEstudiante.length; i++) {
      notas[i - primerIndiceNotas] = Integer.valueOf(camposEstudiante[i]);
    }
    return notas;
  }

  private String getNombreEstudiante(String alumno) {
    return alumno.substring(1, alumno.indexOf("\","));
  }


  private void validaCabecera() throws IOException, CSVCalificacionesException {
    String cabecera = fichero.readLine();
    
    if (! CABECERA_CSV.equalsIgnoreCase(cabecera)) {
      throw new CSVCalificacionesException("Error detectado en la cabecera del CSV: " + cabecera);
    }
    
  }

}
