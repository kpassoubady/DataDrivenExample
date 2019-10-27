package com.kavinschool.zenphoto.utils;

import java.util.TimeZone;

public class Constants {
    public static final String BASE_DIR = "src/test/resources/";

    public final static String USER_XLS_FILE = BASE_DIR + "users3.xls";
    public final static String TAGS_FILE = BASE_DIR + "tags.txt";

    /** The user csv file. */
    public final static String USER_CSV_FILE = BASE_DIR + "users3.csv";

    public final static String CAR_XLS_FILE = BASE_DIR + "MyAdvCars.xls";

    public final static String SERV_PROP_FILE = BASE_DIR + "server.properties.txt";

    /** The Constant DB_CONN. */
    public static final String DB_CONN = "jdbc:mysql://host422.hostmonster.com/kavinsch_TellAFriend";
    public static final String DB_TIME_ZONE = "?serverTimezone=" + TimeZone.getDefault().getID();
    public static final String DB_CONN_WITH_TZ = DB_CONN + DB_TIME_ZONE;

    /** The Constant DB_USER_ID. */
    public static final String DB_USER_ID = "kavinsch_Friend";

    /** The Constant DB_PASSWORD. */
    public static final String DB_PASSWORD = "Friend123";

}
