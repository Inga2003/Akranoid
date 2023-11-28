package com.example.akranoid;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Paddle extends GameObject implements GameObjectMethods{
    private static final int DEFAULT_X = 210;
    private static final int DEFAULT_Y = 350;
    private static final int DEFAULT_WIDTH = 60;
    private static final int DEFAULT_HEIGHT = 10;

    public Paddle() {
        x = DEFAULT_X;
        y = DEFAULT_Y;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void moveLeft() {
        if (x - 20 >= 0) { // Adjust the condition to ensure it doesn't go beyond the left wall
            x -= 20;
        } else {
            x = 0; // Set x to 0 if it goes beyond the left wall
        }
    }

    public void moveRight() {
        if (x < 440) {
            x += 20;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void resetPosition() {
        x = 210;
    }
}
