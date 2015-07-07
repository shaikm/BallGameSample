package com.example.mastan.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;
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
    private float ballSpeedX = 20;  // Ball's speed (x,y)
    private float ballSpeedY = 20;
    private RectF ballBounds;      // Needed for Canvas.drawOval
    private Paint paint;           // The paint (e.g. style, color) used for drawing

    private Handler h;
    public BouncingBallView(Context context, int x, int y) {
        super(context);
        ballBounds = new RectF();
        paint = new Paint();
        ballY = y;
        ballX = x;
        h = new Handler();
    }


    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    // Called back to draw the view.
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the ball
        ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
        paint.setColor(Color.GREEN);
        canvas.drawOval(ballBounds, paint);

        // Update the position of the ball, including collision detection and reaction.
        update();

        h.postDelayed(r, 30);
    }

    // Detect collision and update the position of the ball.
    private void update() {

            ballX += ballSpeedX;
            ballY += ballSpeedY;

        // Detect collision and react
        if (ballX + ballRadius > xMax || ballX < 0) {
            ballSpeedX = ballSpeedX*-1;
        } else if (ballX - ballRadius < xMin) {
            ballSpeedX = ballSpeedX*-1;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = ballSpeedY*-1;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = ballSpeedY*-1;
        }
    }

    // Called back when the view is first created or its size changes.
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

         xMax = MeasureSpec.getSize(widthMeasureSpec);
         yMax = MeasureSpec.getSize(heightMeasureSpec);

    }


    public  void pause() {
        h.removeCallbacks(r);
    }
    public void play()
    {
        h.postDelayed(r,30);
    }
}