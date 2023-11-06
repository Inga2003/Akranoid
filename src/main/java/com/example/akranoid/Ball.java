package com.example.akranoid;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ball {
    private int x, y, diameter;
    private double xSpeed, ySpeed;
    private boolean isFalling;

    public Ball() {
        x = 232;
        y = 335;
        diameter = 15;
        xSpeed = 0;
        ySpeed = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        if (x <= 0 || x >= 485) {
            xSpeed = -xSpeed;
        }

        if (y <= 0) {
            ySpeed = -ySpeed;
        }

        if (y >= 385) {
            x = 232;
            y = 335;
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void reverseYDirection() {
        ySpeed = -ySpeed;
    }

    public void reverseXDirectionLeft() {
        xSpeed = -Math.abs(xSpeed);
    }

    public void reverseXDirectionRight() {
        xSpeed = Math.abs(xSpeed);
    }

    public void resetPosition() {
        x = 232;
        y = 335;
        xSpeed = 0;
        ySpeed = 0;
    }

    public void setSpeed(double xSpeed, double ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
}
