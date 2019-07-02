package com.ci.smsforwarder.presenter;

import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.view.AddFilterView;

import java.util.List;

public class AddFilterPresenter extends BasePresenter<AddFilterView>{

    private CacheImpl cacheImpl;

    public AddFilterPresenter(CacheImpl cacheImpl) {
        this.cacheImpl = cacheImpl;
    }


    public void saveFilterInfo(FilterInfo filterInfo) {
        cacheImpl.saveFilterInfo(filterInfo);
    }

    public void validateUserEnteredDetails(String filterName, String filterForwardNumber) {

        if (true) {
            FilterInfo filterInfo = FilterInfo.builder()
                    .name(filterName)
                    .number(filterForwardNumber)
                    .isfilterStatusOn(true)
                    .build();
            getView().saveUserInfoAndNavigateToMainScreen(filterInfo);
        } else {
            getView().invalidDataErrorMessage();
        }

    }
}
