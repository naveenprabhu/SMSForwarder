package com.ci.smsforwarder.module;

import android.app.Application;

import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.presenter.AddFilterPresenter;
import com.ci.smsforwarder.presenter.ViewFiltersPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SMSForwarderMoudle {


    @Provides
    @Singleton
    CacheImpl provideCacheImpl(Application application){
        return new CacheImpl(application);

    }

    @Provides
    AddFilterPresenter getAddFilterPresenter(CacheImpl cacheImpl){
        return new AddFilterPresenter(cacheImpl);
    }

    @Provides
    ViewFiltersPresenter getViewFiltersPresenter(CacheImpl cacheImpl){
        return new ViewFiltersPresenter(cacheImpl);
    }
}
