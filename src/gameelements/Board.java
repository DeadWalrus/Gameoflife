package gameelements;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board extends Pane{
    private Cell[][] cells;
    private int width;
    private int height;
    private boolean animate = false;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        this.cells = new Cell[this.width][this.height];
        populateCells();
        addCells();
    }

    private void populateCells(){
        for(int i = 0; i < this.cells.length; i++){
            for(int j = 0; j < this.cells[0].length; j++){
                Cell cell = new Cell();
                cell.setOnMouseEntered(e ->{
                    cell.setStroke(Color.BLUE);
                    if(e.isShiftDown()){
                        if(!cell.isAlive){
                            cell.setAlive(true);
                        }
                    } else if(e.isControlDown()){
                        if(cell.isAlive){
                            cell.setAlive(false);
                        }
                    }
                });
                cell.setOnMouseExited(e ->{
                   cell.setStroke(Color.BLACK);
                });
                cell.setOnMouseClicked(e ->{
                    if(cell.isAlive()){
                        cell.setAlive(false);
                    } else{
                        cell.setAlive(true);
                    }
                });
                this.cells[i][j] = cell;
            }
        }
    }
    private void addCells(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                Cell cell = this.cells[i][j];
                cell.setLayoutX(i * cell.getWidth());
                cell.setLayoutY(j * cell.getHeight());
                getChildren().add(cell);
            }
        }
    }

    public void setAnimate(boolean animate){
        this.animate = animate;
    }
    public boolean getAnimate(){
        return this.animate;
    }
    public void updateBoard(){
        if(this.animate) {
            for (int i = 0; i < this.cells.length; i++) {
                for (int j = 0; j < this.cells[0].length; j++) {
                    Cell cell = this.cells[i][j];
                    if (cell.isAlive) {
                        cell.setFill(Color.GREEN);
                    }
                }
            }
            this.calculateCellNeighborsAndUpdateState();
        }
    }

    public void clearBoard(){
        this.animate = false;
        for(int i = 0; i < this.cells.length; i++){
            for(int j = 0; j < this.cells[0].length; j++){
                Cell cell = this.cells[i][j];
                cell.setAlive(false);
            }
        }
    }

    private void calculateCellNeighborsAndUpdateState(){
        for(int i = 0; i < this.cells.length; i++){
            for(int j = 0; j < this.cells[0].length; j++){
                Cell cell = this.cells[i][j];
                byte Neighbors = 0;
                    for(int g = -1; g <= 1; g++){
                        for(int h = -1; h <= 1; h++){
                            if(g == 0 && h == 0){
                                continue;
                            }
                            if((i + g < 0 || i + g >= this.width) || j + h < 0 || j + h >= this.height){
                                continue;
                            }
                            if(cells[i+g][j+h].isAlive){
                                Neighbors++;
                            }
                        }
                    }
                    cell.setNumNeighbors(Neighbors);
            }
        }
        updateCellStates();
    }
    private void updateCellStates(){
        for(int i = 0; i < this.cells.length; i++){
            for(int j = 0; j < this.cells[0].length; j++){
                this.cells[i][j].updateState();
            }
        }
    }
}
