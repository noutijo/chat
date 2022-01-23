package client;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Noumodong
 */
public class ClientController implements FrameInterface,Initializable {

    private EnvoyeurMessage envoirMess;
    private RecepteurMessage resMess;

    @FXML
    private JFXTextField portClient;
    @FXML
    private JFXTextField adresseIPClient;
    @FXML
    private JFXTextField messageClient;
    @FXML
    private TextArea affichageClient;

    @FXML
    private void envoyerClientCliquer(MouseEvent event) {
        try {
            String recu = messageClient.getText();
            envoirMess.envoyer(recu);
            affichageClient.appendText("Moi :" + recu.trim() + "\n");
            messageClient.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void connexionClientCliquer(MouseEvent event) {
        try {
            int port = Integer.valueOf(portClient.getText());
            String address = adresseIPClient.getText();
            Socket sendStream = new Socket(address, port);
            envoirMess = new EnvoyeurMessage(sendStream.getOutputStream());
            resMess = new RecepteurMessage(sendStream.getInputStream(), this);

            this.setEnvoyeur(envoirMess);
            this.setRecepteur(resMess);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void recoieMessage(String message) {
        affichageClient.appendText("serveur: " + message.trim() + "\n");
    }

    @Override
    public void setEnvoyeur(EnvoyeurMessage e) {
        envoirMess = e;
    }

    @Override
    public void setRecepteur(RecepteurMessage r) {
        resMess = r;
        resMess.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    affichageClient.getStyleClass().addAll("visible-lg", "visible-md","visible-xs", "visible-sm");
    }

}
