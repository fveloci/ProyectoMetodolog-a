
package proyectodamas; 

import java.applet.*;

import java.awt.Color;
import java.awt.Font;



public class Damas extends Applet {
   
   public void init() {  
    
		
                
               
		setSize(1024,1024);
		setLayout(null); 
                TableroDamas tablero = new TableroDamas(); // 
                tablero.botonNuevoJuego.setBackground(Color.GREEN);
                add(tablero);
		add(tablero.botonNuevoJuego);

		tablero.botonAbandona.setBackground(Color.RED);
		add(tablero.botonAbandona);

		tablero.mensaje.setForeground(Color.green);
              
		tablero.mensaje.setFont(new Font("Serif", Font.TRUETYPE_FONT, 32));
		add(tablero.mensaje);
                
		tablero.setBounds(30, 30, 645,645 ); 
		tablero.botonNuevoJuego.setBounds(1100, 250, 200, 100);
		tablero.botonAbandona.setBounds(1100, 400, 200, 100);
                
}
  } 