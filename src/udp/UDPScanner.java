/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author paolo
 */
public class UDPScanner {
    
    private final String address;
    
    private DatagramSocket socket;
    
    private DatagramPacket out;
    
    public UDPScanner(String address){
        this.address=address;
        try {
            socket=new DatagramSocket();
        } catch (SocketException ex) {
            System.err.println("Errore nella creazione del Socket!");
        }
    }
    
    public String scan(int port){
        String log="";
        String msg="a";
        try{
            out = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName(address), port);
            socket.send(out);
            log="La porta "+port+" dell'host "+address+" è aperta";
        } catch (InterruptedIOException ex){
            log="La porta "+port+" dell'host "+address+" è chiusa";
        } catch (UnknownHostException ex) {
            System.err.println("Indirizzo sconosciuto!");
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
        
        return log;
    }
    
    public void close() {
        if (socket!=null) {
            socket.close();
        }
    }
    
}
