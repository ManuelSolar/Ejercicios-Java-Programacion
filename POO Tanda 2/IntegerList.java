package EjerciciosPOO;

/**
 * Crea una clase que represente una estructura de datos tipo lista de números enteros que internamente se almacenan en un array.
 * Basaros en el archivo que viene adjunto.
 * 
 * Estado de los objetos:
 * 
 * - content: array de enteros donde guardaremos los elementos de la lista.
 * - contentSize: números de elementos guardados en la lista.
 * 
 * Comportamiento:
 * 
 * - IntegerList(): crea una lista vacía cuyo tamaño máximo va a estar determinado por una constante
 *  de la clase (10).
 * 
 * - IntegerList(ele1,...,eleN): crea una lista con los elementos ele1 .... eleN.
 * 
 * - pop(): me devuelve el último elemento de la lista y lo saca.
 * 
 * - remove(elemento): borra la primera ocurrencia de "elemento" en la lista. Devolverá true o false
 *  en función del éxito de la operación.
 * 
 * - insert(elemento): añadir "elemento" al final de la lista.
 *  Devolveremos true o false en función del éxito de la operación.
 *  
 * - insert(elemento, posición): añadir "elemento" en la posición "posición" de la lista.
 *  Devolveremos true o false en función del éxito de la operación.
 *  
 * - clear(): vacía la lista.
 * 
 * - isFull(): me dice si la lista está llena.
 * 
 * - isEmpty(): me dice si la lista está vacía.
 * 
 * - resize(): cambia el tamaño máximo de la lista si el nuevo tamaño máximo no es menor que
 *  el número de elementos que tiene. Devolveremos true o false en función del éxito de la operación. 
 *  
 * - getContenSize(): nos devuelve el número de elementos que hay en la lista.
 * 
 * - maxSize(): tamaño máximo de la lista.
 * 
 * 
 * 
 * 
 * @author Manuel Solar Bueno
 *
 */

public class IntegerList {
  
  private static final int DEFAULT_MAX_SIZE = 10;
  private int[] content;
  private int contentSize;
  
  public IntegerList() {
    this.contentSize=0;
    this.content=new int[DEFAULT_MAX_SIZE];
  }
  
  public IntegerList(int ... content) {
    this.contentSize=content.length;
    
    // Si el número de parámetros es mayor que el contentSize del array éste aumenta 
    this.content = (content.length > DEFAULT_MAX_SIZE)
        ? new int[content.length] : new int[DEFAULT_MAX_SIZE];
        
    // Meto los valores en el array
    for(int i = 0; i < content.length; i++) {
      this.content[i] = content[i];
    }
  }
  
  /**
   * Devuelve el último elemento de la lista
   * @return
   */
  public int pop() {
    --this.contentSize;
    return this.content[this.contentSize];
  }
  
  public int pop(int posicion) {
    int aDevolver = this.content[posicion];
    
    scrollLeft(posicion);
    
    --this.contentSize;
    return aDevolver;
  }
  
  private void scrollLeft(int posicion) {
    for(int i = 0; i < this.contentSize-1; i++) {
      this.content[i] = this.content[i+1];
    }
  }
  
  /**
   * Elimina la primera ocurrencia de un elemento de la lista
   * 
   * @param element
   * @return
   */
  public boolean remove(int element) {
    if(this.isEmpty()) {
      return false;
    }
    
    int posicionElement = this.indexOf(element);
    if (posicionElement < 0) {
      return false;
    }
    
    scrollLeft(posicionElement);
    
    --this.contentSize;
    return true;
  }
  
  /**
   * 
   * @return si la lista está vacía
   */
  public boolean isEmpty() {
    return this.contentSize==0;
  }
  
  private int indexOf(int element) {
    int posicionElement = 0;
    while (posicionElement < this.contentSize-1 && this.content[posicionElement] != element) {
      posicionElement++;
    }
    if (this.content[posicionElement] != element) { // si no es el último no está
      return -1;
    }
    return posicionElement;
  }
  
  /**
   * Añade un elemento al final de la lista
   * 
   * @param element
   * @return
   */
  public boolean insert(int element) {
    if(this.isFull()) {
      return false;
    }
    
    this.content[this.contentSize] = element;
    ++this.contentSize;
    return true;
  }
  
  public boolean insert(int element, int posicion) {
    if(this.isFull()) {
      return false;
    }
    
    scrollRight(posicion);
    this.content[posicion] = element;
    ++this.contentSize;
    return true;
  }
  
  public boolean isFull() {
    return this.contentSize == this.content.length;
  }
  
  private void scrollRight(int posicion) {
    for(int i = 0; i < this.contentSize-1; i++) {
      this.content[i+1] = this.content[i];
    }
  }
  
  public void clear() {
    this.contentSize = 0;
  }
  
  /**
   * Cambia el tamaño máximo de la lista si el nuevo tamaño máximo no es menor que
   * el número de elementos que tiene.
   * 
   * @param capacidad
   * @return
   */
  public boolean rezise(int newMaxSize) {
    if (this.contentSize >= newMaxSize ) {
      return false;
    }
    this.content=this.copy(newMaxSize);
    return true;
  }
  
  private int[] copy(int newMaxSize) {
    int newContent[] = new int [newMaxSize];
    for (int i = 0; i < this.contentSize; i++) {
      newContent[i] = this.content[i];
    }
    return newContent;
  }
  
  public int getContentSize() {
    return this.contentSize;
  }
  
  public int maxSize() {
    return this.content.length;
  }
  
  

}
