package com.apps.akhilsreekar.frontend;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by AKHIL on 29-09-2016.
 */
public class MainActivity extends Activity implements View.OnTouchListener{
    public static RecyclerView recyclerView;
    MyCustomAdapter adapter;
    public static MainActivity mMainActivity;
    public static int TYPE;

    Matrix matrix;
    float scale = 1f;
    ScaleGestureDetector SGD;
    CardView card;
    MyCustomAdapter.ViewHolder zooper;
    TextView[] tv = new TextView[MyCustomAdapter.holding.size()];
    ImageView[] iv = new ImageView[MyCustomAdapter.holding.size()];
    //ArrayList<TextView> tv = new ArrayList<TextView>();
    //ArrayList<ImageView> iv = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TYPE = MyCustomAdapter.LIST;
        mMainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyCustomAdapter(this, Data.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

//        card = (CardView) findViewById(R.id.card);



        for(int i=0;i<MyCustomAdapter.holding.size();i++){
            tv[i] = MyCustomAdapter.holding.get(i).tvGameName;
            iv[i] = MyCustomAdapter.holding.get(i).imgGame;
        }
        matrix = new Matrix();
        SGD = new ScaleGestureDetector(this.getBaseContext() ,new ScaleListener());
        Log.d("asdf","asdf");

        //Data.getData().get(0).imageId;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale*detector.getScaleFactor();
            scale = Math.max(0.1f,Math.min(scale,5f));
            matrix.setScale(scale,scale);
            for(int i=0;i<MyCustomAdapter.holding.size();i++) {
                iv[i].setImageMatrix(matrix);
                tv[i].setTextSize(24 * scale);
            }
            return true;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }



    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
