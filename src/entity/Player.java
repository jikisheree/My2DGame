package entity;

import main.KeyHandler;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 -(gp.tileSize/2);
        screenY =gp.screenHeight/2 -(gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        // change position
        if(keyH.upPressed){
            direction = "up";
            worldY -= speed;
        }else if(keyH.downPressed){
            direction = "down";
            worldY += speed;
        }else if(keyH.leftPressed){
            direction = "left";
            worldX -= speed;
        }else if(keyH.rightPressed){
            direction = "right";
            worldX += speed;
        }
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1)
                    spriteNum = 2;
                else
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d){

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1)
                    image = up1;
                else
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                else
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                else
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                else
                    image = right2;
                break;
        }
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}


