package com.wpt.mydemos.packer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.multilevel.treelist.Node;
import com.multilevel.treelist.TreeRecyclerAdapter;
import com.wpt.mydemos.R;

import java.util.List;

/**
 * Author: wpt
 * Time: 2022/5/13
 *
 * @Desc：
 */
public class MyTreeAdapter extends TreeRecyclerAdapter {

    private Context mContext;

    private List<Node> mList;

    public MyTreeAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);
        mContext = context;
        mList = datas;
    }

    public MyTreeAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel) {
        super(mTree, context, datas, defaultExpandLevel);
        mContext = context;
        mList = datas;
    }

    @Override
    public void onBindViewHolder(Node node, RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder){
            ((MyViewHolder)viewHolder).label.setText(mList.get(i).getName());
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_text,viewGroup,false));
        return viewHolder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        public TextView label;

        public MyViewHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView
                    .findViewById(R.id.tvItem);

        }

    }
}
