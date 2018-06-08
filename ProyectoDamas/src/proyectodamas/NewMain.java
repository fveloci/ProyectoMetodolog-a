/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodamas;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Alumno
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       
        //crear un objeto f de la clase JFrame 
    JFrame f = new JFrame("DAMAS"); 

    //crear una instancia de TestApplet 
    Damas ViedoJuego = new Damas(); 

    //añadir la instancia del applet al marco 
    f.getContentPane().setLayout(new BorderLayout()); 
    f.getContentPane().add("Center", ViedoJuego); 


    //inicializar las variables al ancho y el alto de la tag <applet> 
    int width = 990; 
    int height = 750; 
    f.setSize(width, height); 

    //llamar a init() y a start() si es necesario 
    ViedoJuego.start(); 
     

   //hacer visible el marco 
    f.show(true); 
  }
    }
    

