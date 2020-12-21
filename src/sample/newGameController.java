package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;

import java.time.LocalDate;
import java.util.EventListener;

public class newGameController{

    @FXML
    private ToggleGroup numberOfPlayers;
    @FXML
    private RadioButton one;
    @FXML
    private RadioButton two;
    @FXML
    private RadioButton three;
    @FXML
    private RadioButton four;
    @FXML
    private CheckBox isAuto;

    public int newGame() {
        if (isAuto.isSelected()) {
            Controller.automaticDice = true;
        } else {
            Controller.automaticDice = false;
        }

        if (one.isSelected()) {
            return 1;
        }
        if (two.isSelected()) {
            return 2;
        }
        if (three.isSelected()) {
            return 3;
        }
        if (four.isSelected()) {
            return 4;
        }
        return -1;
    }
}
