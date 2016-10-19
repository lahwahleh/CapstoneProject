package com.android.evp.db2;

import android.provider.BaseColumns;

/**
 * Created by St. Whales on 19/06/2016.
 */
public class TaskContract2 {

    public static final String DB_NAME = "com.android.evp.db2";
    public static final int DB_VERSION = 1;

    public class TaskEntry2 implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }

}
