package uielements;

import gameelements.Board;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class GameControl extends HBox {
    Board gameBoard;

    private Button btPlay;
    private Button btClear;

    public GameControl(Board gameBoard){
        this.gameBoard = gameBoard;
        initButtons();
        getChildren().add(this.btPlay);
        getChildren().add(this.btClear);
    }

    private void initButtons(){
        this.btPlay = new Button("Play/Pause");
        this.btPlay.setOnAction(e -> {
            if(this.gameBoard.getAnimate()){
                this.gameBoard.setAnimate(false);
            } else{
                this.gameBoard.setAnimate(true);
            }
        });

        this.btClear = new Button("Clear");
        this.btClear.setOnAction(e -> this.gameBoard.clearBoard());
    }
}
