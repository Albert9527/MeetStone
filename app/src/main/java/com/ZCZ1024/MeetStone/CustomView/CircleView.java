package com.ZCZ1024.MeetStone.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


public class CircleView extends AppCompatImageView {

    private Paint mPaint;//画笔

    private int mRadius;//图片半径

    private float mScale;//图片缩放比

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //圆形，保持宽高一致

        int size = Math.min(getMeasuredWidth(),getMeasuredHeight());
        mRadius = size / 2;
        setMeasuredDimension(size,size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        Bitmap bitmap = drawableToBitmp(getDrawable());

        //初始化BitmapShader，传入bitmap对象
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        //就算缩放比例
        mScale = (mRadius * 2.0f ) / Math.min(bitmap.getHeight(),bitmap.getWidth());

        Matrix matrix = new Matrix();
        matrix.setScale(mScale,mScale);
        bitmapShader.setLocalMatrix(matrix);

        mPaint.setShader(bitmapShader);

        //画圆形，指定中心点坐标，半径，画笔
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
    }

    private Bitmap drawableToBitmp(Drawable drawable) {

        if (drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,w,h);
            drawable.draw(canvas);
            return bitmap;

    }
}
