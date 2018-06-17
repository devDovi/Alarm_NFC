package com.dovi.alarm;

/**
 * Created by dsm2016 on 2018-06-12.
 */

public interface ParsedRecord {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_URI = 2;
    public static final int TYPE_CUSTOM_URI = 3;
    public static final int APPLICATION = 4;
    public static final int MAIL = 5;
    public static final int CONTACT = 6;
    public static final int PHONE_NUMBER  = 7;
    public static final int SMS = 8;
    public static final int LOCATION = 9;
    public static final int ADDRESS = 10;
}
