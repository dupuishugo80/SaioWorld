package saioworld;

import Personnage.Guerrier;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket s;
    private ObjectOutputStream os;
    private InputStream is;

    public Client(String h, int p) throws IOException {
        InetAddress ip = InetAddress.getByName(h);
        this.s = new Socket(ip, p);
        this.os = new ObjectOutputStream(s.getOutputStream());
        this.is = new ObjectInputStream(s.getInputStream());
    }
    public void sendGuerrier(Guerrier g) throws IOException {
        os.writeObject(g);
    }
    public void closeConnection() throws IOException {
        this.os.writeObject("close");
    }
    public void sendInt(int i) throws IOException {
        this.os.writeObject(i);
    }
    public void sendCoord(String st) throws IOException{
        this.os.writeObject(st);
    }
}
