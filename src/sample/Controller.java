package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.File;

public class Controller {

    @FXML
    private GridPane dices;

    public void dices() {
        ImageView pic = new ImageView();
        pic.setFitWidth(130);
        pic.setFitHeight(130);
        pic.setImage(new Image("dice1.jpg"));
        dices.getChildren().add(pic);
    }

    public void initialize() {
        dices();
    }





    }
