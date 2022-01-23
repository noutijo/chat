package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecepteurMessage extends Thread {

    private DataInputStream recepteur;
    private FrameInterface finter;
    public RecepteurMessage(InputStream connect, FrameInterface finter) {

        this.recepteur = new DataInputStream(connect);
        this.finter = finter;

    }

    @Override
    public void run() {
        while (true) {

            try {
                int length = this.recepteur.readInt();
                byte [] bite= new byte[1024];
                int lut = 0;
                String afte = "";
                while (lut < length) {
                    lut += this.recepteur.read(bite);
                    String textlut = new String(bite);
                    afte += textlut;
                   bite= new byte[1024];
                   System.out.println("txt: " + afte);
                }
                System.out.println("txt1: " + afte);
                finter.recoieMessage(afte);
                               
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
