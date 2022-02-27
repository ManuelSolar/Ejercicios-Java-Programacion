package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Clase con los métodos necesarios para la exportación de archivos xml en el programa Calificaciones.
 * 
 * @author Manuel Solar Bueno
 *
 */

public class CalificacionesXMLWriter {
  
  private ArrayList<Estudiante> alumnos;
  private Document xml;
  
  public CalificacionesXMLWriter(ArrayList<Estudiante> alumnos) {
    this.alumnos = alumnos;
  }
  
  public void guarda(String nombreFichero) throws IOException, XMLCalificacionesException, TransformerException, ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    xml = builder.newDocument();
    
    Element raiz = xml.createElement("Calificaciones");
    xml.appendChild(raiz);
    
    for (Estudiante alumno : alumnos) {
      guardaAlumno(alumno);
    }
    
    DOMSource xmlSource = new DOMSource(xml);
    StreamResult output = new StreamResult(new FileWriter(nombreFichero));
    transformer.transform(xmlSource, output);
  }

  private void guardaAlumno(Estudiante alumno) {
    Element raiz = xml.getDocumentElement();

    Element estudianteElement = xml.createElement("Estudiante");
    raiz.appendChild(estudianteElement);

    estudianteElement.appendChild(getCampoAlumno("Nombre", alumno.getNombre()));
    for (int nota = 1; nota <= 5; nota++) {
      Element notaElement = getCampoAlumno("Nota" + nota, String.valueOf(alumno.getNota(nota)));
      estudianteElement.appendChild(notaElement);
    }
    estudianteElement.appendChild(getCampoAlumno("Media", String.valueOf(alumno.getMedia())));
    
  }

  private Element getCampoAlumno(String campo, String valor) {
    Element campoElement = xml.createElement(campo);
    campoElement.appendChild(xml.createTextNode(valor));
    return campoElement;
  }

}
