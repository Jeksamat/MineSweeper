package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MineSweeperController {

    @FXML
    private GridPane gameGrid;
    Text text = new Text();
    Rectangle rectangle = new Rectangle(MineSweeperApp.cellSize-2, MineSweeperApp.cellSize-2);

    public void initialize() {
        // Generate cells and add them to the game grid
        for (int y = 0; y < MineSweeperApp.numY; y++) {
            for (int x = 0; x < MineSweeperApp.numX; x++) {
                Cell cell = MineSweeperApp.grid[x][y];
                Rectangle cellRect = createCellRectangle(cell);
                Text cellText = createCellText(cell);

                // Add cell components to the grid pane
                gameGrid.add(cellRect, x, y);
                gameGrid.add(cellText, x, y);
            }
        }
    }

    private Rectangle createCellRectangle(Cell cell) {
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        return rectangle;
    }

    private Text createCellText(Cell cell) {
        text.setFill(Color.BLACK);
        text.setFont(Font.font(18));
        text.setText(getCellText(cell));
        text.setVisible(false);
        return text;
    }

    private String getCellText(Cell cell) {
        if (cell.value == 9) {
            return "X"; // Bomb
        } else {
            return String.valueOf(cell.value);
        }
    }
    public void open(Cell cell){
        if(cell.isOpen){
            return;
        }
        if(cell.value == 9){
            System.out.println("Game Over");
            return;
        }

        cell.isOpen = true;
        text.setVisible(true);
        rectangle.setFill(null);
        if (text.getText().isEmpty()) {
            for (Cell neighbor : MineSweeperApp.Neighbors(cell)) {
                open(neighbor);
            }
        }
    }
}