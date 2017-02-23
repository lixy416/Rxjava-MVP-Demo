package com.rxjava.mvp.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjava.mvp.R;
import com.rxjava.mvp.bean.NewsBean;
import com.rxjava.mvp.utils.ImageLoaderUtils;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/9 13:46.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private WeakReference<Context> outer;
    private List<NewsBean> data;
    private boolean mShowFooter = true;
    private OnItemClickListener itemClickListener;
    public NewsRecyclerAdapter(Context context){
        outer = new WeakReference<Context>(context);
    }

    public void setData(List<NewsBean> list){
        this.data = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, parent,false);

            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            NewsBean newsBean = getItem(position);
            if (newsBean == null){
                return;
            }
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.setNews(newsBean);
        }
    }

    public NewsBean getItem(int position){
        return data == null ? null : data.get(position);
    }
    public boolean ismShowFooter() {
        return mShowFooter;
    }

    public void setmShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    @Override
    public int getItemCount() {
        int end = mShowFooter ? 1 : 0;
        if (data == null){
            return end;
        }
        return data.size() + end;
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter){
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()){
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 列表内容的viewholder
     */
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTtileTv;
        private TextView mDescTv;
        private ImageView imageIv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mTtileTv = (TextView) itemView.findViewById(R.id.tvTitle);
            mDescTv = (TextView) itemView.findViewById(R.id.tvDesc);
            imageIv = (ImageView) itemView.findViewById(R.id.ivNews);
            itemView.setOnClickListener(this);
        }

        public void setNews(NewsBean bean){
            mTtileTv.setText(bean.getTitle());
            mDescTv.setText(bean.getDigest());
            ImageLoaderUtils.display(outer.get(),imageIv,bean.getImgsrc());
        }
        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
