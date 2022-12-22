package saioworld;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import Personnage.Guerrier;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;
    private int mode;
    int FPS = 60;
    Thread gameThread;
    TileManager tileM = new TileManager(this);
    Guerrier guerrier1;
    Guerrier guerrier2;
    Socket s;
    ObjectOutputStream os;
    ObjectInputStream is;
    Guerrier getGuerrier;
    Client client;


    public GamePanel(int i, Guerrier guerrier1, Guerrier guerrier2, KeyHandler keyH){
        this.mode = i;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.guerrier1 = guerrier1;
        this.guerrier2 = guerrier2;
        this.guerrier1.setGp(this);
        this.s = null;
    }
    public void setSocket(Socket s) throws IOException {
        this.s = s;
        this.os = new ObjectOutputStream(s.getOutputStream());
        this.is = new ObjectInputStream(s.getInputStream());

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel getGamePanel(){
        return this;
    }

    @Override
    public void run() {
        Client client = null;
        double Interval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        try {
            if(this.mode == 2){
                this.client = new Client("localhost", 7777);
                this.client.sendGuerrier(guerrier1);
                this.getGuerrier = (Guerrier) this.client.getObject();
                System.out.println(this.getGuerrier.getNom());
            }
            while (gameThread != null) {
                if(this.mode == 2){
                    String st = (String) this.client.getObject();
                    String[] parts = st.split("-");
                    if(this.guerrier2.getX() == Integer.parseInt(parts[0]) && this.guerrier2.getY() > Integer.parseInt(parts[1])){
                        this.guerrier2.setDirection("up");
                    }
                    else if(this.guerrier2.getX() == Integer.parseInt(parts[0]) && this.guerrier2.getY() < Integer.parseInt(parts[1])){
                        this.guerrier2.setDirection("down");
                    }
                    else if(this.guerrier2.getX() > Integer.parseInt(parts[0]) && this.guerrier2.getY() == Integer.parseInt(parts[1])){
                        this.guerrier2.setDirection("left");
                    }
                    else if(this.guerrier2.getX() < Integer.parseInt(parts[0]) && this.guerrier2.getY() == Integer.parseInt(parts[1])){
                        this.guerrier2.setDirection("right");
                    }
                    this.guerrier2.setX(Integer.parseInt(parts[0]));
                    this.guerrier2.setY(Integer.parseInt(parts[1]));
                    this.guerrier2.setSpriteCounter(Integer.parseInt(parts[2]));
                    this.guerrier2.setSpriteNum(Integer.parseInt(parts[3]));
                    String xtsosend = String.valueOf(this.guerrier1.getX());
                    String ytsosend = String.valueOf(this.guerrier1.getY());
                    String scounter = String.valueOf(this.guerrier1.getSpriteCounter());
                    String snum = String.valueOf(this.guerrier1.getSpriteNum());
                    String tosend = xtsosend + "-" + ytsosend + "-" + scounter + "-" + snum;
                    this.client.sendCoord(tosend);
                }
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / Interval;
                lastTime = currentTime;
                if (delta >= 1) {
                    if(this.mode == 1){
                        update_host();
                    }else if(this.mode == 2){
                        update(this.client);
                    }
                    repaint();
                    delta--;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void update(Client client){
        try {
            guerrier1.update(client);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update_host(){
        try {
            if(this.mode == 1){
                guerrier1.update_host();
            }
            else if(this.mode == 2){
                guerrier2.update_host();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        try {
            tileM.draw(g2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        guerrier1.draw(g2);
        guerrier2.draw(g2);
        g2.dispose();
    }

    public Guerrier getClient(){
        return this.guerrier2;
    }

    public void updateClient(Guerrier gr){
        if(this.guerrier2.getSpriteCounter() != gr.getSpriteCounter()){
            this.guerrier2.setSpriteCounter(this.guerrier2.getSpriteCounter()+1);
            if(this.guerrier2.getSpriteCounter() > 12){
                if(this.guerrier2.getSpriteNum() == 1){
                    this.guerrier2.setSpriteNum(2);
                }else if(this.guerrier2.getSpriteNum() == 2){
                    this.guerrier2.setSpriteNum(1);
                }
                this.guerrier2.setSpriteCounter(0);
            }
        }
        if(gr.getY() < this.guerrier2.getY()){
            this.guerrier2.setDirection("up");
        }
        else if(gr.getY() > this.guerrier2.getY()){
            this.guerrier2.setDirection("down");
        }
        else if(gr.getX() < this.guerrier2.getX()){
            this.guerrier2.setDirection("left");
        }
        else if(gr.getX() > this.guerrier2.getX()){
            this.guerrier2.setDirection("right");
        }
        this.guerrier2.setY(gr.getY());
        this.guerrier2.setX(gr.getX());
    }
}
