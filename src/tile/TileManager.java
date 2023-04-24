package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;


    public TileManager (GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void loadMap(String map){
        try{
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row=0; row < gp.maxScreenRow; row++){
                String line = br.readLine();

                for (int col=0; col< gp.maxScreenCol; col++){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d){
//        g2d.drawImage(tile[0].image,0,0,gp.tileSize, gp.tileSize, null);

        int x;
        int y = 0;

        for (int row = 0; row< gp.maxScreenRow;row++){
            x = 0;
            for (int col = 0; col< gp.maxScreenCol;col++){
                int tileNum = mapTileNum[row][col];
                g2d.drawImage(tile[tileNum].image,x,y,gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }
            y += gp.tileSize;
        }
    }

}
