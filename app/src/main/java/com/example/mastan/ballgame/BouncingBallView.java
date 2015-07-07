package com.example.mastan.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;

import java.util.Random;

/**
 * Created by Mastan on 06-07-2015.
 */
public class BouncingBallView extends View {
    private int xMin = 0;          // This view's bounds
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private float ballRadius = 40; // Ball's radius
    private float ballX ;  // Ball's center (x,y)
    private float ballY ;
    private float ballSpeedX = 30;  // Ball's speed (x,y)
    private float ballSpeedY = 30;
    private RectF ballBounds;      // Needed for Canvas.drawOval
    private Paint paint;           // The paint (e.g. style, color) used for drawing
    private boolean direction;
    Random rnd = new Random();
    public BouncingBallView(Context context, int x, int y) {
        super(context);
        ballBounds = new RectF();
        paint = new Paint();
        ballY = y;
        ballX = x;
        direction = getRandomBoolean(rnd);
    }

    private boolean getRandomBoolean(Random rnd) {
        return rnd.nextBoolean();
    }

    // Called back to draw the view. Also called by invalidate().
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the ball
        ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
        paint.setColor(Color.GREEN);
        canvas.drawOval(ballBounds, paint);

        // Update the position of the ball, including collision detection and reaction.
        update();

        try {
            Thread.sleep(60);
        } catch (InterruptedException e) { }

        invalidate();  // Force a re-draw
    }

    // Detect collision and update the position of the ball.
    private void update() {

        if(direction){
            ballX += ballSpeedX;
        }else {
            ballY += ballSpeedY;
        }

        // Detect collision and react
        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
        } else if (ballX - ballRadius < xMin) {
            ballSpeedX = -ballSpeedX;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = -ballSpeedY;
        }
    }


    private float randomInRange(float min, float max) {
        float range = max - min;
        float scaled = rnd.nextFloat() * range;
        float shifted = scaled + min;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }

    // Called back when the view is first created or its size changes.
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

         xMax = MeasureSpec.getSize(widthMeasureSpec);
         yMax = MeasureSpec.getSize(heightMeasureSpec);

    }
}