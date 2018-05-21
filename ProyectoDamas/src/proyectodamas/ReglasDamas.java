/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodamas;

import java.util.Vector;

/**
 *
 * @author VELOCI
 */
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
		 * First, check for any possible jumps. Look at each square on the
		 * board. If that square contains one of the player's pieces, look at a
		 * possible jump in each of the four directions from that square. If
		 * there is a legal jump in that direction, put it in the moves vector.
		 */

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (tablero[row][col] == jugador || tablero[row][col] == jugadorRey) {
					if (puedeSaltar(jugador, row, col, row + 1, col + 1, row + 2,
							col + 2))
						movimientos.addElement(new CheckersMove(row, col, row + 2,
								col + 2));
					if (puedeSaltar(jugador, row, col, row - 1, col + 1, row - 2,
							col + 2))
						movimientos.addElement(new CheckersMove(row, col, row - 2,
								col + 2));
					if (puedeSaltar(jugador, row, col, row + 1, col - 1, row + 2,
							col - 2))
						movimientos.addElement(new CheckersMove(row, col, row + 2,
								col - 2));
					if (puedeSaltar(jugador, row, col, row - 1, col - 1, row - 2,
							col - 2))
						movimientos.addElement(new CheckersMove(row, col, row - 2,
								col - 2));
				}
			}
		}

		/*
		 * If any jump moves were found, then the user must jump, so we don't
		 * add any regular moves. However, if no jumps were found, check for any
		 * legal regualar moves. Look at each square on the board. If that
		 * square contains one of the player's pieces, look at a possible move
		 * in each of the four directions from that square. If there is a legal
		 * move in that direction, put it in the moves vector.
		 */

		if (movimientos.size() == 0) {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == jugador
							|| board[row][col] == playerKing) {
						if (canMove(jugador, row, col, row + 1, col + 1))
							movimientos.addElement(new CheckersMove(row, col,
									row + 1, col + 1));
						if (canMove(jugador, row, col, row - 1, col + 1))
							movimientos.addElement(new CheckersMove(row, col,
									row - 1, col + 1));
						if (canMove(jugador, row, col, row + 1, col - 1))
							movimientos.addElement(new CheckersMove(row, col,
									row + 1, col - 1));
						if (canMove(jugador, row, col, row - 1, col - 1))
							movimientos.addElement(new CheckersMove(row, col,
									row - 1, col - 1));
					}
				}
			}
		}

		/*
		 * If no legal moves have been found, return null. Otherwise, create an
		 * array just big enough to hold all the legal moves, copy the legal
		 * moves from the vector into the array, and return the array.
		 */

		if (movimientos.size() == 0)
			return null;
		else {
			CheckersMove[] moveArray = new CheckersMove[movimientos.size()];
			for (int i = 0; i < movimientos.size(); i++)
				moveArray[i] = (CheckersMove) movimientos.elementAt(i);
			return moveArray;
		}

	} // end getLegalMoves
    
}
