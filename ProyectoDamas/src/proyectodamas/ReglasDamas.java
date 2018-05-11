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
public class ReglasDamas {
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
        for(int fila=0;fila<8;fila++){
        
    }
        
    
    }
}
