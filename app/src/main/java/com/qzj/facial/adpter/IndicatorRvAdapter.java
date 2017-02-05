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

    private Context mContext;
    private String mTypeName;
    private List<FacePart> mDataList;

    private OnIndicatorRvItemListener rvItemListener;

    public IndicatorRvAdapter(Context context,String typeName, List<FacePart> dataList) {
        this.mContext = context;
        this.mTypeName = typeName;
        this.mDataList = dataList;
    }

    @Override
    public IndicatorRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndicatorRvHolder(View.inflate(mContext, R.layout.layout_indicator_rv_item,null));
    }

    @Override
    public void onBindViewHolder(IndicatorRvHolder holder, int position) {
        final String path = "file:///android_asset/" + mDataList.get(position).imgName;
        Glide.with(mContext).load(path).into(holder.pic);

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rvItemListener != null) {
                    rvItemListener.onRvItemSelected(mTypeName, path);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataList != null){
            return mDataList.size();
        }

        return 0;
    }

    public void setRvItemListener(OnIndicatorRvItemListener rvItemListener) {
        this.rvItemListener = rvItemListener;
    }

    class IndicatorRvHolder extends RecyclerView.ViewHolder {

        ImageView pic;

       public IndicatorRvHolder(View itemView) {
           super(itemView);
           pic = (ImageView) itemView.findViewById(R.id.indicator_rv_item_image);
       }
    }

    public interface OnIndicatorRvItemListener {
        void onRvItemSelected(String typeName, String iconPath);
    }

}
