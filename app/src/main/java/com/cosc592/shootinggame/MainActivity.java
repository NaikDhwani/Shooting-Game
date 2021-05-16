package com.cosc592.shootinggame;

import androidx.appcompat.app.AppCompatActivity;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Game game;
    private GameView gameView;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoundPool.Builder soundPoolBuilder = new SoundPool.Builder();
        SoundPool soundPool = soundPoolBuilder.build();
        int soundId = soundPool.load(this, R.raw.explosion, 1);

        game = new Game(soundPool, soundId);
        gameView = new GameView(this, game);
        setContentView(gameView);

        Timer timer = new Timer();
        GameTimerTask task = new GameTimerTask(game, gameView);
        timer.schedule(task, 2000, 20);

        TouchHandler temp = new TouchHandler();
        gestureDetector = new GestureDetector(this, temp);
    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            game.fire();
            return true;
        }
    }
}
