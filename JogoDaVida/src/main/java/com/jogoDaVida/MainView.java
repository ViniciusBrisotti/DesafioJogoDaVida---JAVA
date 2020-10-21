package com.jogoDaVida;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {

    public static final int EDITING = 0;
    public static final int SIMULATING = 1;

    private BarraMenu barraMenu;
    private Canvas canvas;

    private Affine affine;

    private Celula celula;
    private Celula initialCelula;

    private Simulador simulador;

    private int drawMode = Celula.viva;

    private int applicationState = EDITING;

    public MainView() {
        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleMoved);

        this.setOnKeyPressed(this::onKeyPressed);

        BarraDeFerramentas barraDeFerramentas = new BarraDeFerramentas(this);

        this.barraMenu = new BarraMenu();
        this.barraMenu.setDrawMode(this.drawMode);
        this.barraMenu.setCursorPosition(0, 0);

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(barraDeFerramentas, this.canvas, spacer, barraMenu);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.initialCelula = new Celula(10, 10);
        this.celula = Celula.copy(this.initialCelula);
    }

    private void handleMoved(MouseEvent mouseEvent) {
        Point2D simCoord = this.getSimulationCoordinates(mouseEvent);

        this.barraMenu.setCursorPosition((int) simCoord.getX(), (int) simCoord.getY());
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.drawMode = Celula.viva;
            System.out.println("Modo de inserção");
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.drawMode = Celula.morta;
            System.out.println("Modo de apagar");
        }
    }

    private void handleDraw(MouseEvent event) {

        if (this.applicationState == SIMULATING) {
            return;
        }

        Point2D simCoord = this.getSimulationCoordinates(event);

        int simX = (int) simCoord.getX();
        int simY = (int) simCoord.getY();

        System.out.println(simX + ", " + simY);

        this.initialCelula.setState(simX, simY, drawMode);
        draw();
    }

    private Point2D getSimulationCoordinates(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            return simCoord;
        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException("Non invertible transform");
        }
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        if (this.applicationState == EDITING) {
            drawSimulation(this.initialCelula);
        } else {
            drawSimulation(this.celula);
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.celula.largura; x++) {
            g.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y <= this.celula.altura; y++) {
            g.strokeLine(0, y, 10, y);
        }

    }

    private void drawSimulation(Celula celulaToDraw) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        for (int x = 0; x < celulaToDraw.largura; x++) {
            for (int y = 0; y < celulaToDraw.altura; y++) {
                if (celulaToDraw.getState(x, y) == Celula.viva) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }

    public Celula getSimulation() {
        return this.celula;
    }

    public void setDrawMode(int newDrawMode) {
        this.drawMode = newDrawMode;
        this.barraMenu.setDrawMode(newDrawMode);
    }

    public void setApplicationState(int applicationState) {
        if (applicationState == this.applicationState) {
            return;
        }

        if (applicationState == SIMULATING) {
            this.celula = Celula.copy(this.initialCelula);
            this.simulador = new Simulador(this, this.celula);
        }

        this.applicationState = applicationState;

        System.out.println("Status da aplicação: " + this.applicationState);
    }

    public Simulador getSimulator() {
        return simulador;
    }
}
