package saioworld;

import Personnage.Guerrier;
import Server.Server;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        KeyHandler keyH = new KeyHandler();
        Scanner sc = new Scanner(System.in);
        System.out.println("Heberger une partie (1) - Rejoindre partie (2)");
        int choice = sc.nextInt();
        if(choice == 1){
            Guerrier guerrier1 = new Guerrier("Host", null, keyH, 100, 100);
            Guerrier guerrier2 = new Guerrier("Client", null, keyH, 150, 150);
            System.out.println("Server Start");
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("SaioWorld");
            GamePanel gamePanel = new GamePanel(1, guerrier1, guerrier2, keyH);
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.startGameThread();
            guerrier1.setGp(gamePanel);
            guerrier2.setGp(gamePanel);
            Server server = new Server(gamePanel, guerrier1);
            Socket s = server.getSocket();
            gamePanel.setSocket(s);
        }
        if(choice == 2){
            Guerrier guerrier2 = new Guerrier("Host", null, keyH, 100, 100);
            Guerrier guerrier1 = new Guerrier("Client", null, keyH, 150, 150);
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("SaioWorld");
            GamePanel gamePanel = new GamePanel(2,guerrier1,guerrier2, keyH);
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            guerrier1.setGp(gamePanel);
            guerrier2.setGp(gamePanel);
            gamePanel.startGameThread();
        }
    }
}
