package com.apps.akhilsreekar.frontend;

import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Zoomer extends MainActivity implements View.OnTouchListener {

    Matrix matrix = new Matrix();
    float scale = 1f;
    ScaleGestureDetector SGD;
    TextView tv;
    ImageView iv;
    static MyCustomAdapter.ViewHolder hold;

    protected void set(MyCustomAdapter.ViewHolder holder,View v) {
        hold = holder;
        SGD = new ScaleGestureDetector(v.getContext(), new ScaleListener());
        Log.d("test", holder.tvGameName.toString());
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("Tons","tintin");
            scale = scale * detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5f));
            matrix.setScale(scale, scale);
            hold.imgGame.setImageMatrix(matrix);
            Log.d("test2", hold.tvGameName.toString());
            hold.tvGameName.setTextSize(tv.getTextSize() * scale);
            return true;
        }
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }
}
