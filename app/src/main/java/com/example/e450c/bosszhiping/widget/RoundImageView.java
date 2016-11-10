package com.example.e450c.bosszhiping.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by e450c on 2016/11/6.
 */
public class RoundImageView extends ImageView {


    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;

    public static final int BORDER_RADIUS_DEFAULT = 10;

    private Paint mPaint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;
    private WeakReference<Bitmap> mWeakBitmap;

    private int mType = TYPE_CIRCLE;
    private int mBorderRadius = BORDER_RADIUS_DEFAULT;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        if (attrs != null) {

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mType == TYPE_CIRCLE) {
            int width = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();

        if (bitmap == null || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();

            int dWidth = drawable.getIntrinsicWidth();
            int dHeight = drawable.getIntrinsicHeight();

            if (drawable != null) {
                bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);

                float scale = 1.0f;

                Canvas drawCanvas = new Canvas(bitmap);

                if (mType == TYPE_ROUND) {
                    scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
                } else if (mType == TYPE_CIRCLE) {
                    scale = getWidth() * 1.0f / Math.min(dWidth, dHeight);
                }

                drawable.setBounds(0, 0, (int) (scale * dWidth), (int) (scale * dHeight));
                drawable.draw(drawCanvas);

                if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                    mMaskBitmap = getBitmap();
                }

                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);
                //绘制形状
                drawCanvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
                mPaint.setXfermode(null);
                //将准备好的bitmap绘制出来
                canvas.drawBitmap(bitmap, 0, 0, null);
                //bitmap缓存起来，避免每次调用onDraw，分配内存
                mWeakBitmap = new WeakReference<Bitmap>(bitmap);
            }
        }

        //如果bitmap还存在，则直接绘制即可
        if (bitmap != null) {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
            return;
        }
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if (mType == TYPE_ROUND) {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mBorderRadius, mBorderRadius, paint);
        } else {
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, mPaint);
        }

        return bitmap;
    }

    @Override
    public void invalidate() {
        mWeakBitmap = null;

        if (mMaskBitmap != null) {
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }
}
