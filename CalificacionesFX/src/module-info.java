module CalificacionesFX {
  
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.base;
  requires java.xml;
  
  opens app to javafx.fxml;
  opens Vista to javafx.fxml;
  
  exports app;
}
