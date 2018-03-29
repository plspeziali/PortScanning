/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author paolo
 */
public class Menu {
    
    private UDPScanner scanner;
    
    BufferedReader tastiera;
    
    public void avvia() throws IOException{
        System.out.println("Digita l'indirizzo di cui effettuare lo scanning:");
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        String address = tastiera.readLine();
        scanner = new UDPScanner(address);
        boolean stop = false;
        int opt;
        do {
            opt=0;
            System.out.println("Port Scanning UDP:\n1.Controlla una singola porta\n2.Controlla un range di porte\n3.Controlla alcuni servizi noti\n4.Esci");
            try{
                opt = Integer.parseInt(tastiera.readLine());
            } catch (NumberFormatException ex) {
                System.err.println("Inserisci un numero!");
            }
            switch(opt){
                case 1:
                    checkPort();
                    break;
                case 2:
                    checkRange();
                    break;
                case 3:
                    checkWellKnown();
                    break;
                case 4:
                    stop=true;
                    scanner.close();
                    System.out.println("Arrivederci!");
                    break;
            }
        } while (stop == false);
    }
    
    public void checkPort(){
        System.out.println("Inserisci la porta da controllare:");
        try {
            int port = Integer.parseInt(tastiera.readLine());
            System.out.println(scanner.scan(port));
        } catch (NumberFormatException ex) {
            System.err.println("Inserisci un numero!");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
    }
    
    public void checkRange(){
        int portA=0;
        int portB=0;
        System.out.println("Inserisci la porta da cui far partire il controllo:");
        try {
            portA = Integer.parseInt(tastiera.readLine());
        } catch (NumberFormatException ex) {
            System.err.println("Errore di inserimento del numero! Porta selezionata: 0");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
        System.out.println("Inserisci l'ultima porta da controllare:");
        try {
            portB = Integer.parseInt(tastiera.readLine());
        } catch (NumberFormatException ex) {
            System.err.println("Errore di inserimento del numero! Porta selezionata: 0");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
        // Swap delle variabili
        if(portA>portB){
            portA=portA+portB;
            portB=portA-portB;
            portA=portA-portB;
        }
        for(int i=portA;i<=portB;i++){
            System.out.println(scanner.scan(i));
        }
    }
    
    public void checkWellKnown(){
        int port=0;
        System.out.println("Servizi noti:\n1. DNS\n2. LDAP\n3. Teamspeak\nAltro. Esci");
        try{
            port = Integer.parseInt(tastiera.readLine());
        } catch (NumberFormatException ex) {
            System.err.println("Inserisci un numero!");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
        switch(port){
            case 1:
                System.out.println(scanner.scan(53));
                break;
            case 2:
                System.out.println(scanner.scan(389));
                break;
            case 3:
                System.out.println(scanner.scan(9987));
                break;
        }
    }
}
