package com.ZCZ1024.MeetStone.Util;

public class PhoneNumUtil {

    public static String dealPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            int len = phoneNumber.length();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                if (i > 2 && i < 7) {
                    builder.append("*");
                } else {
                    builder.append(phoneNumber.charAt(i));
                }

                if (i == 2 || i == 6) {
                    if (i != len - 1)
                        builder.append(" ");
                }
            }

            return builder.toString();
        }
        return null;
    }
}
