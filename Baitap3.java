package multiThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Baitap3 extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private int birdY = HEIGHT / 2;
    private int birdVelocity = 0;

    private int obstacleX = WIDTH;
    private int obstacleWidth = 30;
    private int obstacleGap = 150;
    private int obstacleHeight = 200;

    private int score = 0;
    private boolean gameOver = false;

    public Baitap3() {
        setTitle("Flappy Bird Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    update();
                }
                repaint();
            }
        });
        timer.start();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (!gameOver) {
                    jump();
                }
            }
        });

        setFocusable(true);
    }

    private void update() {
        birdVelocity += 1;
        birdY += birdVelocity;

        obstacleX -= 5; // Tốc độ chướng ngại vật di chuyển

        if (birdY > HEIGHT - 30) {
            birdY = HEIGHT - 30;
            birdVelocity = 0;
        }

        if (obstacleX + obstacleWidth < 0) {
            obstacleX = WIDTH;
            obstacleHeight = 100 + (int) (Math.random() * 300);
            score++; // Tăng điểm khi vượt qua chướng ngại vật
        }

        if (birdY < obstacleHeight || birdY > obstacleHeight + obstacleGap) {
            if (obstacleX < 80 && obstacleX + obstacleWidth > 50) {
                gameOver = true;
                showGameOverDialog();
            }
        }
    }

    private void jump() {
        birdVelocity = -15;
    }

    private void showGameOverDialog() {
        Object[] options = {"Chơi lại", "Thoát"};
        int result = JOptionPane.showOptionDialog(this,
                "Game Over\nĐiểm số của bạn: " + score,
                "Kết quả",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (result == JOptionPane.YES_OPTION) {
            // Chơi lại
            resetGame();
        } else {
            // Thoát chương trình
            System.exit(0);
        }
    }

    private void resetGame() {
        birdY = HEIGHT / 2;
        birdVelocity = 0;
        obstacleX = WIDTH;
        obstacleHeight = 200;
        score = 0; // Reset điểm số
        gameOver = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.GREEN);
        g.fillRect(50, birdY, 30, 30);

        g.setColor(Color.BLACK);
        g.fillRect(0, HEIGHT - 30, WIDTH, 30);

        g.setColor(Color.RED);
        g.fillRect(obstacleX, 0, obstacleWidth, obstacleHeight);

        g.setColor(Color.RED);
        g.fillRect(obstacleX, obstacleHeight + obstacleGap, obstacleWidth, HEIGHT - obstacleHeight - obstacleGap);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Điểm số: " + score, WIDTH - 100, 20);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", WIDTH / 2 - 100, HEIGHT / 2 - 20);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Baitap3().setVisible(true);
            }
        });
    }
}
