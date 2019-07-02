package com.ci.smsforwarder;

import android.app.Application;

import com.ci.smsforwarder.module.AppModule;
import com.ci.smsforwarder.module.SMSForwarderMoudle;

/*
 * we use our AppComponent (now prefixed with Dagger)
 * to inject our Application class.
 * This way a DispatchingAndroidInjector is injected which is
 * then returned when an injector for an activity is requested.
 * */
public class AppController extends Application {


    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .sMSForwarderMoudle(new SMSForwarderMoudle())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}