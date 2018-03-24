/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author paolo
 */
public class TCPScanner {
    
    private final String address;
    
    private Socket socket;
    
    public TCPScanner(String address){
        this.address=address;
        socket=null;
    }
    
    public String scan(int port){
        String log="";
        try{
            socket = new Socket(address,port);
            log="La porta "+port+" dell'host "+address+" è aperta";
        } catch (ConnectException e){
            log="La porta "+port+" dell'host "+address+" è chiusa";
        } catch (UnknownHostException ex) {
            System.err.println("Indirizzo sconosciuto!");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        } finally {
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("Errore di I/O");
                }
            }
        }
        
        return log;
    }
    
}
