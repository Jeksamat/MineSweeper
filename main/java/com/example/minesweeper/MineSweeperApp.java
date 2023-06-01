package com.example.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MineSweeperApp extends Application {
    static int cellSize = 40;
    static int numX = 10;
    static int numY = 10;
    static Cell[][] grid = new Cell[numX][numY];
    private Scene scene;
    private Parent CreateContent(){
        Pane root = new Pane();
        root.setPrefSize(cellSize * numX,cellSize * numY);
        for(int y = 0; y < numY; y++){
            for(int x = 0; x < numX; x++){
                double isBomb = Math.random()*11;
                if(isBomb > 9){
                    isBomb = 9;
                }else {
                    isBomb = 0;
                }
                Cell cell = new Cell(x,y,(int)isBomb);
                grid[x][y] = cell;
                root.getChildren().add(cell);
            }
        }
        for(int y = 0; y < numY; y++){
            for(int x = 0; x < numX; x++){
                Cell cell = grid[x][y];
                int bombs = (int)Neighbors(cell).stream().filter(nCell -> nCell.value==9).count();
                if(cell.value ==9){
                    continue;
                }
                if(bombs > 0){
                    cell.text.setText(String.valueOf(bombs));
                }
            }
        }
        return root;
    }
    static List<Cell> Neighbors(Cell cell){
        List<Cell> neighbors = new ArrayList<>();
        int[] coordX = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] coordY = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            int dx = coordX[i];
            int dy = coordY[i];

            int relX = cell.x + dx;
            int relY = cell.y + dy;

            if(relX >= 0 && relX < numX && relY >= 0 && relY < numY) {
                neighbors.add(grid[relX][relY]);
            }
        }
        return neighbors;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MineSweeperApp.class.getResource("MineSweeper-view.fxml"));
        MineSweeperController controller = new MineSweeperController();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MineSweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}