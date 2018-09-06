package com.amycohen.lab42conwaygameoflife;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
    implements ViewTreeObserver.OnGlobalLayoutListener,
    View.OnTouchListener {

    @BindView(R.id.canvasView)
    public ImageView imageView;
    @BindView(R.id.value)
    public TextView valueDisplay;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    public SquareDrawingEngine engine = new SquareDrawingEngine();

    private float xDown;
    private float yDown;

    private float xUp;
    private float yUp;

    private float xMove;
    private float yMove;

    int SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //From Steve's lecture
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        float xx = motionEvent.getX();
        float yy = motionEvent.getY();

        //played with it some and decided on Math.floor so I could get a 0 index shown. With everything as it shows now, I'm getting a 20x20 grid with a 0 through 19 index.
        String line1 = "size: " + engine.getSize() + " x: " + ((int) Math.floor(xx)) + " y: " + ((int) Math.floor(yy));
        String line2 = "Col X = " + ((int) Math.floor(xx / engine.getSize())) + " \n Row Y = " + ((int) Math.floor(yy / engine.getSize()));

        //just getting coordinates so we can see them
        valueDisplay.setText(line1 + "\n" + line2);

        if (action == MotionEvent.ACTION_DOWN) {
            Log.d("ACTION", "down");
            xDown = xx;
            yDown = yy;

            return true;

        } else if (action == MotionEvent.ACTION_UP) {
            Log.d("ACTION", "up");
            xUp = xx;
            yUp = yy;

            return true;

        } else if (action == MotionEvent.ACTION_MOVE) {
            Log.d("ACTION", "move");
            xMove = xx;
            yMove = yy;
        }

        return false;
    }

    @Override
    public void onGlobalLayout() {
        initBitmap();
//        engine.doesHaveNeighbors();
    }

    public void initBitmap() {
        imageView.setOnTouchListener(this);

        int width = imageView.getWidth();
        int height = imageView.getHeight();

        Log.d("DIMENSIONS", "" + width + "x" + height + "y");

        imageView.setImageBitmap(engine.drawGrid(width, height));
    }

}