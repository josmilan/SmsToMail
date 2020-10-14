package com.github.josmilan.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static final String PREFERENCE_KEY_EMAIL_ID_LIST = "email_id_list";
    private static Context mAppContext;

    // Prevent instantiation
    private PreferenceUtils() {
    }

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences("sms_to_mail", Context.MODE_PRIVATE);
    }

    public static void setEmailId(String emailId) {
        String emailList = getSharedPreferences().getString(PREFERENCE_KEY_EMAIL_ID_LIST, "");
        emailList = emailList + emailId + ",";
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_EMAIL_ID_LIST, emailList).apply();
    }

    public static String[] getEmailIdList() {
        String emailList = getSharedPreferences().getString(PREFERENCE_KEY_EMAIL_ID_LIST, "");
        return emailList.split(",");
    }

    public static void removeEmail(String emailId) {
        String emailList = getSharedPreferences().getString(PREFERENCE_KEY_EMAIL_ID_LIST, "");
        assert emailList != null;
        emailList.replace(emailId + ",", "");
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_EMAIL_ID_LIST, emailList).apply();
    }

}
