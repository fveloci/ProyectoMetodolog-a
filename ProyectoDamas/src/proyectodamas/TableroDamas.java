
package proyectodamas;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class TableroDamas extends Damas implements ActionListener, MouseListener  {
    //SE DECLARAN LOS BOTONES NUEVO Y ABANDONA 
    Button botonNuevoJuego;
    Button botonAbandona;
    Button CajaTiempo;
   
    Panel panelDeJuego;
    Panel DentroPanelDeJuego;
    Panel panelMensajes;
    Panel DentroPanelMensajes;
    Button reglasDelJuego;
    Button reglasDeMovimientos;
   
    
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
                DentroPanelDeJuego= new Panel ();
                panelDeJuego= new Panel ();
		setBackground(Color.blue);
		addMouseListener(this);
		setFont(new Font("Arial", Font.ITALIC, 16));
		botonAbandona = new Button("ABANDONAR");
		botonAbandona.addActionListener(this);
		botonNuevoJuego = new Button("NUEVO JUEGO");
		botonNuevoJuego.addActionListener(this);
		
		tablero = new ReglasDamas();
                reglasDelJuego = new Button ("Reglas De Juego");
                reglasDelJuego.addActionListener(this);
                reglasDeMovimientos= new Button ("Reglas De Movimientos");
                panelMensajes = new Panel ();
                DentroPanelMensajes= new Panel ();
                mensaje= new Label("MENSAJES", Label.RIGHT);
                add(mensaje);
                CajaTiempo= new Button ("TIEMPO DE JUEGO :");
                
                
                
		//nuevoJuego(); INICIO EL JUEGO
		
	}
    public void actionPerformed(ActionEvent eventoClick){
        //Realiza acciones de acuerdo al boton que se clickee.
        Object src = eventoClick.getSource();
        if(src == botonNuevoJuego){
            nuevoJuego();
        }
            if(src == botonAbandona){
                rendirse();
            }
                if(src == reglasDelJuego){
                    JOptionPane.showMessageDialog(null, "LAS REGLAS SON: \n 1- HOLA \n 2-ETC");
                }
                    if(src == reglasDeMovimientos){
                        JOptionPane.showMessageDialog(null, "REGLAS DE MOVIMIENTO : AWWSDQWDQ");
                    }
                if(src == menuPrincipal){
                         
                          
                         
                       }
        }
                
            
        
        
        
    
    
    
    
    
	public void nuevoJuego(){//Empieza un nuevo juego.
            
            tablero.preparaJuego(); // Coloca las piezas de la manera correcta.
            jugadorActual=ReglasDamas.rojo; //LAS ROJAS MUEVEN PRIMERO
            movimientosLegales = tablero.getMovimientosLegales(ReglasDamas.rojo); /*Toma los 
            movimientos legales para las fichas rojas.*/
															// moves.
		filaSeleccionada = -1; // Si no se selecciona una ficha roja la fila es igual a -1.
		CajaTiempo.setLabel("TURNO FICHAS ROJAS");
		juegoEnProgreso = true;
		botonNuevoJuego.setEnabled(false);
		botonAbandona.setEnabled(true);
                
		repaint();
            }
        public void rendirse() {
		//Si el jugador actual toca el boton, gana el contrario.
		
		if (jugadorActual == ReglasDamas.rojo){
                
			gameOver("El jugador ROJO se rinde. ¡GANAN LAS NEGRAS!");
                JOptionPane.showMessageDialog(null,"El jugador ROJO se rinde. ¡GANAN LAS NEGRAS!");
                }else{
			gameOver("El jugador NEGRO se rinde. ¡GANAN LAS ROJAS!");
                        JOptionPane.showMessageDialog(null,"El jugador NEGRO se rinde. ¡GANAN LAS ROJAS!");
                }
	}
        
        void gameOver(String string) {
		/* JUEGO TERMINADO. El parametro "string" mostrara un mensaje en pantalla
            al usuario. Los botones se mostraran de acuerdo a las posibilidades.*/
		CajaTiempo.setLabel(string);
		botonNuevoJuego.setEnabled(true);
		botonAbandona.setEnabled(false);
		juegoEnProgreso = false;
	}     
        
        
        
        void hacerClickEnUnCuadrado (int fila, int columna){
            // Este METODO VA A SER LLAMADO POR mousePressed(), cuando un jugador haga click en un cuadrado especifico en una Fila y Columna. Si se compruba
            // que el juego esta en progreso
            
            /*
            CUANDO EL JUGADOR HAGA CLICK EN UNA PIEZA DE LAS QUE PUEDE MOVER , SE MARCA LA FILA Y COLUMNA QUE SELECCIONA. 
            */
            
            for (int i = 0; i < movimientosLegales.length; i++)
			if (movimientosLegales[i].de_Fila == fila && movimientosLegales[i].de_Columna == columna) {
                            
				filaSeleccionada = fila;
				columnaSeleccionada = columna;
				
                                if (jugadorActual == ReglasDamas.rojo)
					CajaTiempo.setLabel("ROJO DEBE DE MOVER");
				else
					CajaTiempo.setLabel("NEGRO DEBE DE MOVER");
				repaint();
				return;
			}

	
           /* VAMOS A CONSIDERAR QUE SI NINGUNA PIEZA ES SELECCIONADA TENEMOS QUE AVISARLE , DARLE , LA INFORMACION AL JUGADOR DE QUE 
           DEBE DE MOVER UNA PIEZA SELECCIONANDOLA PRIMERO.
            */

		if (filaSeleccionada < 0) {
			CajaTiempo.setLabel("SELECCIONE UNA PIEZA Y REALICE UN MOVIMIENTO");
			return;
		}

                  // PROCEDEMOS A CREAR EL METODO PARA RECORRER TODOS LOS MOVIMIENTOS LEGALES QUE EL JUGADOR PUEDE HACER CON LA PIEZA
                // SELECCIONADA . SI EL MOVIMIENTO SELECCIONADO EN FILA Y COLUMNA ES IGUAL FILA SELECCIONADA Y COLUMNA A LA QUE EL JUGADOR 
                // QUIERE REALIZAR EL MOVIMIENTO SE EJECUTA EL METODO . HACER MOVIMIENTO CON EL PARAMETRO DONDE LAS CONDICIONES SE CUMPLIERON
		
              
               
		for (int i = 0; i < movimientosLegales.length; i++) //Vamos A RE CORRER TODO EL ARREGLO DE LOS MOVIMIENTOS LEGALES
			if (movimientosLegales[i].de_Fila == filaSeleccionada // CADA VEZ QUE RECORRAMOS LOS MOVIMIENTOS LEGALES , VEMOS SI COINCIDEN CON LA FILA SELECCIONADA
			&& movimientosLegales[i].de_Columna == columnaSeleccionada // TAMBIEN SI LA COLUMNA CUMPLE LA MISMA CONDICION
			&& movimientosLegales[i].a_Fila == fila && movimientosLegales[i].a_Columna == columna) { // Y AHORA VEMOS QUE PARA QUE SE CUMPLA
                            // LA CONDICION EL LUGAR A DONDE QUEREMOS MOVERLO SEA FILA Y COLUMNA COINCIDE CON LA VARIABLE a_fila y a_columna CON LA SELECCIONADA
                            // PARA QUE EL MOVIMIENTO SEA LEGAL
				hacerMovimiento(movimientosLegales[i]);// CUANDO SE CUMPLA LA CONDICION VAMOS A TENER EN CUENTA QUE LA POSICION DONDE SE "DETUVO" AL HABER
                  // CUMPLIDO LA CONDICION DE QUE SEA UN MOVIMIENTO LEGALE EL SELECCIONADO , EJECUTAMOS EL METODO HACERMOVIMIENTO  CON 
                //MOVIMIENTOS LEGALES Y LA VARIABLE I QUE DEPENDIENDO EL MOMENTO EN QUE SE CUMPLA LA CONDICION SE VA A PARAR SU VALOR Y PASRA COMO
                // PARAMETRO.
				return;
			}

                // SI NUNGA CONDICION SE CUMPLE ESTAMOS SABIENDO QUE EL JUGADOR CLICKEA EN UN CUADRADO DONDE NO HAY PIEZA QUE LE PERTENESCA PARA MOVER
                // Y SI LE PERTENECE PERO NO PUEDE SER MOVIDA SE LE DARA EL MISMO MENSAJE

		CajaTiempo.setLabel("SELECCIONE UNA PIEZA QUE PUEDA MOVER LEGALMENTE Y CONCUERDE CON SU TURNO");

        }
        
      void hacerMovimiento ( MovimientoDamas mueve) {
		
		tablero.hacerMovimiento(mueve);   // SE PROCEDE A EJECUTAR EL METODO DE HACER MOVIMIENTOS , PASANDO COMO PARAMETRO EL OBJETO MUEVE
                if (mueve.esSalto()) { // SI EL MOVIMIENTO ES UN SALTO , DEBEMOS VER LA POSIBILIDAD  DE QUE LA FICHA SIGA REALIZANDO OTRO SALTO
			
                    movimientosLegales = tablero.getSaltosLegalesDesde(jugadorActual, mueve.a_Fila, // VUELVE A VERIFICAR YA MOVIDA LA PIEZA SI 
		    mueve.a_Columna);// HAY OTRO SALTO LEGAL PARA REALIZAR.
			
                    if (movimientosLegales != null) { // SI EL ARREGLO INDICA QUE HAY MOVIMIENTOS LEGALES
				
                              if (jugadorActual == ReglasDamas.rojo) // VERIFICAMOS QUIEN ES EL JUGADOR ACTUAL Y REALIZAMOS EL MENSAJE DE QUE DEBE SEGUIR SALTANDO
				CajaTiempo.setLabel("ROJO DEBE SEGUIR REALIZANDO SALTO");
                                      else
					CajaTiempo.setLabel("NEGRAS DEBEN SEGUIR REALIZANDO SALTO");
                                 
                                      filaSeleccionada = mueve.a_Fila; //AHORA AL CUMPLIR ESTA CONDICION VOLVEMOS A PASAR LOS PARAMETROS PARA REALIZAR MOVIMIENTO
				      columnaSeleccionada= mueve.a_Columna; // PASAMOS FILA Y COLUMNA .
				repaint();
				return;
			}
		}


		if (jugadorActual == ReglasDamas.rojo) { // SI EL JUGADOR ACTUAL ES EL ROJO
                    
                   jugadorActual = ReglasDamas.negro; // SE PROCEDE A CAMBIAR EL TURNO
	            movimientosLegales = tablero.getMovimientosLegales (jugadorActual);// Y OBTENER LOS MOVIMIENTOS LEGALES DEL JUGADOR ACTUAL.
			
                    if (movimientosLegales == null){ // SI NO HAY MOVIMIENTOS LEGALES SIGNIFICA QUE O HAY PIEZAS BLOQUEADAS EN SU TOTALIDAD O NO HAY MOVIMIENTO
				gameOver("NEGRO NO TIENE MOVIMIENTOS, EL ROJO ES EL GANADOR");
			JOptionPane.showMessageDialog(null,"¡ROJAS GANAN!");
                    }else if (movimientosLegales[0].esSalto()) // SINO SI EL UNICO MOVIMIENTO QUE TIENE ES UN SALTO SE LO OBLIGA A COMER .
				
                        CajaTiempo.setLabel("NEGRO DEBE SALTAR");
			
                    else // SINO SI HAY MAS DE DOS MOVIMIENTOS Y NO ES NULO EL ARRAY , SIGNIFICA QUE TIENE MAS DE 1 POSIBILIDAD DE MOVER LIBREMENTE.
				CajaTiempo.setLabel("NEGRO DEBE MOVER");
		} 
                else { // SE PROCEDE A REALIZAR LA MISMA SECUENCIA SINO QUE PARA LAS PIEZAS ROJAS.
			jugadorActual = ReglasDamas.rojo;
			movimientosLegales = tablero.getMovimientosLegales(jugadorActual);
			if (movimientosLegales == null){
				gameOver("ROJO NO TIENE MOVIMIENTOS, LAS NEGRAS GANAN");
                                JOptionPane.showMessageDialog(null,"¡NEGRAS GANAN!");
                        }else if (movimientosLegales[0].esSalto())
				CajaTiempo.setLabel("ROJO DEBE SALTAR");
			else
				CajaTiempo.setLabel("ROJO DEBE MOVER");
		}

		
		filaSeleccionada = -1;// SE IGUALA A MENOS 1 PARA SETEAR QUE NO HAY PIEZA SELECCIONADA PARA MOVER.
		
                
               
                 /* 
                AYUDA AL USUARIO PARA INDICARLE SI TIENE UNA UNICA PIEZA PARA MOVER, SE LE MARCARA 
                AUTOMATICAMENTE.
                */
                
                
		if (movimientosLegales != null) { // SI HAY ALMENOS UN MOVIMIENTO LEGAL
			boolean mismaFicha = true; // DECLARAREMOS PRIMERO UN BOOLEAN PARA SABER SI ESTA CLICKEANDO LA MISMA FICHA
                        
			for (int i = 1; i < movimientosLegales.length; i++) // REOCRREMOS LOS DISTINTOS MOVIMIENTOS LEGALES
			if (movimientosLegales[i].de_Fila != movimientosLegales[0].de_Fila || movimientosLegales[i].de_Columna != movimientosLegales[0].de_Columna) {
                                    
					mismaFicha = false; // SI UNA CONDICION SE CUMPLA QUE ES DISTINTA , SE LE ASIGNA EL VALOR FALSO A MISMA FICHA
					break;
				}
			if (mismaFicha) { 
				filaSeleccionada = movimientosLegales[0].de_Fila;
				columnaSeleccionada = movimientosLegales[0].de_Columna;
			}
		}
		repaint();

	} // FINALIZA METODO DE HACER MOVIMIENTO().
      
      
      public void update(Graphics g) {
		// Este metodo dibuja completamente el tablero.
		paint(g);
	}
     public void paint(Graphics g) {
		/* Metodo que se utiliza para dibujar el tablero y las fichas.
                Si hay un juego en progreso lo que se dibujan son los movimientos legales 
                utilizando un borde en el casillero correspondiente.
                .*/
                super.paint(g);
		/* Borde al rededor del tablero.*/
              ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);   
		g.setColor(Color.black);
		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		g.drawRect(1, 1, getSize().width - 3, getSize().height - 3);

		/* Dibujamos los cuadrados del tablero y la fichas. */

		for (int fila = 0; fila < 8; fila++) {//Bucle de dibujado de tablero y fichas
			for (int columna = 0; columna < 8; columna++) {
				if (fila % 2 == columna % 2)
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.black);
				g.fillRect(2 + columna * 80, 2 + fila * 80, 81, 81);
				switch (tablero.posicionPieza(fila, columna)) {
				case ReglasDamas.rojo:
					g.setColor(Color.red);
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					break;
				case ReglasDamas.negro:
					g.setColor(Color.BLACK);
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					break;
				case ReglasDamas.rey_rojo:
					g.setColor(Color.red);
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					g.setColor(Color.white);
					g.drawString("*REY B*", 7 + columna * 80, 64 + fila * 78);
					break;
				case ReglasDamas.rey_negro:
					g.setColor(Color.black);
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					g.setColor(Color.white);
					g.drawString("*REY N*", 7 + columna * 80, 64 + fila * 78);
                                        
					break;
				}
			}
                        
		}
   
                if(juegoEnProgreso){
                    // Se dibuja un borde al rededor de las piezas que se pueden mover.
			g.setColor(Color.green);
			for (int i = 0; i < movimientosLegales.length; i++) {
				g.drawRect(2 + movimientosLegales[i].de_Columna * 80,
						2 + movimientosLegales[i].de_Fila * 80, 79, 79);
			}
			/*Cuando seleccionamos la pieza ese cuadrado tiene un borde amarillo*/
			if (filaSeleccionada >= 0) {
                            
				g.setColor(Color.yellow);//Ficha seleccionada
				g.drawRect(2 + columnaSeleccionada * 80, 2 + filaSeleccionada * 80, 77, 77);
				g.drawRect(3 + columnaSeleccionada * 80, 3 + filaSeleccionada * 80, 77, 77);/*Se dibujan
                                dos contornos para que se vea mejor*/
				g.setColor(Color.BLUE);//Color de los movimientos legales.
				for (int i = 0; i < movimientosLegales.length; i++) {
					if (movimientosLegales[i].de_Columna == columnaSeleccionada
							&& movimientosLegales[i].de_Fila == filaSeleccionada)
						g.drawRect(2 + movimientosLegales[i].a_Columna * 80,
								2 + movimientosLegales[i].a_Fila * 80, 78, 78);/*Se dibujan los
                                        movimientos legales*/
				}
			}
                             
}
                
     }//FIN paint()
    public void mousePressed(MouseEvent evt) {
		/* Responde a un click.Si no hay juego, no se puede clikear(muestra mensaje).
        Si no, busca la fila y columna seleccionada para manejarla con el metodo hacerClickEnUnCuadrado()*/
		if (juegoEnProgreso == false)
			CajaTiempo.setLabel("DA CLICK EN -NUEVO JUEGO-");
		else {
                    
			int columna = (evt.getX() - 2) / 80;
			int fila = (evt.getY() - 2) / 80;
			if (columna >= 0 && columna < 8 && fila >= 0 && fila < 8){
				hacerClickEnUnCuadrado(fila, columna);
                        }
		
               
	}
    }
	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}













}//Fin de la clase TABLERO DAMAS
