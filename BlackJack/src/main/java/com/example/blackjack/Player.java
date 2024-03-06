package com.example.blackjack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Player
{
    public void getPlayerStartCards(BlackJackGame bg)
    {
        Cards drawnCard = bg.deck.draw();
        bg.playerValue += drawnCard.getValue();
        bg.playersCard.setText(String.valueOf(bg.playerValue));
        bg.deck.pictures(drawnCard, true, bg.playerPane, bg.dealerPane);
    }

    public void drawCard(BlackJackGame bg)
    {
        bg.draw.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                bg.inStand = false;
                bg.isDealerDrawing = false;
                Cards drawnCard = bg.deck.draw();
                System.out.println(drawnCard.getRank());
                if (drawnCard.getRank().equals("Ace")) {
                    if (bg.playerValue + 11 > 21) {
                        bg.playerValue += 1;
                    } else {
                        bg.playerValue += 11;
                    }
                } else {
                    bg.playerValue += drawnCard.getValue();
                }
                bg.playersCard.setText(String.valueOf(bg.playerValue));
                bg.deck.pictures(drawnCard, true, bg.playerPane, bg.dealerPane);

                if (bg.checkPlayerBust()) {
                    bg.checkWinner();
                }
            }
        });
    }
}
