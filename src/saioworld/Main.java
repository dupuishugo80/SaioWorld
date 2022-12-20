package saioworld;

import Server.Server;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Heberger une partie (1) - Rejoindre partie (2)");
        int choice = sc.nextInt();
        if(choice == 1){
            System.out.println("Server Start");
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("SaioWorld");
            GamePanel gamePanel = new GamePanel(1);
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.startGameThread();
            Server server = new Server(gamePanel);
        }
        if(choice == 2){
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("SaioWorld");
            GamePanel gamePanel = new GamePanel(2);
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.startGameThread();
        }
    }
}
