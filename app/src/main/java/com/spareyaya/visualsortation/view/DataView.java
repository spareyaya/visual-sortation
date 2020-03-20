package com.spareyaya.visualsortation.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2019/9/20.
 */
public class DataView extends View implements Animator.AnimatorListener {

    private int defaultWidth = 200;
    private int defaultHeight = 150;
    private int barColor = Color.parseColor("#FF9800");
    private int dividerColor = Color.parseColor("#889E9E9E");

    private ArrayList<int[]> dataFrames;
    private int maxData = 10; // 最大数据默认为300，画柱状时以此为标准计算高度
    private Paint axisPaint;
    private Paint barPaint;
    private Paint dividerPaint;

    private int offset;
    private ObjectAnimator oa;
    private boolean isRunning;

    public DataView(Context context) {
        this(context, null);
    }

    public DataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        axisPaint = new Paint();
        axisPaint.setStyle(Paint.Style.STROKE);
        axisPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(2);

        barPaint = new Paint();
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        barPaint.setColor(barColor);

        dividerPaint = new Paint();
        dividerPaint.setStyle(Paint.Style.STROKE);
        dividerPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        dividerPaint.setColor(dividerColor);
        dividerPaint.setPathEffect(new DashPathEffect(new float[]{10, 5}, 0));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = defaultWidth;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = defaultHeight;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("DataView", "onDraw");
        int width = getWidth();
        int height = getHeight();

        float availableWidth = width * 0.98F;
        float availableHeight = height * 0.98F;

        canvas.save();

        canvas.translate(0, height);

        // 画坐标轴
        canvas.drawLine(0, 0, width, 0, axisPaint);
        canvas.drawLine(0, 0, 0, -height, axisPaint);

        // 画数据条
        int dataCounts = dataFrames == null ? 0 : dataFrames.size();
        if (dataCounts > 0) {
            int size = dataFrames.get(offset).length;
            float barWidth = availableWidth / size;
            barPaint.setStrokeWidth(barWidth * 0.9F);
            float barHeight;
            float scale = availableHeight / maxData;
            float dx;
            for (int i = 0; i < size; i++) {
                barHeight = dataFrames.get(offset)[i] * scale;
                dx = i * barWidth + barWidth / 2;
                canvas.drawLine(dx, 0, dx, -barHeight, barPaint);
            }
        }

        // 画水平线
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(0, -i * 0.1F * availableHeight, availableWidth, -i * 0.1F * availableHeight, dividerPaint);
        }

        canvas.restore();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isRunning = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public void setMax(int max) {
        this.maxData = max;
    }

    private int getOffset() {
        return this.offset;
    }

    private void setOffset(int offset) {
        Log.i("DataView", offset + "");
        this.offset = offset;
        invalidate();
    }

    public void setDataFrames(ArrayList<int[]> dataFrames) {
//        oa.end();
        offset = 0;
        this.dataFrames = dataFrames;
        invalidate();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void startAnim(ArrayList<int[]> df) {
        isRunning = true;
        this.dataFrames = df;
        oa = ObjectAnimator.ofInt(this, "offset", 0, dataFrames.size() - 1);
        oa.setDuration(dataFrames.size() * 15);
        oa.setInterpolator(new LinearInterpolator());
        oa.setRepeatCount(0);
        oa.addListener(this);
        oa.start();
    }
}
