package com.android.evp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.evp.db2.TaskContract2;
import com.android.evp.db2.TaskDbHelper2;

import java.util.ArrayList;

/**
 * Created by St. Whales on 19/06/2016.
 */
public class CreateActivity2 extends AppCompatActivity{

    private static final String TAG = "CreateActivity2";
    private TaskDbHelper2 mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity2);

        mHelper = new TaskDbHelper2(this);
        mTaskListView = (ListView) findViewById(R.id.list_todo2);

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Event Details and Tasks")
                        .setMessage("Add a task or event details?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                SQLiteDatabase db2 = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(TaskContract2.TaskEntry2.COL_TASK_TITLE, task);
                                db2.insertWithOnConflict(TaskContract2.TaskEntry2.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db2.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title2);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db2 = mHelper.getWritableDatabase();
        db2.delete(TaskContract2.TaskEntry2.TABLE,
                TaskContract2.TaskEntry2.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db2.close();
        updateUI();
    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db2 = mHelper.getReadableDatabase();
        Cursor cursor = db2.query(TaskContract2.TaskEntry2.TABLE,
                new String[]{TaskContract2.TaskEntry2._ID, TaskContract2.TaskEntry2.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract2.TaskEntry2.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo2,
                    R.id.task_title2,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db2.close();
    }

}
