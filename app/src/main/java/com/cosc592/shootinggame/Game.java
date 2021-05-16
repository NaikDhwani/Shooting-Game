package com.cosc592.shootinggame;

import android.media.SoundPool;

public class Game {
    private double birdX;
    private double birdY;
    private double birdSpeed;

    private double bulletX;
    private double bulletY;
    private double bulletSpeed;

    private double gunX;
    private double gunY;

    private double radius;
    private double distanceThreshold;

    private boolean fired;
    private boolean hit;

    private SoundPool soundPool;
    private int soundId;

    public Game(SoundPool soundPool, int soundId)
    {
        this.soundPool = soundPool;
        this.soundId = soundId;
        initializeGame();
    }

    private void initializeGame()
    {
        double sceneWidth = 1800;
        double sceneHeight = 1000;

        this.birdX = sceneWidth - 50 - 1300 * Math.random();
        this.birdY = sceneHeight - 50;
        this.birdSpeed = 5 + 10 * Math.random();

        this.gunX = 200;
        this.gunY = sceneHeight - 300 - 200 * Math.random();

        this.bulletX = gunX;
        this.bulletY = gunY;
        this.bulletSpeed = 20;

        this.radius = 50;
        this.distanceThreshold = 100;

        this.fired = false;
        this.hit = false;
    }

    //Call by timer task class
    public void update() {
        moveBird();

        if (fired)
            moveBullet();

        if (sceneClear())
            initializeGame();
    }

    private void moveBird() {
        if (!hit) {
            birdY = birdY - birdSpeed;
            hit = decideHit();
            if (hit)
                soundPool.play(soundId, 1, 1, 1, 0, 1);
        }else
            birdY = 0;
    }

    public boolean decideHit() {
        double distance = Math.sqrt((birdX - bulletX)*(birdX - bulletX) +
                (birdY - bulletY)*(birdY - bulletY));
        return distance <= distanceThreshold;
    }

    private void moveBullet()
    {
        bulletX = bulletX + bulletSpeed;
    }

    private boolean sceneClear() {
        return (birdY == 0 || birdY <=1000) && bulletX > 1800;
    }

    public void fire() {
        fired = true;
    }

    public double getBirdX()
    {
        return birdX;
    }

    public double getBirdY()
    {
        return birdY;
    }

    public double getBulletX()
    {
        return bulletX;
    }

    public double getBulletY()
    {
        return bulletY;
    }

    public double getGunX()
    {
        return gunX;
    }

    public double getGunY()
    {
        return gunY;
    }

    public double getRadius()
    {
        return radius;
    }
}
