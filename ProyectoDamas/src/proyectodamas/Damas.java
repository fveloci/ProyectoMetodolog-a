
package proyectodamas; 
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Vector;


public class Damas extends Applet  {

    public void inicio(){
        setBackground(new Color(2,2,3));                                        //Se le asigna un color al fondo del lienzo
        
        
    TableroDamas tablero=new TableroDamas();
    
    /*Con este constructor creamos ademas del lienzo los botones de 
      NuevoJuego y Abandonar*/
    
    add(tablero);
    //Le asignamos color al boton de NUEVO JUEGO
    tablero.botonNuevo.setBackground(Color.lightGray);
    add(tablero.botonNuevo);
    
    
    }
    
}
