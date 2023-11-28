package com.example.akranoid;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Brick extends GameObject implements GameObjectMethods{

    private int leftX, rightX, topY, bottomY;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 20;

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        this.color = color;
        calculateCorners();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
    }

    @Override
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
