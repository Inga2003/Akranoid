package com.example.akranoid;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollisionDetectorTest {

    @Test
    void testScoreIncreaseWhenBrickRemoved() {
        ArkanoidGame game = new ArkanoidGame();
        CollisionDetector collisionDetector = new CollisionDetector();

        Brick brick = new Brick(50, 50, Color.RED);
        game.getBricks().clear();
        game.getBricks().add(brick);

        Ball ball = new Ball();
        ball.setSpeed(1, 1);
        ball.setX(brick.getX());
        ball.setY(brick.getY() - ball.getDiameter());
        game.setBall(ball);

        game.setPaddle(new Paddle());
        game.setScore(new Score());
        game.getScore().increaseScore(0);

        ActionEvent actionEvent = new ActionEvent(game, ActionEvent.ACTION_PERFORMED, "SimulatedEvent");
        game.actionPerformed(actionEvent);
        assertEquals(1, game.getScore().getScore(), "Score should increase by 1 when one brick is removed");
    }
}
