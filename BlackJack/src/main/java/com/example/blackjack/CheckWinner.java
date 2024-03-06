package com.example.blackjack;

import javafx.scene.control.Label;

public class CheckWinner
{

    public void checkWinner( Balance balance,boolean checkPlayerBust, boolean checkDealerBust, boolean isDouble, int bet, Label money, int playerValue, int dealerValue)
    {
        if (checkPlayerBust) {
            if (isDouble)
            {balance.looseDoubleBet(bet,money);
            }else
            {balance.loose(bet,money);}

        } else if (checkDealerBust) {
            if (isDouble)
            {
                balance.WinDoubleBet(bet,money);
            }else {
                balance.win(bet, money);
            }

        } else if (playerValue > dealerValue) {
            if (isDouble )
            {
                balance.WinDoubleBet(bet,money);
            }else {
                balance.win(bet, money);
            }
        } else if (playerValue < dealerValue) {
            if (isDouble)
            {
                balance.looseDoubleBet(bet,money);
            }else {
                balance.loose(bet, money);
            }
        } else {
            System.out.println("It's a tie");
        }
    }

}

