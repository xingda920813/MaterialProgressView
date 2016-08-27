package com.xdandroid.materialprogressview;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.util.*;
import android.view.*;

public class MaterialProgressView extends ViewGroup {

    protected CircleImageView mCircleView;
    protected MaterialProgressDrawable mProgress;
    protected static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    protected static final int CIRCLE_DIAMETER = 40;
    protected int mCircleWidth;
    protected int mCircleHeight;
    protected int mExtraShadowSpace;

    public MaterialProgressView(Context context) {
        super(context);
        initProgressView();
    }

    public MaterialProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProgressView();
    }

    public MaterialProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProgressView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MaterialProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initProgressView();
    }

    protected void initProgressView() {
        setWillNotDraw(false);
        createProgressView();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mCircleHeight = mCircleWidth = (int) (CIRCLE_DIAMETER * metrics.density);
        mExtraShadowSpace = (int) ((0.1333 * CIRCLE_DIAMETER) * metrics.density);
        setVisibility(VISIBLE);
    }

    protected void createProgressView() {
        mCircleView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT, CIRCLE_DIAMETER / 2);
        mProgress = new MaterialProgressDrawable(getContext(), this);
        mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        mCircleView.setImageDrawable(mProgress);
        mCircleView.getBackground().setAlpha(255);
        mProgress.setAlpha(255);
        addView(mCircleView);
    }

    public void setProgressBackgroundColor(@ColorInt int color) {
        mCircleView.setBackgroundColor(color);
        mProgress.setBackgroundColor(color);
    }

    public void setColorSchemeColors(@ColorInt int[] colors) {
        mProgress.setColorSchemeColors(colors);
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == GONE || visibility == INVISIBLE) {
            mCircleView.clearAnimation();
            mProgress.stop();
        } else {
            mProgress.start();
            mProgress.showArrow(true);
        }
        super.setVisibility(visibility);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = getMeasuredWidth();
        int circleWidth = mCircleView.getMeasuredWidth();
        int circleHeight = mCircleView.getMeasuredHeight();
        mCircleView.layout((width / 2 - circleWidth / 2), 0, (width / 2 + circleWidth / 2), circleHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCircleWidth + mExtraShadowSpace, mCircleHeight + mExtraShadowSpace);
        mCircleView.measure(MeasureSpec.makeMeasureSpec(mCircleWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(mCircleHeight, MeasureSpec.EXACTLY));
    }
}