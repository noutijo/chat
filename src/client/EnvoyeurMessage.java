package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EnvoyeurMessage {

    private DataOutputStream envoyeur;

    public EnvoyeurMessage(OutputStream envoyeur) {
        this.envoyeur = new DataOutputStream(envoyeur);
    }

    public void envoyer(String messageVeutEnvoyer) throws IOException {
        
            envoyeur.writeInt(messageVeutEnvoyer.length());
            envoyeur.write(messageVeutEnvoyer.getBytes());
            envoyeur.flush();
    }
}
