package com.cosc592.shootinggame;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    private Game game;
    private GameView gameView;

    public GameTimerTask(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        game.update();
        gameView.postInvalidate();
    }
}
