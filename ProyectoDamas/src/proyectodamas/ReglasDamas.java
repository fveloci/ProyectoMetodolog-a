/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodamas;

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
		// Make the specified move. It is assumed that move
		// is non-null and that the move it represents is legal.
		hacerMovimiento(mover.de_Fila, mover.de_Columna, mover.a_Fila, mover.a_Columna);
	}

	public void hacerMovimiento(int de_Fila, int de_Columna, int a_Fila, int a_Columna) {
		// Make the move from (fromRow,fromCol) to (toRow,toCol). It is
		// assumed that this move is legal. If the move is a jump, the
		// jumped piece is removed from the board. If a piece moves
		// the last row on the opponent's side of the board, the
		// piece becomes a king.
		tablero[a_Fila][a_Columna] = tablero[de_Fila][a_Columna];
		tablero[de_Fila][de_Columna] = esp_vacio;
		if (de_Fila - a_Fila == 2 || de_Fila - a_Fila == -2) {
			// The move is a jump. Remove the jumped piece from the board.
			int saltoFila = (de_Fila + a_Fila) / 2; // Row of the jumped piece.
			int saltoColumna = (de_Columna + a_Columna) / 2; // Column of the jumped piece.
			tablero[saltoFila][saltoColumna] = esp_vacio;
		}
		if (a_Fila == 0 && tablero[a_Fila][a_Columna] == rojo)
			tablero[a_Fila][a_Columna] = rey_rojo;
		if (a_Fila == 7 && tablero[a_Fila][a_Columna] == negro)
			tablero[a_Fila][a_Columna] = rey_negro;
	}
    
}
