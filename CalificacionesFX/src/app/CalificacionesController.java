package app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CalificacionesController {

  private static final String EXP_REG = "[0-9]*";

  @FXML
  private MenuItem CargaCSVItem;

  @FXML
  private MenuItem guardaXMLItem;

  @FXML
  private MenuItem NumeroEstudiantesPresentanEjercicioItem;

  @FXML
  private MenuItem NotaMediaEjercicioItem;

  @FXML
  private MenuItem NotaMasAltaEjercicioItem;

  @FXML
  private MenuItem NumeroEstudiantesMediaAprobadaItem;

  @FXML
  private MenuItem NotaMediaMasAltaItem;

  @FXML
  private TextArea visor;

  @FXML
  private TextField ejercicioTextField;
  
  @FXML
  private TableView<Estudiante> calificacionesTableView;

  @FXML
  private TableColumn<Estudiante, String> nombreColumn;

  @FXML
  private TableColumn<Estudiante, Integer> nota1Column;

  @FXML
  private TableColumn<Estudiante, Integer> nota2Column;

  @FXML
  private TableColumn<Estudiante, Integer> nota3Column;

  @FXML
  private TableColumn<Estudiante, Integer> nota4Column;

  @FXML
  private TableColumn<Estudiante, Integer> nota5Column;
  
  private static Calificaciones calificaciones = new Calificaciones();

  @FXML
  void cargaCalificacionesCSV(ActionEvent event) throws CSVCalificacionesException {
    calificacionesTableView.getItems().clear();
    
    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
    
    Stage stage = (Stage) visor.getScene().getWindow();
    File file = fileChooser.showOpenDialog(stage);
    
    String nombreFichero = file.getName();
    
    try {
      calificaciones.cargaCSV(nombreFichero);
      habilitarOpciones();
      insertarTableView();
      
    } catch(IOException e){
      mensaje("Error al abrir " + nombreFichero + ": " + e.toString() + "\n",e.toString(),AlertType.ERROR);
    }
  }

  @FXML
  void guardaCalificacionesXML(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
    
    Stage stage = (Stage) visor.getScene().getWindow();
    File file = fileChooser.showOpenDialog(stage);
    
    String nombreFichero = file.getName();
    
    try {
      calificaciones.guardaXML(nombreFichero);
    } catch (IOException e) {
      mensaje("Error al guardar " + nombreFichero + ": " + e.toString() + "\n",e.toString(),AlertType.ERROR);
    } catch (XMLCalificacionesException e) {
      mensaje("Error al generar XML: " + e.toString() + "\n",e.toString(),AlertType.ERROR);
    } catch (TransformerException e) {
      System.err.println(e.getMessage());
    } catch (ParserConfigurationException e) {
      System.err.println(e.getMessage());
    }
    
    

  }

  @FXML
  void muestraNotaMediaEjercicio(ActionEvent event) {
    visor.clear();
    try {
      int ejercicio = Integer.parseInt(ejercicioTextField.getText());
      if (ejercicio >= 1 || ejercicio <= 5  ) {
          visor.appendText("La media en el ejercicio es de " + calificaciones.mediaEjercicioEntregados(ejercicio)); 
      }
    }catch (NumberFormatException e) {
      mensaje("Tienes que indicar un número de ejercicio",e.toString(),AlertType.ERROR);
    }catch (ArrayIndexOutOfBoundsException e) {
      mensaje("Tienes que indicar un número de ejercicio entre el 1 y el 5",e.toString(),AlertType.ERROR);
    }
    }
    
    

  @FXML
  void muestraNumeroEstudiantesEntregaTodoConMediaAprobada(ActionEvent event) {
    
    visor.clear();
    
    visor.appendText("Hay " + calificaciones.cantidadAlumnosAprobados() + " alumnos con todo entregado y la media aprobada.");

  }

  @FXML
  void muestraNumeroEstudiantesPresentaEjercicio(ActionEvent event) {
    visor.clear();
    try {
      int ejercicio = Integer.parseInt(ejercicioTextField.getText());
      if (ejercicio >= 1 || ejercicio <= 5  ) {
          visor.appendText("El ejercicio lo han entregado " + calificaciones.cantidadEjercicioEntregados(ejercicio) + " alumnos."); 
      }
    }catch (NumberFormatException e) {
      mensaje("Tienes que indicar un número de ejercicio",e.toString(),AlertType.ERROR);
    }catch (ArrayIndexOutOfBoundsException e) {
      mensaje("Tienes que indicar un número de ejercicio entre el 1 y el 5",e.toString(),AlertType.ERROR);
    }

  }

  @FXML
  void notaMasAltaEjercicio(ActionEvent event) {
    visor.clear();
    try {
      int ejercicio = Integer.parseInt(ejercicioTextField.getText());
      if (ejercicio >= 1 || ejercicio <= 5  ) {
          
        double notaMasAlta = calificaciones.notaMasAlta(ejercicio);
        
        visor.appendText("La nota más alta del ejercicio " + ejercicio + " ha sido " + notaMasAlta
            + " y la han obtenido:\n");
        
        for (int i = 1; i < calificaciones.size(); i++) {
          Estudiante e = calificaciones.getEstudiante(i);
          if (e.getNota(ejercicio) == notaMasAlta) {
            visor.appendText(e.getNombre() + "\n");
          }
        }
      }
    }catch (NumberFormatException e) {
      mensaje("Tienes que indicar un número de ejercicio",e.toString(),AlertType.ERROR);
    }catch (ArrayIndexOutOfBoundsException e) {
      mensaje("Tienes que indicar un número de ejercicio entre el 1 y el 5",e.toString(),AlertType.ERROR);
    }

  }

  @FXML
  void notaMediaMasAlta(ActionEvent event) {
    
    visor.clear();
    
    double mediaMasAlta = calificaciones.mediaMasAlta();
    visor.appendText("La nota más alta del ha sido " + mediaMasAlta + " y la ha obtenido:\n");
    
    for (int i = 1; i < calificaciones.size(); i++) {
      Estudiante e = calificaciones.getEstudiante(i);
      if (e.getMedia() == mediaMasAlta) {
        visor.appendText(e.getNombre() + "\n");
      }
    }

  }
    
    
    void habilitarOpciones() {
      guardaXMLItem.setDisable(false);
      NumeroEstudiantesPresentanEjercicioItem.setDisable(false);
      NotaMediaEjercicioItem.setDisable(false);
      NotaMasAltaEjercicioItem.setDisable(false);
      NumeroEstudiantesMediaAprobadaItem.setDisable(false);
      NotaMediaMasAltaItem.setDisable(false);
    }
    
    @SuppressWarnings("exports")
    public static void mensaje(String header, String content, AlertType type) {
      Alert alert = new Alert(type, content);
      alert.setHeaderText(header);
      alert.setResizable(true);
      alert.showAndWait();
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void initialize() {
      ejercicioTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches(EXP_REG)) {
          ejercicioTextField.setText(oldValue);
        }
      });
      
      this.nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
      this.nota1Column.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getNota(1)));
      this.nota2Column.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getNota(2)));
      this.nota3Column.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getNota(3)));
      this.nota4Column.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getNota(4)));
      this.nota5Column.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getNota(5)));
    }
    
    void insertarTableView() {
      
      for(int i=0; i<5; i++ ){
        
        
        Estudiante alumno = calificaciones.getEstudiante(i);
        
        calificacionesTableView.getItems().add(alumno);
      }
      
      
    }

}

