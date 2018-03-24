/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            opt=0;
            System.out.println("Port Scanning TCP:\n1.Controlla una singola porta\n2.Controlla un range di porte\n3.Controlla alcuni servizi noti\n4.Scappa - Get Out");
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
        System.out.println("Servizi noti:\n1. FTP\n2. SSH\n3. SMTP\n4. HTTP\n5. Kerberos\n6. POP3\n7. IMAP\n8. LDAP\n9. HTTPS\n10. Doom\nAltro. Esci");
        try{
            port = Integer.parseInt(tastiera.readLine());
        } catch (NumberFormatException ex) {
            System.err.println("Inserisci un numero!");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
        switch(port){
            case 1:
                System.out.println(scanner.scan(20));
                break;
            case 2:
                System.out.println(scanner.scan(22));
                break;
            case 3:
                System.out.println(scanner.scan(25));
                break;
            case 4:
                System.out.println(scanner.scan(80));
                break;
            case 5:
                System.out.println(scanner.scan(88));
                break;
            case 6:
                System.out.println(scanner.scan(110));
                break;
            case 7:
                System.out.println(scanner.scan(143));
                break;
            case 8:
                System.out.println(scanner.scan(389));
                break;
            case 9:
                System.out.println(scanner.scan(443));
                break;
            case 10:
                System.out.println(scanner.scan(666));
                break;
        }
    }
}
