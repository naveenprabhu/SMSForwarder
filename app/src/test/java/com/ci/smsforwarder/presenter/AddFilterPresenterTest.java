package com.ci.smsforwarder.presenter;

import com.ci.smsforwarder.R;
import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.view.AddFilterView;
import com.hbb20.CountryCodePicker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddFilterPresenterTest {

    private AddFilterPresenter addFilterPresenter;

    @Mock
    private CacheImpl cacheImpl;
    
    @Mock
    private AddFilterView addFilterView;

    @Mock
    private CountryCodePicker countryCodePicker;

    @Before
    public void setUp() {
        addFilterPresenter = new AddFilterPresenter(cacheImpl);
        addFilterPresenter.setView(addFilterView);
    }

    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNameIsIncorrect() {

        addFilterPresenter.validateAndSubmitUserEnteredDetails("%3Invalid%file$name", countryCodePicker);
        
        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }


    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNumberDoesNotStartWithPlus() {

        when(countryCodePicker.isValidFullNumber()).thenReturn(false);

        addFilterPresenter.validateAndSubmitUserEnteredDetails("ValidFileName", countryCodePicker);

        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNumberIsInvalid() {

        when(countryCodePicker.isValidFullNumber()).thenReturn(false);

        addFilterPresenter.validateAndSubmitUserEnteredDetails("ValidFileName", countryCodePicker);

        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }


    @Test
    public void shouldShowInvalidateErrorMessageWhenFilterNumberIsInvalidWithMoreThan13Numbers() {

        when(countryCodePicker.isValidFullNumber()).thenReturn(false);

        addFilterPresenter.validateAndSubmitUserEnteredDetails("ValidFileName", countryCodePicker);

        verify(addFilterView).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView, never()).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNotShowInvalidateErrorMessageWhenFilterNumberAndNameIsValid() {

        when(countryCodePicker.isValidFullNumber()).thenReturn(true);

        addFilterPresenter.validateAndSubmitUserEnteredDetails("ValidFileName", countryCodePicker);

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNavigateToMainScreenWhenFileNameHasDigits() {

        when(countryCodePicker.isValidFullNumber()).thenReturn(true);

        addFilterPresenter.validateAndSubmitUserEnteredDetails("ValidFileName2", countryCodePicker);

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNavigateToMainScreenWhenFileNameIsSmallCase() {

        when(countryCodePicker.isValidFullNumber()).thenReturn(true);


        addFilterPresenter.validateAndSubmitUserEnteredDetails("validfilename", countryCodePicker);

        verify(addFilterView, never()).invalidDataErrorMessage(R.string.invalid_number_error_message);
        verify(addFilterView).saveUserInfoAndNavigateToMainScreen(any(FilterInfo.class));
    }

    @Test
    public void shouldNavigateToMainScreenWhenFileNameIsUpperCase() {


        when(countryCodePicker.isValidFullNumber()).thenReturn(true);


        addFilterPresenter.validateAndSubmitUserEnteredDetails("VALIDFILENAME", countryCodePicker);

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