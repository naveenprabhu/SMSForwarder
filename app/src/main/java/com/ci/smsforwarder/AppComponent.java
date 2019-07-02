package com.ci.smsforwarder;


import com.ci.smsforwarder.activity.AddFilterActivity;
import com.ci.smsforwarder.activity.ViewFiltersActivity;
import com.ci.smsforwarder.module.AppModule;
import com.ci.smsforwarder.module.SMSForwarderMoudle;
import com.ci.smsforwarder.receiver.SMSReceiver;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SMSForwarderMoudle.class})
public interface AppComponent {

    void inject(ViewFiltersActivity viewFiltersActivity);

    void inject(AddFilterActivity addFilterActivity);

    void inject(SMSReceiver smsReceiver);

}
