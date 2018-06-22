package com.ray.lib.android.util;

import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * use for process sms
 * 用于操作短信
 */
public class SmsUtil {
    private SmsUtil() {
    }

    public static void sendSms(String contact, String content) {
        if (TextUtils.isEmpty(contact) || TextUtils.isEmpty(content)) {
            return;
        }

        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> smss = smsManager.divideMessage(content);

        for (String sms : smss) {
            smsManager.sendTextMessage(contact, null, sms, null, null);
        }
    }

    public class Sms implements Serializable {

        private static final long serialVersionUID = 8866544114172489906L;
        public String sms;
        public String sender;


        @Override
        public String toString() {
            return "Sms [sms=" + sms + ", sender=" + sender + "]";
        }

        public Sms(String sms, String sender) {
            super();
            this.sms = sms;
            this.sender = sender;
        }


        public Sms() {

        }
    }

    public static Sms getSmsFromIntent(Intent intent) {
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        String sender = null;
        StringBuilder sms = new StringBuilder();
        for (Object obj : objs) {
            SmsMessage message = SmsMessage.createFromPdu((byte[]) obj);
            if (sender == null) {
                sender = message.getOriginatingAddress();
            }
            String messageBody = message.getMessageBody();
            sms.append(messageBody);

        }
        return new SmsUtil().new Sms(sms.toString(), sender);
    }
}
