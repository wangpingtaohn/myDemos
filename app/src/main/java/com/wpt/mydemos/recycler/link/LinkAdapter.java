package com.wpt.mydemos.recycler.link;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wpt.mydemos.R;
import com.wpt.mydemos.bean.LinkBean;
import java.util.List;

/**
 * Author: wpt
 * Time: 2022/6/7
 *
 * @Desc：
 */
public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {

    private Context mContext;
    private List<LinkBean> mList;

    public LinkAdapter(Context context,List<LinkBean> list){
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinkViewHolder(View.inflate(mContext, R.layout.item_link, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder viewHolder, int i) {

        TextView tvTitle = viewHolder.itemView.findViewById(R.id.tvTitle);
        RecyclerView rvLink = viewHolder.itemView.findViewById(R.id.rvLink);

        LinkBean bean = mList.get(i);

        if (bean == null){
            return;
        }
        tvTitle.setText(bean.getTitle());
        List<LinkBean> subList = bean.getList();
        if (subList == null || subList.isEmpty()){
            return;
        }
        rvLink.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        LinkAdapter subAdapter = new LinkAdapter(mContext,bean.getList());
        rvLink.setAdapter(subAdapter);

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class LinkViewHolder extends RecyclerView.ViewHolder {

        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            setIsRecyclable(false);
        }
    }
}
