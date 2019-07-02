package com.ci.smsforwarder.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.ci.smsforwarder.AppController;
import com.ci.smsforwarder.R;
import com.ci.smsforwarder.models.FilterInfo;
import com.ci.smsforwarder.presenter.AddFilterPresenter;
import com.ci.smsforwarder.view.AddFilterView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddFilterActivity extends Activity implements AddFilterView {

    @BindView(R.id.submitButton)
    Button submitButton;

    @BindView(R.id.filterNumberEditText)
    EditText filterNumberEditText;

    @BindView(R.id.filterNameEditText)
    EditText filteredNameEditText;

    @Inject
    AddFilterPresenter addFilterPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_filter);
        ((AppController) getApplicationContext()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        addFilterPresenter.setView(this);

    }

    @OnClick(R.id.submitButton)
    public void submitButtonClicked(){
        String filterName = filteredNameEditText.getText().toString();
        String filterForwardNumber = filterNumberEditText.getText().toString();
        addFilterPresenter.validateUserEnteredDetails(filterName, filterForwardNumber);

    }


    @Override
    public void saveUserInfoAndNavigateToMainScreen(FilterInfo filterInfo) {
        Intent resultIntent = new Intent();
        addFilterPresenter.saveFilterInfo(filterInfo);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void invalidDataErrorMessage(int errorMessageResourceId) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.invalid_error_title);
        builder.setMessage(errorMessageResourceId);
        builder.setPositiveButton(R.string.ok_button, null);
        builder.show();


    }
}
