package com.example.blackjack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application
{

    BlackJackGame blackJackGame = new BlackJackGame();
    private Stage stage;
    ComboBox comboBox = new ComboBox();

    Label navn = new Label("Indtast navn");
    Button confirm = new Button();
    TextField navnIndtastning = new TextField();
    AnchorPane anchorPane = new AnchorPane();

    @Override
    public void start(Stage stage) throws IOException
    {

        this.stage = stage;
        Scene scene = new Scene(anchorPane, 800, 800);
        navn.setLayoutY(400);
        navn.setLayoutX(400);
        navnIndtastning.setLayoutY(navn.getLayoutY()+30);
        navnIndtastning.setLayoutX(navn.getLayoutX()-35);
        confirm.setLayoutX(navnIndtastning.getLayoutX());
        confirm.setLayoutY(navnIndtastning.getLayoutY()+35);
        confirm.setPrefHeight(20);
        confirm.setPrefWidth(150);
        confirm.setPrefWidth(200);
        confirm.setPrefHeight(20);
        confirm.setText("LOG IN");
        comboBox.setLayoutX(confirm.getLayoutX());
        comboBox.setLayoutY(confirm.getLayoutY()-150);
        anchorPane.getChildren().addAll(confirm,navn,navnIndtastning,comboBox);


        File[] imageFiles = new File("C:\\Users\\rasmu\\IdeaProjects\\Blacjack-real\\BlackJack\\src\\main\\java\\Userpicture").listFiles();
        for (File file : imageFiles) {
            if (file.isFile()) {
                String imagePath = file.toURI().toString();
                ImageView imageView = new ImageView(new Image(imagePath));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                comboBox.getItems().add(imageView);
            }
        }
        confirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                String navn = navnIndtastning.getText();
                if (comboBox.getSelectionModel().getSelectedItem() == null) {
                    Scene createBlackJack = blackJackGame.createBlackJackScene(stage);
                    stage.setScene(createBlackJack);

                }else
                {
                    blackJackGame.getuserImageAndName((ImageView) comboBox.getSelectionModel().getSelectedItem(),navn);
                    Scene createBlackJack = blackJackGame.createBlackJackScene(stage);
                    stage.setScene(createBlackJack);
                }

            }
        });
        stage.setTitle("Black Jack");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        launch();
    }
}