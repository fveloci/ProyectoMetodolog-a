/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodamas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//dada
/**
 *
 * @author Alumno
 */
public class NewMain  {

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
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    f.addWindowListener(new java.awt.event.WindowAdapter() {
    
    // DESTRUCCION DE APPLET MEDIANTE UN
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(f, 
            "SEGURO DE CERRAR EL JUEGO?", "CERRAR VIDEO JUEGO", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            ViedoJuego.destroy();
            System.exit(0);
            
        }
    }
});
    
    
    
    //MAIN PUSH
    }
  
}

        

  

    
    
    

