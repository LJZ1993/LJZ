package com.example.administrator.ljz.bottomloading;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.ljz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/16.
 */
public class BottomLoadingActivity extends AppCompatActivity implements RecyclerOnItemClickListener, LoadingMoreBottomListener {
    private static final String TAG = "BottomLoadingActivityww";
    @InjectView(R.id.m_bl_rv)
    RecyclerView mBlRv;
    List<String> mDataList = new ArrayList<>();
    //当前滚动的position下面最小的items的临界值
    private int visibleThreshold = 5;
    MyAdapter mAdapter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botomloading);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initView() {
    }

    private void initData() {
        mBlRv.setHasFixedSize(true);
        mBlRv.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView的数据获取
        mDataList.addAll(getData());
        mAdapter = new MyAdapter(mBlRv, mDataList);
        mBlRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setBottomLoadingMoreListener(this);
    }

    /**
     * 获取rv初始化的数据
     *
     * @return
     */
    public List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item中的数据" + i);
        }
        return data;
    }

    /**
     * 获取rv加载更多数据
     *
     * @return
     */
    public List<String> getLoadingMoreData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("item中被加载更多的数据啊的数据" + i);
        }
        return data;
    }

    /**
     * RecyclerView的item点击事件
     *
     * @param itemView
     */
    @Override
    public void onClick(View itemView, int position) {
        //  Log.w(TAG, position + "");

    }

    //加载更多数据的回调接口
    @Override
    public void loadingMoreListener() {
        Log.w(TAG, "loadingMoreListener : " + System.currentTimeMillis());

        //利用getItemViewType这个方法来使onCreateView中的viewType类型被确定，从而判断出应该创造出那种item，是view还是progress
        mDataList.add(null);
        mAdapter.notifyDataSetChanged();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //移除progress
                mDataList.remove(mDataList.size() - 1);
                mAdapter.notifyDataSetChanged();
                //添加加载更多的数据
                mDataList.addAll(getLoadingMoreData());
                mAdapter.notifyDataSetChanged();
                mAdapter.setLoading();
            }
        }, 5000);

    }


    class MyAdapter extends RecyclerView.Adapter {
        //不同item的标志
        private static final int ITEM_VIEW = 0;
        private static final int ITEM_PRESS = 1;

        private RecyclerOnItemClickListener recyclerOnItemClickListener;
        private LoadingMoreBottomListener loadingMoreBottomListener;
        private RecyclerView mBlRv;
        private List<String> mDataList;
        private boolean isLoading;

        public MyAdapter(RecyclerView mBlRv, List<String> mDataList) {
            this.mBlRv = mBlRv;
            this.mDataList = mDataList;
            RecyclerView.LayoutManager layoutManager = mBlRv.getLayoutManager();
            final LinearLayoutManager manager;
            if (layoutManager instanceof LinearLayoutManager) {
                manager = (LinearLayoutManager) layoutManager;
                //RV的滚动监听事件
                mBlRv.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int allTtemCount = manager.getItemCount();
                        int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                        Log.w(TAG, allTtemCount + "    " + lastVisibleItemPosition);
                        Log.w(TAG, "isLoading :" + isLoading);
                        if (!isLoading && allTtemCount <= lastVisibleItemPosition + visibleThreshold) {
                            loadingMoreBottomListener.loadingMoreListener();
                            isLoading = true;
                            Log.w(TAG, "scrolled : " + System.currentTimeMillis());
                        }
                    }
                });
            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            if (viewType == ITEM_VIEW) {
                View view = inflater.inflate(R.layout.item_bottomloading, parent, false);
                MyItemViewHolder myItemViewHolder = new MyItemViewHolder(view);
                return myItemViewHolder;
            } else {
                View view = inflater.inflate(R.layout.footerview, parent, false);
                MyPregressViewHolder pregressViewHolder = new MyPregressViewHolder(view);
                return pregressViewHolder;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof MyItemViewHolder) {
                ((MyItemViewHolder) holder).mBlTv.setText(mDataList.get(position));
                ((MyItemViewHolder) holder).mBlLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerOnItemClickListener.onClick(v, position);
                    }
                });
            } else if (holder instanceof MyPregressViewHolder) {
                ((MyPregressViewHolder) holder).mFvProg.setIndeterminate(true);
            }
        }

        @Override
        public int getItemCount() {
            return mDataList.size() > 0 ? mDataList.size() : 0;
        }

        //获取不同itemView的Type
        @Override
        public int getItemViewType(int position) {
            return mDataList.get(position) != null ? ITEM_VIEW : ITEM_PRESS;
        }

        public class MyItemViewHolder extends RecyclerView.ViewHolder {
            @InjectView(R.id.m_bl_tv)
            TextView mBlTv;
            @InjectView(R.id.m_bl_ll)
            LinearLayout mBlLl;

            public MyItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.inject(this, itemView);
            }
        }

        public class MyPregressViewHolder extends RecyclerView.ViewHolder {
            @InjectView(R.id.m_fv_prog)
            ProgressBar mFvProg;

            public MyPregressViewHolder(View itemView) {
                super(itemView);
                ButterKnife.inject(this, itemView);
            }
        }

        //给Item注册点击事件
        public void setOnItemClickListener(RecyclerOnItemClickListener recyclerOnItemClickListener) {
            this.recyclerOnItemClickListener = recyclerOnItemClickListener;
        }

        //注册加载更多数据的事件监听器
        public void setBottomLoadingMoreListener(LoadingMoreBottomListener loadingMoreListener) {
            loadingMoreBottomListener = loadingMoreListener;
        }

        public void setLoading() {
            isLoading = false;
        }
    }
}


