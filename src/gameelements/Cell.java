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
        switch(gameType){
            case 1:
                if(this.numNeighbors < 2){
                    this.setAlive(false);
                } else if(this.numNeighbors == 3 && !this.isAlive){
                    this.setAlive(true);
                } else if(this.numNeighbors > 3 && this.isAlive){
                    this.setAlive(false);
                }
                break;
            case 2:
                if(this.numNeighbors < 2){
                    this.setAlive(false);
                } else if(this.numNeighbors == 3 && !this.isAlive){
                    this.setAlive(true);
                } else if(this.numNeighbors > 5 && this.isAlive){
                    this.setAlive(false);
                }
        }

    }
}