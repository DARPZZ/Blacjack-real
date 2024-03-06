package com.example.blackjack;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Dealer
{
    Timeline timeline = new Timeline();
    public void dealerLoop(BlackJackGame bg)
    {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {

            if (bg.dealerValue >= 17 && bg.dealerValue <= 21) {
                timeline.stop();
                bg.checkWinner();
            } else if (bg.checkDealerBust()) {
                timeline.stop();
                bg.checkWinner();
            } else {
                Cards card = bg.deck.draw();
               bg.dealerValue += card.getValue();
                bg.deck.pictures(card,false,bg.playerPane,bg.dealerPane);
            }
            bg.DealerValue.setText(String.valueOf(bg.dealerValue));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void getDealercards(BlackJackGame bg) {
        Cards drawnCard = bg.deck.draw();
        bg.dealerValue += drawnCard.getValue();
        bg.DealerValue.setText(String.valueOf(bg.dealerValue));
        bg.inStand = true;
        bg.isDealerDrawing = true; // Dealer is drawing, not player
        bg.deck.pictures(drawnCard,false,bg.playerPane,bg.dealerPane);
        bg.stand.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                bg.stand.setDisable(true);
               bg.doubbleButton.setDisable(true);
               bg.inStand = true;
               bg.isDealerDrawing = true; // Dealer is drawing, not player
                bg.deck.shuffle();
                bg.draw.setDisable(true);
                Cards drawnCard = bg.deck.draw();
                bg.dealerValue += drawnCard.getValue();
                bg.deck.pictures(drawnCard, false,bg.playerPane,bg.dealerPane);
                bg.DealerValue.setText(String.valueOf(bg.dealerValue));
                bg.dealer.dealerLoop(bg);
            }
        });
    }
}
