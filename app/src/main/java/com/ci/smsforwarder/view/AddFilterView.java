package com.ci.smsforwarder.view;

import com.ci.smsforwarder.models.FilterInfo;

public interface AddFilterView {

    void saveUserInfoAndNavigateToMainScreen(FilterInfo filterInfo);

    void invalidDataErrorMessage(int errorMessageResourceId);
}
