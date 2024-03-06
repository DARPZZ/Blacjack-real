package com.example.blackjack;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardEvents
{
    public void initialize(Scene scene, Button draw,Button stand, Button doubbleButton) {
        scene.setOnKeyPressed(event ->
        {
            if (draw.isDisable()||stand.isDisable()||doubbleButton.isDisable())
            {

            }else {
                if (event.getCode() == KeyCode.H) {
                    draw.getOnAction().handle(null);
                } else if (event.getCode() == KeyCode.S) {
                    stand.getOnAction().handle(null);
                } else if (event.getCode() == KeyCode.D) {
                    doubbleButton.getOnAction().handle(null);
                }
            }
        });
    }
}
