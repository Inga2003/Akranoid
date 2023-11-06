package com.example.akranoid;

import lombok.Getter;
import lombok.Setter;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

@Getter
@Setter
public class Score {
    private int score;
    public Score() {
        score = 0;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void resetScore() {
        score = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE.darker());
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Score: " + score, 15, 25);
        g.setColor(g.getColor());
    }
}
