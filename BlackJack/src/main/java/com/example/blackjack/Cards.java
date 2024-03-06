package com.example.blackjack;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;

public class Cards {
    private String suit;
    private String rank;
    private int value;
    private Image image;

    public Cards(String suit, String rank, Image image) {
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

