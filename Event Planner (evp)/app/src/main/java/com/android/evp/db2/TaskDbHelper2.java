package com.android.evp.db2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by St. Whales on 19/06/2016.
 */
public class TaskDbHelper2  extends SQLiteOpenHelper{

    public TaskDbHelper2(Context context) {
        super(context, TaskContract2.DB_NAME, null, TaskContract2.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        String createTable = "CREATE TABLE " + TaskContract2.TaskEntry2.TABLE + " ( " +
                TaskContract2.TaskEntry2._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract2.TaskEntry2.COL_TASK_TITLE + " TEXT NOT NULL);";

        db2.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS " + TaskContract2.TaskEntry2.TABLE);
        onCreate(db2);
    }
}
