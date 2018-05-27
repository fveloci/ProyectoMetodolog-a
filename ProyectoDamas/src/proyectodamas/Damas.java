
package proyectodamas; 
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Damas extends JFrame implements ActionListener  {
private JButton botonNuevoJuego,botonAbandonar;
private JLabel mensaje;

    public Damas(){
       super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes 
        TableroDamas tablero=new TableroDamas();
        tablero.setBounds(325, 20, 644, 644);
    }
     private void configurarVentana() {
        this.setTitle("DAMAS GRATIS");                   // colocamos titulo a la ventana
        this.setSize(1024, 720);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }
     private void inicializarComponentes() {
        // creamos los componentes
        TableroDamas tablero;
        tablero=new TableroDamas();
        mensaje = new JLabel();
        botonNuevoJuego = new JButton();
        botonAbandonar = new JButton();
        // configuramos los componentes
        mensaje.setText("Hola");    // colocamos un texto a la etiqueta
        mensaje.setBounds(0, 200, 330, 30);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        
        tablero.botonNuevoJuego.setLabel("Nuevo Juego");   // colocamos un texto al boton
        tablero.botonNuevoJuego.setBounds(210, 60, 125, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        tablero.botonNuevoJuego.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        
        tablero.botonAbandonar.setLabel("Rendirse");   // colocamos un texto al boton
        tablero.botonAbandonar.setBounds(210, 120, 125, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        tablero.botonAbandonar.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        
        tablero.setBackground(Color.blue);
        tablero.setBounds(325, 20, 644, 644);
// adicionamos los componentes a la ventana
        this.add(mensaje);
        this.add(botonNuevoJuego);
        this.add(botonAbandonar);
        add(tablero);
   

   }
     @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public static void main(String[] args) {
        Damas ventana = new Damas();      // creamos una ventana
        ventana.setVisible(true);             // hacemos visible la ventana creada
    } 
     
}