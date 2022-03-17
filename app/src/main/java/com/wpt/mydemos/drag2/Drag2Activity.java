package com.wpt.mydemos.drag2;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wpt.mydemos.R;
import com.wpt.mydemos.drag.OnRecyclerItemClickListener;
import com.wpt.mydemos.widget.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * author : wpt
 * date   : 2019-12-27 20:55
 * desc   :
 */
public class Drag2Activity extends BaseActivity implements MyItemTouchCallback.OnDragListener {
        private List<String> list = new ArrayList<String>();
        private View parent;
        //显示模块的recyleview
        private RecyclerView mRecyclerView;
        private ShimmerLayout shimmerLayout;
        //可拖动模块的帮助类
        private ItemTouchHelper itemTouchHelper;
        //适配器
        private RecyleViewAdapter mRecyclerAdapter;

        //声明刷新控件
        private SmartRefreshLayout srf;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_drag_2);

        initView();
    }

        private void initView() {


            shimmerLayout = findViewById(R.id.shimmerLayout);

            shimmerLayout.setShimmerColor(ContextCompat.getColor(this,R.color.white));

            shimmerLayout.startShimmerAnimation();

            for (int i = 0; i < 100; i++) {
                list.add(i + "");
            }
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            // 设置添加删除item的时候的动画效果
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());


            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
//            LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            gridLayoutManager.setSmoothScrollbarEnabled(true);
            gridLayoutManager.setAutoMeasureEnabled(true);

            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setNestedScrollingEnabled(false);

            findViewById(R.id.refresh_btn).setOnClickListener(v -> {
                list.add(30,"新增");
                mRecyclerAdapter.notifyDataSetChanged();
            });



            // 设置适配器
            mRecyclerAdapter = new RecyleViewAdapter(list);
            itemTouchHelper = new ItemTouchHelper(new MyItemTouchCallback(mRecyclerAdapter).setOnDragListener(this));
            itemTouchHelper.attachToRecyclerView(mRecyclerView);
            mRecyclerView.setAdapter(mRecyclerAdapter);
            mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
                @Override
                public void onLongClick(RecyclerView.ViewHolder vh) {
                    itemTouchHelper.startDrag(vh);
                    //VibratorUtil.Vibrate(Drag2Activity.this, 70);   //震动70ms
                }

                @Override
                public void onItemClick(RecyclerView.ViewHolder vh) {
                    super.onItemClick(vh);
                }
            });
            srf = findViewById(R.id.refresh);
            srf.setEnableRefresh(false);
            /*srf.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
                @Override
                public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                    return new ClassicsHeader(context);//指定为经典Header，默认是 贝塞尔雷达Header
                }
            });*/
            /*srf.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    srf.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerAdapter.notifyDataSetChanged();

                            srf.finishRefresh();
                            Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);
                }
            });
            srf.setEnableLoadmore(false);//屏蔽掉上拉加载的效果*/


        }

        @Override
        public void onFinishDrag() {

        }


        class RecyleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MyItemTouchCallback.ItemTouchAdapter {
            private List<String> mData;

            public RecyleViewAdapter(List<String> data) {
                mData = data;
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new TestViewHolder(View.inflate(parent.getContext(), R.layout.item_drag_2, null));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                TestViewHolder tHolder = (TestViewHolder) holder;
//                tHolder.tv.setText(mData.get(position));
            }

            @Override
            public int getItemCount() {
                return mData == null ? 0 : mData.size();
            }

            @Override
            public void onMove(int fromPosition, int toPosition) {
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mData, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mData, i, i - 1);
                    }
                }
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onSwiped(int position) {
                mData.remove(position);
                notifyItemRemoved(position);

            }

            private class TestViewHolder extends RecyclerView.ViewHolder {
//                TextView tv;

                public TestViewHolder(View itemView) {
                    super(itemView);
//                    tv = (TextView) itemView.findViewById(R.id.tv);
                }

            }

        }


}
