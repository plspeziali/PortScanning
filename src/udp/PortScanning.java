/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.IOException;

/**
 *
 * @author paolo
 */
public class PortScanning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu m = new Menu();
        try {
            m.avvia();
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
    }
    
}
