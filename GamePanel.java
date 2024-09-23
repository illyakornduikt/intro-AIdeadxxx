import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable {
    final int originalTailSize = 16; //16x16 tile
    final int scale = 3; // 3 

    final int tileSize = originalTailSize * scale; // 48x48 tile
    final int maxScreenCol = 16; // 16
    final int maxScreenRow = 12; // 12
    final int screenWight = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // создаем поток

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; //4

    public GamePanel () {
        this.setPreferredSize(new Dimension (screenHeight, screenWight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    //метода с потоком
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    // public void run() {
    //     double darvInterval = 1000000000/FPS;
    //     double nextDravTime = System.nanoTime() + darvInterval;

    //     while (gameThread != null) {
    //         update();

    //         repaint();

    //         try {
    //             double remainingTime = nextDravTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;

    //             if (remainingTime < 0) {
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep((long) remainingTime);

    //             nextDravTime += darvInterval;

    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //     }
    // }
    //!-------------------------------------------------------------------------
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;
    
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
    
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update () {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
