/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolo
 */
public class Menu {
    
    private TCPScanner scanner;
    
    BufferedReader tastiera;
    
    public void avvia() throws IOException{
        System.out.println("Digita l'indirizzo di cui effettuare lo scanning:");
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        String address = tastiera.readLine();
        scanner = new TCPScanner(address);
        boolean stop = false;
        int opt;
        do {
            System.out.println("Port Scanning TCP:\n1.Controlla una singola porta\n2.Controlla un range di porte\n3.Scappa - Get Out");
            opt = Integer.parseInt(tastiera.readLine());
            switch(opt){
                case 1:
                    checkPort();
                    break;
                case 2:
                    //checkRange();
                    break;
                case 3:
                    stop=true;
                    System.out.println("Ciao Phrego");
                    break;
            }
        } while (stop == false);
    }
    
    public void checkPort(){
        System.out.println("Inserisci la porta da controllare:");
        try {
            int port = Integer.parseInt(tastiera.readLine());
            System.out.println(scanner.scan(port));
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
    }
    
    public void checkRange(){
        
    }
}
