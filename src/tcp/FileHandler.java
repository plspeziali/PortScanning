/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolo
 */
public class FileHandler {
    
    private final String FILENAME = "log.txt";
    
    private BufferedWriter bw;
    
    FileHandler(){
        try{
            File file = new File(FILENAME);
            if (!file.exists()) {
                    file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
    }
    
    void scrivi(String data){
        try {
            bw.write(data);
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
    }
    
    void chiudi(){
        try {
            if (bw != null){
                bw.close();
            }
        } catch (IOException ex) {
            System.err.println("Errore di I/O");
        }
    }
    
}
