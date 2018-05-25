package proyectodamas;
import java.util.Vector;


public class ReglasDamas  {
    /* -Esta clase contiene las reglas que las fichas deberan seguir.
       -Las fichas ROJAS se moveran hacia arriba del tablero.(El numero de filas ira en aumento)
       -Las fichas NEGRAS se moveran hacia abajo del tablero.(El numero de filas decrecerá)
       -Tambien tendremos metodos que daran posibilidades de movimiento, 
        en caso de que el usuario no tenga experiencia jugando DAMAS/*
   */
    /*SE CREAN LOS NUMEROS QUE CORRESPONDEN A LAS FICHAS, ESP_VACIO INDICA UN ESPACIO QUE NO CONTIENE
    FICHA*/
    public static final int esp_vacio=0,rojo=1,negro=2,rey_rojo=3,rey_negro=4;
    
    private int [][]tablero;//Este arreglo representa al tablero, el cual tiene [filas][columnas].
    // CONSTRUCTOR
    public ReglasDamas(){
        tablero=new int[8][8];//Creamos un tablero de 8x8(es la medida de un tablero de damas convencional).
        preparaJuego();
    }
    public void preparaJuego(){
        /*Con este metodo vamos a preparar el tablero con las fichas colocadas
        en su posicion correspondiente. */
        for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				if (fila % 2 == columna % 2) {
					if (fila < 3)
						tablero[fila][columna] = negro;
					else if (fila > 4)
						tablero[fila][columna] = rojo;
					else
						tablero[fila][columna] = esp_vacio;
				} else {
					tablero[fila][columna] = esp_vacio;
				}
			}
		}
    }
        public int posicionPieza(int fila, int columna) {
		//devuelve el valor de lo que se encuentra en esa posición
		return tablero[fila][columna];
	}
    public void colocaPieza(int row, int col, int piece) {
		/*Coloca pieza (o no)en el lugar especificado,esta 
        pieza se coloca con su valor constante.
		*/
		tablero[row][col] = piece;
	}
    public void hacerMovimiento(MovimientoDamas mover) {
		/*Realiza el movimiento(no sabe si es nulo e ilegal)*/
		hacerMovimiento(mover.de_Fila, mover.de_Columna, mover.a_Fila, mover.a_Columna);
	}

	public void hacerMovimiento(int de_Fila, int de_Columna, int a_Fila, int a_Columna) {
		/* Hace el movimiento desde un lugar hacia otro. 
                 Si hay salto la pieza saltada es comida y removida.Si se llega 
            al final del lado del oponente esa pieza se convierte en REY.*/
		tablero[a_Fila][a_Columna] = tablero[de_Fila][a_Columna];
		tablero[de_Fila][de_Columna] = esp_vacio;
		if (de_Fila - a_Fila == 2 || de_Fila - a_Fila == -2) {
			// Esto es un salto. Por lo tanto la pieza saltada es comida.
			int saltoFila = (de_Fila + a_Fila) / 2; // Se calcula la fila de la pieza que se salta.
			int saltoColumna = (de_Columna + a_Columna) / 2; // Se calcula la columna de la pieza que se salta.
			tablero[saltoFila][saltoColumna] = esp_vacio;
		}
		if (a_Fila == 0 && tablero[a_Fila][a_Columna] == rojo)
			tablero[a_Fila][a_Columna] = rey_rojo;
		if (a_Fila == 7 && tablero[a_Fila][a_Columna] == negro)
			tablero[a_Fila][a_Columna] = rey_negro;
	}
        
        public MovimientoDamas[] getMovimientosLegales(int jugador) {
		/* Se devuelve un array con los movimiento legales para el jugador.
                  Si el jugador no tiene movimiento se devuelve null.El valor del
                  corresponde a una de las constantes "rojo" o "negro".Si no se devuelve
                  un nulo significa que hay saltos o movimiento regulares.*/

		if (jugador != rojo && jugador != negro)
			return null;

		int jugadorRey; // .
		if (jugador == rojo)
			jugadorRey = rey_rojo;
		else
			jugadorRey = rey_negro;

		Vector movimientos = new Vector(); // los movimiento se guardan en este vector.

		/*
		 * Hay que revisar en todo el tablero si hay salto posible. 
                   Si la posicion contiene una de la piezas del jugador que mueve en ese momento
                se corrobora si hay algun salto en las cuatro direcciones.Si el movimiento
                posible es legal, lo podemos colocar en el vector de movimientos.
		 */

		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				if (tablero[fila][columna] == jugador || tablero[fila][columna] == jugadorRey) {
					if (puedeSaltar(jugador, fila, columna, fila + 1, columna + 1, fila + 2,
							columna + 2)) //Se observa salto hacia la esquina INFERIOR DERECHA 
						movimientos.addElement(new MovimientoDamas(fila, columna, fila + 2,
								columna + 2));
					if (puedeSaltar(jugador, fila, columna, fila - 1, columna + 1, fila - 2,
							columna + 2))// Salto hacia la esquina SUPERIOR DERECHA
						movimientos.addElement(new MovimientoDamas(fila, columna, fila - 2,
								columna + 2));
					if (puedeSaltar(jugador, fila, columna, fila + 1, columna - 1, fila + 2,
							columna - 2)) // Salto hacia la esquina INFERIOR IZQUIERDA
						movimientos.addElement(new MovimientoDamas(fila, columna, fila + 2,
								columna - 2));
					if (puedeSaltar(jugador, fila, columna, fila - 1, columna - 1, fila - 2,
							columna - 2)) // Salto hacia la esquina SUPERIOR IZQUIERDA
						movimientos.addElement(new MovimientoDamas(fila, columna, fila - 2,
								columna - 2));
				}
			}
		}

		/*
		 * Si se puede saltar , el jugador debe saltar, no se agregan movimiento normales. 
                   Si no se puede saltar, observamos si existen movimientos normales de igual
                manera que en los saltos, y se guardan en el vector de movimientos.
		 */

		if (movimientos.size() == 0) {
			for (int fila = 0; fila < 8; fila++) {
				for (int columna = 0; columna < 8; columna++) {
					if (tablero[fila][columna] == jugador || tablero[fila][columna] == jugadorRey) {
						if (puedeMover(jugador, fila, columna, fila + 1, columna + 1))
                                                      //Se observa movimiento hacia la esquina INFERIOR DERECHA 
							movimientos.addElement(new MovimientoDamas(fila, columna,
									fila + 1, columna + 1));
						if (puedeMover(jugador, fila, columna, fila - 1, columna + 1))
							movimientos.addElement(new MovimientoDamas(fila, columna,
									fila - 1, columna + 1)); // esquina SUPERIOR DERECHA
						if (puedeMover(jugador, fila, columna, fila + 1, columna - 1))
							movimientos.addElement(new MovimientoDamas(fila, columna,
									fila + 1, columna - 1)); // esquina INFERIOR IZQUIERDA
						if (puedeMover(jugador, fila, columna, fila - 1, columna - 1))
							movimientos.addElement(new MovimientoDamas(fila, columna,
									fila - 1, columna - 1)); // esquina SUPERIOR IZQUIERDA
					}
				}
			}
		}

		/*
		 * Si no hay movimientos se devuelve null.Si no, se crea un 
                   array del mismo tamaño, se copian los movimientos del vector
                   en el array, y se devuelve el array con los movimientos legales(o no).
		 */

		if (movimientos.size() == 0)
			return null;
		else {
			MovimientoDamas[] arrayMovimientos = new MovimientoDamas[movimientos.size()];
			for (int i = 0; i < movimientos.size(); i++)
				arrayMovimientos[i] = (MovimientoDamas) movimientos.elementoActual(i);
			return arrayMovimientos;
		}

	} // Final getMovimientosLegales()
        
        
        public MovimientoDamas[] getSaltosLegalesDesde (int jugador, int fila, int columna) { // CON ESTE METODO PASAMOS PARAMETROS DEL JUGADOR SEA ROJO O NEGRO Y LA POSIION 
            // EN LA QUE ESTA PARA QUE PODAMOS ANALIZAR LOS MOVIMIENTOS
		
            /* El metodo esta creado para hacer una coleccion un vector en este caso que contenga los movimientos legales de salto que el jugador 
            puede hacer desde una fila y columna. Si no hay movimientos vamos a devolver un valor nulo que va a indicar que no tiene posibilidades de hacer
            algun movimiento de la pieza.
            */
            
            
		if (jugador != rojo && jugador != negro)
			return null;
                
		int rey_del_Jugador; // vamos a Darle el nombre de rey del jugador dependiendo de que "Color" es el que esta buscando movimientos
		if (jugador == rojo) // si el jugador es el rojo se le va asignar la constante rey del jugador = a rey rojo en valor de matriz "3"
			rey_del_Jugador = rey_rojo;
		else
			rey_del_Jugador = rey_negro;
                
		Vector movimientos_almacenados = new Vector();  // los movimientos de La ficha Seleccionada se van a almacenar el un vector
                // se crea un vector dinamico debido a que no sabemos cuantos elementos (movimientos) se puedan llegar a generar.
		if (tablero[fila][columna] == jugador || tablero[fila][columna] == rey_del_Jugador) { //  AHORA ANALIZAMOS EL MOVIMIENTO CON EL METODO DE PUEDE SALTAR
                // SI LA CONDICION RESULTA VAMOS A AÑADIR UN ELEMENTO AL VECTOR DE LOS MOVIMIENTOS ALMACENADOS.
			if (puedeSaltar(jugador, fila, columna, fila + 1, columna + 1, fila + 2, columna + 2))
				movimientos_almacenados.addElement(new MovimientoDamas(fila, columna, fila + 2, columna + 2));
			if (puedeSaltar(jugador, fila, columna, fila - 1, columna + 1, fila - 2, columna + 2))
				movimientos_almacenados.addElement(new MovimientoDamas(fila, columna, fila - 2, columna + 2));
			if (puedeSaltar(jugador, fila, columna, fila + 1, columna - 1, fila + 2, columna - 2))
				movimientos_almacenados.addElement(new MovimientoDamas(fila, columna, fila + 2, columna - 2));
			if (puedeSaltar(jugador, fila, columna, fila - 1, columna - 1, fila - 2, columna - 2))
				movimientos_almacenados.addElement(new MovimientoDamas(fila, columna, fila - 2, columna - 2));
		}
		if (movimientos_almacenados.size() == 0) // SI NOS HAY MOVIMIENTOS VAMOS A HACER QUE EL ARREGLO SEA IGUAL A 0 Y POR LO TANTO RETORNAMOS UN NULO
			return null;
		else { // SI HAY MOVIMIENTOS VAMOS A PROCEDER A 
			
                MovimientoDamas[] Arreglo_Movimientos = new MovimientoDamas[movimientos_almacenados.size()]; // VAMOS A METER LOS MOVIMIENTOS AL ARREGLO QUE CONTIENE LOS QUE SE PUEDEN HACER
			for (int i = 0; i < movimientos_almacenados.size(); i++) // VAMOS A INICIAR UN BUCLE PARA METER LOS MOVIMIENTOS EN EL ARREGLO
				Arreglo_Movimientos[i] = (MovimientoDamas) movimientos_almacenados.elementAt(i);
                        
			return Arreglo_Movimientos;
		}
	} // fin del metodo getSaltosLegalDesde
        
    
}
