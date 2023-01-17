package uielements;

import gameelements.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.util.Duration;



public class GameControl extends HBox {
    Board gameBoard;

    private Button btPlay;
    private Button btClear;
    private ComboBox<String> cbGameType;

    public GameControl(Board gameBoard){
        this.gameBoard = gameBoard;
        initButtons();
        initComboBox();
        initAnimation();
        getChildren().add(this.btPlay);
        getChildren().add(this.btClear);
        getChildren().add(this.cbGameType);
    }

    private void initButtons(){
        this.btPlay = new Button("Play/Pause");

        this.btPlay.setOnAction(e -> this.gameBoard.setAnimate(!this.gameBoard.getAnimate()));

        this.btClear = new Button("Clear");
        this.btClear.setOnAction(e -> this.gameBoard.clearBoard());
    }

    private void initComboBox(){
        this.cbGameType = new ComboBox<>();
        this.cbGameType.getItems().addAll("Default", "Growth");
        this.cbGameType.getSelectionModel().selectFirst();
        this.cbGameType.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{
            switch (newVal) {
                case "Default" -> gameBoard.setGameType((byte) 1);
                case "Growth" -> gameBoard.setGameType((byte) 2);
                default -> {
                    System.out.println("Game type not valid");
                    gameBoard.setGameType((byte) 1);
                }
            }
        });
    }

    private void initAnimation(){
        Timeline tlGameLoop = new Timeline();
        tlGameLoop.setCycleCount(Timeline.INDEFINITE);
        tlGameLoop.getKeyFrames().add(new KeyFrame(Duration.millis(100), actionEvent -> this.gameBoard.updateBoard()));
        tlGameLoop.play();
    }
}
