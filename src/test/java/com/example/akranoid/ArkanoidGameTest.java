package com.example.akranoid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ArkanoidGameTest {

    @Test
    void testPaddleMovement() {
        ArkanoidGame game = new ArkanoidGame();
        game.movePaddleLeft();
        game.movePaddleRight();
    }

    @Test
    void testGameOverConditions() {
        ArkanoidGame game = new ArkanoidGame();
        simulateLosingLives(game);
        Assertions.assertTrue(game.isGameOver(), "Game over screen should appear when no lives are left");
        game.restartGame();
        simulateDestroyingBricks(game);
        Assertions.assertTrue(game.isGameOver(), "Game over screen should appear when no bricks are left");
    }

    private void simulateLosingLives(ArkanoidGame game) {
        game.getLives().setLives(0);
    }

    private void simulateDestroyingBricks(ArkanoidGame game) {
        game.getBricks().clear();
    }
}