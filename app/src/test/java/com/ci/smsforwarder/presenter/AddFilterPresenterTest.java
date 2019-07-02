package com.ci.smsforwarder.presenter;

import com.ci.smsforwarder.R;
import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.view.AddFilterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddFilterPresenterTest {

    private AddFilterPresenter addFilterPresenter;

    @Mock
    private CacheImpl cacheImpl;
    
    @Mock
    private AddFilterView addFilterView;

    @Before
    public void setUp() {
        addFilterPresenter = new AddFilterPresenter(cacheImpl);
        addFilterPresenter.setView(addFilterView);
    }

    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNameIsIncorrect() {
        
        addFilterPresenter.validateUserEnteredDetails("%3Invalid%file$name", "+61452678902");
        
        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }


    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNumberDoesNotStartWithPlus() {

        addFilterPresenter.validateUserEnteredDetails("ValidFileName", "1452678902");

        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNumberIsInvalid() {

        addFilterPresenter.validateUserEnteredDetails("ValidFileName", "+1a452678902");

        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }


    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNumberIsInvalidWithMoreThan13Numbers() {

        addFilterPresenter.validateUserEnteredDetails("ValidFileName", "+0123456789123456789");

        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNotShowInvalidateErrorMessageWhenFilterNumberAndNameIsValid() {

        addFilterPresenter.validateUserEnteredDetails("ValidFileName", "+61561347199");

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNavigateToMainScreenWhenFileNameHasDigits() {

        addFilterPresenter.validateUserEnteredDetails("ValidFileName2", "+61561347199");

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNavigateToMainScreenWhenFileNameIsSmallCase() {

        addFilterPresenter.validateUserEnteredDetails("validfilename", "+61561347199");

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNavigateToMainScreenWhenFileNameIsUpperCase() {

        addFilterPresenter.validateUserEnteredDetails("VALIDFILENAME", "+61561347199");

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }




    @Test
    public void shouldSaveFilterInfo() {

        FilterInfo filterInfo = FilterInfo.builder().build();
        addFilterPresenter.saveFilterInfo(filterInfo);

        verify(cacheImpl).saveFilterInfo(filterInfo);
    }
}