package gameelements;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board extends Pane{
    private Cell[][] cells;
    private int width;
    private int height;
    private boolean animate = false;
    private byte gameType;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        this.cells = new Cell[this.width][this.height];
        this.gameType = 1;
        populateCells();
        addCells();

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

    public void setGameType(byte gameType) throws IllegalArgumentException{
        if(gameType != 1 && gameType != 2){
            this.gameType = 1;
            throw new IllegalArgumentException("Argument '" + gameType + "' is not valid. Default game type used");
        } else{
            this.gameType = gameType;
        }
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


    private void calculateCellNeighborsAndUpdateState(){
        for(int x = 0; x < this.cells.length; x++){
            for(int y = 0; y < this.cells[0].length; y++){
                Cell cell = this.cells[x][y];
                byte neighbors = 0;
                    //check if index is out of bounds, and wrap around grid if so
                    for(int dx = -1; dx <= 1; dx++){
                        for(int dy = -1; dy <= 1; dy++){
                            if(dx == 0 && dy == 0){
                                continue;
                            }

                            int nx = x + dx; //used for wrapping
                            int ny = y + dy;

                            if(nx < 0){
                                nx = this.width-1;
                                System.out.println("1 " + nx);
                            }else if(nx >= this.width){
                                nx = this.width / nx - 1;
                                System.out.println("2" + nx);
                            }

                            if(ny < 0){
                                ny = this.height-1;
                                System.out.println("1" + ny);
                            } else if(ny >= this.height){
                                ny = this.height / ny - 1;
                                System.out.println("2" + ny);
                            }

                            if(cells[nx][ny].isAlive){
                                neighbors++;
                            }
                        }
                    }
                    cell.setNumNeighbors(neighbors);
            }
        }
        updateCellStates();
    }
    private void updateCellStates(){
        for(int i = 0; i < this.cells.length; i++){
            for(int j = 0; j < this.cells[0].length; j++){
                this.cells[i][j].updateState(this.gameType);
            }
        }
    }
}
