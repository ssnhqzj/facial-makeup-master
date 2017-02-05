package com.qzj.facial.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chinamobile.streetlight.R;
import com.qzj.facial.bean.FacePart;

import java.util.List;

public class IndicatorRvAdapter extends RecyclerView.Adapter<IndicatorRvAdapter.IndicatorRvHolder> {

    private Context context;
    private List<FacePart> mDataList;

    public IndicatorRvAdapter(Context context, List<FacePart> dataList) {
        this.context = context;
        this.mDataList = dataList;
    }

    @Override
    public IndicatorRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndicatorRvHolder(View.inflate(context, R.layout.layout_indicator_rv_item,null));
    }

    @Override
    public void onBindViewHolder(IndicatorRvHolder holder, int position) {
        String path = "file:///android_asset/" + mDataList.get(position).imgName;
        Glide.with(context).load(path).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        if (mDataList != null){
            return mDataList.size();
        }

        return 0;
    }

    class IndicatorRvHolder extends RecyclerView.ViewHolder {

        ImageView pic;

       public IndicatorRvHolder(View itemView) {
           super(itemView);
           pic = (ImageView) itemView.findViewById(R.id.indicator_rv_item_image);
       }
   }

}
