package com.example.akranoid;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Brick {
    private int x, y;
    private int width, height;
    private int leftX, rightX, topY, bottomY;
    private Color color;

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        width = 50;
        height = 20;
        this.color = color;
        calculateCorners();
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void calculateCorners() {
        leftX = x;
        rightX = x + width;
        topY = y;
        bottomY = y + height;
    }
}
