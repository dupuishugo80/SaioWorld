package saioworld;

import Personnage.Guerrier;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private Socket s;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public Client(String h, int p) throws IOException {
        InetAddress ip = InetAddress.getByName(h);
        this.s = new Socket(ip, p);
        this.os = new ObjectOutputStream(s.getOutputStream());
        this.is = new ObjectInputStream(s.getInputStream());
    }

    public Client(Socket s, ObjectInputStream is, ObjectOutputStream os) throws IOException{
        this.s = s;
        this.is = is;
        this.os = os;
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
    public void sendString(String s) throws IOException {
        this.os.writeObject(s);
    }
    public void sendCoord(String st) throws IOException{
        this.os.writeObject(st);
    }
    public Object getObject() throws IOException, ClassNotFoundException {
        return this.is.readObject();
    }
    public int available() throws IOException {
        return this.is.available();
    }
}
