package com.ci.smsforwarder.presenter;

import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.view.ViewFiltersView;

import java.util.List;

public class ViewFiltersPresenter extends BasePresenter<ViewFiltersView> {

    private CacheImpl cacheImpl;

    public ViewFiltersPresenter(CacheImpl cacheImpl) {
        this.cacheImpl = cacheImpl;
    }

    public List<FilterInfo> getFilterInfo() {
        return cacheImpl.retrieveFilterInfoDetails();
    }
}
