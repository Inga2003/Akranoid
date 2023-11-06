package com.example.akranoid;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Paddle {
    private int x, y, width, height;

    public Paddle() {
        x = 210;
        y = 350;
        width = 60;
        height = 10;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void moveLeft() {
        if (x > 0) {
            x -= 20;
        }
    }

    public void moveRight() {
        if (x < 440) {
            x += 20;
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
    public void resetPosition() {
        x = 210;
    }
}
