
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
                // CREACION DE TABLERO
                TableroDamas tablero = new TableroDamas(); // INICIAMOS EL OBJETO TABLERO CON TODAS SUS REGLAS
                tablero.setBounds(20, 15, 645,645 );  // PROCEDEMOS A DAR LIMITES , Y LUGAR DE POSICION DE CADA OBJETO
                add(tablero);// AGREGAMOS EL OBJETO A INIT
                
                
               // CREO LA CAJA PARA PONEER EL TIEMPO DE JUEGO :
                tablero.CajaTiempo.setBounds(700, 210, 200, 50);
                tablero.CajaTiempo.setBackground(Color.WHITE);
                tablero.CajaTiempo.setForeground(Color.BLACK);
                add (tablero.CajaTiempo);


                // CREACION BOTON NUEVO JUEGO
                tablero.botonNuevoJuego.setBounds(700, 90, 200, 50);
                tablero.botonNuevoJuego.setBackground(Color.WHITE); // LE DAMOS COLOR AL COLOR DE BOTON NUEVO JUEGO
                tablero.botonNuevoJuego.setForeground(Color.GREEN);
                add(tablero.botonNuevoJuego); // TAMBIEN AGREGAMOS EL BOTON PARA QUE APAREZCA 
                
                // CREACION BOTON ABANDONAR
                tablero.botonAbandona.setBounds(700, 150, 200, 50);
                tablero.botonAbandona.setBackground(Color.WHITE); // DAMOS UN COLOR AL BOTO NUEVO JUEGO
                tablero.botonAbandona.setForeground(Color.red);
		add(tablero.botonAbandona); // AGREGAMOS EL BOTON AL INIT 

                // CREACION BOTON MENU PRINCIPAL
                
                tablero.menuPrincipal.setBounds(700, 30, 200, 50);
                tablero.menuPrincipal.setBackground(Color.white);
                tablero.menuPrincipal.setForeground(Color.black);
                add(tablero.menuPrincipal);
                
                // CREACION DE BOTONES DE REGLAS Y REGLA DE MOVIMIENTOS
                tablero.reglasDelJuego.setBounds(700, 700, 200, 50);
                tablero.reglasDelJuego.setBackground(Color.white);
                tablero.reglasDelJuego.setForeground(Color.black);
                add(tablero.reglasDelJuego);
               
                // REGLA DE MOVIMIENTOS
                tablero.reglasDeMovimientos.setBounds(700, 640, 200, 50);
                tablero.reglasDeMovimientos.setBackground(Color.white);
                tablero.reglasDeMovimientos.setForeground(Color.black);
                add (tablero.reglasDeMovimientos);
                
                
                
                // CREANDO TITULO DEL JUEGO MEDIANTE UN BOTON 
                //tablero.tituloJuego.setBounds(20,10, 645, 60);
                //tablero.tituloJuego.setBackground(Color.BLACK);
                //tablero.tituloJuego.setForeground(Color.WHITE);
                //add (tablero.tituloJuego);
                
               
              
               // CREACION DE DENTRO DE PANEL
               tablero.DentroPanelDeJuego.setBounds(685,15,235,780); 
               tablero.DentroPanelDeJuego.setBackground(Color.BLACK);
               add (tablero.DentroPanelDeJuego);
                 // CREACION DEL PANEL 
               
               tablero.panelDeJuego.setBounds(680,10,245,790); 
               tablero.panelDeJuego.setBackground(Color.white);
               add (tablero.panelDeJuego);
               
              
               
               
               // CREACION PANEL DENTRO DE MENSAJES
                tablero.DentroPanelMensajes.setBounds(25,675,635,115); 
               tablero.DentroPanelMensajes.setBackground(Color.BLACK);
               add (tablero.DentroPanelMensajes);
                // CREACION PANEL DE MENSAJES 
                tablero.panelMensajes.setBounds(20,670,645,125); 
               tablero.panelMensajes.setBackground(Color.white);
               add (tablero.panelMensajes);
               
               
               		
                tablero.mensaje.setForeground(Color.green);
		
                tablero.mensaje.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
		add(tablero.mensaje);
                
                
                
                
                
               
		
		
		
               
                
                
               
                
               
                
                                    
                 }
    
                
}
   
   