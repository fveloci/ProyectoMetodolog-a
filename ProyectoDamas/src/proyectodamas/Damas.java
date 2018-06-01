package proyectodamas;

import java.applet.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class Damas extends Applet implements ActionListener, MouseListener {

    Button botonIniciarJuego;
    Button HumanoContraHumano;
    Button HumanoConComputadora;
    Panel PanelDeGrafica;
    Panel SuperiorPanel;
    Panel PañoLimpiador;
    Button menuPrincipal;

    public void actionPerformed(ActionEvent eventoClick) {
        //Realiza acciones de acuerdo al boton que se clickee.
        Object src = eventoClick.getSource();
        if (src == HumanoContraHumano) { // SI SE CLICKEA EN HUMANO CONTRA HUMANO LIMPIO LA PANTALLA DE UNA MANERA POCO ORTODOXA
            LimpiarPantalla();
            Comenzar();

        }
        if (src == menuPrincipal) {
            LimpiarPantalla();

        }
        if (src == HumanoConComputadora) {
            LimpiarPantalla();
            ComenzarComputadora();
            
        }

    }

    public void start() {  // con el metodo INIT sobreescrito desde applet , Inicializamos la ventana, que contendra el awt del juego. 

        PañoLimpiador = new Panel();
        botonIniciarJuego = new Button("-BIENVENIDO A DAMAS INGLESAS-");
        HumanoContraHumano = new Button(" 1VS 1");
        HumanoConComputadora = new Button(" 1 VS IA");
        PanelDeGrafica = new Panel();
        SuperiorPanel = new Panel();
        // CREACION BOTONES INICIAR JUEGO
        botonIniciarJuego.setBackground(Color.WHITE);
        botonIniciarJuego.setForeground(Color.BLACK);
        botonIniciarJuego.setBounds(30, 30, 850, 100);
        add(botonIniciarJuego);
        // FIN BOTON DE TITULO INICIAR JUEGO
        // INICIO BOTON HUMANO CONTRA HUMANO 
        HumanoContraHumano.setBackground(Color.white);
        HumanoContraHumano.setForeground(Color.BLACK);
        HumanoContraHumano.setBounds(200, 250, 250, 250);
        HumanoContraHumano.addActionListener(this);
        add(HumanoContraHumano);
        //FIN BOTON HUMANO CONTRA HUMANO
        // BOTON HUMANOCONTRA COMPUTADORA
        HumanoConComputadora.setBackground(Color.DARK_GRAY);
        HumanoConComputadora.setForeground(Color.WHITE);
        HumanoConComputadora.setBounds(450, 250, 250, 250);
        HumanoConComputadora.addActionListener(this);
        add(HumanoConComputadora);
        // FIN BOTON HUMANO CONTRA COMPUTADORA
        // LIENZO BAJO PANEL
        SuperiorPanel.setBackground(Color.BLACK);
        SuperiorPanel.setBounds(15, 15, 885, 580);
        add(SuperiorPanel);

        // CREACION DE PANELES.
        PanelDeGrafica.setBackground(Color.WHITE);
        PanelDeGrafica.setBounds(10, 10, 900, 600);
        add(PanelDeGrafica);

        Frame c = (Frame) this.getParent().getParent(); // DAMOS TITULO AL JUEGO
        c.setTitle("DAMAS BETA 0.01");
        setSize(950, 650); // DAMOS ANCHO Y ALTO  A LA VENTANA DEL APPLET
        setLayout(null);  // SETEAMOS UN LAYOUT SIN DEFINIR PARA QUE INICIE
        setBackground(Color.GREEN.darker());// LE DAMOS UN FONDO AL APPLET
        //addMouseListener(this);
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 36));

    }

    public void Comenzar() {

        menuPrincipal = new Button("MENU PRINCIPAL");
        menuPrincipal.addActionListener(this);
        setSize(950, 670); // DAMOS ANCHO Y ALTO  A LA VENTANA DEL APPLET
        setLayout(null);  // SETEAMOS UN LAYOUT SIN DEFINIR PARA QUE INICIE
        setBackground(Color.blue.darker());// LE DAMOS UN FONDO AL APPLET
        Frame c = (Frame) this.getParent().getParent(); // DAMOS TITULO AL JUEGO
        c.setTitle("DAMAS BETA 0.01");
        // CREACION DE TABLERO
        TableroDamas tablero = new TableroDamas(); // INICIAMOS EL OBJETO TABLERO CON TODAS SUS REGLAS
        tablero.setBounds(20, 15, 645, 645);  // PROCEDEMOS A DAR LIMITES , Y LUGAR DE POSICION DE CADA OBJETO
        add(tablero);// AGREGAMOS EL OBJETO A INIT

        tablero.mensaje.setForeground(Color.red);
        tablero.mensaje.setFont(new Font("Serif", Font.TRUETYPE_FONT, 32));
        add(tablero.mensaje);

        // CREO LA CAJA PARA PONEER EL TIEMPO DE JUEGO :
        tablero.CajaTiempo.setBounds(700, 210, 200, 50);
        tablero.CajaTiempo.setBackground(Color.WHITE);
        tablero.CajaTiempo.setForeground(Color.BLACK);
        add(tablero.CajaTiempo);

        // CREACION BOTON NUEVO JUEGO
        tablero.botonNuevoJuego.setBounds(700, 90, 200, 50);
        tablero.botonNuevoJuego.setBackground(Color.WHITE); // LE DAMOS COLOR AL COLOR DE BOTON NUEVO JUEGO
        tablero.botonNuevoJuego.setForeground(Color.GREEN);
        add(tablero.botonNuevoJuego); // TAMBIEN AGREGAMOS EL BOTON PARA QUE APAREZCA 

        // CREACION BOTON ABANDONAR
        tablero.botonAbandona.setBounds(700, 150, 200, 50);
        tablero.botonAbandona.setBackground(Color.WHITE); // DAMOS UN COLOR AL BOTO NUEVO JUEGO
        tablero.botonAbandona.setForeground(Color.red);
        add(tablero.botonAbandona); // AGREGAMOS EL BOTON AL INIT 

        // CREACION BOTON MENU PRINCIPAL
        menuPrincipal.setBounds(700, 30, 200, 50);
        menuPrincipal.setBackground(Color.white);
        menuPrincipal.setForeground(Color.black);
        add(menuPrincipal);

        // CREACION DE BOTONES DE REGLAS Y REGLA DE MOVIMIENTOS
        tablero.reglasDelJuego.setBounds(700, 600, 200, 50);
        tablero.reglasDelJuego.setBackground(Color.white);
        tablero.reglasDelJuego.setForeground(Color.black);
        add(tablero.reglasDelJuego);

        // REGLA DE MOVIMIENTOS
        tablero.reglasDeMovimientos.setBounds(700, 530, 200, 50);
        tablero.reglasDeMovimientos.setBackground(Color.white);
        tablero.reglasDeMovimientos.setForeground(Color.black);
        add(tablero.reglasDeMovimientos);

        // CREACION DE DENTRO DE PANEL
        tablero.DentroPanelDeJuego.setBounds(685, 15, 235, 640);
        tablero.DentroPanelDeJuego.setBackground(Color.BLACK);
        add(tablero.DentroPanelDeJuego);
        // CREACION DEL PANEL 

        tablero.panelDeJuego.setBounds(680, 10, 245, 650);
        tablero.panelDeJuego.setBackground(Color.white);
        add(tablero.panelDeJuego);

        tablero.mensaje.setForeground(Color.green);

        tablero.mensaje.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
        add(tablero.mensaje);

    }
