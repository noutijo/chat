package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ManageurClient extends Thread {

    private final Socket manager;
    private final String text;

    public ManageurClient(Socket manager, String text) {
        this.manager = manager;
        this.text = text;

    }

    @Override
    public void run() {

        OutputStream os;

        try {
            os = manager.getOutputStream();

            byte[] bites = text.getBytes();
            os.write(bites);
            os.flush();
            os.close();
        } catch (IOException ex) {
        }

    }

}
