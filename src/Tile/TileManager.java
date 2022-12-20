package Tile;

import saioworld.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/texture/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/texture/sand.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/texture/water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(tile[2].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 0, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 0, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 0, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 0, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 0, 240, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 0, 288, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[2].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 144, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 192, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 240, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 0, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[2].image, 48, 288, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 288, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 144, 288, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 192, 288, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 240, 288, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 288, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[2].image, 288, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 240, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 288, 288, gp.tileSize, gp.tileSize, null);

    }
}
