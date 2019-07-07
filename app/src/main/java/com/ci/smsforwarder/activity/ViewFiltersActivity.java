package com.ci.smsforwarder.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.ci.smsforwarder.AppController;
import com.ci.smsforwarder.R;
import com.ci.smsforwarder.adapter.MyRecyclerViewAdapter;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.presenter.ViewFiltersPresenter;
import com.ci.smsforwarder.view.ViewFiltersView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFiltersActivity extends AppCompatActivity implements ViewFiltersView {

    public static final int ADD_FILTER_ACTIVITY_REQUEST_CODE = 2;
    private static final int SMS_FORWARD_PERMISSION_CONSTANT = 100;

    private MyRecyclerViewAdapter mAdapter;

    @Inject
    ViewFiltersPresenter viewFiltersPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.emptytextView)
    TextView emptyTextView;

    @BindView(R.id.adView)
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, getString(R.string.AD_MOB_APPID));
        setContentView(R.layout.activity_main);
        ((AppController) getApplicationContext()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        viewFiltersPresenter.setView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddFilterActivity.class);
                startActivityForResult(intent, ADD_FILTER_ACTIVITY_REQUEST_CODE);

            }
        });
        setupRecyclerViewWithData();
        checkAndRequestPermissions();

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


    }

    private void setupRecyclerViewWithData() {
        List<FilterInfo> filterInfoList = viewFiltersPresenter.getFilterInfo();
        mAdapter = new MyRecyclerViewAdapter(getApplicationContext(), filterInfoList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(ViewFiltersActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        List<FilterInfo> filterInfoList = viewFiltersPresenter.getFilterInfo();
        mAdapter.udpateData(filterInfoList);
        if (filterInfoList.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }

    }

    public void checkAndRequestPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, SMS_FORWARD_PERMISSION_CONSTANT);
        }
    }
}
