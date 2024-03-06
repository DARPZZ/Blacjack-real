package com.example.blackjack;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class Balance
{
    private Scene scene;


    public int getBalance()
    {
        return balance;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    private int balance;

    public int setStartBalance()
    {
     setBalance(1000);
     return balance;
    }
    public void loose(int bet, Label moneyLabel)
    {
        balance -=  bet;
        moneyLabel.setText("balance: " + balance);
    }
    public void win(int bet, Label moneyLabel)
    {
        balance = balance- bet+ (bet * 2);
        moneyLabel.setText("balance: " + balance);
    }

    public void CheckIfNoMoreMoney(Label moneyLabel, Stage stage)
    {
        if (getBalance() <=0)
        {
          moneyLabel.setText("balance: " + setStartBalance());
        }
    }
    public void looseDoubleBet(int bet, Label moneyLabel) {
        balance -= bet * 2;
        moneyLabel.setText("balance: " + balance);
    }

    public void WinDoubleBet(int bet, Label moneyLabel) {
        balance += bet * 3;
        moneyLabel.setText("balance: " + balance);
    }

}
