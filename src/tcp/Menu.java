/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author paolo
 */
public class Menu {
    
    private TCPScanner scanner;
    
    private BufferedReader tastiera;
    
    private FileHandler fh;
    
    public void avvia() throws IOException{
        fh = new FileHandler();
        System.out.println("Digita l'indirizzo di cui effettuare lo scanning:");
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        String address = tastiera.readLine();
        scanner = new TCPScanner(address);
        boolean stop = false;
        int opt;
        do {
            opt=0;
            System.out.println("Port Scanning TCP:\n1.Controlla una singola porta\n2.Controlla un range di porte\n3.Controlla alcuni servizi noti\n4.Esci");
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
                    fh.chiudi();
                    System.out.println("Arrivederci");
                    break;
            }
        } while (stop == false);
    }
    
    public void checkPort(){
        System.out.println("Inserisci la porta da controllare:");
        try {
            int port = Integer.parseInt(tastiera.readLine());
            String msg = scanner.scan(port);
            fh.scrivi("--|"+getTime()+"|--\n");
            fh.scrivi(msg+"\n");
            System.out.println(msg);
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
        fh.scrivi("--|"+getTime()+"|--\n");
        for(int i=portA;i<=portB;i++){
            String msg = scanner.scan(i);
            fh.scrivi(msg+"\n");
            System.out.println(msg);
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
        int opt=0;
        switch(port){
            case 1:
                opt=20;
                break;
            case 2:
                opt=22;
                break;
            case 3:
                opt=25;
                break;
            case 4:
                opt=80;
                break;
            case 5:
                opt=88;
                break;
            case 6:
                opt=110;
                break;
            case 7:
                opt=143;
                break;
            case 8:
                opt=389;
                break;
            case 9:
                opt=443;
                break;
            case 10:
                opt=666;
                break;
        }
        if(port!=0 && port<11){
            String msg = scanner.scan(opt);
            fh.scrivi("--|"+getTime()+"|--\n");
            fh.scrivi(msg+"\n");
            System.out.println(msg);
        }
    }
    
    public static String getTime(){
        DateFormat dateFormat = new SimpleDateFormat(" HH:mm:ss - dd/MM/yyyy");
        Date date = new Date();
        return (dateFormat.format(date));
    }
}
