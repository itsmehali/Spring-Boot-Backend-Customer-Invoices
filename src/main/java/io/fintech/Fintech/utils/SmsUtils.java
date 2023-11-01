package io.fintech.Fintech.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static com.twilio.rest.api.v2010.account.Message.creator;


public class SmsUtils {
    //Twilio number (API)
    //TODO: set it to env variables
    public static final String FROM_NUMBER = "+19789653217";
    public static final String SID_KEY = "AC415023870e3717825955c5fb06832281";
    public static final String TOKEN_KEY = "4ab067533e5769e3c43c92a2c3a08807";

    public static void sendSMS(String to, String messageBody) {
        Twilio.init(SID_KEY, TOKEN_KEY);
        Message message = creator(new PhoneNumber("+" + to), new PhoneNumber(FROM_NUMBER), messageBody).create();
        System.out.println(message);
    }
}
