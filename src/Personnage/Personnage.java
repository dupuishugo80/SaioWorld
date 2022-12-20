package Personnage;

import saioworld.Client;
import saioworld.GamePanel;
import saioworld.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.security.Key;

public class Personnage extends Player implements Serializable {
    private String nom;
    private int x, y;
    private int speed;
    private int pv;
    private int force;
    private int magic;
    private int coin;
    private transient BufferedImage up, up2, down, down2, left, left2, right, right2;
    private String direction;
    private int spriteNum;
    private int spriteCounter;

    public Personnage(String nom, GamePanel gp, KeyHandler KeyH, int x, int y, int speed, int pv, int force, int magic, int coin){
        super(gp,KeyH);
        this.nom = nom;
        this.pv = pv;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.force = force;
        this.magic = magic;
        this.coin = coin;
        this.direction = "down";
        this.spriteNum = 1;
        this.spriteCounter = 0;
        getImage();
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getCoin() {
        return coin;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void update(Client client) throws IOException {
        if(keyH.upPress == true || keyH.downPress == true ||
                keyH.leftPress == true || keyH.rightPress == true){
            if(keyH.upPress == true){
                this.direction = "up";
                this.y -= this.speed;
                String xtsosend = String.valueOf(this.x);
                String ytsosend = String.valueOf(this.y);
                String tosend = xtsosend + "-" + ytsosend;
                client.sendCoord(tosend);
            }
            else if(keyH.downPress == true){
                this.direction = "down";
                this.y += this.speed;
                String xtsosend = String.valueOf(this.x);
                String ytsosend = String.valueOf(this.y);
                String tosend = xtsosend + "-" + ytsosend;
                client.sendCoord(tosend);
            }
            else if(keyH.rightPress == true){
                this.direction = "right";
                this.x += this.speed;
                String xtsosend = String.valueOf(this.x);
                String ytsosend = String.valueOf(this.y);
                String tosend = xtsosend + "-" + ytsosend;
                client.sendCoord(tosend);
            }
            else if(keyH.leftPress == true){
                this.direction = "left";
                this.x -= this.speed;
                String xtsosend = String.valueOf(this.x);
                String ytsosend = String.valueOf(this.y);
                String tosend = xtsosend + "-" + ytsosend;
                client.sendCoord(tosend);
            }
            this.spriteCounter++;
            if(this.spriteCounter > 12){
                if(this.spriteNum == 1){
                    this.spriteNum = 2;
                }else if(this.spriteNum == 2){
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        }
    }

    public void update_host() throws IOException {
        if(keyH.upPress == true || keyH.downPress == true ||
                keyH.leftPress == true || keyH.rightPress == true){
            if(keyH.upPress == true){
                this.direction = "up";
                this.y -= this.speed;
            }
            else if(keyH.downPress == true){
                this.direction = "down";
                this.y += this.speed;
            }
            else if(keyH.rightPress == true){
                this.direction = "right";
                this.x += this.speed;
            }
            else if(keyH.leftPress == true){
                this.direction = "left";
                this.x -= this.speed;
            }
            this.spriteCounter++;
            if(this.spriteCounter > 12){
                if(this.spriteNum == 1){
                    this.spriteNum = 2;
                }else if(this.spriteNum == 2){
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        }
    }

    public void getImage(){
        try{
            down = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_front.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_front2.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_back.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_back2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_left2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/bleu_right2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage img = null;
        switch (this.direction){
            case "up":
                if(this.spriteNum == 1) {
                    img = up;
                }
                if(this.spriteNum == 2){
                    img = up2;
                }
                break;
            case "down":
                if(this.spriteNum == 1) {
                    img = down;
                }
                if(this.spriteNum == 2){
                    img = down2;
                }
                break;
            case "left":
                if(this.spriteNum == 1) {
                    img = left;
                }
                if(this.spriteNum == 2){
                    img = left2;
                }
                break;
            case "right":
                if(this.spriteNum == 1) {
                    img = right;
                }
                if(this.spriteNum == 2){
                    img = right2;
                }
                break;
        }
        g2.drawImage(img, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }

    public void frapper(Personnage p){
        p.setPv(p.getPv()-this.force);
    }

    public void lancerUnSort(Personnage p){
        p.setPv(p.getPv()-this.magic);
    }
}
