package com.qzj.facial.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qzj.facial.bean.FacePart;
import com.qzj.facial.common.data.FacePartUtils;
import com.qzj.facial.widget.DividerGridItemDecoration;

import java.util.List;

public class IndicatorPagerAdapter extends PagerAdapter implements IndicatorRvAdapter.OnIndicatorRvItemListener {
    private List<String> mDataList;
    private Context mContext;

    private OnIndicatorPagerItemListener mItemListener;

    public IndicatorPagerAdapter(Context context, List<String> dataList) {
        this.mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView = new RecyclerView(mContext);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));

        List<FacePart> list = FacePartUtils.getPicFacePartList(mDataList.get(position));

        IndicatorRvAdapter rvAdapter = new IndicatorRvAdapter(mContext, mDataList.get(position), list);
        rvAdapter.setRvItemListener(this);
        recyclerView.setAdapter(rvAdapter);

        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        TextView textView = (TextView) object;
        String text = textView.getText().toString();
        int index = mDataList.indexOf(text);
        if (index >= 0) {
            return index;
        }
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataList.get(position);
    }

    public void setmItemListener(OnIndicatorPagerItemListener mItemListener) {
        this.mItemListener = mItemListener;
    }

    @Override
    public void onRvItemSelected(String typeName, String iconPath) {
        if (mItemListener != null) {
            mItemListener.onItemSelected(typeName, iconPath);
        }
    }

    public interface OnIndicatorPagerItemListener {
        void onItemSelected(String typeName, String path);
    }
}
