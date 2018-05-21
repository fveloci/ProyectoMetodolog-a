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
public class MovimientoDamas {
    /*Clase de movimiento de fichas(No se asegura que sean legales)*/
    
    int de_Fila, de_Columna; // Posicion pieza a mover.
    int a_Fila,a_Columna ; // Lugar a donde se movera.
    MovimientoDamas(int f1, int c1, int f2, int c2) {//f= Fila , c= Columna
		// Constructor de movimiento
		de_Fila = f1;
		de_Columna = c1;
		a_Fila = f2;
		a_Columna = c2;
	}
    boolean esSalto() {
		/*Se testea si el movimiento es un salto. Para un salto
        la ficha debe moverse dos filas hacia abajo(-2) en el caso de la fichas negras
        y hacia arriba(2) en el caso de las rojas */
		return (de_Fila - de_Columna == 2 || a_Fila - a_Columna == -2);
	}
}
