package com.jogoDaVida;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulador {

    private Timeline timeline;
    private MainView mainView;
    private Celula celula;

    public Simulador(MainView mainView, Celula celula) {
        this.mainView = mainView;
        this.celula = celula;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void doStep(ActionEvent actionEvent) {
        this.celula.step();
        this.mainView.draw();
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }
}
