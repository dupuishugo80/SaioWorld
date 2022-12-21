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
    KeyHandler keyH = new KeyHandler();
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


    public GamePanel(int i){
        this.mode = i;
        if(i == 2){
            this.guerrier2 = new Guerrier("Host", this, keyH, 100, 100);
            this.guerrier1 = new Guerrier("Client", this, keyH, 150, 150);
        } else if(i == 1){
            this.guerrier1 = new Guerrier("Host", this, keyH, 100, 100);
            this.guerrier2 = new Guerrier("Client", this, keyH, 150, 150);
        }
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
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
                client = new Client("localhost", 7777);
                client.sendGuerrier(guerrier1);
//               Guerrier guerrierHost = (Guerrier)client.getObject();
//                System.out.println(guerrierHost.getNom());
            }
            while (gameThread != null) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / Interval;
                lastTime = currentTime;
                if (delta >= 1) {
                    if(this.mode == 1){
                        update_host();
                    }else if(this.mode == 2){
                        update(client);
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
        }
    }

    public void update_host(){
        try {
            guerrier1.update_host();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        guerrier1.draw(g2);
        guerrier2.draw(g2);
        g2.dispose();
    }

    public Guerrier getClient(){
        return this.guerrier2;
    }

    public void updateClient(Guerrier gr){
        this.guerrier2.setSpriteCounter(this.guerrier2.getSpriteCounter()+1);
        if(this.guerrier2.getSpriteCounter() > 12){
            if(this.guerrier2.getSpriteNum() == 1){
                this.guerrier2.setSpriteNum(2);
            }else if(this.guerrier2.getSpriteNum() == 2){
                this.guerrier2.setSpriteNum(1);
            }
            this.guerrier2.setSpriteCounter(0);
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
