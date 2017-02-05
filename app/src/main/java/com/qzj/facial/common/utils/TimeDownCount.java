package com.qzj.facial.common.utils;

import android.os.CountDownTimer;

/**
 * qzj
 */
public class TimeDownCount extends CountDownTimer {

    private OnTimeCountListener listener;

    public TimeDownCount(long millisInFuture, long countDownInterval) {
        // 参数依次为总时长,和计时的时间间隔
        super(millisInFuture, countDownInterval);
    }

    // 计时完毕时触发
    @Override
    public void onFinish() {
        listener.onFinish();
    }

    public void setListener(OnTimeCountListener listener) {
        this.listener = listener;
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished){
        listener.onTick(millisUntilFinished);
    }

    public interface OnTimeCountListener{
        void onFinish();
        void onTick(long millisUntilFinished);
    }
}
