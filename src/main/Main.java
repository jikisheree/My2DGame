package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // creating window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        // size this window to fir the preferred size and layouts of its subcomponent (gamePanel)
        window.pack();

        window.setLocationRelativeTo(null); // render at center
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
