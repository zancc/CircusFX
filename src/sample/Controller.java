package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    public static ArrayList<CircusPlayer> circusPlayers = new ArrayList<>();
    public static int numberOfPlayer;

    @FXML
    private BorderPane mainBorderPane;


    @FXML
    public void showNewGameDialog() {
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
            controller.playerInfo();
        }
    }




    @FXML
    public void handleExit() {
        Platform.exit();
    }

}
