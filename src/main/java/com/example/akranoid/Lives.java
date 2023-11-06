package com.example.akranoid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lives {
    private int lives;

    public Lives(int initialLives) {
        lives = initialLives;
    }

    public void decrementLife() {
        lives--;
    }

    public void resetLives(int initialLives) {
        lives = initialLives;
    }
}
