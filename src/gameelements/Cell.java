package gameelements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public boolean isAlive;
    private byte numNeighbors;

    public Cell(){
        super(5, 5);
        this.isAlive = false;
        this.setFill(Color.BLACK);
        this.setStroke(Color.BLACK);
    }

    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
        if(this.isAlive){
            this.setFill(Color.GREEN);
        }else{
            this.setFill(Color.BLACK);
        }
    }
    public void setNumNeighbors(byte numNeighbors) {
        this.numNeighbors = numNeighbors;
    }

    public void updateState(byte gameType){
        switch(gameType) {
            //default game of life
            case 1 -> {
                if (this.isAlive && this.numNeighbors < 2) {
                    this.setAlive(false);
                } else if (!this.isAlive && this.numNeighbors == 3) {
                    this.setAlive(true);
                } else if (this.isAlive && this.numNeighbors > 3) {
                    this.setAlive(false);
                }
            }

            //a game of growth
            case 2 -> {
                if (this.isAlive && this.numNeighbors < 2) {
                    this.setAlive(false);
                } else if (!this.isAlive && this.numNeighbors == 2) {
                    this.setAlive(true);
                } else if (this.isAlive && this.numNeighbors > 5) {
                    this.setAlive(false);
                }
            }
        }

    }
}