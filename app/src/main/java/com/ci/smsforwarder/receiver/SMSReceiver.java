package com.ci.smsforwarder.receiver;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.ci.smsforwarder.AppController;
import com.ci.smsforwarder.models.CacheImpl;
import com.ci.smsforwarder.models.FilterInfo;

import java.util.List;

import javax.inject.Inject;

public class SMSReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Inject
    CacheImpl cache;

    @Override
    public void onReceive(Context context, Intent intent) {

        ((AppController) context.getApplicationContext()).getAppComponent().inject(this);

        if (intent.getAction().equals(SMS_RECEIVED)){

            Bundle bundle = intent.getExtras();
            if (bundle != null){

                // get sms object
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0)
                    return;

                // large message might be broken to many
                SmsMessage smsMessage = null;
                StringBuilder stringBuilder = new StringBuilder();

                for (Object pdusByteArray : pdus){
                    smsMessage = SmsMessage.createFromPdu((byte[]) pdusByteArray);
                    stringBuilder.append(smsMessage.getMessageBody());
                }

                String message = stringBuilder.toString();
                List<FilterInfo> filterInfoList = cache.retrieveFilterInfoDetails();
                for (FilterInfo filterInfo : filterInfoList){
                    SmsManager smsManager = SmsManager.getDefault();
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                    smsManager.sendTextMessage(filterInfo.getNumber(), null, message, pendingIntent, null);
                }
            }



        }

    }
}
