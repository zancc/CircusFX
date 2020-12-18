package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class playerInfoDialogController {
    @FXML
    private TextField playerNameField;

    @FXML
    private ColorPicker colorPicker;

    public void playerInfo() {
        String name = playerNameField.getText().trim();
        Color playerColor = colorPicker.getValue();

        Controller.circusPlayers.add(new CircusPlayer(name, playerColor));
    }


    public void handleMouseClicked() {
        playerNameField.setText("");
    }
}
