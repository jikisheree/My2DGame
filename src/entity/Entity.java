package entity;

import java.awt.image.BufferedImage;


/* This class store variables that will be used in player, monster, and NPC classes
 */
public class Entity {
    public int x, y, speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;


}
