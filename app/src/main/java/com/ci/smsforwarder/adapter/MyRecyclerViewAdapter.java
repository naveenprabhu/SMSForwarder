package com.ci.smsforwarder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ci.smsforwarder.AppController;
import com.ci.smsforwarder.R;
import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.presenter.AddFilterPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<FilterInfo> mFilterInfoList = new ArrayList<>();
    private Context mContext;
    public MyRecyclerViewAdapter(Context context, List<FilterInfo> filterInfoList) {
        mContext = context;
        mFilterInfoList = filterInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int posistion) {
        viewHolder.bindFilter(mFilterInfoList.get(posistion));

    }

    @Override
    public int getItemCount() {
        return mFilterInfoList != null ? mFilterInfoList.size() : 0;
    }

    public void udpateData(List<FilterInfo> filterInfoList){
        mFilterInfoList = filterInfoList;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.filterNameTextView)
        TextView filterNameTextView;

        @BindView(R.id.filterNumberTextView)
        TextView filterNumberTextView;

        @BindView(R.id.filterStatus)
        Switch filterStatusSwitch;

        @Inject
        CacheImpl cacheImpl;

        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            ((AppController) mContext.getApplicationContext()).getAppComponent().inject(this);
        }

        public void bindFilter(FilterInfo filterInfo){
            filterNameTextView.setText(filterInfo.getName());
            filterNumberTextView.setText(filterInfo.getNumber());
            filterStatusSwitch.setChecked(filterInfo.isIsfilterStatusOn());
        }

        @OnClick(R.id.filterStatus)
        void onSwitchSelected() {
            FilterInfo filterInfo = mFilterInfoList.get(getAdapterPosition());
            filterInfo.setIsfilterStatusOn(!filterInfo.isIsfilterStatusOn());
            cacheImpl.updateFilterInfo(getAdapterPosition(), filterInfo);

        }
    }
}
