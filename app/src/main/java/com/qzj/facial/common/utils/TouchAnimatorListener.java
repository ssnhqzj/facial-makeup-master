package com.qzj.facial.common.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by edward on 2016/10/21.
 */

public class TouchAnimatorListener implements View.OnTouchListener{


    private boolean isFirstClick = true;
    private float mStartY;
    private boolean mIsAnimatorShowing = false;
    private boolean mIsAnimatorShowed = false;
    private int mTouchSlop;
    private LinearLayout mHeaderOne;

    public void setmIsAnimate(boolean mIsAnimate) {
        this.mIsAnimate = mIsAnimate;
        mAnimator.setDuration(300);
    }

    private boolean mIsAnimate = true;


    private int mHeaderHeight;
    public void setmHeaderHeight(int mHeaderHeight) {
        this.mHeaderHeight = mHeaderHeight;
    }


    ValueAnimator mAnimator = ValueAnimator.ofInt(0,1);

    public TouchAnimatorListener(LinearLayout mHeaderOne) {
        super();
        this.mHeaderOne = mHeaderOne;
        mTouchSlop = ViewConfiguration.getTouchSlop();

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimatorShowing = true;
                mIsAnimatorShowed = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimatorShowing = false;
                mIsAnimatorShowed = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @TargetApi(12)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        boolean isConsume = false;

        if(isFirstClick){//只有第一次进来的时候用获取位置的方法给lastY赋值，后面的值都是上一次的move坐标
            //如果不做此判断，每次的lasty和movey是相同的值，这是因为在此处获取的的y值其实就是move的值，
            //是因为在listview中，down事件是默认传递进去给条目的，在此处无法响应down事件。
            mStartY =event.getRawY();
            isFirstClick=false;//初始值是true，此处置为false。
            mIsAnimatorShowed = false;
        }

        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.d("gouhong","MotionEvent.ACTION_DOWN");
                mIsAnimatorShowed = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("gouhong","MotionEvent.ACTION_MOVE");

                float currentY = event.getRawY();
//                Log.d("gouhong","currentY = "+currentY);
//                Log.d("gouhong","mStartY = "+mStartY);
//
//                Log.d("gouhong ", "do nothing cause mIsAnimatorShowed = "+mIsAnimatorShowed
//                        +"// mIsAnimatorShowing = "+mIsAnimatorShowing
//                        +"// mHeaderOne == null 为 "+(mHeaderOne == null)
//                        +"// Math.abs(currentY - mStartY) < mTouchSlop 为 "+(Math.abs(currentY - mStartY) < mTouchSlop));


                if (mIsAnimatorShowed||mIsAnimatorShowing
                        || mHeaderOne == null
                        || mIsAnimate == false
                        || Math.abs(currentY - mStartY) < mTouchSlop){
                    // TODO: 2016/10/21  do nothing
                }else{
                    //上下滑动

                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)mHeaderOne.getLayoutParams();
//                    Log.d("gouhong kk","params ppp = "+params.topMargin);
                    if(currentY > mStartY){
                        //下拉
//                        Log.d("gouhong kk","下拉选项");
                        if(params.topMargin == - mHeaderHeight){
                            //如果header没有显示，显示动画
//                            Log.d("gouhong kk","下拉动画");
                            animateShowHeader();
                        }



                    }else if(currentY < mStartY){
                        //上滑

//                        Log.d("gouhong kk","上滑选项");
                        if (params.topMargin == 0){//header 以显示
                            //如果title在显示开始动画
//                            Log.d("gouhong kk","上滑动画");
                            animateHideHeader();
                        }

                    }else {
//                        Log.d("gouhong","no movement");
                    }
                }


                break;
            case MotionEvent.ACTION_UP:
                Log.d("gouhong","MotionEvent.ACTION_UP");
                mIsAnimatorShowed = false;
                isFirstClick = true;
                break;

            case MotionEvent.ACTION_CANCEL:

                break;
            default:
                break;
        }
        return isConsume;
    }


    @TargetApi(12)
    public void animateHideHeader(){
        //上滑动画
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)mHeaderOne.getLayoutParams();
                marginLayoutParams.topMargin = -(int)(fraction*mHeaderHeight);
                Log.d("gouhong","marginLayoutParams.topMargin = "+marginLayoutParams.topMargin+"///fraction = "+fraction);
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin,marginLayoutParams.topMargin
                        ,marginLayoutParams.rightMargin,marginLayoutParams.bottomMargin);
                mHeaderOne.requestLayout();

            }
        });
        mAnimator.start();
    }

    @TargetApi(12)
    public void animateShowHeader(){
        //下拉动画
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)mHeaderOne.getLayoutParams();
                marginLayoutParams.topMargin = -(int)((1-fraction)*mHeaderHeight);
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin,marginLayoutParams.topMargin
                        ,marginLayoutParams.rightMargin,marginLayoutParams.bottomMargin);
                mHeaderOne.requestLayout();

            }
        });
        mAnimator.start();
    }
}
