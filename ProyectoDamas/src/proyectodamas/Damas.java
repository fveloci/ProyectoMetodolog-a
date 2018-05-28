
package proyectodamas; 

import java.applet.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;


public class Damas extends Applet {
   
   public void init() {  // con el metodo INIT sobreescrito desde applet , Inicializamos la ventana, que contendra el awt del juego. 
    
		
                
               
		setSize(950,850); // DAMOS ANCHO Y ALTO  A LA VENTANA DEL APPLET
		setLayout(null);  // SETEAMOS UN LAYOUT SIN DEFINIR PARA QUE INICIE
                setBackground(Color.GREEN.darker());// LE DAMOS UN FONDO AL APPLET
                Frame c = (Frame)this.getParent().getParent(); // DAMOS TITULO AL JUEGO
                c.setTitle("DAMAS BETA 0.01");
                TableroDamas tablero = new TableroDamas(); // INICIAMOS EL OBJETO TABLERO CON TODAS SUS REGLAS
                
                
                // CREANDO TITULO DEL JUEGO MEDIANTE UN BOTON 
                tablero.tituloJuego.setBackground(Color.BLACK);
                add (tablero.tituloJuego);
                tablero.tituloJuego.setForeground(Color.WHITE);
               
                // CREACION DE DENTRO DE PANEL
               
               tablero.DentroPanelDeJuego.setBounds(685,15,235,780); 
               tablero.DentroPanelDeJuego.setBackground(Color.BLACK);
               
               add (tablero.DentroPanelDeJuego);
                
                // CREACION DEL PANEL 
               
                
               tablero.panelDeJuego.setBounds(680,10,245,790); 
               tablero.panelDeJuego.setBackground(Color.white);
               
               
               add (tablero.panelDeJuego);
               
               
               
               
                
                tablero.botonNuevoJuego.setBackground(Color.BLACK); // LE DAMOS COLOR AL COLOR DE BOTON NUEVO JUEGO
                add(tablero);// AGREGAMOS EL OBJETO A INIT
		add(tablero.botonNuevoJuego); // TAMBIEN AGREGAMOS EL BOTON PARA QUE APAREZCA 
                tablero.botonNuevoJuego.setForeground(Color.GREEN);
                // CREO LA CAJA PARA PONEER EL TIEMPO DE JUEGO :
                tablero.CajaTiempo.setForeground(Color.white);
		
                tablero.botonAbandona.setBackground(Color.black); // DAMOS UN COLOR AL BOTO NUEVO JUEGO
                tablero.botonAbandona.setForeground(Color.red);
		add(tablero.botonAbandona); // AGREGAMOS EL BOTON AL INIT 
                		
                tablero.mensaje.setForeground(Color.green);
		
                tablero.mensaje.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
		add(tablero.mensaje);
                add (tablero.CajaTiempo);
                tablero.CajaTiempo.setBackground(Color.BLACK);
                
                
                
                
                tablero.setBounds(20, 150, 645,645 );  // PROCEDEMOS A DAR LIMITES , Y LUGAR DE POSICION DE CADA OBJETO
		tablero.botonNuevoJuego.setBounds(20, 90, 200, 50);
		tablero.botonAbandona.setBounds(465, 90, 200, 50);
		tablero.CajaTiempo.setBounds(250, 90, 200 , 50);
                tablero.tituloJuego.setBounds(20,10, 645, 60);
                
                
               
                
               
                
                                    
                 }
    
                
}
   
   