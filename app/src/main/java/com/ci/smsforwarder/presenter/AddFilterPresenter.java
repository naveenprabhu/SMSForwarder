package com.ci.smsforwarder.presenter;

import com.ci.smsforwarder.R;
import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.view.AddFilterView;

import java.util.List;

public class AddFilterPresenter extends BasePresenter<AddFilterView>{

    public static final String FILTER_NAME_REGEX = "^[a-zA-Z0-9_.-]*$";
    public static final String PHONE_NUMBER_REGEX = "^\\+[0-9]{10,13}$";
    private CacheImpl cacheImpl;

    public AddFilterPresenter(CacheImpl cacheImpl) {
        this.cacheImpl = cacheImpl;
    }


    public void saveFilterInfo(FilterInfo filterInfo) {
        cacheImpl.saveFilterInfo(filterInfo);
    }

    public void validateUserEnteredDetails(String filterName, String filterForwardNumber) {

        if (hasUserEnteredValidDetails(filterName, filterForwardNumber)) {
            FilterInfo filterInfo = FilterInfo.builder()
                    .name(filterName)
                    .number(filterForwardNumber)
                    .isfilterStatusOn(true)
                    .build();
            getView().saveUserInfoAndNavigateToMainScreen(filterInfo);
        } else {
            getView().invalidDataErrorMessage(R.string.invalid_number_error_message);
        }

    }

    private boolean hasUserEnteredValidDetails(String filterName, String filterForwardNumber) {
        return filterName.matches(FILTER_NAME_REGEX) && filterForwardNumber.matches(PHONE_NUMBER_REGEX);
    }
}
