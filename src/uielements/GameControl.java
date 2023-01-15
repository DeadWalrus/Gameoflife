package uielements;

import gameelements.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class GameControl extends HBox {
    Board gameBoard;

    private Button btPlay;
    private Button btClear;
    private ComboBox<String> cbGameType;
    private Timeline tlGameLoop;
    private byte gameType;

    public GameControl(Board gameBoard){
        this.gameBoard = gameBoard;
        this.gameType = 1;
        initButtons();
        initComboBox();
        getChildren().add(this.btPlay);
        getChildren().add(this.btClear);
        getChildren().add(this.cbGameType);
        this.tlGameLoop = new Timeline();
        this.tlGameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    private void initButtons(){
        this.btPlay = new Button("Play/Pause");

        this.btPlay.setOnAction(e -> {
            if (this.cbGameType.getSelectionModel().getSelectedItem().equals("Default")) {
                this.tlGameLoop.stop();
                this.tlGameLoop.getKeyFrames().clear();
                this.tlGameLoop.getKeyFrames().add(new KeyFrame(Duration.millis(100), actionEvent -> gameBoard.updateBoard((byte) 1)));
                this.tlGameLoop.play();
            } else {
                this.tlGameLoop.stop();
                this.tlGameLoop.getKeyFrames().clear();
                this.tlGameLoop.getKeyFrames().add(new KeyFrame(Duration.millis(100), actionEvent -> gameBoard.updateBoard((byte) 2)));
                this.tlGameLoop.play();
            }

            this.gameBoard.setAnimate(!this.gameBoard.getAnimate());
        });
        this.btClear = new Button("Clear");
        this.btClear.setOnAction(e -> this.gameBoard.clearBoard());
    }

    private void initComboBox(){
        this.cbGameType = new ComboBox<>();
        this.cbGameType.getItems().addAll("Default", "Growth");
        this.cbGameType.getSelectionModel().selectFirst();
    }
}
