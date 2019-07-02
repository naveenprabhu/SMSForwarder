package com.ci.smsforwarder.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.ci.smsforwarder.utils.Constants.FILTER_INFO_OBJECT;
import static com.ci.smsforwarder.utils.Constants.SHARED_PREF_NAME;

public class CacheImpl implements Cache{

    private SharedPreferences sharedPreferences;

    public CacheImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    public void saveFilterInfo(FilterInfo filterInfo) {
        List<FilterInfo> filterInfos = this.retrieveFilterInfoDetails();
        if (filterInfos == null)
            filterInfos = new ArrayList<>();
        filterInfos.add(filterInfo);
        Editor filtereditor = this.sharedPreferences.edit();
        Gson gson = new Gson();
        String filterInfoJson = gson.toJson(filterInfos);
        filtereditor.putString(FILTER_INFO_OBJECT, filterInfoJson);
        filtereditor.commit();
    }

    public List<FilterInfo> retrieveFilterInfoDetails(){
        Gson gson = new Gson();
        String filterInfoJson = this.sharedPreferences.getString(FILTER_INFO_OBJECT, "");
        Type listType = new TypeToken<ArrayList<FilterInfo>>(){}.getType();
        List<FilterInfo> filterInfos = gson.fromJson(filterInfoJson, listType);
        return filterInfos != null ? filterInfos : new ArrayList<FilterInfo>();
    }

}
