package com.qzj.facial.common.toolbar;

/**
 * toolbar bean
 */
@SuppressWarnings("unused")
public class TabToolbar {

    // toolbar左侧资源ID和文本
    public int leftResId;
    public int leftBgResId;
    public String leftText;

    // toolbar中间文本
    public String leftTabText;
    public String rightTabText;

    // toolbar右侧资源ID和文本
    public int rightResId;
    public int rightBgResId;
    public String rightText;

    // 背景色
    public int bgColor;

    // tab选中监听
    public OnTbTabCheckedListener tabCheckedListener;

    @Override
    public String toString() {
        return "TabToolbar{" +
                "leftResId=" + leftResId +
                ", leftBgResId=" + leftBgResId +
                ", leftText='" + leftText + '\'' +
                ", leftTabText='" + leftTabText + '\'' +
                ", rightTabText='" + rightTabText + '\'' +
                ", rightResId=" + rightResId +
                ", rightBgResId=" + rightBgResId +
                ", rightText='" + rightText + '\'' +
                ", bgColor=" + bgColor +
                '}';
    }
}
