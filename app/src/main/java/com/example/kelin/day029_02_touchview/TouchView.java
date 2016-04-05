package com.example.kelin.day029_02_touchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kelin on 2016/3/29.
 */
public class TouchView extends View {
    private Paint paint;
    private Path path;

    public TouchView(Context context) {
        this(context,null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        if (attrs == null) {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
        }else {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TouchView);
            int color = typedArray.getColor(R.styleable.TouchView_paintColor, Color.BLACK);
            setPaintColor(color);
            float dimension = typedArray.getDimension(R.styleable.TouchView_paintWidth, 5);
            setPaintWidth(dimension);
        }

    }

    public void setPaintColor(int color){
        paint.setColor(color);
    }

    public void setPaintWidth(float width){
        paint.setStrokeWidth(width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
        //取到一个动作。单点触摸时使用,只用四种动作
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        //多点触摸时使用
        int masked = event.getActionMasked();
        switch (masked) {
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
        //手指落在控件上点的个数
        int pointerCount = event.getPointerCount();
        /**
         * 点的坐标再当前控件的坐标系下，会根据当前view 的缩放比和旋转进行变化。。
         *一般是以当前view 的左上角为0点
         /
        //单点操控手指落在屏幕上的坐标
        float x = event.getX();
        float x1 = event.getX(0);//多点操控时第几个点的坐标
        //获得屏幕上的绝对坐标值
        float rawX = event.getRawX();
        //手指在屏幕上点击的区域
        float size = event.getSize();
        float size1 = event.getSize(0);//多点
        */
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:
                //up和cancel的处理方式是一样的
//                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        invalidate();
        return true;


    }


}
