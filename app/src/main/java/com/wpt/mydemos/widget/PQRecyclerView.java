package com.wpt.mydemos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wpt.mydemos.R;

/**
 * author : wpt
 * date   : 2021/8/1211:09
 * desc   : 可下拉刷新，上滑到底部加载更多的RecyclerView
 */
public class PQRecyclerView extends FrameLayout {

    private SmartRefreshLayout mSmartRefreshLayout;
    private RecyclerView mRecyclerView;
    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;
    private View mErrorView;
    private boolean mRefreshEnable;
    private boolean mLoadMoreEnable;
    private int mLayoutResId;
    private Context mContext;

    public PQRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public PQRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,null,0);
    }

    public PQRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PQRefreshView);
        mRefreshEnable = ta.getBoolean(R.styleable.PQRefreshView_crv_refreshEnable, true);
        mLoadMoreEnable = ta.getBoolean(R.styleable.PQRefreshView_crv_loadMoreEnable, true);
        mLayoutResId = ta.getResourceId(R.styleable.PQRefreshView_crv_layout, R.layout.view_smart_refresh_recyclerview);
        ta.recycle();

        initView();
    }

    /**
     * 当我们的XML布局被加载完后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mLayoutResId, this, false);
        addView(view);

        mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.srl);
        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
        mSmartRefreshLayout.setEnableOverScrollBounce(false);//设置是否开启越界回弹功能（默认true）

        mEmptyView = view.findViewById(R.id.rlv_empty);
        mErrorView = (TextView) view.findViewById(R.id.rlv_error);

        mHeaderView = view.findViewById(R.id.rlv_header);
        mFooterView = view.findViewById(R.id.rlv_footer);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rlv);
        mRecyclerView.setHasFixedSize(true);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refresh();
                Log.d("===wpt===","下拉更新");
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
//                loadMore();
                Log.d("===wpt===","加载更多");
            }
        });
        mSmartRefreshLayout.setEnableRefresh(mRefreshEnable);
        mSmartRefreshLayout.setEnableLoadMore(mLoadMoreEnable);
        View btnReload = view.findViewById(R.id.rlv_reload);
        if (btnReload != null) {
            btnReload.setOnClickListener(v -> {

            });
        }

    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public SmartRefreshLayout getSmartRefreshLayout() {
        return mSmartRefreshLayout;
    }
}
