package com.cosc592.shootinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {

    private Game game;
    private double sceneWidth;
    private double sceneHeight;

    public GameView(Context context, Game game) {
        super(context);
        this.game = game;
        this.sceneWidth = 1800;
        this.sceneHeight = 1000;
    }

    public void onDraw(Canvas canvas) {
        //getting values to draw game from model class
        double birdX = game.getBirdX();
        double birdY = game.getBirdY();
        double bulletX = game.getBulletX();
        double bulletY = game.getBulletY();
        double gunX = game.getGunX();
        double gunY = game.getGunY();
        double radius = game.getRadius();

        Paint paint = new Paint();

        //Background
        paint.setColor(Color.parseColor("#E3D9CF"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, (float)sceneWidth, (float)sceneHeight, paint);

        //Bird
        paint.setColor(Color.parseColor("#E5BA4A"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float) birdX, (float) (sceneHeight - birdY), (float) radius, paint);

        //Bullet
        paint.setColor(Color.parseColor("#4B8DB0"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float)bulletX, (float)(sceneHeight - bulletY), (float)radius, paint);

        //Gun
        paint.setColor(Color.parseColor("#4B8DB0"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        canvas.drawLine(0, (float)(sceneHeight - gunY), (float)gunX, (float)(sceneHeight - gunY), paint);
    }
}
