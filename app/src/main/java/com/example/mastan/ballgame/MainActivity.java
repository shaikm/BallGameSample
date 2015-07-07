package com.example.mastan.ballgame;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
    private Button swapBtn;
    private FrameLayout surface;
    private boolean locker=true;
    private Thread thread;
    private BouncingBallView ballView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);



        surface = (FrameLayout) findViewById(R.id.mysurface);

        surface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    // When user touches the screen
                    case MotionEvent.ACTION_DOWN:

                        ballView = new BouncingBallView(getApplicationContext(), (int) event.getX(), (int) event.getY());
                        surface.addView(ballView);
                        // Setting the coordinates on TextView

                }
                return true;
            }
        });
    }

    public void pause(View v)
    {
        for (int i=0; i< surface.getChildCount(); i++)
        {
           if(surface.getChildAt(i) instanceof BouncingBallView)
               ((BouncingBallView)surface.getChildAt(i)).pause();
        }

    }

    public void play(View v)
    {
        for (int i=0; i< surface.getChildCount(); i++)
        {
            if(surface.getChildAt(i) instanceof BouncingBallView)
                ((BouncingBallView)surface.getChildAt(i)).play();
        }
    }


}