public void ComenzarComputadora(){
     menuPrincipal = new Button("MENU PRINCIPAL");
        menuPrincipal.addActionListener(this);
        setSize(950, 670); // DAMOS ANCHO Y ALTO  A LA VENTANA DEL APPLET
        setLayout(null);  // SETEAMOS UN LAYOUT SIN DEFINIR PARA QUE INICIE
        setBackground(Color.GREEN.darker());// LE DAMOS UN FONDO AL APPLET
        Frame c = (Frame) this.getParent().getParent(); // DAMOS TITULO AL JUEGO
        c.setTitle("DAMAS BETA 0.01");
        // CREACION DE TABLERO
        TableroDamasComputadora tablero = new TableroDamasComputadora(); // INICIAMOS EL OBJETO TABLERO CON TODAS SUS REGLAS
        tablero.setBounds(20, 15, 645, 645);  // PROCEDEMOS A DAR LIMITES , Y LUGAR DE POSICION DE CADA OBJETO
        add(tablero);// AGREGAMOS EL OBJETO A INIT

        tablero.mensaje.setForeground(Color.red);
        tablero.mensaje.setFont(new Font("Serif", Font.TRUETYPE_FONT, 32));
        add(tablero.mensaje);

        // CREO LA CAJA PARA PONEER EL TIEMPO DE JUEGO :
        tablero.CajaTiempo.setBounds(700, 210, 200, 50);
        tablero.CajaTiempo.setBackground(Color.WHITE);
        tablero.CajaTiempo.setForeground(Color.BLACK);
        add(tablero.CajaTiempo);

        // CREACION BOTON NUEVO JUEGO
        tablero.botonNuevoJuego.setBounds(700, 90, 200, 50);
        tablero.botonNuevoJuego.setBackground(Color.WHITE); // LE DAMOS COLOR AL COLOR DE BOTON NUEVO JUEGO
        tablero.botonNuevoJuego.setForeground(Color.GREEN);
        add(tablero.botonNuevoJuego); // TAMBIEN AGREGAMOS EL BOTON PARA QUE APAREZCA 

        // CREACION BOTON ABANDONAR
        tablero.botonAbandona.setBounds(700, 150, 200, 50);
        tablero.botonAbandona.setBackground(Color.WHITE); // DAMOS UN COLOR AL BOTO NUEVO JUEGO
        tablero.botonAbandona.setForeground(Color.red);
        add(tablero.botonAbandona); // AGREGAMOS EL BOTON AL INIT 

        // CREACION BOTON MENU PRINCIPAL
        menuPrincipal.setBounds(700, 30, 200, 50);
        menuPrincipal.setBackground(Color.white);
        menuPrincipal.setForeground(Color.black);
        add(menuPrincipal);

        // CREACION DE BOTONES DE REGLAS Y REGLA DE MOVIMIENTOS
        tablero.reglasDelJuego.setBounds(700, 600, 200, 50);
        tablero.reglasDelJuego.setBackground(Color.white);
        tablero.reglasDelJuego.setForeground(Color.black);
        add(tablero.reglasDelJuego);

        // REGLA DE MOVIMIENTOS
        tablero.reglasDeMovimientos.setBounds(700, 530, 200, 50);
        tablero.reglasDeMovimientos.setBackground(Color.white);
        tablero.reglasDeMovimientos.setForeground(Color.black);
        add(tablero.reglasDeMovimientos);

        // CREACION DE DENTRO DE PANEL
        tablero.DentroPanelDeJuego.setBounds(685, 15, 235, 640);
        tablero.DentroPanelDeJuego.setBackground(Color.BLACK);
        add(tablero.DentroPanelDeJuego);
        // CREACION DEL PANEL 

        tablero.panelDeJuego.setBounds(680, 10, 245, 650);
        tablero.panelDeJuego.setBackground(Color.white);
        add(tablero.panelDeJuego);

        tablero.mensaje.setForeground(Color.green);

        tablero.mensaje.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
        add(tablero.mensaje);
}
    public void mouseReleased(MouseEvent evt) {
    }

    public void mouseClicked(MouseEvent evt) {
    }

    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }

    public void mousePressed(MouseEvent evt) {
    }

    public void LimpiarPantalla() {
        // CREACION BOTONES INICIAR JUEGO
        remove(botonIniciarJuego);
        remove(HumanoContraHumano);
        remove(HumanoConComputadora);
        remove(SuperiorPanel);
        remove(PanelDeGrafica);
        setFont(new Font("Arial", Font.TRUETYPE_FONT, 16));
    }

}
