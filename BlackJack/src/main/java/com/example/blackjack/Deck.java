package com.example.blackjack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {


    private ArrayList<Cards> cards;

    public Deck() {
        cards = new ArrayList<>();
        reset();
    }
    String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Cards draw() {
        return cards.remove(0);
    }

    public void reset() {
        cards.clear();

        for (String suit : suits) {
            for (String rank : ranks) {
                String filename = "file:///C:/Users/rasmu/IdeaProjects/Blacjack-real/BlackJack/src/main/java/CardPictures/" + rank + " of " + suit + ".jpg";
                Image image = new Image(filename);
                Cards card = new Cards(suit, rank, image);
                if (rank.equals("Ace")) {
                    card.setValue(11);
                } else if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                    card.setValue(10);
                } else {
                    card.setValue(Integer.parseInt(rank));
                }
                cards.add(card);
            }
        }
    }
    public void pictures(Cards drawnCard, boolean isPlayer, AnchorPane playerPane, AnchorPane dealerPane) {
        double xOffset;
        double yOffset;
        if (isPlayer) {
            xOffset = 50 * playerPane.getChildren().size();
            yOffset = 150;
        } else {
            xOffset = 50 * dealerPane.getChildren().size();
            yOffset = 250;
        }
        ImageView cardImageView = new ImageView(drawnCard.getImage());
        cardImageView.setLayoutX(xOffset);
        cardImageView.setLayoutY(yOffset);
        cardImageView.setFitHeight(100);
        cardImageView.setFitWidth(100);
        cardImageView.autosize();
        if (isPlayer) {
            playerPane.getChildren().add(cardImageView);
        } else {
            dealerPane.getChildren().add(cardImageView);
        }
    }


    public int size() {
        return cards.size();
    }
}
