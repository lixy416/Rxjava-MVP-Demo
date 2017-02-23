package com.rxjava.mvp.images.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rxjava.mvp.R;
import com.rxjava.mvp.bean.ImageBean;
import com.rxjava.mvp.utils.ImageLoaderUtils;
import com.rxjava.mvp.utils.ToolsUtil;

import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/17 16:20.
 */
public class ImageListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int mMaxWidth;
    private final int mMaxHeight;
    private Context mContext;
    private List<ImageBean> list;
    private OnItemClickListener mOnItemClickListener;

    public ImageListRecyclerAdapter(Context context){
        this.mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageBean imageBean = list.get(position);
        if(imageBean == null) {
            return;
        }
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        itemHolder.mTitle.setText(imageBean.getTitle());
        float scale = (float)imageBean.getWidth() / (float) mMaxWidth;
        int height = (int)(imageBean.getHeight() / scale);
        if(height > mMaxHeight) {
            height = mMaxHeight;
        }
        itemHolder.mImage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext, itemHolder.mImage, imageBean.getThumburl());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setDate(List<ImageBean> mData) {
        this.list = mData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public android.widget.ImageView mImage;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mImage = (android.widget.ImageView) v.findViewById(R.id.ivImage);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }
}
