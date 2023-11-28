package com.example.akranoid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.awt.*;

class ArkanoidGameTest {

    @Test
    void testPaddleMovement() {
        ArkanoidGame game = new ArkanoidGame();
        int screenWidth = 500; // Set your screen width here
        int paddleWidth = game.getPaddle().getWidth();
        game.setPreferredSize(new Dimension(screenWidth, game.getHeight()));

        while (game.getPaddle().getX() > 0) {
            game.movePaddleLeft();
        }
        Assertions.assertEquals(0, game.getPaddle().getX(), "Paddle should not go beyond left wall");
        while (game.getPaddle().getX() + paddleWidth < screenWidth) {
            game.movePaddleRight();
        }
        Assertions.assertEquals(screenWidth - paddleWidth, game.getPaddle().getX(), "Paddle should not go beyond right wall");
    }

    @Test
    void testGameOverConditions() {
        ArkanoidGame game = new ArkanoidGame();
        simulateDestroyingBricks(game);
        game.actionPerformed(null);
        Assertions.assertTrue(game.isGameOver(), "Game over screen should appear when no bricks are left");
        game.restartGame();
        simulateLosingLives(game);
        game.actionPerformed(null);
        Assertions.assertTrue(game.isGameOver(), "Game over screen should appear when no lives are left");

    }

    private void simulateLosingLives(ArkanoidGame game) {
        game.getLives().setLives(1);
        game.getBall().setY(game.getPaddle().getY() + 1);
    }

    private void simulateDestroyingBricks(ArkanoidGame game) {
        game.getBricks().clear();
    }
}