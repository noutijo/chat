package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurCode extends Thread {

    private FrameInterface frameinter;
     private int port;

    public ServeurCode(FrameInterface frameint, int port) {
        this.frameinter = frameint;
        this.port = port;
    }

    @Override
    public void run() {
        ExecutorService exercuto = Executors.newFixedThreadPool(10);
        try {
            ServerSocket serv = new ServerSocket(port);
            while (true) {
                System.out.println("Attente de la connexion ...");
                Socket connection = serv.accept();

                exercuto.submit(new RecepteurMessage(connection.getInputStream(), this.frameinter));
                this.frameinter.setEnvoyeur(new EnvoyeurMessage(connection.getOutputStream()));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
