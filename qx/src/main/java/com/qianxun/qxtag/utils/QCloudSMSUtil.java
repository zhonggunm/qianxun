package com.qianxun.qxtag.utils;

import com.qcloud.sms.SmsMultiSender;
import com.qcloud.sms.SmsMultiSenderResult;

import java.util.ArrayList;

public class QCloudSMSUtil {

    private static final String NATION_CODE = "86";
    private static final int APPID = 1400031877;
    private static final String APPKEY = "f73fbaf8c365f43884f191b97d77f451";
    public static final int QCLOUD_VCODE_TEMPLETE = 21276;
    public static final int QCLOUD_ALARM_TEMPLETE = 118592;
    public static final int QCLOUD_NOTIFY_TEMPLETE = 118623;

    public static void sendSms(int templete, String target, String arg){
        ArrayList<String> targets = new ArrayList<>(), args = new ArrayList<>();
        targets.add(target);
        args.add(arg);
        try {
            SmsMultiSenderResult r = new SmsMultiSender(APPID, APPKEY).
                sendWithParam(NATION_CODE, targets, templete, args, "", "", "");
            if (r.result != 0){
                System.out.println(r.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
        }
    }
}
