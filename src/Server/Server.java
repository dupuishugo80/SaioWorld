package Server;

import Personnage.Guerrier;
import saioworld.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ObjectOutputStream os;
    ObjectInputStream is;
    GamePanel gp;
    int nbOnline;
    Guerrier guerrierHost;
    Socket s;

    public Server(GamePanel gp, Guerrier guerrierHost) throws IOException {
        this.guerrierHost = guerrierHost;
        this.gp = gp;
        this.guerrierHost.setGp(gp);
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Serveur en attente de joueurs");
        System.out.println("1/2 Joueur co");
        nbOnline++;
        while (nbOnline<2) {
            Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            this.s = socket;
            this.os = new ObjectOutputStream(socket.getOutputStream());
            this.is = new ObjectInputStream(socket.getInputStream());
            System.out.println("Nouveau joueur connecté : " + socket + "!");
            nbOnline++;
            System.out.println(nbOnline + "/2 Joueur co");
            System.out.println("---Nouveau thread---");
            Thread t = new ClientHandler(socket, this.is, this.os, this.gp, this.guerrierHost);
            t.run();
            nbOnline--;
        }
    }

    public Socket getSocket(){
        return this.s;
    }
}

class ClientHandler extends Thread implements  Runnable{
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private Socket socket;
    GamePanel gp;
    public Guerrier guerrierHost;
    private boolean isConnected;
    private String lastPos;

    // Constructor
    public ClientHandler(Socket socket, ObjectInputStream is, ObjectOutputStream os, GamePanel gp, Guerrier guerrierHost) {
        this.gp = gp;
        this.socket = socket;
        this.os = os;
        this.is = is;
        this.isConnected = true;
        this.guerrierHost = guerrierHost;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.is.available());
            Guerrier guerrierClient = (Guerrier) this.is.readObject();
            System.out.println(guerrierClient.getNom());
            os.writeObject(this.guerrierHost);
            while(this.isConnected) {
                try{
                    int xhost = this.guerrierHost.getX();
                    int yhost = this.guerrierHost.getY();
                    int scounter = this.guerrierHost.getSpriteCounter();
                    int snum = this.guerrierHost.getSpriteNum();
                    String toSend = String.valueOf(xhost) + "-" + String.valueOf(yhost) + "-" + String.valueOf(scounter) + "-" + String.valueOf(snum);
                    this.os.writeObject(toSend);
                    String st = (String) this.is.readObject();
                    String[] parts = st.split("-");
                    guerrierClient.setX(Integer.parseInt(parts[0]));
                    guerrierClient.setY(Integer.parseInt(parts[1]));
                    guerrierClient.setSpriteCounter(Integer.parseInt(parts[2]));
                    guerrierClient.setSpriteNum(Integer.parseInt(parts[3]));
                }catch (IOException e){
                    this.isConnected = false;
                    System.out.println("Logout");
                }
                this.gp.updateClient(guerrierClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}