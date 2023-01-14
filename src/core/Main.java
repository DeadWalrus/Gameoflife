package core;
import gameelements.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import uielements.GameControl;

public class Main extends Application{

    public static void main(String[] args){
        System.out.println("Hello");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        mainPane.setPrefSize(500, 525);
        Board gameBoard = new Board(100, 100);
        mainPane.setCenter(gameBoard);
        mainPane.setBottom(new GameControl(gameBoard));
        Scene mainScene = new Scene(mainPane);
        stage.setScene(mainScene);
        stage.setTitle("Conway's Game of Life");
        stage.show();
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.millis(100), actionEvent -> gameBoard.updateBoard()));
        gameLoop.play();
    }
}
