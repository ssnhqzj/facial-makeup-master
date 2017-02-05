package com.qzj.facial.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinamobile.streetlight.R;

/**
 * 用于显示正在加载，加载出错，无数据等情况
 *  qzj
 */
public class EmptyLayout extends RelativeLayout implements View.OnTouchListener {
    private static final String TAG = "EmptyLayout";

    // 正在加载
    public static final int DOWN_LOADING = 1;
    // 正在上传
    public static final int UP_LOADING = 2;
    // 无可用网络
    public static final int NO_NETWORK = 3;
    // 无数据
    public static final int NO_DATA = 4;
    // 出错了
    public static final int ERROR = 5;
    // 关闭正在加载
    public static final int CLOSE_LOADING = 6;

    private int[] imageIds = new int[]{
            R.mipmap.empty_no_wifi,
            R.mipmap.empty_no_data,
            R.mipmap.empty_error
    };

    private String[] text = new String[]{
            "网络异常",
            "暂无数据",
            "数据加载失败"
    };

    private String[] hintText = new String[]{
            "请检查您的网络",
            "",
            "请刷新重试"
    };

    private Context context;
    private ViewHolder viewHolder;
    private OnEmptyLayoutTouchListener touchListener;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflateChildView();
    }

    /**
     * 设置显示状态
     */
    public void setEmptyState(int state, boolean isShade) {
        switch (state) {
            // 正在加载
            case DOWN_LOADING:
                changeStateToDownLoading();
                break;

            // 正在上传
            case UP_LOADING:
                changeStateToUpLoading();
                break;

            // 无可用网络
            case NO_NETWORK:
                changeStateToNoNetwork(isShade);
                break;

            // 无数据
            case NO_DATA:
                changeStateToNoData();
                break;

            // 出错了
            case ERROR:
                changeStateToNoError(isShade);
                break;

            // 关闭正在加载
            case CLOSE_LOADING:
                close();
                break;
        }
    }

    /**
     * 切换状态到正在加载
     */
    private void changeStateToDownLoading() {
        if (viewHolder != null) {
            viewHolder.loadingText.setText("正在加载...");
            viewHolder.loading.setVisibility(VISIBLE);
            viewHolder.other.setVisibility(GONE);
            viewHolder.hint.setVisibility(GONE);
        }

        this.setTransparentBackGround();
        this.setVisibility(VISIBLE);
    }

    /**
     * 切换状态到正在上传
     */
    private void changeStateToUpLoading() {
        if (viewHolder != null) {
            viewHolder.loadingText.setText("正在提交...");
            viewHolder.loading.setVisibility(VISIBLE);
            viewHolder.other.setVisibility(GONE);
            viewHolder.hint.setVisibility(GONE);
        }

        this.setTransparentBackGround();
        this.setVisibility(VISIBLE);
    }

    /**
     * 切换到无可用的网络
     */
    private void changeStateToNoNetwork(boolean isShade) {
        if (isShade){
            this.setNotTransparentBackground();
            if (viewHolder != null) {
                viewHolder.otherImage.setImageResource(imageIds[0]);
                viewHolder.otherText.setText(text[0]);
                viewHolder.hint.setText(hintText[0]);

                viewHolder.loading.setVisibility(GONE);
                viewHolder.other.setVisibility(VISIBLE);
                viewHolder.hint.setVisibility(VISIBLE);
            }

            this.setNotTransparentBackground();
            this.setVisibility(VISIBLE);
        }else{
            this.setVisibility(GONE);
            Toast.makeText(context, "无可用的网络", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 切换状态到无数据
     */
    private void changeStateToNoData() {
        if (viewHolder != null) {
            viewHolder.otherImage.setImageResource(imageIds[1]);
            viewHolder.otherText.setText(text[1]);
            viewHolder.hint.setText(hintText[1]);

            viewHolder.loading.setVisibility(GONE);
            viewHolder.other.setVisibility(VISIBLE);
            viewHolder.hint.setVisibility(VISIBLE);
        }

        this.setNotTransparentBackground();
        this.setVisibility(VISIBLE);
    }

    /**
     * 切换状态到出错
     */
    private void changeStateToNoError(boolean isShade) {
        if (isShade){
            if (viewHolder != null) {
                viewHolder.otherImage.setImageResource(imageIds[2]);
                viewHolder.otherText.setText(text[2]);
                viewHolder.hint.setText(hintText[2]);

                viewHolder.loading.setVisibility(GONE);
                viewHolder.other.setVisibility(VISIBLE);
                viewHolder.hint.setVisibility(VISIBLE);
            }

            this.setNotTransparentBackground();
            this.setVisibility(VISIBLE);
        }else{
            this.setVisibility(GONE);
            Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭正在加载对话框
     */
    public void close() {
        this.setVisibility(GONE);
    }

    /**
     * 设置透明背景色
     */
    private void setTransparentBackGround() {
        this.setBackgroundColor(0x66000000);
    }

    /**
     * 设置不透明的背景色
     */
    private void setNotTransparentBackground(){
        this.setBackgroundColor(0xFFFFFFFF);
    }

    /**
     * 填充子view
     */
    private void inflateChildView() {
        this.setId(R.id.empty_layout);
        this.setVisibility(GONE);

        View view = View.inflate(context, R.layout.empty_layout, this);
        initView(view);
    }

    /**
     * 初始化视图
     */
    private void initView(View view) {
        if (view != null) {
            if (viewHolder == null) viewHolder = new ViewHolder();

            viewHolder.loading = (LinearLayout) view.findViewById(R.id.empty_loading);
            viewHolder.other = (LinearLayout) view.findViewById(R.id.empty_other);
            viewHolder.loadingProgressBar = (ProgressBar) view.findViewById(R.id.empty_loading_progress);
            viewHolder.loadingText = (TextView) view.findViewById(R.id.empty_loading_text);
            viewHolder.otherImage = (ImageView) view.findViewById(R.id.empty_other_image);
            viewHolder.otherText = (TextView) view.findViewById(R.id.empty_other_text);
            viewHolder.hint = (TextView) view.findViewById(R.id.empty_hint_text);
        }

        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (touchListener != null) {
            touchListener.onTouch(event);
        }
        return true;
    }

    public OnEmptyLayoutTouchListener getTouchListener() {
        return touchListener;
    }

    class ViewHolder {
        LinearLayout loading;
        ProgressBar loadingProgressBar;
        TextView loadingText;

        LinearLayout other;
        ImageView otherImage;
        TextView otherText;
        TextView hint;
    }

    public interface OnEmptyLayoutTouchListener {
        void onTouch(MotionEvent event);
    }

}
