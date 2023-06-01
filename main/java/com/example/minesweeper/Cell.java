package com.example.minesweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends StackPane {
    public int x, y, value;
    boolean isOpen = false;
    static int cellSize = 40;
    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;

        setTranslateX((x*cellSize));
        setTranslateY((y*cellSize));

    }

}
