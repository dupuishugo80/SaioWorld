package Tile;

import saioworld.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    Tile chosenTile;

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

    public void draw(Graphics2D g2) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/res/map/world.txt"));
        String line = reader.readLine();
        int count = 0;
        while (line != null) {
            for(int i=0; i<line.length(); i++){
                if(line.charAt(i) == '0'){
                    chosenTile = tile[2];
                }else if(line.charAt(i) == '1'){
                    chosenTile = tile[1];
                }
                g2.drawImage(chosenTile.image, i*48, count*48, gp.tileSize, gp.tileSize, null);
            }
            line = reader.readLine();
            count++;
        }

    }
}
