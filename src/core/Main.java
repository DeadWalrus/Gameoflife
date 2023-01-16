package core;

import gameelements.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uielements.GameControl;

public class Main extends Application{

    public static void main(String[] args){
        System.out.println("Hello");
        launch(args);
    }

    @Override
    public void start(Stage stage){
        BorderPane mainPane = new BorderPane();
        mainPane.setPrefSize(500, 530);
        Board gameBoard = new Board(100, 100);
        mainPane.setCenter(gameBoard);
        mainPane.setBottom(new GameControl(gameBoard));
        Scene mainScene = new Scene(mainPane);
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.setTitle("Conway's Game of Life");
        stage.show();
    }
}
