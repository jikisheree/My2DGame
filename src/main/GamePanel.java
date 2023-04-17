package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTitleSize = 16; // 16*16 pixels tile (standard)
    final int scale = 3; // scale to 48*48 pixels

    // how to set new tile size using two above variables?
    public final int tileSize = originalTitleSize*scale;

    // next >> how many tiles the game can display both horizontally and vertically?
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;            // as pixels
    public final int screenHeight = maxScreenRow * tileSize;            // as pixels


    int FPS = 60;
    TileManager tm = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // Game panel can be focused to receive key input
        this.setVisible(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

//    @Override
//    public void run() {
//        // we will create game loop here (core)
//        // while gameThread exists do what in while loop
//        // draw the screen every 0.0166 seconds >> 60 times per seconds
//        double drawInterval = 1000000000/FPS; // 1 second
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while (gameThread != null){
//            // this process is repeating
//            System.out.println("The game is running");
//            long currentTime = System.nanoTime();
//            System.out.println("Current Time: "+currentTime);
//
//            // There are two things we gonna do in this loop
//            // UPDATING info(ie. position) & DRAW screen with updated info
//            update();
//            repaint(); // how we call the paintComponent method
//            // if FPS is 30 this loop updates&draws 30 times per second
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;  // change into milliseconds
//                // if update and repaint took more time than this draw interval
//                // then no time is left
//                if(remainingTime < 0)
//                    remainingTime = 0;
//                Thread.sleep((long)remainingTime);  // pause game loop
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
        // change position
//        if(keyH.upPressed){
//            playerX -= playerSpeed;
//        }else if(keyH.downPressed){
//            playerY += playerSpeed;
//        }else if(keyH.leftPressed){
//            playerY -= playerSpeed;
//        }else if(keyH.rightPressed){
//            playerX += playerSpeed;
//        }
    }

    // standard methods to draw things on JPanel
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
//        g2d.setColor(Color.white);
//        g2d.fillRect(playerX, playerY, tileSize, tileSize);
//        g2d.dispose(); //
        tm.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
