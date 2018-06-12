package proyectodamas;
import java.awt.Button;
import java.awt.Color;
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


public class TableroDamasComputadora extends Damas implements ActionListener,MouseListener {
  
    

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
    public TableroDamasComputadora() { //CONSTRUCTOR
		
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
                reglasDelJuego = new Button ("REGLAS");
                reglasDelJuego.addActionListener(this);
                reglasDeMovimientos= new Button ("PREMISA DE DAMAS");
                reglasDeMovimientos.addActionListener(this);
                panelMensajes = new Panel ();
                DentroPanelMensajes= new Panel ();
                mensaje= new Label("MENSAJES", Label.RIGHT);
                add(mensaje);
                CajaTiempo= new Button ("MENSAJES");
                
                
                
                
                
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
                    JOptionPane.showMessageDialog(null, "Cada jugador dispone de 12 piezas de un mismo color (uno blanco y otro negro) que al principio de la partida se colocan  \n" +
"en las casillas negras de las tres filas más próximas a él. El objetivo del juego de damas es capturar las fichas del oponente \n" +
"o acorralarlas para que los únicos movimientos que puedan realizar sean los que lleven a su captura.\n" +
"Si un peón consigue llegar hasta su última línea (primera fila del rival), se convierte automáticamente en Dama. \n" +
" Para distinguirlas del resto de piezas se suele colocar otra pieza sobre esta. La dama puede moverse en diagonal hacia delante y\n" +
"hacia atrás todas las casillas que desee en un solo movimiento, siempre y cuando no salte por encima de ninguna de sus propias fichas. \n" +
" Si al finalizar una captura, un peón se convierte en dama, esta no puede seguir capturando piezas, y deberá esperar otro turno para hacerlo. \n" +
"El peón captura en diagonal, saltando por encima de la ficha contraria que va a ser capturada, cayendo sobre la casilla inmediatamente detrás  \n" +
" de ésta (en el sentido de la captura), y siempre que el que captura esté en una casilla adyacente al capturado, y que la casilla inmediatamente \n" +
" detrás de éste esté libre para que acabe el movimiento.\n" +
"La captura con dama es igual que con peón, aunque puede capturar tanto hacia adelante como hacia atrás. La captura en todos los casos es obligatoria,  \n" +
" es decir, si al llegar el turno de un jugador, una o más de sus piezas estuvieran en situación de realizar capturas, será obligatorio mover ésta o una de   \n" +
"estas piezas y realizar tal captura, no pudiendo optar por mover una pieza que no esté en situación de realizar captura. Tanto con dama como con peón,   \n" +
"si tras una captura, la pieza en cuestión estuviera en situación de realizar una nueva captura, esta se llevará a cabo de forma encadenada, y así sucesivamente   \n" +
"mientras se diera tal circunstancia de poder seguir capturando. Su movimiento y su turno terminan cuando ya no hay más piezas para capturar.");
                }
                    if(src == reglasDeMovimientos){
                        JOptionPane.showMessageDialog(null, "Es un juego de mesa para dos contrincantes. El juego consiste en mover las piezas en diagonal \n"
                                + " a través de los cuadros negros o blancos de un tablero de 64 o 100 cuadros con la intención de capturar (comer) las piezas del \n"
                                + " jugador contrario pasando por encima de dichas piezas. ");
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
                        JOptionPane.showMessageDialog(null,"El jugador ROJO se rinde. ¡GANAN LAS NEGRAS!");}
                else{
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
      public void moverNegra(){
         boolean seEncuentraReyNegro=false;
         
     
          if(movimientosLegales.length == 1){
          
             
                   hacerMovimiento(movimientosLegales[0]);
                  
                   repaint();
                   return;
          }else{
          int random=0;
          for(int i=0;i<movimientosLegales.length;i++){
              if(tablero.tablero[movimientosLegales[i].de_Fila][movimientosLegales[i].de_Columna] == ReglasDamas.rey_negro){
               while(seEncuentraReyNegro == false){
               random=(int)(Math.random()*movimientosLegales.length);
               if(tablero.tablero[movimientosLegales[random].de_Fila][movimientosLegales[random].de_Columna] == ReglasDamas.rey_negro){
                   
                   hacerMovimiento(movimientosLegales[random]);
                   repaint();
                   seEncuentraReyNegro=true;
                   return;
                   
               }
               }   
              }
          }
           
              
              for(int i=0;i<movimientosLegales.length;i++){
              if((movimientosLegales[i].a_Fila == 7 && tablero.tablero[movimientosLegales[i].de_Fila][movimientosLegales[i].de_Columna] == ReglasDamas.negro)){
                  hacerMovimiento(movimientosLegales[i]);
                 
                 repaint(); 
                 return;
                  }
          }
          for(int i=0;i<movimientosLegales.length;i++){
              
          if((movimientosLegales[i].a_Fila+1!= ReglasDamas.rojo && movimientosLegales[i].a_Columna+1 != ReglasDamas.rojo)
               &&(movimientosLegales[i].a_Fila+1!= ReglasDamas.rojo && movimientosLegales[i].a_Columna-1 != ReglasDamas.rojo) ){
                  hacerMovimiento(movimientosLegales[i]);
                   CajaTiempo.setLabel("ANALISIS COMPUTADORA");
                  repaint();
                   return;
                  
                  }
          }    
          
          random =(int)(Math.random()*movimientosLegales.length);
                   
          hacerMovimiento(movimientosLegales[random]);
          repaint();
          return;
          }
      }
      //PRUEBA GITHUB ERRORES IGNORAR
      void hacerMovimiento ( MovimientoDamas mueve) {
		
		tablero.hacerMovimiento(mueve);   // SE PROCEDE A EJECUTAR EL METODO DE HACER MOVIMIENTOS , PASANDO COMO PARAMETRO EL OBJETO MUEVE
                if (mueve.esSalto() ) { // SI EL MOVIMIENTO ES UN SALTO , DEBEMOS VER LA POSIBILIDAD  DE QUE LA FICHA SIGA REALIZANDO OTRO SALTO
			
                    movimientosLegales = tablero.getSaltosLegalesDesde(jugadorActual, mueve.a_Fila, // VUELVE A VERIFICAR YA MOVIDA LA PIEZA SI 
		    mueve.a_Columna);// HAY OTRO SALTO LEGAL PARA REALIZAR.
                  
			
                    if (movimientosLegales != null) { // SI EL ARREGLO INDICA QUE HAY MOVIMIENTOS LEGALES
				
                              if (jugadorActual == ReglasDamas.rojo) // VERIFICAMOS QUIEN ES EL JUGADOR ACTUAL Y REALIZAMOS EL MENSAJE DE QUE DEBE SEGUIR SALTANDO
				CajaTiempo.setLabel("ROJO DEBE SEGUIR REALIZANDO SALTO");
                              else{
                                    movimientosLegales = tablero.getSaltosLegalesDesde(jugadorActual, mueve.a_Fila, // VUELVE A VERIFICAR YA MOVIDA LA PIEZA SI 
		    mueve.a_Columna);// HAY OTRO SALTO LEGAL PARA REALIZAR.
                                    moverNegra();
                              
					CajaTiempo.setLabel("NEGRAS DEBEN SEGUIR REALIZANDO SALTO");
                                        
                                 
                                      filaSeleccionada = mueve.a_Fila; //AHORA AL CUMPLIR ESTA CONDICION VOLVEMOS A PASAR LOS PARAMETROS PARA REALIZAR MOVIMIENTO
				      columnaSeleccionada= mueve.a_Columna; // PASAMOS FILA Y COLUMNA .
				repaint();
				return;
			}
		}
                    }


		if (jugadorActual == ReglasDamas.rojo) { // SI EL JUGADOR ACTUAL ES EL ROJO
                    
                   jugadorActual = ReglasDamas.negro; // SE PROCEDE A CAMBIAR EL TURNO
	            movimientosLegales = tablero.getMovimientosLegales (jugadorActual);// Y OBTENER LOS MOVIMIENTOS LEGALES DEL JUGADOR ACTUAL.
		   
                    if (movimientosLegales == null){ // SI NO HAY MOVIMIENTOS LEGALES SIGNIFICA QUE O HAY PIEZAS BLOQUEADAS EN SU TOTALIDAD O NO HAY MOVIMIENTO
				gameOver("NEGRO NO TIENE MOVIMIENTOS, EL ROJO ES EL GANADOR");
                                JOptionPane.showMessageDialog(null,"¡ROJAS GANAN!");
                    }
                    else if (movimientosLegales[0].esSalto()) // SINO SI EL UNICO MOVIMIENTO QUE TIENE ES UN SALTO SE LO OBLIGA A COMER .
				
                       CajaTiempo.setLabel("NEGRO DEBE SALTAR");
			
                    else // SINO SI HAY MAS DE DOS MOVIMIENTOS Y NO ES NULO EL ARRAY , SIGNIFICA QUE TIENE MAS DE 1 POSIBILIDAD DE MOVER LIBREMENTE.
				CajaTiempo.setLabel("NEGRO DEBE MOVER");
                if(movimientosLegales.length!=0){
                    
                
		moverNegra();
                
                   
                   }
                
                
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
		// Este metodo dibuja completamente el tablero actualizado para no provocar fallos.
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
                                        g.setColor(Color.LIGHT_GRAY);
                                        g.drawOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					break;
				case ReglasDamas.negro:
					g.setColor(Color.BLACK);
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
                                          g.setColor(Color.LIGHT_GRAY);
                                        g.drawOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					break;
				case ReglasDamas.rey_rojo:
					g.setColor(Color.red);
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
                                          g.setColor(Color.LIGHT_GRAY);
                                        g.drawOval(4 + columna * 80, 4 + fila * 80, 74, 74);
					g.setColor(Color.YELLOW);
					g.drawString("REY ROJO", 7 + columna * 80, 64 + fila * 78);
					break;
				case ReglasDamas.rey_negro:
					g.setColor(Color.black);
                                        
					g.fillOval(4 + columna * 80, 4 + fila * 80, 74, 74);
                                         g.setColor(Color.LIGHT_GRAY);
                                        g.drawOval(4 + columna * 80, 4 + fila * 80, 74, 74);
                                        
					g.setColor(Color.white);
                                       
					g.drawString("REY NEGRO", 7 + columna * 80, 64 + fila * 78);
                                        
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

}
