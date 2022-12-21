package Personnage;

import saioworld.GamePanel;
import saioworld.KeyHandler;

import java.io.Serializable;
import java.security.Key;

public class Player implements Serializable {
     transient GamePanel gp;
     transient KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
}
