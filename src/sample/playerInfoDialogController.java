package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class playerInfoDialogController {
    @FXML
    private TextField playerNameField;

    @FXML
    private ColorPicker colorPicker;

    public void playerInfo(int i) {
        String name = playerNameField.getText().trim();
        System.out.println(name);
        Paint playerColor = colorPicker.getValue();
        System.out.println(playerColor);

        Controller.circusPlayers.add(new CircusPlayer(name, playerColor));
    }

    public void handleMouseClicked() {
        playerNameField.setText("");
    }
}
