
package proyectodamas;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class TableroDamas extends Damas implements ActionListener, MouseListener  {
    //SE DECLARAN LOS BOTONES NUEVO Y ABANDONA 
    Button botonNuevoJuego;
    Button botonAbandona;
    //SE CREA UNA ETIQUETA PARA MOSTRAR MENSAJES
    Label mensaje;
    
    ReglasDamas tablero;/*Aqui encontramos los datos de las reglas
    */
    boolean juegoEnProgreso; //Para saber si hay un juego en progreso o no.
    int jugadorActual; //Nos dice el turno del jugador. Si es "1"(ROJO) si es "2"(negro).
    int filaSeleccionada,columnaSeleccionada; /* Nos indica la posicion de la
    ficha seleccionada por el jugador.*/
    MovimientoDamas movimientosLegales[];/*Es un array que nos muestra los movimiento legales de la
    ficha seleccionada.*/
    public TableroDamas() { //CONSTRUCTOR
		
    /*Crea los botones y la etiqueta de "mensaje".Espera a los clicks de MOUSE y clicks
        en los botones. Ademas creamos el tablero y la primera partida.*/
		setBackground(Color.blue);
		addMouseListener(this);
		setFont(new Font("Arial", Font.ITALIC, 16));
		botonAbandona = new Button("Abandonar");
		botonAbandona.addActionListener(this);
		botonNuevoJuego = new Button("Nuevo Juego");
		botonNuevoJuego.addActionListener(this);
		mensaje = new Label("", Label.CENTER);
		tablero = new ReglasDamas();
		
		
	}
    public void actionPerformed(ActionEvent eventoClick){
        //Realiza acciones de acuerdo al boton que se clickee.
        Object src = eventoClick.getSource();
        if(src == botonNuevoJuego){
            nuevoJuego();
        }else{
            if(src == botonAbandona){
                rendirse();
            }}}
    
	public void nuevoJuego(){//Empieza un nuevo juego.
            
            tablero.preparaJuego(); // Coloca las piezas de la manera correcta.
            jugadorActual=ReglasDamas.rojo; //LAS ROJAS MUEVEN PRIMERO
            movimientosLegales = tablero.getMovimientosLegales(ReglasDamas.rojo); /*Toma los 
            movimientos legales para las fichas rojas.*/
															// moves.
		filaSeleccionada = -1; // Si no se selecciona una ficha roja la fila es igual a -1.
		mensaje.setText("TURNO FICHAS ROJAS");
		juegoEnProgreso = true;
		botonNuevoJuego.setEnabled(false);
		botonAbandona.setEnabled(true);
		repaint();
            }
        public void rendirse() {
		//Si el jugador actual toca el boton, gana el contrario.
		
		if (jugadorActual == ReglasDamas.rojo)
                
			gameOver("El jugador ROJO se rinde. ¡GANAN LAS NEGRAS!");
		else
			gameOver("El jugador NEGRO se rinde. ¡GANAN LAS ROJAS!");
	}
        
        void gameOver(String string) {
		/* JUEGO TERMINADO. El parametro "string" mostrara un mensaje en pantalla
            al usuario. Los botones se mostraran de acuerdo a las posibilidades.*/
		mensaje.setText(string);
		botonNuevoJuego.setEnabled(true);
		botonAbandona.setEnabled(false);
		juegoEnProgreso = false;
	}        
}
