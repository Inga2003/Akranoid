package com.example.akranoid;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class ArkanoidGame extends JPanel implements ActionListener, KeyListener {
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private CollisionDetector collisionDetector;
    private Score score;
    private boolean gameIsOver = false;
    private Lives lives;
    private boolean ballSpeedModified = false;
    private JButton playAgainButton;

    public ArkanoidGame() {
        setPreferredSize(new Dimension(500, 400));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.BLACK);
        score = new Score();
        lives = new Lives(3);
        paddle = new Paddle();
        ball = new Ball();
        bricks = new ArrayList<>();
        Color[] brickColors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        int colorIndex = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.add(new Brick(40 + i * 50, 50 + j * 20, getRandomColor()));
            }
        }

        playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        playAgainButton.setBounds(getWidth() / 2 - 75, getHeight() / 2 + 50, 150, 40);
        playAgainButton.setVisible(false);
        add(playAgainButton);

        collisionDetector = new CollisionDetector();
        Timer timer = new Timer(5, this);
        timer.start();
    }

    public void restartGame() {
        gameIsOver = false;
        score.resetScore();
        lives.resetLives(3);
        paddle.resetPosition();
        ball.resetPosition();
        ballSpeedModified = false;
        bricks.clear();
        playAgainButton.setVisible(false);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.add(new Brick(40 + i * 50, 50 + j * 20, getRandomColor()));
            }
        }

        this.setVisible(true);
        this.requestFocus();
        this.setFocusable(true);
        repaint();
    }

    private Color getRandomColor() {
        Random random = new Random();
        int colorChoice = random.nextInt(4);

        switch (colorChoice) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            default:
                return Color.RED;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameIsOver) {
            paddle.draw(g);
            ball.draw(g);
            for (Brick brick : bricks) {
                brick.draw(g);
            }

            score.draw(g);
            g.setColor(Color.WHITE.darker());
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Lives: " + lives.getLives(), getWidth() - 80, 25);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String gameOverMessage = "GAME OVER";
            String scoreMessage = "YOUR SCORE IS: " + score.getScore();

            int messageWidth = g.getFontMetrics().stringWidth(gameOverMessage);
            int scoreWidth = g.getFontMetrics().stringWidth(scoreMessage);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            g.drawString(gameOverMessage, centerX - messageWidth / 2, centerY - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString(scoreMessage, centerX - scoreWidth / 2 + 90, centerY + 20);
            playAgainButton.setBounds(centerX - 75, centerY + 50, 150, 40);
            playAgainButton.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();
        collisionDetector.checkCollision(ball, paddle, bricks, score);
        repaint();

        if (ball.getY() > paddle.getY()) {
            lives.decrementLife();
            if (lives.getLives() == 0) {
                gameIsOver = true;
            } else {
                paddle.resetPosition();
                ball.resetPosition();
                ballSpeedModified = false;
            }
        }

        if (bricks.isEmpty()) {
            gameIsOver = true;
        }
    }

    public boolean isGameOver() {
        return gameIsOver;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void movePaddleLeft() {
        paddle.moveLeft();
    }

    public void movePaddleRight() {
        paddle.moveRight();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            movePaddleLeft();
            if (ball.getXSpeed() == 0) {
                ball.setX(paddle.getX() + (paddle.getWidth() / 2) - (ball.getDiameter() / 2));
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            movePaddleRight();
            if (ball.getXSpeed() == 0) {
                ball.setX(paddle.getX() + (paddle.getWidth() / 2) - (ball.getDiameter() / 2));
            }
        } else if (key == KeyEvent.VK_UP && !ballSpeedModified) {
            ball.setSpeed(3, 3);
            ballSpeedModified = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Arkanoid Game");
        StartMenu startMenu = new StartMenu(frame);
        frame.add(startMenu);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
