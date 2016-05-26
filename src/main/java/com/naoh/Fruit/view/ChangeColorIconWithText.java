package com.naoh.Fruit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * Created by NaOH on 2016/4/13.
 */
public class ChangeColorIconWithText extends View {

    private static final int DEFAULT_ICON_COLOR = 0xFF45C01A;
    private static final int DEFAULT_TEXT_COLOR = 0xff333333;
    private static final String DEFAULT_TEXT = "默认";
    private static final int DEFAULT_TEXT_SIZE = 12;

    private int mColor = DEFAULT_ICON_COLOR;
    private Bitmap mIconBitmap;
    private String mText = DEFAULT_TEXT;
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics());

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;
    private float mAlpha;
    private Rect mIconRect;
    private Rect mTextBound;
    private Paint mTextPaint;

    public ChangeColorIconWithText(Context context) {
        this(context, null);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedValue = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);
        for (int i = 0; i < typedValue.getIndexCount(); i++) {
            int attr = typedValue.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorIconWithText_color:
                    mColor = typedValue.getColor(attr, DEFAULT_ICON_COLOR);
                    break;
                case R.styleable.ChangeColorIconWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable) typedValue.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconWithText_text:
                    mText = typedValue.getString(i);
                    break;
                case R.styleable.ChangeColorIconWithText_text_size:
                    mTextSize = (int) typedValue.getDimension(attr,
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE,
                                    getResources().getDisplayMetrics()));
                    break;

            }
        }
        typedValue.recycle();

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(DEFAULT_TEXT_COLOR);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_ALPHA = "status_alpha";

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA, mAlpha);
        return bundle;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight()
                - getPaddingTop() - getPaddingBottom()
                - mTextBound.height());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - mTextBound.height() / 2 - iconWidth / 2;

        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);

        int alpha = (int) Math.ceil(255 * mAlpha);
        //内存去准备mBitmap，setAlpha，纯色，xfermode，图标
        setupTargetBitmap(alpha);

        //1.绘制原文本2.绘制变色的文本
        drawSourceText(mCanvas, alpha);
        drawTargetText(mCanvas, alpha);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    /**
     * 绘制变色的文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 绘制原文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(DEFAULT_TEXT_COLOR);
        mTextPaint.setAlpha(255 - alpha);
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 内存去准备mBitmap，setAlpha，纯色，xfermode，图标
     * @param alpha
     */
    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
    }

    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    //重绘
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
