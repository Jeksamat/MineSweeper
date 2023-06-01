package com.example.minesweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends StackPane {
    public int x, y, value;
    private boolean isOpen = false;
    Text text = new Text();
    static int cellSize = 40;
    private Rectangle border = new Rectangle(cellSize-2, cellSize-2);
    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;

        border.setStroke(Color.BLACK);
        text.setFill(Color.BLACK);
        text.setFont(Font.font(18));
        text.setText(value == 9 ? "X" : "");
        text.setVisible(false);

        getChildren().addAll(border, text);
        setTranslateX((x*cellSize));
        setTranslateY((y*cellSize));

        setOnMouseClicked(e -> open()); //NEED EXPLANATION
    }
    public void open(){
        if(isOpen){
            return;
        }
        if(value == 9){
            System.out.println("Game Over");
            return;
        }

        isOpen = true;
        text.setVisible(true);
        border.setFill(null);
        if(text.getText().isEmpty()){
            MineSweeperApp.Neighbors(this).forEach(Cell::open);
        }
    }
}
