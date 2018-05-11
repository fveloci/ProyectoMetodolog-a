/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodamas;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Vector;
/**
 *
 * @author VELOCI
 */
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
