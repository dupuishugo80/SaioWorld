package Personnage;

import Personnage.Personnage;
import saioworld.GamePanel;
import saioworld.KeyHandler;

import java.io.Serializable;

public class Guerrier extends Personnage implements Serializable {
    public Guerrier(String nom, GamePanel gp, KeyHandler KeyH, int x, int y){
        super(nom, gp, KeyH, x, y, 3, 100, 15, 5 , 0);
    }


}
