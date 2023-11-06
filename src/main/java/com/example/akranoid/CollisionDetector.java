package com.example.akranoid;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class CollisionDetector {
    public void checkCollision(Ball ball, Paddle paddle, List<Brick> bricks, Score score) {
        Rectangle ballRect = ball.getRectangle();
        Rectangle paddleRect = paddle.getRectangle();
        boolean ballCollided = false;
        boolean brickCollided = false;
        Brick collidedBrick = null;

        if (ballRect.intersects(paddleRect)) {
            int paddleCenterX = paddle.getX() + paddle.getWidth() / 2;
            int ballCenterX = ball.getX() + ball.getDiameter() / 2;

            if (ballCenterX < paddleCenterX) {
                ball.reverseYDirection();
                ball.reverseXDirectionLeft();
            } else {
                ball.reverseYDirection();
                ball.reverseXDirectionRight();
            }

            ballCollided = true;
        }

        List<Brick> bricksToRemove = new ArrayList<>();

        for (Brick brick : bricks) {
            if (ballRect.intersects(brick.getRectangle()) && !bricksToRemove.contains(brick)) {
                bricksToRemove.add(brick);
                brickCollided = true;
                collidedBrick = brick;
                score.increaseScore(1);

                int ballCenterX = ball.getX() + ball.getDiameter() / 2;
                int brickCenterX = brick.getX() + brick.getWidth() / 2;
                int diffX = ballCenterX - brickCenterX;
                int halfBallWidth = ball.getDiameter() / 2;

                boolean leftQuarter = Math.abs(diffX) <= halfBallWidth && diffX < 0;
                boolean rightQuarter = Math.abs(diffX) <= halfBallWidth && diffX > 0;

                boolean touchesLeftWall = ballRect.intersects(new Rectangle(brick.getLeftX(), brick.getTopY(), 1, brick.getHeight()));
                boolean touchesRightWall = ballRect.intersects(new Rectangle(brick.getRightX() - 1, brick.getTopY(), 1, brick.getHeight()));

                if (touchesLeftWall && ball.getXSpeed() > 0) {
                    ball.reverseXDirectionLeft();
                } else if (touchesRightWall && ball.getXSpeed() < 0) {
                    ball.reverseXDirectionRight();
                } else if (leftQuarter) {
                    ball.reverseXDirectionLeft();
                } else if (rightQuarter) {
                    ball.reverseXDirectionRight();
                }
                ballCollided = true;
            }

        }

        int bricksRemoved = 0;
        bricks.removeAll(bricksToRemove);

        if (brickCollided) {
            bricksRemoved += removeAdjacentBricks(bricks, collidedBrick);
        }

        score.increaseScore(bricksRemoved);

        if (ballCollided && !ballRect.intersects(paddleRect)) {
            ball.reverseYDirection();
        }
    }

    public int removeAdjacentBricks(List<Brick> bricks, Brick collidedBrick) {
        Color color = collidedBrick.getColor();
        List<Brick> bricksToRemove = new ArrayList<>();

        for (Brick brick : bricks) {
            if (brick.getColor().equals(color) && isAdjacent(collidedBrick, brick)) {
                bricksToRemove.add(brick);
            }
        }

        if (!bricksToRemove.isEmpty()) {
            bricks.removeAll(bricksToRemove);
        }

        return bricksToRemove.size();
    }

    private boolean isAdjacent(Brick brick1, Brick brick2) {
        int brick1RightX = brick1.getRightX();
        int brick1LeftX = brick1.getLeftX();
        int brick1TopY = brick1.getTopY();
        int brick1BottomY = brick1.getBottomY();

        int brick2RightX = brick2.getRightX();
        int brick2LeftX = brick2.getLeftX();
        int brick2TopY = brick2.getTopY();
        int brick2BottomY = brick2.getBottomY();

        // Check if any side of brick1 is adjacent to any side of brick2
        boolean leftRightAdjacent = (brick1RightX == brick2LeftX || brick1LeftX == brick2RightX) &&
                (brick1TopY <= brick2BottomY && brick1BottomY >= brick2TopY);
        boolean topBottomAdjacent = (brick1BottomY == brick2TopY || brick1TopY == brick2BottomY) &&
                (brick1LeftX <= brick2RightX && brick1RightX >= brick2LeftX);

        // Check if any corner of brick1 is not adjacent to any corner of brick2
        boolean notCornerAdjacent = !((brick1RightX == brick2LeftX && brick1BottomY == brick2TopY) ||
                (brick1LeftX == brick2RightX && brick1BottomY == brick2TopY) ||
                (brick1RightX == brick2LeftX && brick1TopY == brick2BottomY) ||
                (brick1LeftX == brick2RightX && brick1TopY == brick2BottomY));

        return (leftRightAdjacent || topBottomAdjacent) && notCornerAdjacent;
    }
}
