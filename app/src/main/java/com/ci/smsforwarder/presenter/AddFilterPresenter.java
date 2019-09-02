package com.ci.smsforwarder.presenter;

import com.ci.smsforwarder.R;
import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.view.AddFilterView;
import com.hbb20.CountryCodePicker;

public class AddFilterPresenter extends BasePresenter<AddFilterView>{

    public static final String FILTER_NAME_REGEX = "^[a-zA-Z0-9_.-]*$";
    public static final String PHONE_NUMBER_REGEX = "^\\[0-9]{10,13}$";
    private CacheImpl cacheImpl;

    public AddFilterPresenter(CacheImpl cacheImpl) {
        this.cacheImpl = cacheImpl;
    }


    public void saveFilterInfo(FilterInfo filterInfo) {
        cacheImpl.saveFilterInfo(filterInfo);
    }

    public void validateAndSubmitUserEnteredDetails(String filterName, CountryCodePicker ccp) {

        if (hasUserEnteredValidDetails(filterName, ccp)) {
            FilterInfo filterInfo = FilterInfo.builder()
                    .name(filterName)
                    .number(ccp.getFullNumberWithPlus())
                    .isfilterStatusOn(true)
                    .build();
            getView().saveUserInfoAndNavigateToMainScreen(filterInfo);
        } else {
            getView().invalidDataErrorMessage(R.string.invalid_number_error_message);
        }

    }

    private boolean hasUserEnteredValidDetails(String filterName, CountryCodePicker ccp) {
        return filterName.matches(FILTER_NAME_REGEX) && ccp.isValidFullNumber();
    }
}
