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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
    implements ViewTreeObserver.OnGlobalLayoutListener,
    View.OnTouchListener {

    @BindView(R.id.canvasView) public ImageView imageView;

    private Bitmap mBitmap;
    private Canvas mCanvas;

//    Not sure what it will be called yet, but supposed to use it
    private SquareDrawingEngine engine;

    private float xDown;
    private float yDown;

    private float xUp;
    private float yUp;

    private float xMove;
    private float yMove;

    int SIZE;
    boolean[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        int size = 20;
        cells = new boolean[size][size];
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = Math.random() < .5;
            }
        }

        //From Steve's lecture
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        float xx = event.getX();
        float yy = event.getY();

        //just getting coordinates so we can see them
//        valueDisplay.setText("size: " + SIZE + "x: " + (int)xx + " y: " + (int)yy);
        drawGrid();

        if (action == MotionEvent.ACTION_DOWN) {
            xDown = xx;
            yDown = yy;

            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            xUp = xx;
            yUp = yy;

//            drawAll();
            return true;
        } else if (action == MotionEvent.ACTION_MOVE)
            xMove = xx;
            yMove = yy;

        return false;
    }

    @Override
    public void onGlobalLayout() {
        initBitmap();
    }

    public void initBitmap () {
        imageView.setOnTouchListener(this);

        int width = imageView.getWidth();
        int height = imageView.getHeight();

        Log.d("DIMENSIONS", "" + width + "x" + height + "y");

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        drawGrid();
    /*
        Testing code:
        Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        brush.setColor(Color.BLUE);
        mCanvas.drawRect(50, 50, 50, 50, brush);
     */
    }

//    public void drawAll() {
//        engine.drawAll(mCanvas);
//        imageView.setImageBitmap(mBitmap);
//    }


    //from steve's mini-lecture

    public void drawGrid() {
        int height = imageView.getHeight();
        int width = imageView.getWidth();
        int smallest = Math.min(width, height);
        int size = smallest/cells.length;

        float x0 = 0;
        float y0 = 0;

        float x1 = size;
        float y1 = size;


        for (int row = 0; row < cells.length; row++) {
            x0 = 0;
            x1 = size;

            for (int col = 0; col < cells[row].length; col++) {
                int color;

                if (cells[row][col] == true) {
                    //Steve has this one white
                    color = Color.BLACK;
                } else {
                    color = Color.WHITE;
                }

                Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
                brush.setColor(color);
                mCanvas.drawRect(x0, y0, x1, y1, brush);

                //update to the next column
                x0 += size;
                x1 += size;
            }

            //update the row
            y0 += size;
            y1 += size;
        }
        imageView.setImageBitmap(mBitmap);
    }


}
