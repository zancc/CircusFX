package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private Label currentPlayerLabel;
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
    @FXML
    private Label currentAction;
    @FXML
    private GridPane circusBoardGrid;
    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane dicePane;
    @FXML
    private VBox vBox;
    @FXML
    private FlowPane flowPane;

    @FXML
    private Button rollButton;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private ImageView im3;
    @FXML
    private ImageView im4;
    @FXML
    private ImageView im5;
    @FXML
    private ImageView im6;
    @FXML
    private Label playerRolled;

    public static boolean automaticDice;

    private String a1;
    private String a2;
    private String a3;
    private String latestAction;
    private String tempAction;
    private static boolean correctInfoInput;

    private int playerIndex;

    public void initialize() {
        System.out.println("Initialize started");
        clearBeforeGame();


    }


    @FXML
    public void startNewGame() {
        //removes data of previous players
        while (!circusPlayers.isEmpty()) {
            circusPlayers.remove(0);
        }

        //"choose the number of players" dialog
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
//            System.out.println(numberOfPlayer);

            correctInfoInput = true;
            //player data input dialog
            for (int i = 0; i < numberOfPlayer; i++) {
                showPlayerInfoInputDialog(i);
            }

            if (correctInfoInput) {
                prepareGameField();
                latestAction = "New Game. Number of players - " + numberOfPlayer;
                currentAction.setText(latestAction);
                playerIndex = 0;
            } else {
                initialize();
            }

        } else {
            initialize();
        }
    }

    private void showPlayerInfoInputDialog(int i) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Player " + (i + 1) + " info");
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
        } else {
            correctInfoInput = false;
        }

    }

    public void diceRolled(ActionEvent e) {
        CircusPlayer currentPlayer = circusPlayers.get(playerIndex);
        a3 = a2;
        a2 = a1;
        a1 = latestAction;
        int diceValue = -1;

        if (automaticDice) {
            diceValue = automaticDiceRoll();
        } else {
            diceValue = manualDiceRoll(e);
        }
//        System.out.println(diceValue);
        currentPlayer.addToPosition(diceValue);

        latestAction = currentPlayer.getName()  +" - dice value was " + diceValue + ", moved to the field " +
                currentPlayer.boardAction(currentPlayer.getPosition());
        action1.setText(a1);
        action2.setText(a2);
        action3.setText(a3);

        movePlayer(currentPlayer.getToken(), currentPlayer.getPosition());

        if (currentPlayer.getPosition() > 120) {
            int pos = currentPlayer.getPosition();
            currentPlayer.setPosition(120 - (pos-120));
            latestAction = currentPlayer.getName()  +" - dice value was " + diceValue + ", crossed the end and returned back, " +
                    "current position: " + currentPlayer.getPosition();

            a3=a2; a2=a1; a1=latestAction;
            tempAction = currentPlayer.boardAction(currentPlayer.getPosition());
            currentAction.setText(latestAction + "\n " + tempAction);
            latestAction = currentPlayer.getName()  + " - " + tempAction;
            action2.setText(a2);
            action3.setText(a3);
            action1.setText(a1);
            movePlayer(currentPlayer.getToken(), currentPlayer.getPosition());

        } else if (currentPlayer.getPosition() == 120) {
            latestAction = currentPlayer.getName() + " reached field No.120. Winner is " + currentPlayer.getName() + "!";
            currentAction.setText(latestAction);
            endOfGame(); //disable dices etc

        } else {
            currentAction.setText (latestAction);
        }

        setPlayerIndexLabel(playerIndex);

        playerIndex = (playerIndex+1)%numberOfPlayer;
        currentPlayerColor.setFill(circusPlayers.get(playerIndex).getColor());
        currentPlayerLabel.setText("   Roll the dice for " + circusPlayers.get(playerIndex).getName()+ "  ");

    }

    private int automaticDiceRoll() {
        im1.setVisible(false);
        im2.setVisible(false);
        im3.setVisible(false);
        im4.setVisible(false);
        im5.setVisible(false);
        im6.setVisible(false);
        int randomDiceValue = (int)(Math.random()*6+1);
        switch (randomDiceValue) {
            case 1:
                im1.setVisible(true);
                break;
            case 2:
                im2.setVisible(true);
                break;
            case 3:
                im3.setVisible(true);
                break;
            case 4:
                im4.setVisible(true);
                break;
            case 5:
                im5.setVisible(true);
                break;
            case 6:
                im6.setVisible(true);
                break;
        }
        playerRolled.setVisible(true);
        playerRolled.setText(circusPlayers.get(playerIndex).getName() + " rolled:");

        return randomDiceValue;
    }

    private int manualDiceRoll(ActionEvent e) {
        if(e.getSource().equals(dice1)) {
            return 1;
        } else if (e.getSource().equals(dice2)) {
            return 2;
        } else if (e.getSource().equals(dice3)) {
            return 3;
        } else if (e.getSource().equals(dice4)) {
            return 4;
        } else if (e.getSource().equals(dice5)) {
            return 5;
        } else if (e.getSource().equals(dice6)) {
            return 6;
        }
        return -1;
    }

    private void endOfGame() {
        dice1.setDisable(true);
        dice2.setDisable(true);
        dice3.setDisable(true);
        dice4.setDisable(true);
        dice5.setDisable(true);
        dice6.setDisable(true);
        rollButton.setDisable(true);
    }

    private void setPlayerIndexLabel(int playerIndex) {
        switch (playerIndex) {
            case 0:
                p1pos.setText(String.valueOf(circusPlayers.get(playerIndex).getPosition()));
                break;
            case 1:
                p2pos.setText(String.valueOf(circusPlayers.get(playerIndex).getPosition()));
                break;
            case 2:
                p3pos.setText(String.valueOf(circusPlayers.get(playerIndex).getPosition()));
                break;
            case 3:
                p4pos.setText(String.valueOf(circusPlayers.get(playerIndex).getPosition()));
                break;
        }
    }

    private void prepareGameField() {
        if (automaticDice) {
            prepareAutomaticRoll();
        } else {
            prepareManualRoll();
        }

        rollButton.setDisable(false);
        dice1.setDisable(false);
        dice2.setDisable(false);
        dice3.setDisable(false);
        dice4.setDisable(false);
        dice5.setDisable(false);
        dice6.setDisable(false);


        String pos;
        movePlayer(c1, 1);
        movePlayer(c2, 1);
        movePlayer(c3,1);
        movePlayer(c4,1);
        gridPane.setVisible(true);
        vBox.setVisible(true);
        dicePane.setVisible(true);
        flowPane.setVisible(true);
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
        a1="";
        a2="";
        a3="";


        currentPlayerColor.setFill(circusPlayers.get(0).getColor());
        currentPlayerLabel.setText("   Roll the dice for " + circusPlayers.get(0).getName() + "   ");

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

    private void prepareAutomaticRoll() {
        dice1.setVisible(false);
        dice2.setVisible(false);
        dice3.setVisible(false);
        dice4.setVisible(false);
        dice5.setVisible(false);
        dice6.setVisible(false);

        rollButton.setVisible(true);
        im1.setVisible(false);
        im2.setVisible(false);
        im3.setVisible(false);
        im4.setVisible(false);
        im5.setVisible(false);
        im6.setVisible(false);
        playerRolled.setVisible(false);

    }

    private void prepareManualRoll() {
        dice1.setVisible(true);
        dice2.setVisible(true);
        dice3.setVisible(true);
        dice4.setVisible(true);
        dice5.setVisible(true);
        dice6.setVisible(true);

        rollButton.setVisible(false);
        im1.setVisible(false);
        im2.setVisible(false);
        im3.setVisible(false);
        im4.setVisible(false);
        im5.setVisible(false);
        im6.setVisible(false);
        playerRolled.setVisible(false);

    }

    private void movePlayer(Circle token, int position) {
        circusBoardGrid.getChildren().remove(token);
        int row = CircusPlayer.getRow(position);
        int column = CircusPlayer.getColumn(position);
        circusBoardGrid.add(token, column, row);
    }

    private void clearBeforeGame() {
        c1.setVisible(false);
        c2.setVisible(false);
        c3.setVisible(false);
        c4.setVisible(false);
        vBox.setVisible(false);
        gridPane.setVisible(false);
        dicePane.setVisible(false);
        flowPane.setVisible(false);

    }


    @FXML
    public void handleExit() {
        Platform.exit();
    }

}
