package com.jogoDaVida;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class BarraMenu extends HBox {

    private static String drawModeFormat = "Ação selecionada: %s";
    private static String cursorPosFormat = "Cursor: (%d, %d)";

    private Label cursor;
    private Label editingTool;

    public BarraMenu() {

        this.editingTool = new Label();
        this.cursor = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(this.editingTool, spacer, this.cursor);

    }

    public void setDrawMode(int drawMode) {
        String drawModeString;
        if (drawMode == Celula.viva) {
            drawModeString = "Inserindo";
        } else {
            drawModeString = "Apagando";
        }

        this.editingTool.setText(String.format(drawModeFormat, drawModeString));
    }

    public void setCursorPosition(int x, int y) {
        this.cursor.setText(String.format(cursorPosFormat, x, y));
    }

}
