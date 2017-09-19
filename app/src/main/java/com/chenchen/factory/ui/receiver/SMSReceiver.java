package com.chenchen.factory.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.security.InvalidParameterException;

/**
 * 收到短信的广播接收器
 * 用于监测短信
 */
public class SMSReceiver extends BroadcastReceiver {
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SMS_DELIVER_ACTION = "android.provider.Telephony.SMS_DELIVER";
    private OnReceiveListener listener;

    public void setListener(OnReceiveListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (SMS_RECEIVED_ACTION.equals(action) || SMS_DELIVER_ACTION.equals(action)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    if(null == listener){
                        throw new InvalidParameterException("listener can't be null");
                    }
                    Object[] pdus = (Object[])bundle.get("pdus");
                    if (pdus != null && pdus.length > 0) {
                        SmsMessage[] messages = new SmsMessage[pdus.length];
                        for (int i = 0; i < pdus.length; i++) {
                            byte[] pdu = (byte[]) pdus[i];
                            messages[i] = SmsMessage.createFromPdu(pdu);
                        }
                        for (SmsMessage message : messages) {
                            listener.onReceive(message);
                        }
                    }
                }
        }
    }
    public interface OnReceiveListener{
        void onReceive(SmsMessage message);
    }
}
