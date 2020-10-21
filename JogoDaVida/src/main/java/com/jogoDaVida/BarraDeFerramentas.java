package com.jogoDaVida;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class BarraDeFerramentas extends ToolBar {

    private MainView mainView;

    public BarraDeFerramentas(MainView mainView) {
        this.mainView = mainView;
        Button draw = new Button("Inserir");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Apagar");
        erase.setOnAction(this::handleErase);
        Button step = new Button("Simulação de etapa");
        step.setOnAction(this::handleStep);
        Button reset = new Button("Resetar");
        reset.setOnAction(this::handleReset);
        Button start = new Button("Iniciar");
        start.setOnAction(this::handleStart);
        Button stop = new Button("Parar");
        stop.setOnAction(this::handleStop);

        this.getItems().addAll(draw, erase, reset, step, start, stop);
    }

    private void handleStop(ActionEvent actionEvent) {
        this.mainView.getSimulator().stop();
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulator().start();
    }

    private void handleReset(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("Opção de simulação selecionada");

        this.mainView.setApplicationState(MainView.SIMULATING);

        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

    private void handleErase(ActionEvent actionEvent) {
        System.out.println("Opção de apagar célula selecionada");
        this.mainView.setDrawMode(Celula.morta);
    }

    private void handleDraw(ActionEvent actionEvent) {
        System.out.println("Opção de inserir célula selecionada");
        this.mainView.setDrawMode(Celula.viva);
    }

}
