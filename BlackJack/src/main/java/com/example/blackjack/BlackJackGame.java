package com.example.blackjack;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class BlackJackGame
{
    ImageView dealerImage = new ImageView();
    Label Name = new Label();
    Dealer dealer = new Dealer();
    Player player = new Player();

    KeyboardEvents keyboardEvents = new KeyboardEvents();
    CheckWinner checkWinner = new CheckWinner();

    public boolean isDealerDrawing = false;
    boolean inStand = false;
    Balance balance = new Balance();
    Label money = new Label();
    private Stage stage;
    int playerValue = 0;
    Button draw = new Button();
    Button stand = new Button();
    int dealerValue = 0;

    ImageView playercard1 = new ImageView();
    Label playersCard = new Label();
    Label DealerValue = new Label();
    Deck deck = new Deck();
    Cards card = deck.draw();
    Button doubbleButton = new Button("double");

    Timeline timeline = new Timeline();
    TextField betAmount = new TextField();
    int bet;
    AnchorPane anchorPane = new AnchorPane();
    AnchorPane dealerPane = new AnchorPane();
    AnchorPane playerPane = new AnchorPane();
    boolean isDouble = false;
    Label dealerlabel = new Label("BOT");
    //region setScene
    public Scene createBlackJackScene(Stage stage)
    {
        doubbleButton.setDisable(true);
        this.stage = stage;

        balance.CheckIfNoMoreMoney(money,stage);
        deck.shuffle();
        player.drawCard(BlackJackGame.this);
        playersCard.setLayoutX(400);
        playersCard.setLayoutY(50);
        DealerValue.setLayoutX(400);
        DealerValue.setLayoutY(650);
        draw.setLayoutY(725);
        draw.setLayoutX(150);
        draw.setPrefWidth(100);
        draw.setPrefHeight(20);
        draw.setText("Hit");
        stand.setPrefHeight(draw.getPrefHeight());
        stand.setPrefWidth(draw.getPrefWidth());
        stand.setLayoutX(300);
        stand.setLayoutY(draw.getLayoutY());
        stand.setText("Stand");
        doubbleButton.setPrefHeight(draw.getPrefHeight());
        doubbleButton.setPrefWidth(draw.getPrefWidth());
        doubbleButton.setLayoutY(stand.getLayoutY());
        doubbleButton.setLayoutX(450);
        doubbleButton.setId("stand");
        money.setLayoutY(20);
        money.setLayoutX(75);
        money.setText(("balance: " + balance.setStartBalance()));
        betAmount.setLayoutX(money.getLayoutX());
        betAmount.setLayoutY(money.getLayoutY()+50);
        betAmount.setPromptText("Enter bet amount");
        getamount();
        draw.setDisable(true);
        stand.setDisable(true);
        money.setId("money");
        betAmount.setId("betAmount");
        stand.setId("stand");
        draw.setId("draw");
        playersCard.setId("playersCard");
        DealerValue.setId("DealerValue");
        playercard1.setFitHeight(50);
        playercard1.setFitWidth(50);
        playercard1.setLayoutX(150);
        playerPane.setLayoutX(350);
        playerPane.setLayoutY(20);
        playerPane.setPrefHeight(100);
        playerPane.setPrefWidth(250);
        dealerPane.setLayoutX(350);
        dealerPane.setLayoutY(220);
        dealerPane.setPrefHeight(100);
        dealerPane.setPrefWidth(250);
        doubleBet();
        anchorPane.getChildren().addAll(playersCard, draw, stand, DealerValue,money,betAmount,playercard1,playerPane,dealerPane,doubbleButton,Name,dealerImage,dealerlabel);
        Scene scene = new Scene(anchorPane, 800, 800);
        keyboardEvents.initialize(scene,draw,stand,doubbleButton);
        String css = this.getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;

    }
    //endregion

    public void  doubleBet()
    {
        doubbleButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (balance.getBalance() < bet*2)
                {
                    doubbleButton.setDisable(true);
                   return;
                }
                isDouble = true;
                draw.setDisable(true);
                isDealerDrawing = false;

                stand.setDisable(true);
                doubbleButton.setDisable(true);
                Cards drawnCard = deck.draw();
                playerValue += drawnCard.getValue();
                playersCard.setText(String.valueOf(playerValue));
                deck.pictures(drawnCard,true,playerPane,dealerPane);
                dealer.getDealercards(BlackJackGame.this);
                dealer.dealerLoop(BlackJackGame.this);
            }
        });
    }
    public boolean checkPlayerBust()
    {
        if (playerValue > 21) {
            draw.setDisable(true);
            stand.setDisable(true);

            return true;
        }else {
            return false;
        }
    }
    public boolean checkDealerBust()
    {
        if (dealerValue > 21) {
            return true;
        }else
            return false;
    }


    public void checkWinner()
    {
        checkWinner.checkWinner(balance,checkPlayerBust(),checkDealerBust(),isDouble,bet,money,playerValue,dealerValue);

        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {}));
        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(actionEvent -> resetGame());
    }
    public void  getamount()
    {
        betAmount.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                betAmount.setDisable(true);
                doubbleButton.setDisable(false);
                bet = Integer.parseInt(betAmount.getText());
                checkIfEnoughMoney();
            }
        });
    }
    public void checkIfEnoughMoney()
    {
        Tooltip tooltip = new Tooltip("Not enough money");
        if (bet> balance.getBalance())
        {
            doubbleButton.setDisable(true);
            draw.setDisable(true);
            stand.setDisable(true);
            betAmount.setTooltip(tooltip);
            tooltip.setStyle("-fx-background-color: cyan; -fx-text-fill: red;");
            betAmount.setStyle("-fx-border-color: red ; -fx-text-fill: white");
            tooltip.setShowDelay(Duration.millis(0));
            betAmount.setDisable(false);
        }else
        {
            player.getPlayerStartCards(BlackJackGame.this);
            player.getPlayerStartCards(BlackJackGame.this);
            tooltip.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            betAmount.setStyle("-fx-border-color: white ; -fx-text-fill: white");
            betAmount.setTooltip(null);
            dealer.getDealercards(BlackJackGame.this);
            doubbleButton.setDisable(false);
            draw.setDisable(false);
            stand.setDisable(false);
        }
    }
    public void resetGame()
    {
        doubbleButton.setDisable(true);
        isDouble = false;
        deck.reset();
        deck.shuffle();
        player.drawCard(BlackJackGame.this);
        stand.setDisable(true);
        draw.setDisable(true);
        betAmount.setText("");
        dealerValue = 0;
        playerValue = 0;
        betAmount.setDisable(false);
        DealerValue.setText("");
        playersCard.setText("");
        playerPane.getChildren().clear();
        dealerPane.getChildren().clear();
        balance.CheckIfNoMoreMoney(money,stage);
    }
    public void getuserImageAndName(ImageView imageView,String name)
    {
        imageView.getImage();
        anchorPane.getChildren().add(imageView);
        imageView.setLayoutX(500);
        imageView.setLayoutY(20);
        Name.setId("navn");
        Name.setLayoutX(600);
        Name.setLayoutY(imageView.getFitHeight()/2);
        Name.setText("WELCOME: " + "\n" + name);
        System.out.println(imageView.getFitHeight() +  "height");
        System.out.println(imageView.getFitWidth() + "width");
        dealerImage();


    }
    public void dealerImage()
    {
        Random random = new Random();
         int chance =random.nextInt(100);
        if(chance<=33) {
            Image image = new Image(("C:\\Users\\Rasmus T. Hermansen\\BlackJack\\src\\main\\java\\DealerImage\\images.jpg"));
            dealerImage.setImage(image);

        }else if(chance<=66) {
          Image  image = new Image(("C:\\Users\\Rasmus T. Hermansen\\BlackJack\\src\\main\\java\\DealerImage\\POG.jpg"));
            dealerImage.setImage(image);
        }else {
            Image image = new Image(("C:\\Users\\Rasmus T. Hermansen\\BlackJack\\src\\main\\java\\DealerImage\\POG 2.jpg"));
            dealerImage.setImage(image);
        }
        dealerlabel.setLayoutX(600);
        dealerlabel.setLayoutY(630);
        dealerlabel.setId("navn");
        dealerImage.setLayoutX(500);
        dealerImage.setLayoutY(600);
        dealerImage.setFitHeight(100);
        dealerImage.setFitWidth(100);
    }
}