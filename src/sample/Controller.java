package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    public static ArrayList<CircusPlayer> circusPlayers = new ArrayList<>();
    public static int numberOfPlayer;

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Circle p1color;
    @FXML
    private Circle p2color;
    @FXML
    private Circle p3color;
    @FXML
    private Circle p4color;
    @FXML
    private Label p1text;
    @FXML
    private Label p2text;
    @FXML
    private Label p3text;
    @FXML
    private Label p4text;
    @FXML
    private Label p1pos;
    @FXML
    private Label p2pos;
    @FXML
    private Label p3pos;
    @FXML
    private Label p4pos;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;
    @FXML
    private Circle c4;
    @FXML
    private Circle currentPlayerColor;
    @FXML
    private Label currentPlayer;
    @FXML
    private Button dice1;
    @FXML
    private Button dice2;
    @FXML
    private Button dice3;
    @FXML
    private Button dice4;
    @FXML
    private Button dice5;
    @FXML
    private Button dice6;
    @FXML
    private Label action1;
    @FXML
    private Label action2;
    @FXML
    private Label action3;

    private String a1;
    private String a2;
    private String a3;
    private String tempAction;


    private int diceValue;
    private int playerIndex;


    public void initialize() {



    }



    @FXML
    public void startNewGame() {
        while (!circusPlayers.isEmpty()) {
            circusPlayers.remove(0);
        }
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Number of players");
        dialog.setHeaderText("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newGame.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            newGameController controller = fxmlLoader.getController();
            numberOfPlayer = controller.newGame();
            System.out.println(numberOfPlayer);

            for (int i = 0; i < numberOfPlayer; i++) {
                showPlayerInfoInputDialog(i);
            }
        }

        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.println(circusPlayers.get(i));
        }
        prepareGameField();

        playerIndex = 0;

    }



    private void showPlayerInfoInputDialog(int i) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Player " + i + " info");
        dialog.setHeaderText("Choose the name and color for the player " + (i + 1));
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("playerInfoDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            playerInfoDialogController controller = fxmlLoader.getController();
            controller.playerInfo(i);
        }
    }

    public void diceClicked(ActionEvent e) {
        CircusPlayer currentPlayer = circusPlayers.get(playerIndex);
        a3 = a2;
        a2 = a1;
        int diceValue = -1;
        if(e.getSource().equals(dice1)) {
            diceValue = 1;
        } else if (e.getSource().equals(dice2)) {
            diceValue = 2;
        } else if (e.getSource().equals(dice3)) {
            diceValue = 3;
        } else if (e.getSource().equals(dice4)) {
            diceValue = 4;
        } else if (e.getSource().equals(dice5)) {
            diceValue = 5;
        } else if (e.getSource().equals(dice6)) {
            diceValue = 6;
        }
        System.out.println(diceValue);
/*
        currentPlayer.addToPosition(diceValue);
        a1 = currentPlayer.boardAction(currentPlayer.getPosition());
        action2.setText(a2);
        action3.setText(a3);

        if (currentPlayer.getPosition() > 120) {
            int pos = currentPlayer.getPosition();
            currentPlayer.setPosition(120 - (pos-120));
            a3=a2;
            a2=a1;
            a1 = currentPlayer.getName() + " crossed the end and returned back, current position: "
                    + currentPlayer.getPosition();

//            currentPlayer.setFieldPrint(currentPlayer.getPosition(), ' '); //for the board printing
//            action = currentPlayer.boardAction(currentPlayer.getPosition());
//            currentPlayer.setFieldPrint(currentPlayer.getPosition(), currentPlayer.getToken()); //for the board printing
//
//            CircusGameBoard.printBoard();
//            System.out.println("\t"+action);

        } else if (currentPlayer.getPosition() == 120) {
//            CircusGameBoard.printBoard();
            a3=a2;
            a2=a1;
            a1 = "Game over. The winner is " + currentPlayer.getName();
//            endOfGame(); //disable dices etc

        } else {
//            CircusGameBoard.printBoard();
            action1.setText (currentPlayer.getName() +" - " + a1);
        }

        playerIndex = (playerIndex+1)%numberOfPlayer;


*/
    }


    private void prepareGameField() {
        String pos;
        p1color.setVisible(true);
        p1text.setVisible(true);
        p1pos.setVisible(true);
        p2color.setVisible(true);
        p2text.setVisible(true);
        p2pos.setVisible(true);
        p3color.setVisible(true);
        p3text.setVisible(true);
        p3pos.setVisible(true);
        p4color.setVisible(true);
        p4text.setVisible(true);
        p4pos.setVisible(true);
        c1.setVisible(true);
        c2.setVisible(true);
        c3.setVisible(true);
        c4.setVisible(true);
        action1.setText("");
        action2.setText("");
        action3.setText("");


        currentPlayerColor.setFill(circusPlayers.get(0).getColor());
        currentPlayer.setText("Choose a dice value for " + circusPlayers.get(0).getName());

        if (numberOfPlayer == 1) {
            p1color.setFill(circusPlayers.get(0).getColor());
            p1text.setText(circusPlayers.get(0).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(0).getPosition());
            p1pos.setText(pos);
            p2color.setVisible(false);
            p2text.setVisible(false);
            p2pos.setVisible(false);
            p3color.setVisible(false);
            p3text.setVisible(false);
            p3pos.setVisible(false);
            p4color.setVisible(false);
            p4text.setVisible(false);
            p4pos.setVisible(false);
            circusPlayers.get(0).setToken(c1);
            c1.setFill(circusPlayers.get(0).getColor());
            c2.setVisible(false);
            c3.setVisible(false);
            c4.setVisible(false);
        } else if (numberOfPlayer == 2) {
            p1color.setFill(circusPlayers.get(0).getColor());
            p1text.setText(circusPlayers.get(0).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(0).getPosition());
            p1pos.setText(pos);
            p2color.setFill(circusPlayers.get(1).getColor());
            p2text.setText(circusPlayers.get(1).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(1).getPosition());
            p2pos.setText(pos);
            p3color.setVisible(false);
            p3text.setVisible(false);
            p3pos.setVisible(false);
            p4color.setVisible(false);
            p4text.setVisible(false);
            p4pos.setVisible(false);
            circusPlayers.get(0).setToken(c1);
            circusPlayers.get(1).setToken(c2);
            c1.setFill(circusPlayers.get(0).getColor());
            c2.setFill(circusPlayers.get(1).getColor());
            c3.setVisible(false);
            c4.setVisible(false);
        } else if (numberOfPlayer == 3) {
            p1color.setFill(circusPlayers.get(0).getColor());
            p1text.setText(circusPlayers.get(0).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(0).getPosition());
            p1pos.setText(pos);
            p2color.setFill(circusPlayers.get(1).getColor());
            p2text.setText(circusPlayers.get(1).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(1).getPosition());
            p2pos.setText(pos);
            p3color.setFill(circusPlayers.get(2).getColor());
            p3text.setText(circusPlayers.get(2).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(2).getPosition());
            p3pos.setText(pos);
            p4color.setVisible(false);
            p4text.setVisible(false);
            p4pos.setVisible(false);
            circusPlayers.get(0).setToken(c1);
            circusPlayers.get(1).setToken(c2);
            circusPlayers.get(2).setToken(c3);
            c1.setFill(circusPlayers.get(0).getColor());
            c2.setFill(circusPlayers.get(1).getColor());
            c3.setFill(circusPlayers.get(2).getColor());
            c4.setVisible(false);
        } else if (numberOfPlayer == 4) {
            p1color.setFill(circusPlayers.get(0).getColor());
            p1text.setText(circusPlayers.get(0).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(0).getPosition());
            p1pos.setText(pos);
            p2color.setFill(circusPlayers.get(1).getColor());
            p2text.setText(circusPlayers.get(1).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(1).getPosition());
            p2pos.setText(pos);
            p3color.setFill(circusPlayers.get(2).getColor());
            p3text.setText(circusPlayers.get(2).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(2).getPosition());
            p3pos.setText(pos);
            p4color.setFill(circusPlayers.get(3).getColor());
            p4text.setText(circusPlayers.get(3).getName() + "'s current position:");
            pos = String.valueOf(circusPlayers.get(3).getPosition());
            p4pos.setText(pos);
            circusPlayers.get(0).setToken(c1);
            circusPlayers.get(1).setToken(c2);
            circusPlayers.get(2).setToken(c3);
            circusPlayers.get(3).setToken(c4);
            c1.setFill(circusPlayers.get(0).getColor());
            c2.setFill(circusPlayers.get(1).getColor());
            c3.setFill(circusPlayers.get(2).getColor());
            c4.setFill(circusPlayers.get(3).getColor());
        }

    }


    @FXML
    public void handleExit() {
        Platform.exit();
    }

}
